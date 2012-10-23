import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


public class SiteInfosPanel extends JPanel { //JEditorPane
	private ImageIcon picture;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3675615169816677858L;

	public SiteInfosPanel() {
	}
	
	public void paintComponent( Graphics g ){
		super.paintComponent( g );
		g.draw3DRect(10, 10, 40, 60, false);
		//picture.paintIcon( this, g, 0, 0 );
		
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(600,400);
		//return new Dimension( picture.getIconWidth(), picture.getIconHeight() ); 
	}
}
