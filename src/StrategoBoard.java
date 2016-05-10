/**
 * @(#)Stratego.java
 *
 *
 * @author 
 * @version 1.00 2008/4/1
 */


public class StrategoBoard
{
   public StrategoBoard()
   {
      //** Debug purposes only:
         System.out.println("Constructor to initialize the grid array");
      //**/
      gridArray = new String[10][10];
   }
   
   public void setUpGrid()
   {
      //** Debug purposes only:
         System.out.println("Set up the grid");
      //**/
   	  for(int row = 0; row < 10; row++)
   	  {
   	     for(int column = 0; column < 10; column++)
   	     {
   	        gridArray[row][column] = "";
   	     }
   	  }
   }
   
   private void clearGrid()
   {
      //** Debug purposes only:
         System.out.println("Clear the values from grid");
      //**/
   	  for(int row = 0; row < 10; row++)
   	  {
   	     for(int column = 0; column < 10; column++)
   	     {
   	        gridArray[row][column] = "";
   	     }
   	  }
   }
   
   public void insertValue(int row, int column,  String value)
   {
      //** Debug purposes only:
         System.out.println("Insert value "+value.substring(1,2)+" into cell ["+row+", "+column+"]");
      //**/
   	  gridArray[row][column] = value;
   }
   
   public void removeValue(int row, int column)
   {
      //** Debug purposes only:
         System.out.println("Remove value from cell ["+row+", "+column+"]");
      //**/
   	  gridArray[row][column] = "";
   }
   
   public String obtainValue(int row, int column)
   {
      /** Debug purposes only:
         System.out.println("Obtain the value from cell ["+row+", "+column+"]");
      //**/
   	  return gridArray[row][column];
   }
   
   public void moveValue(int oldRow, int oldColumn, int newRow, int newColumn)
   {
   	  //** Debug purposes only:
         System.out.println("Move value from cell ["+oldRow+", "+oldColumn+"] to ["+newRow+", "+newColumn+"]");
      //**/
   	  String value = obtainValue(oldRow,oldColumn);
   	  insertValue(newRow,newColumn,value);
   	  removeValue(oldRow,oldColumn);
   }
   
   public int countMoves(int oldRow, int oldColumn, int newRow, int newColumn)
   {
   	  //** Debug purposes only:
         System.out.println("Count move from cell ["+oldRow+", "+oldColumn+"] to ["+newRow+", "+newColumn+"]");
      //**/
      return (Math.abs(newRow - oldRow) + Math.abs(newColumn - oldColumn));
   }
   
