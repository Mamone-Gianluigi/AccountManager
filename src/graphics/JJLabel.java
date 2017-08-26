package graphics;


import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JJLabel extends JLabel {
		
	public JJLabel () {
		super();
		setBorder(null);
		ImageIcon ii1a=new ImageIcon(getClass().getResource("/resource/wer.png"));
		setIcon(ii1a);
		setVisible(false);
		
	}
	
	
	

	private static final long serialVersionUID = 1L;
	

}
