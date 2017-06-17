package graphics;

import java.awt.*;

import javax.swing.JTextField;

public class MyLabel extends JTextField {
		
	public MyLabel (String text) {
		super(text);
		
		setSelectedTextColor(Color.RED);
		setBorder(null);
		setBackground(null);
		setCursor(null);
		setEditable(false);
		
	}
	
	public void setColonne(int n) {
		System.out.println(n);
		this.setColumns(n);
	}
	
	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(0, 0, getWidth(), getHeight());
		// Richiamiamo il paintComponent originale per stampare il testo al centro del pulsante
		super.paintComponent(g);
	}

	
	

	private static final long serialVersionUID = 1L;
	

}
