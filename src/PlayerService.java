import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;


public class PlayerService implements Runnable {
	private Socket s;
	private Socket ims;//instant messenger socket
	
	private Scanner in;
	private Scanner imIn;
	private PrintWriter imOut;
	private PrintWriter out;
	private int pos;
	private int playerNum;
	
	final String specialChar = "**$#$**";
	
		public PlayerService(Socket aSocket, int position) {
			s = aSocket;
			pos = position;
			playerNum = (pos%2)+1;
		
			
		}
		
		public void run() {
			try {
				try {
					in = new Scanner(s.getInputStream());
					out = new PrintWriter(s.getOutputStream());
					out.println(playerNum);
					out.flush();
					out = null;
					
					doService();		
				}
				finally {
					System.out.println("Closed Thread");
					StrategoServer.players.remove(s);
					s.close();
				}
			}
			catch(IOException exception) {
				exception.printStackTrace();
			}
		}
		
		public void doService() throws IOException {
			while(true) {

			/**	if (in.hasNext()){
					String command = in.next();
					if(command.equals("Quit")) return;
					else executeCommand(command);
				}
				*/
				
				if (in.hasNextLine()){
					if (out == null){
						if(pos%2 == 0) {
							out = new PrintWriter(StrategoServer.players.get(pos+1).getOutputStream());                       
						}
						else {
							out = new PrintWriter(StrategoServer.players.get(pos-1).getOutputStream());
						}
					}
					
					while (in.hasNext()){
						String string = in.next();
						if (string.equals(specialChar)){
							out.print(specialChar + " ");
							break;
						}
						if (string.equals(specialChar+"QUIT"+specialChar)){
							System.out.println("Closed Thread");
							StrategoServer.players.remove(s);
							s.close();
						}
						out.print(string + " ");
						
					}
					out.flush();
				}
				
			}
		}
		public void executeCommand(String command) throws IOException{
			
			
			
			if (command.equals("M")) {
				//should recieve and then send 4 coordinates
				String string = " ";
				string += in.next()+ " ";
				string += in.next()+ " ";
				string += in.next()+ " ";
				string += in.next()+ " ";
				string += "end";
				
				out.println(string);
				System.out.println("Opponent Moved");
			}
			
			else if(command.equals("I")) {
				ArrayList initialize = new ArrayList();
				//this removes the first brackets from  the ArrayList.toString
			    while(initialize.size() < 40) {
			    	initialize.add(in.next());
			    	}
				out.print(initialize);
				System.out.println("Sent Setup Information to Player " + pos);
				}
				out.print(" end");//the problem that occured earlier appeared to be that we could not read the last item in this output, so we added this decoy item
				
			
			//out.println("Your Turn");
			out.flush();
			return;
		}
		
		
		
	}