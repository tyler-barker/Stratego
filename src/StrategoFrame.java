import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StrategoFrame extends JApplet{
	

	StrategoBoard board =  new StrategoBoard();

	final String empty = "";
	HashMap<String,Image> pieceNames = new HashMap<String,Image>();
	String[] nameList = {"Images/marshal.gif","Images/general.gif","Images/colonel.gif","Images/major.gif","Images/captain.gif",
						"Images/lieutenant.gif","Images/sergeant.gif","Images/miner.gif","Images/scout.gif","Images/spy.gif",
						"Images/bomb2.gif","Images/flag.gif"};
	int[] pieceQuantities = {1,1,2,3,4,4,4,5,8,1,6,1};
	String[] rankList = {"1","2","3","4","5","6","7","8","9","S","B","F"};
	
	JButton[][] buttons = new JButton[10][10];
	PieceData[] leftButtons = new PieceData[12];
	PieceData[] rightButtons = new PieceData[12];
	JMenuItem quitItem;
	JMenuItem surrenderItem;
	JTextArea topText;
	JTextArea bottomText;
	JLabel  informationLabel;
	//this label says things like "your turn", or "place your units"
	boolean isPlayer1 = true; //TODO
	//1 is red, 2 is blue
	
	Socket socket ;
	Scanner in;
	PrintWriter out;
	Scanner imIn;
	PrintWriter imOut;
	
	boolean isFinished = false;
	boolean haveOpponentsData = false;
	//is the player finished  finished placing his units? 
	//only one of these two booleans will be true. Used to determine when both players are finished placing units.
	
	final int secondsToWait = 2;
	//this is the amount of time to see the piece being attacked or attacking
	
	boolean isTimerOn;
	int rowOfVisibleEnemyPiece = 0;
	int columnOfVisisbleEnemyPiece= 0;
	//these variables are used to determine which of the enemy's piece is visiable (when attacking or being attacked)
	
	FrameState state; //the state the frame is in (either placing pieces or taking a turn)
	final String specialChar = "**$#$**";
	
	
	JButton firstSquareClicked = null;
	int firstSquareRow = 0;
	int firstSquareColumn = 0;
	JButton secondSquareClicked = null;
	//these variables are used to determine which squares you click on
	
	public void init(){

		//Image marshal =getImage(getCodeBase(), "marhsal.gif");
		
		board.setUpGrid();
		doNetworkStuff();
		
		/**pieceNames.put("1", "marshal.gif");
		pieceNames.put("2", "general.gif");
		pieceNames.put("3", "colonel.gif");
		pieceNames.put("4", "major.gif");
		pieceNames.put("5", "captain.gif");
		pieceNames.put("6", "lieutenant.gif");
		pieceNames.put("7", "sergeant.gif");
		pieceNames.put("8", "miner.gif");
		pieceNames.put("9", "scout.gif");
		pieceNames.put("s", "spy.gif");
		pieceNames.put("b", "bomb2.gif");
		pieceNames.put("f", "flag.gif");
		pieceNames.put("x", "redSquare.gif");
		pieceNames.put("y", "blueSquare.gif");
		
		
		pieceNames.put("2", getImage(getCodeBase(), "marshal.gif"));
		pieceNames.put("2", getImage(getCodeBase(), "general.gif"));
			pieceNames.put("3", getImage(getCodeBase(), "colonel.gif"));
			pieceNames.put("4", getImage(getCodeBase(), "major.gif"));
			pieceNames.put("5", getImage(getCodeBase(), "captain.gif"));
			pieceNames.put("6", getImage(getCodeBase(), "lieutenant.gif"));
			pieceNames.put("7", getImage(getCodeBase(), "sergeant.gif"));
			pieceNames.put("8", getImage(getCodeBase(), "miner.gif"));
			pieceNames.put("9", getImage(getCodeBase(), "scout.gif"));
			pieceNames.put("s", getImage(getCodeBase(), "spy.gif"));
			pieceNames.put("b", getImage(getCodeBase(), "bomb2.gif"));
			pieceNames.put("f", getImage(getCodeBase(), "flag.gif"));
			pieceNames.put("x", getImage(getCodeBase(), "redSquare.gif"));
			pieceNames.put("y", getImage(getCodeBase(), "blueSquare.gif"));

		*/
		try{
			URL url = (new URL(getCodeBase().toString()+"/Images"));
			pieceNames.put("1", getImage(url, "Images/marshal.gif"));
			pieceNames.put("2", getImage(url, "Images/general.gif"));
			pieceNames.put("3", getImage(url, "Images/colonel.gif"));
			pieceNames.put("4", getImage(url, "Images/major.gif"));
			pieceNames.put("5", getImage(url, "Images/captain.gif"));
			pieceNames.put("6", getImage(url, "Images/lieutenant.gif"));
			pieceNames.put("7", getImage(url, "Images/sergeant.gif"));
			pieceNames.put("8", getImage(url, "Images/miner.gif"));
			pieceNames.put("9", getImage(url, "Images/scout.gif"));
			pieceNames.put("s", getImage(url, "Images/spy.gif"));
			pieceNames.put("b", getImage(url, "Images/bomb2.gif"));
			pieceNames.put("f", getImage(url, "Images/flag.gif"));
			pieceNames.put("x", getImage(url, "Images/redSquare.gif"));
			pieceNames.put("y", getImage(url, "Images/blueSquare.gif"));
		}
		catch(Exception e){
			System.out.println("Error Loading Images:  " + e);
		}
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		int w = 900;//dim.width*4/5;
		int h = 800;//dim.height;
		this.setBounds(0+20, 0, w-40, h-40);
		//this.setResizable(false);
		this.setVisible(true);
		
		
		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel gridPanel = new JPanel(new GridLayout(10,10));
		JPanel leftPanel = new JPanel(new GridLayout(12,1,0,1));
		JPanel rightPanel = new JPanel(new GridLayout(12,1,0,1));
		JPanel messagingPanel = new JPanel(new GridBagLayout());
		
		topPanel.setMinimumSize(new Dimension(w,h*5/100));
		gridPanel.setMinimumSize(new Dimension(w*90/100,h*73/100));
		
		//initiate the top panel  ("File Option"  and the text at the top like "Your Turn")
		GridBagConstraints t = new GridBagConstraints();
		t.fill = GridBagConstraints.BOTH;
		
		t.weightx = 0.01;
		t.weighty = 1;
		t.gridx = 0;
		t.gridy = 0;
		t.gridwidth = 1;
		JMenuBar menuBar = new JMenuBar();
		topPanel.add(menuBar,t);
		
		
		
		t.weightx = 0.99;
		t.weighty = 1;
		t.gridx = 1;
		t.gridy = 0;
		t.gridwidth = 1;
		JPanel topLeftPanel = new JPanel(); //the panel on the left of the menubar
		
		topPanel.add(topLeftPanel,t);
		
		
		informationLabel = new JLabel();
		topLeftPanel.add(informationLabel);
		
		//fill the menubar with options under "File"
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		surrenderItem = new JMenuItem("Surrender");
		surrenderItem.addActionListener(new MenuListener());
		menu.add(surrenderItem);
		
		quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new MenuListener());
		menu.add(quitItem);
		
		
		
		//gridPanel.setPreferredSize(new Dimension(h*73/100/2,w*80));
		//create the main layout manager as a Grid Bag
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		//the message at the top
		c.weightx = 1;
		c.weighty = 0.05;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		this.add(topPanel,c);
		//the buttons on the left
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0.05;
		c.weighty = 0.73;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(leftPanel,c);
		//the board (the grid) in the middle
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0.90;
		c.weighty = 0.73;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(gridPanel,c);
		//the buttons on the rights
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0.05;
		c.weighty = 0.73;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		this.add(rightPanel,c);
		//the conversation box
		c.insets = new Insets(0,0,0,0);
		c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		this.add(messagingPanel,c);
		
		
		//add buttons to the grid
		for (int i=0;i<10;i++){
			for (int j=0;j<10;j++){

				//ImageIcon image = new ImageIcon();//"spy.gif");
				JButton newButton = new JButton();
				buttons[i][j] = newButton;
				newButton.addActionListener(new GridButtonActionListener());
				gridPanel.add(newButton,BorderLayout.SOUTH);
				//newButton.setToolTipText("Spy");
			}
		}
		//add buttons to each the left and right panel (which is the 1X10 row of buttons)
		for (int i=0;i<24;i++){
			{
				JPanel panel = new JPanel(new GridLayout(1,2));
				panel.setBorder(BorderFactory.createBevelBorder(0));
				panel.addMouseListener(new MyMouseListener());
				
				JPanel secondPanel = new JPanel(new GridLayout(2,1));
				
				JLabel rank = new JLabel("r");
				JLabel quantity = new JLabel("q");
				secondPanel.add(rank);
				secondPanel.add(quantity);
				
				if (i>=12){
					JLabel picture = new JLabel(new ImageIcon(nameList[i-12]));
					panel.add(picture);
					PieceData data = new PieceData(true,panel,picture,rank,quantity);
					rightPanel.add(panel);
					rank.setText(rankList[i-12]);
					quantity.setText(Integer.toString(pieceQuantities[i-12]));
					rightButtons[i-12] = data;
				}
				else{
					JLabel picture = new JLabel(new ImageIcon(nameList[i]));
					panel.add(picture);
					PieceData data = new PieceData(false,panel,picture,rank,quantity);
					leftPanel.add(panel);
					rank.setText(rankList[i]);
					quantity.setText(Integer.toString(pieceQuantities[i]));
					leftButtons[i]=data;
				}
				panel.add(secondPanel);
			}
			
			
		}
		
		
		
		
		//edit the messaging panel (two text areas, one is editable and the other not
		 topText = new JTextArea(5,5);
		 topText.setEditable(false);
		 topText.setLineWrap(true);
		 topText.setFont(new Font("Serif", Font.PLAIN, 12));
		 
		 bottomText = new JTextArea(5,5);
		 bottomText.setLineWrap(true);
		 bottomText.setFont(new Font("Serif", Font.PLAIN, 12));
		 bottomText.addKeyListener(new EnterListener());
		 
		 JScrollPane tScroll = new JScrollPane(topText);
		 JScrollPane bScroll = new JScrollPane(bottomText);
		 
		 
		 
		 GridBagConstraints p = new GridBagConstraints();
		 p.fill = GridBagConstraints.BOTH;
		 
		 p.gridx = 0;
		 p.gridy = 0;
		 p.weightx = 1;
		 p.weighty = 0.8;
		 messagingPanel.add(tScroll,p);
		 p.gridx = 0;
		 p.gridy = 1;
		 p.weightx = 1;
		 p.weighty = 0.2;
		 messagingPanel.add(bScroll,p);
		 
		 messagingPanel.setMinimumSize(new Dimension(w,h*15/100));
		state = new PlaceUnitsState();
		this.validate();
	}
	public void doNetworkStuff(){
		final int port = 8888;
		String host = "localhost";
		try{
			socket = new Socket(host,port);
			InputStream instream = socket.getInputStream();
			OutputStream outstream = socket.getOutputStream();
			in = new Scanner(instream);
			out = new PrintWriter(outstream);
			
			
			
			
			while(true){
				//we need to know what player you are (1 or 2)
				if (in.hasNext()){
					int number = in.nextInt();
					if (number == 1){
						isPlayer1 = true;
						break;
					}
					else if(number == 2){
						isPlayer1 = false;
						break;
					}
					
				}
			}
			Thread thread = new Thread(new InputReader());
			thread.start();
			
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	public void sendMessage(){
		//this occurs when enter is pressed in the bottom box
		//the message will be sent to the server and client
		System.out.println("Sending Message");
		String toBeSent;
		if (isPlayer1){
			toBeSent = "Player 1:  "+bottomText.getText();
		}
		else{
			toBeSent = "Player 2:  "+bottomText.getText();
		}
		topText.append(toBeSent);
		bottomText.setText("");
		out.print(toBeSent+ " "+specialChar+" ");
		out.flush();

		//quickStart(); \
		
		
		
	}
	public void deselectAllSideButtons(){
		for (int i=0;i<12;i++){
			rightButtons[i].deselect();
			leftButtons[i].deselect();
		}
	}
	
	public void updateGrid(){
		for (int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				
				JButton button = buttons[i][j];
				String piece = board.obtainValue(i,j);
				
				if (piece == null){
					button.setIcon(null);
					button.setToolTipText(null);
					continue;
				}
				if( piece.equals("")){
					button.setIcon(null);
					button.setToolTipText(null);
					continue;
				}
				
				String firstString = piece.substring(0,1);
				String secondString = piece.substring(1,2);
				boolean pieceForPlayer1 = false;
				if (firstString.equals("1")){
				  pieceForPlayer1 = true;	
				}
				else if(firstString.equals("2")){
				   pieceForPlayer1 = false;
				}
				else{
					System.out.println("Error: piece is not for player 1 or 2");
				}
				if (isPlayer1 == pieceForPlayer1){
					button.setIcon(new ImageIcon(pieceNames.get(secondString)));
					String text = secondString;
					if (text.equals("f")) text = "Flag";
					if (text.equals("b")) text = "Bomb";
					if (text.equals("s")) text = "Spy";
					button.setToolTipText(text);
				}
				else{
					if (isPlayer1){
						button.setIcon(new ImageIcon(pieceNames.get("y")));
						button.setToolTipText(null);
					}
					else{
						button.setIcon(new ImageIcon(pieceNames.get("x")));
						button.setToolTipText(null);
					}
					
				}
				if (isTimerOn){
					//this is duplicated code
					String string = board.obtainValue(rowOfVisibleEnemyPiece,columnOfVisisbleEnemyPiece).substring(1,2);
					button = buttons[rowOfVisibleEnemyPiece][columnOfVisisbleEnemyPiece];
					button.setIcon(new ImageIcon(pieceNames.get(string)));
					String text = string;
					if (text.equals("f")) text = "Flag";
					if (text.equals("b")) text = "Bomb";
					if (text.equals("s")) text = "Spy";
					button.setToolTipText(text);
				}
			}
		}
	}

	public void quickStart(){
		if (isPlayer1){
			for (int i=6;i<10;i++){
				for(int j=0;j<10;j++){
					board.insertValue(i, j, "19");
				}
			}
			for (int i=0;i<rightButtons.length;i++){
				rightButtons[i].quantity.setText("0");
			}
		}
		else{
			for (int i=0;i<4;i++){
				for(int j=0;j<10;j++){
					board.insertValue(i, j, "29");
				}
			}
			for (int i=0;i<leftButtons.length;i++){
				leftButtons[i].quantity.setText("0");
			}
		}
		
	}
	public void doAction(int firstSquareRow,int firstSquareColumn,int row,int column){
		//the action is valid, now do it on both computers
		//firstSquareRow and *column is the first square clicked
		//row and column is the second square clicked
		
		if (board.obtainValue(row, column).equals(empty)){
			board.insertValue(row, column, board.obtainValue(firstSquareRow, firstSquareColumn));
			board.removeValue(firstSquareRow, firstSquareColumn);
		}
		else{
			String pA = board.obtainValue(firstSquareRow,firstSquareColumn);
			String pB = board.obtainValue(row,column);
			
			if (pA.substring(1,2).equals(pB.substring(1,2))){
				board.removeValue(row, column);
				board.removeValue(firstSquareRow, firstSquareColumn);
			}
			else if (board.strikeSuccess(pA,pB)){
				
				board.removeValue(row, column);
				board.insertValue(row, column, pA);
				board.removeValue(firstSquareRow, firstSquareColumn);
				if (pB.substring(1,2).equals("f")){
					state = new GameOverState(pA.substring(0,1));
					
				}
			}else{
				board.removeValue(firstSquareRow, firstSquareColumn);
			}
		}
		
		
	}

	
	public class MenuListener implements ActionListener{
		//this is the aciton done when certian tabs from the menu are clicked
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == quitItem){
				destroy();
				System.exit(0);
			}
			if (e.getSource() == surrenderItem){
				if (isPlayer1){
					state = new GameOverState("2");
				}
				else{
					state = new GameOverState("1");
				}
				
			}
		}
	}
	
	public class PlaceUnitsState implements FrameState{
		//these function are called from the Listener classes
		//place Unit state determines what happens with the baord while
		// you are in the phase of placing your units
		
		public PlaceUnitsState(){
			if (isPlayer1){
				for (int i=0;i<6;i++){
					for (int j=0;j<10;j++){
						buttons[i][j].setEnabled(false);
					}
				}
			}
			else{
				for (int i=4;i<10;i++){
					for (int j=0;j<10;j++){
						buttons[i][j].setEnabled(false);
					}
				}
			}
			informationLabel.setText("Place Your Units");
		}
		public void sideButtonPressed(MouseEvent e){
			PieceData button = null;
			boolean forPlayer1 = true;
			for (int i=0;i<12;i++){
				JPanel p = rightButtons[i].panel;
				if (e.getSource() == rightButtons[i].panel){
					button = rightButtons[i];
					forPlayer1 = true;
				}
				if (e.getSource() == leftButtons[i].panel){
					button = leftButtons[i];
					forPlayer1 = false;
				}
			}
			if (button == null){
				System.out.println("Error: No button matched to place clicked");
			}
			
			if (isPlayer1){
				if(forPlayer1){
					deselectAllSideButtons();
					button.select();
					
				}
			}
			else{
				if (!forPlayer1){
					deselectAllSideButtons();
					button.select();
				}
			}
			
		}
		
		
		
		public void gridButtonPressed(JButton button,int row,int column){
			PieceData[] list = isPlayer1 ? rightButtons : leftButtons;
			
			for (int i=0;i<12;i++){
				if (list[i].isSelected){
					if (Integer.parseInt(list[i].quantity.getText()) > 0){
						if ((board.obtainValue(row,column).equals(empty))){
							String value = isPlayer1 ? "1" : "2";
							value += list[i].pieceRank.getText().toLowerCase();
							board.insertValue(row, column, value);
							list[i].quantity.setText(Integer.toString(Integer.parseInt(list[i].quantity.getText()) - 1));
						}
					}
					else{
						
					}
				}
			}
			if(isFinishedPlacingUnits()){
				finishedPlacingUnits();
			}
			
			
		}
		public void finishedPlacingUnits(){
			//init gives informations on the position of this player's pieces
		
			String string = "";
			if (isPlayer1){
				for (int i=6;i<10;i++){
					for (int j=0;j<10;j++){
						string +=  board.obtainValue(i,j).substring(1,2) + " ";
					}
				}
			}else{
				for (int i=0;i<4;i++){
					for (int j=0;j<10;j++){
						string +=  board.obtainValue(i,j).substring(1,2) + " ";
					}
				}
			}
			out.print("I");
			out.print(" " + string + specialChar + "\n");
			out.flush();
			deselectAllSideButtons();
			isFinished = true;
			if (haveOpponentsData){
				changeState();
			}
			
			
			
		}
		
		
		public boolean isFinishedPlacingUnits(){
			PieceData[] list = isPlayer1 ? rightButtons : leftButtons;
			for (int i=0;i<list.length;i++){
				if (Integer.parseInt(list[i].quantity.getText()) >0){
					return false;
				}
			}
			return true;
		}
	}
	public class TurnState implements FrameState{
		//these functions are called from the listener classes
		//this represents the phase in which the player can move pieces
		//or is waiting on the other player
		
		
		
		
		public  TurnState(){
			int[][] emptySquares = {{4,2},{4,3},{5,2},{5,3},{4,6},{4,7},{5,6},{5,7}};
			for (int i=0;i<10;i++){
				for (int j=0;j<10;j++){
					buttons[i][j].setEnabled(true);
					for (int k=0;k<8;k++){
						if (i == emptySquares[k][0] && j == emptySquares[k][1]){
							buttons[i][j].setEnabled(false);
						}
					}
				}
			}
			informationLabel.setText("Its Your Turn");
		}
		public void sideButtonPressed(MouseEvent e){}
		public void gridButtonPressed(JButton button,int row,int column){
			if (isTimerOn){
				//a move has already been done
				return;
			}
			if (firstSquareClicked == null){
				if (board.obtainValue(row, column).equals(empty)){
					return;
				}
				
				String pQ = board.obtainValue(row, column).substring(0,1);
				//what player is this piece for?
				if (isPlayer1){
					if (!pQ.equals("1")){
						return; 
					}
				}
				else{
					if (!pQ.equals("2")){
						return; 
					}
				}
				button.setBackground(new Color(200,200,200));
				firstSquareClicked = button;
				firstSquareRow = row;
				firstSquareColumn = column;
				return;
			}
			else{
				if (button == firstSquareClicked){
					firstSquareClicked = null;
					button.setBackground(null);
					return;
				}
				if (board.isMoveValid(firstSquareRow, firstSquareColumn, row, column)){
					setUpAction(row,column);
					out.print("M" + " " + firstSquareRow + " " + firstSquareColumn + " " + row + " " + column + " " + specialChar +"\n");
					out.flush();
					//** send this action to other player
				}
				else{
					return;
				}
				//secondSquareClicked = button;
				
			}
			
		}
		public void setUpAction(int row,int column){
			if (!board.obtainValue(row, column).equals(empty)){
				Timer timer = new Timer();
				isTimerOn = true;
				
				//we need to know which piece to un-hide
				//we need to check whose turn it is
				String aPlayer = board.obtainValue(row, column).substring(0,1);
				if (aPlayer.equals("1")){
					if (isPlayer1){
						rowOfVisibleEnemyPiece = firstSquareRow;
						columnOfVisisbleEnemyPiece= firstSquareColumn;
					}
					else{
						rowOfVisibleEnemyPiece = row;
						columnOfVisisbleEnemyPiece= column;
					}
				}
				else{
					if (!isPlayer1){
						rowOfVisibleEnemyPiece = firstSquareRow;
						columnOfVisisbleEnemyPiece= firstSquareColumn;
					}
					else{
						rowOfVisibleEnemyPiece = row;
						columnOfVisisbleEnemyPiece= column;
					}
				}
				
				timer.schedule(new TimerForSeeingTheUnitBeingAttacked(row,column),1000*secondsToWait);
			}
			else{
				doAction(firstSquareRow,firstSquareColumn,row,column);
				finishTurn();
			}
			
		}
		
		
	}
	
	public class NotYourTurnState implements FrameState{
		Timer timer;
		public NotYourTurnState(){
			super();
			informationLabel.setText("Waiting On Opponent");
			//timer = new Timer();
			//timer.schedule(new WaitForOpponentsMove(),500);
		}
		public void gridButtonPressed(JButton b , int i , int j){}
		public void sideButtonPressed(MouseEvent m){}
	
		
		public class WaitForOpponentsMove extends TimerTask{
			//an update function will be called every so often that checks if the opponent has moved
			public void run(){
				/**if (in.hasNext()){
					System.out.print("J " + in.next());
					
					while (in.hasNextInt()){
						//System.out.print("Next is " + in.next());
						//if in has next, then the first item will be "end"
						//in.next();
						int oldRow = in.nextInt();
						int oldColumn = in.nextInt();
						int newRow = in.nextInt();
						int newColumn = in.nextInt();
							if (!board.obtainValue(newRow, newColumn).equals(empty)){
								Timer timer = new Timer();
								isTimerOn = true;
								
								//we need to know which piece to un-hide
								//we need to check whose turn it is
								String aPlayer = board.obtainValue(newRow, newColumn).substring(0,1);
								if (aPlayer.equals("1")){
									if (isPlayer1){
										rowOfVisibleEnemyPiece = oldRow;
										columnOfVisisbleEnemyPiece= oldColumn;
									}
									else{
										rowOfVisibleEnemyPiece = newRow;
										columnOfVisisbleEnemyPiece= newColumn;
									}
								}
								else{
									if (!isPlayer1){
										rowOfVisibleEnemyPiece = oldRow;
										columnOfVisisbleEnemyPiece= oldColumn;
									}
									else{
										rowOfVisibleEnemyPiece = newRow;
										columnOfVisisbleEnemyPiece= newColumn;
									}
								}
								updateGrid();
								timer.schedule(new TimerForSeeingTheUnitBeingAttacked(oldRow,oldColumn,newRow,newColumn),1000*secondsToWait);
							}
							else{
								doAction(oldRow,oldColumn,newRow,newColumn);
								finishTurn();
							}
					}
				}
				else{
				}
				timer.schedule(new WaitForOpponentsMove(),500);*/
			}
		}
		

	}

	public class EnterListener implements KeyListener{
		
		public void keyTyped(KeyEvent e){
			if (e.getKeyChar() == '\n'){
				sendMessage();
			}
		}
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
	}
	
	public class MyMouseListener implements MouseListener{
		//this if the actions for the events fro the left and right buttons
		public void mousePressed(MouseEvent e){
			state.sideButtonPressed(e);
		}
		public void mouseClicked(MouseEvent e){
		}
		public void mouseEntered(MouseEvent e){
		}
		public void mouseReleased(MouseEvent e){
		}
		public void mouseExited(MouseEvent e){
			JPanel panel = ((JPanel) e.getSource());
			//panel.setBorder(BorderFactory.createEmptyBorder());
		}
	}
	
	public class GridButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton thisButton = null;
			int row = 0;
			int column= 0;
			for (int i=0;i<10;i++){
				for(int j=0;j<10;j++){
					if (e.getSource() == buttons[i][j]){
						thisButton = buttons[i][j];
						row = i;
						column = j;
					}
				}
			}
			if (thisButton != null){
				state.gridButtonPressed(thisButton, row, column);
			}
			
			updateGrid();
		}
	}
	
	class InputReader implements Runnable
    {
		//this class checks if there is input from the oppoent
       public void run()
       {
          while(true)
          {
             if(in.hasNext())
             {
            	String string = in.next();
            	if (string.equals("M")){
            		waitOnOpponentsMove();
            		in.next();
            	}
            	else if (string.equals("I")){
            		getOpponentSetup();
            		in.next();
            	}
            	else{
            		string += " ";
            		while (in.hasNext()){
                		String temp = in.next();
                		
                		if (temp.equals(specialChar)){
                			break;
                		}
                		string += temp + " ";
                	}
            		topText.append(string + "\n");
            	}
            	
            }
             
                
          }
       }
    }
    
	public void getOpponentSetup(){
		//we sent out information, now we need theirs
		//while(true){
			try{
				//if (in.hasNext()){
					
					//System.out.println("Inside the Block");
					
					//in is an arrayList
					//firstsubString is either 1 or 2 depending on what player you are
					String firstSubString = "";
					if (isPlayer1){
						firstSubString = "2";
					}else{
						firstSubString = "1";
					}
					
					
					for (int i=0;i<40;i++){
						System.out.print(i+ " ");
						//add the pieces to the board
						String next = "";
						next = in.next().substring(0,1);
						
					   if (isPlayer1){
						   board.insertValue(i/10 , i%10, firstSubString + next);
					   }
					   else{
						   board.insertValue(i/10 + 6, i%10, firstSubString + next);
					   }
					}
					haveOpponentsData = true;
					if (isFinished){
						changeState();
					}
			}
			catch(Exception e){
				System.out.println(e);
			}
		
	}
	public void changeState(){
		updateGrid();
		for (int i=0;i<leftButtons.length;i++){
			leftButtons[i].quantity.setText(null);
			rightButtons[i].quantity.setText(null);
		}
		if (isPlayer1){
			state = new TurnState();
		}
		else{
			//initialize the board, then go to next states
			state = new TurnState();
			state = new NotYourTurnState();
		}
	}
	public void waitOnOpponentsMove(){
				int oldRow = in.nextInt();
				firstSquareRow = oldRow;
				int oldColumn = in.nextInt();
				firstSquareColumn = oldColumn;
				
				int newRow = in.nextInt();
				int newColumn = in.nextInt();
					if (!board.obtainValue(newRow, newColumn).equals(empty)){
						Timer timer = new Timer();
						isTimerOn = true;
						
						//we need to know which piece to un-hide
						//we need to check whose turn it is
						String aPlayer = board.obtainValue(newRow, newColumn).substring(0,1);
						if (aPlayer.equals("1")){
							if (isPlayer1){
								rowOfVisibleEnemyPiece = oldRow;
								columnOfVisisbleEnemyPiece= oldColumn;
							}
							else{
								rowOfVisibleEnemyPiece = newRow;
								columnOfVisisbleEnemyPiece= newColumn;
							}
						}
						else{
							if (!isPlayer1){
								rowOfVisibleEnemyPiece = oldRow;
								columnOfVisisbleEnemyPiece= oldColumn;
							}
							else{
								rowOfVisibleEnemyPiece = newRow;
								columnOfVisisbleEnemyPiece= newColumn;
							}
						}
						timer.schedule(new TimerForSeeingTheUnitBeingAttacked(newRow,newColumn),1000*secondsToWait);
						updateGrid();
					}
					else{
						doAction(oldRow,oldColumn,newRow,newColumn);
						finishTurn();
					}
			}
	public void finishTurn(){
		if (state instanceof GameOverState){
			updateGrid();
			return;
		}
		if (firstSquareClicked != null){
			firstSquareClicked.setBackground(null);
			firstSquareClicked = null;
			state = new NotYourTurnState();
		}
		else{
			state = new TurnState();
		}
		updateGrid();
		
	}

	public class TimerForSeeingTheUnitBeingAttacked extends TimerTask{
		//this class allows us to wait a few seconds and see what the opponent
		//has before we kill it or die.
		//once the time is up, doAction is called 
		public int row;
		public int column;
		public TimerForSeeingTheUnitBeingAttacked(int r ,int c){
			super();
			row = r;
			column = c;
		}
		public void run(){
			isTimerOn = false;
			doAction(firstSquareRow,firstSquareColumn,row,column);
			finishTurn();
			
			
		}
	}
	
	public class GameOverState implements FrameState{
		Timer timer;
		public GameOverState(String s){
			super();
			informationLabel.setText("GameOver!  Player "+s+" Wins!");
		}
		public void gridButtonPressed(JButton b , int i , int j){}
		public void sideButtonPressed(MouseEvent m){}
	}
	
	public void destroy(){
		try{
			out.print(" " + specialChar+"QUIT"+specialChar + " ");
			out.flush();
			socket.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
}
