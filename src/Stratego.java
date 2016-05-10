/**
 * @(#)Stratego.java
 *
 *
 * @author 
 * @version 1.00 2008/4/1
 */


public class Stratego
{
   public Stratego()
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
   	        gridArray[row][column] = "0";
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
   
   public void insertValue(int row, int column, String value)
   {
      //** Debug purposes only:
         System.out.println("Insert value "+value+" into cell ["+row+", "+column+"]");
      //**/
   	  gridArray[row][column] = value;
   }
   
   public void removeValue(int row, int column)
   {
      //** Debug purposes only:
         System.out.println("Remove value from cell ["+row+", "+column+"]");
      //**/
   	  gridArray[row][column] = "0";
   }
   
   public String obtainValue(int row, int column)
   {
      //** Debug purposes only:
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
   	  if(player.equals("1"))
   	  {
   	     if(opponent.equals("B"))
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
   	     if(opponent.equals("B"))
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
   	     if(opponent.equals("B"))
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
   	     if(opponent.equals("B"))
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
   	     if(opponent.equals("B"))
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
   	     if(opponent.equals("B"))
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
   	     if(opponent.equals("S"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("F"))
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
   	     if(opponent.equals("S"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("B"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("F"))
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
   	     if(opponent.equals("S"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("F"))
   	     {
   	        return true;
   	     }
   	     return false;
   	  }
   	  if(player.equals("S"))
   	  {
   	     if(opponent.equals("1"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("S"))
   	     {
   	        return true;
   	     }
   	     if(opponent.equals("F"))
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
   	  if(value.equals("B") || value.equals("F"))
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
      if(canMove(value)
          && countMoves(oldRow,oldColumn,newRow,newColumn)==1
          && rightDirection(oldRow,oldColumn,newRow,newColumn))
         return true;
      return false;
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
            if((gridArray[row][column]).equals("F"))
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
   
   private String[][] gridArray;
   private int playerNumber;
}