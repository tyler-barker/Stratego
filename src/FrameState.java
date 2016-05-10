import java.awt.event.*;

import javax.swing.JButton;
public interface FrameState {

	public void sideButtonPressed(MouseEvent e);
	public void gridButtonPressed(JButton button,int row,int column);
}