   public boolean strikeSuccess(String player, String opponent)
   {
      //** Debug purposes only:
         System.out.println("Checks to see who wins in a fight between "+player+" and "+opponent);
      //**/
      if(player.substring(0,1).equals(opponent.substring(0,1)))
         return false;
      player = player.substring(1,2);
      System.out.println("Player "+player);
      opponent = opponent.substring(1,2);
      System.out.println("Opponent "+opponent);
   	  if(player.equals("1"))
   	  {
   	     if(opponent.equals("b"))
   	     {
   	        return false;
   	     }
   	     return true;
   	  }
   	  if(player.equals("2"))
   	  {
   	     if(opponent.equals("1"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("b"))
   	     {
   	        return false;
   	     }
   	     return true;
   	  }
   	  if(player.equals("3"))
   	  {
   	     if(opponent.equals("1"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("2"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("b"))
   	     {
   	        return false;
   	     }
   	     return true;
   	  }
   	  if(player.equals("4"))
   	  {
   	     if(opponent.equals("1"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("2"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("3"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("b"))
   	     {
   	        return false;
   	     }
   	     return true;
   	  }
   	  if(player.equals("5"))
   	  {
   	     if(opponent.equals("1"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("2"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("3"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("4"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("b"))
   	     {
   	        return false;
   	     }
   	     return true;
   	  }
   	  if(player.equals("6"))
   	  {
   	     if(opponent.equals("1"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("2"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("3"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("4"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("5"))
   	     {
   	        return false;
   	     }
   	     if(opponent.equals("b"))
   	     {
   	        return false;
   	     }
   	     return true;
   	  }
   	  if(player.equals("7"))
   	  {
   	     if(opponent.equals("7"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("8"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("9"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("s"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("f"))
   	     {
   	        return true;
   	     }
   	     return false;
   	  }
   	  if(player.equals("8"))
   	  {
   	     if(opponent.equals("8"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("9"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("s"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("b"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("f"))
   	     {
   	        return true;
   	     }
   	     return false;
   	  }
   	  if(player.equals("9"))
   	  {
   	     if(opponent.equals("9"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("s"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("f"))
   	     {
   	        return true;
   	     }
   	     return false;
   	  }
   	  if(player.equals("s"))
   	  {
   	     if(opponent.equals("1"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("s"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("f"))
   	     {
   	        return true;
   	     }
   	     return false;
   	  }
   	  return false;
   }
   
   public boolean canMove(String value)
   {
      //** Debug purposes only:
         System.out.println("Determines whether a piece can move based on its value");
      //**/
   	  if(value.equals("b") || value.equals("f"))
         return false;
      return true;
   }
   
   public boolean rightDirection(int oldRow, int oldColumn, int newRow, int newColumn)
   {
      //** Debug purposes only:
         System.out.println("Checks to see if path is a straight line from cell ["+oldRow+", "+oldColumn+"] to ["+newRow+", "+newColumn+"]");
      //**/
   	  if(oldRow == newRow)
      {
         if(oldColumn != newColumn)
         {
            return true;
         }
         return false;
      }
      if(oldColumn == newColumn)
      {
         if(oldRow != newRow)
         {
            return true;
         }
         return false;
      }
      return false;
   }
   
   public boolean isMoveValid(int oldRow, int oldColumn, int newRow, int newColumn)
   {
      //** Debug purposes only:
         System.out.println("Determine validity of move from cell ["+oldRow+", "+oldColumn+"] to ["+newRow+", "+newColumn+"]");
      //**/
   	  String value = gridArray[oldRow][oldColumn];
   	  String opponent = gridArray[newRow][newColumn];
   	  if (value.substring(1,2).equals("9") && opponent.equals("")){
   		  return canScoutMove(oldRow,oldColumn,newRow,newColumn);
   	  }
      if(canMove(value.substring(1,2))
          && countMoves(oldRow,oldColumn,newRow,newColumn)==1
          && rightDirection(oldRow,oldColumn,newRow,newColumn)
          && squareNotOwnedByUser(oldRow,oldColumn,newRow,newColumn))
         return true;
      return false;
   }
   
   public boolean squareNotOwnedByUser(int oldRow,int oldColumn,int newRow,int newColumn){
	   if (obtainValue(newRow,newColumn).equals("")){
		   //"" must correspond to the "empty" instance variable in StrategoFrame
		   return true;
	   }
	   if (obtainValue(oldRow,oldColumn).substring(0,1).equals(obtainValue(newRow,newColumn).substring(0,1))){
		   return false;
	   }
	   else{
		   return true;
	   }
   }
   public boolean isStrikeValid(int oldRow, int oldColumn, int newRow, int newColumn)
   {
      //** Debug purposes only:
         System.out.println("Determine validity of strike from cell ["+oldRow+", "+oldColumn+"] to ["+newRow+", "+newColumn+"]");
      //**/
   	  String player = gridArray[oldRow][oldColumn];
      String opponent = gridArray[newRow][newColumn];
      if(strikeSuccess(player,opponent)
          && countMoves(oldRow,oldColumn,newRow,newColumn)==1
          && rightDirection(oldRow,oldColumn,newRow,newColumn))
         return true;
      return false;
   }
   
   public boolean isFinished()
   {
      for(int row = 0; row < 10; row++)
      {
         for(int column = 0; column < 10; column++)
         {
            if((gridArray[row][column]).equals("f"))
               return true;
         }
      }
      return false;
   }

   public void debug()
   {
      for(int row = 0; row < 10; row++)
      {
         for(int column = 0; column < 10; column++)
         {
            System.out.print(gridArray[row][column] + " ");
         }
         System.out.print("\n");
      }
   }
   
   public void setPlayerNumber(int number)
   {
   	  playerNumber = number;
   }
   
   public int getPlayerNumber()
   {
   	  return playerNumber;
   }
   
   public void setOpponentNumber(int number)
   {
   	  opponentNumber = number;
   }
   
   public int getOpponentNumber()
   {
   	  return opponentNumber;
   }
   public boolean canScoutMove(int oldRow,int oldColumn,int newRow,int newColumn){
	   int height = newRow - oldRow;
	   int width = newColumn - oldColumn;
	   if (height*width != 0){
		   return false;
	   }
	   int[][] emptySquares = {{4,2},{4,3},{5,2},{5,3},{4,6},{4,7},{5,6},{5,7}};
	   if (height != 0){
		   if (height > 0){
			  for (int i=1;i<=height;i++){
				  if (!obtainValue(oldRow + i,oldColumn).equals("")){
					  return false;
				  }
				  
				  for (int j=0;j<emptySquares.length;j++){
					  if (emptySquares[j][0] == oldRow+i && emptySquares[j][1] == oldColumn){
						  return false;
					  }
				  }
			  }
			  
		   }
		  else{
			  for (int i=1;i<=-height;i++){
				  if (!obtainValue(oldRow - i,oldColumn).equals("")){
					  return false;
				  }
				  for (int j=0;j<emptySquares.length;j++){
					  if (emptySquares[j][0] == oldRow-i && emptySquares[j][1] == oldColumn){
						  return false;
					  }
				  }
			  }
		  }
	   }
	   else{
		   if (width > 0){
				  for (int i=1;i<=width;i++){
					  if (!obtainValue(oldRow,oldColumn+i).equals("")){
						  return false;
					  }
					  for (int j=0;j<emptySquares.length;j++){
						  if (emptySquares[j][0] == oldRow && emptySquares[j][1] == oldColumn+i){
							  return false;
						  }
					  }
				  }
				 
			   }
			  else{
				  for (int i=1;i<=-width;i++){
					  if (!obtainValue(oldRow,oldColumn-i).equals("")){
						  return false;
					  }
					  for (int j=0;j<emptySquares.length;j++){
						  if (emptySquares[j][0] == oldRow && emptySquares[j][1] == oldColumn-i){
							  return false;
						  }
					  }
				  }
				  
			  }
		   
	   }
	   return true;
	   
   }
   private String[][] gridArray;
   private int playerNumber;
   private int opponentNumber;
}