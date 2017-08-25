package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Il frame di default che verrà  visualizzato in caso di errori, operazioni avvenute con successo, ecc.
 */
public class DefaultFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Point point = new Point();

	/**
	 * Crea un nuovo frame inserendo in esso la componente specificata.
	 * @param componente La componente da inserire nel frame (Precondizione: componente!= null)
	 * @param title Il titolo del frame (Precondizione: title != null && title != "")
	 * @param x indice riguardante la grandezza (Precondizione x>0)
	 * @param icon l'immagine da affiancare alla componente
	 * @param f il frame dove è stato istanziato (Precondizione: f!= null)
	 */
	public DefaultFrame (JLabel componente, String title,int x,ImageIcon icon,final JFrame f) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((dimension.width - getSize().width) / 2-(x/2), 
		(dimension.height - getSize().height) / 2 -80));
		
		  ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
			 
		     

		     setIconImage(ii8.getImage());
			
		
		setSize(x,160);
		setTitle(title);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setResizable(false);
		setUndecorated(true);
		
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
		addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
         
                setLocation(p.x + e.getX() - point.x,
                        p.y + e.getY() - point.y);
            }
        });
		
		componente.setFont(new Font("Georgia", Font.PLAIN, 18));
		componente.setForeground(Color.BLACK);
		
		JButton chiudi = new JButton("Chiudi");
		chiudi.setFont(new Font("Georgia", Font.PLAIN, 14));
		chiudi.setForeground(Color.BLACK);
		
		/**
		 * Il listener per il pulsante Chiudi.
		 * Selezionando tale pulsante la finestra viene chiusa aprendo la schermata riguardante il frame che l'ha creata
		 */
		class ChiudiListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				dispose();
				f.setVisible(true);
			}
		}
		class AccediMo implements MouseListener {

			public void mouseClicked(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		
		chiudi.addMouseListener(new AccediMo());
		chiudi.addActionListener(new ChiudiListener());
		
		
		getRootPane().setDefaultButton(chiudi);
	    chiudi.requestFocus();
	
		JPanel pannello=new JPanel();
		JPanel pannello1=new JPanel();
		JPanel pannello2=new JPanel();
		componente.setIcon(icon);
		pannello1.add(componente);
		pannello2.add(chiudi);
		pannello.setLayout(new GridLayout(2,1));
		pannello.add(pannello1);
		pannello.add(pannello2);
		
		
		/*JButton dee=new JButton();
		dee.setBorder(null);
		dee.setContentAreaFilled(false);
		ImageIcon ii=new ImageIcon(getClass().getResource("/resource/x.png"));
		dee.setIcon(ii);
		*/
		
		JButton dee1=new JButton();
		dee1.setBorder(null);
		dee1.setContentAreaFilled(false);
		ImageIcon ii1=new ImageIcon(getClass().getResource("/resource/_.png"));
		dee1.setIcon(ii1);
		
		JPanel drf=new JPanel();
		drf.setLayout(new BorderLayout());
		//drf.add(dee,BorderLayout.EAST);
		drf.add(dee1,BorderLayout.EAST);
		
	/*	class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciRegistra.dispose();
				sistema.SvuotaParola();
				conferma=conferma();
				conferma.setVisible(true);
			}
		}*/
		
		class RiduciListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
		
				int state = getExtendedState();
				 
				state = Frame.ICONIFIED;
				setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		
		JLabel label =new JLabel("Errore");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		
		
		TitledBorder t=new TitledBorder(new EtchedBorder(), title);
		t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		t.setTitleColor(Color.BLACK);
		pannello.setBorder(t);
		JPanel tutto=new JPanel();
		tutto.setLayout(new BorderLayout());
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));	
		tutto.add(drf1,BorderLayout.NORTH); //	
		tutto.add(pannello,BorderLayout.CENTER);
		add(tutto);
		
		setVisible(true);
	
		
	}
}
