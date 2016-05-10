/**
 * @(#)StrategoDriver.java
 *
 *
 * @author 
 * @version 1.00 2008/4/2
 */

import java.util.*;

public class StrategoDriver
{
   public static void main(String[] args)
   {
      HashMap bag = new HashMap();
      bag.put("F",1);
      bag.put("B",6);
      bag.put("S",1);
      bag.put("9",8);
      bag.put("8",5);
      bag.put("7",4);
      bag.put("6",4);
      bag.put("5",4);
      bag.put("4",3);
      bag.put("3",2);
      bag.put("2",1);
      bag.put("1",1);
      Set set = bag.entrySet();
      Iterator i =  set.iterator();
      
      Stratego game = new Stratego();
      game.setUpGrid();
      game.insertValue(0,0,"S");
      
      /**while(i.hasNext())
      {
         Map.Entry me = (Map.Entry)i.next();
         System.out.println(me.getKey() + " : " + me.getValue());
         bag.put("D",2);
      }*/
      game.debug();
      if(game.isMoveValid(0,0,1,0))
         game.moveValue(0,0,1,0);
      game.debug();
      game.insertValue(1,1,"1");
      game.debug();
      if(game.isStrikeValid(1,0,1,1))
         game.removeValue(1,1);
      else
         game.removeValue(1,0);
      game.debug();
      Scanner input = new Scanner(System.in);
      String operation = "";
      String v = "";
      int or,oc, nr, nc;
      while(!operation.equals("Q"))
      {
         System.out.print("What do you want to do(M,S,I,Q): ");
         operation = input.next();
         if(operation.equals("M"))
         {
            System.out.print("Enter for move old row column and new row column: ");
            or = input.nextInt();
            oc = input.nextInt();
            nr = input.nextInt();
            nc = input.nextInt();
            if(game.isMoveValid(or,oc,nr,nc))
               game.moveValue(or,oc,nr,nc);
         }
         if(operation.equals("S"))
         {
            System.out.print("Enter for strike old row column and new row column: ");
            or = input.nextInt();
            oc = input.nextInt();
            nr = input.nextInt();
            nc = input.nextInt();
            if(game.isStrikeValid(or,oc,nr,nc))
               game.removeValue(nr,nc);
         }
         if(operation.equals("I"))
         {
            System.out.print("Enter insertion value and row and column: ");
            v = input.next();
            nr = input.nextInt();
            nc = input.nextInt();
            game.insertValue(nr,nc,v);
         }
         game.debug();
      }
   }
}