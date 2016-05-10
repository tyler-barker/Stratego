import java.io.*;
import java.net.*;
import java.util.*;

public class StrategoServer {
	
	public static void main(String[] args) throws IOException {
		final int port = 8888;
		ServerSocket server = new ServerSocket(port);
		System.out.println("Waiting for clients to connect...");
		while(true) {
			try{
				Socket s = server.accept();
				players.add(s);
				System.out.println("Client "+(players.size())+" connected");
				if(players.indexOf(s)%2 == 0) {
					System.out.println("Waiting for another player");
				}	
				PlayerService service = new PlayerService(s, players.indexOf(s));
				Thread t = new Thread(service);
				t.start();			
			}
			catch(Exception e){
				System.out.println(e);
			}
		}	
	}
	public static ArrayList<Socket> players = new ArrayList<Socket>();
}

