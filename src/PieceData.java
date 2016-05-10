import java.awt.*;
import javax.swing.*;
public class PieceData {
//this holds information about the pieces of each team
//all of this data is held inside a panel on the left and right of the frame
	
	public JPanel panel;
	public JLabel picture;
	public JLabel pieceRank;
	public JLabel quantity;
	boolean isPlayer1;
	boolean isSelected;
	PieceData(boolean isP1,JPanel pnl,JLabel p, JLabel r, JLabel q){
		isPlayer1 = isP1;
		panel = pnl;
		picture = p;
		pieceRank = r;
		quantity = q;
		deselect();
		
		if (isPlayer1){
			pieceRank.setForeground(Color.RED);
			quantity.setForeground(Color.RED);
		}
		else{
			pieceRank.setForeground(Color.BLUE);
			quantity.setForeground(Color.BLUE);
		}
	}
	
	public void select(){
		panel.setBorder(BorderFactory.createBevelBorder(1));
		isSelected = true;
	}
	public void deselect(){
		panel.setBorder(BorderFactory.createBevelBorder(0));
		isSelected = false;
	}
}
