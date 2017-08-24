package graphics;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;
import core.*;
import exceptions.*;

public class FrameIniziale extends JFrame{
	
	private static Point point = new Point();
	public FrameIniziale(SistemaAccount siste) {
		
		this.sistema=siste;
		
	   ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
	 
	     

	     setIconImage(ii8.getImage());
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((dimension.width - getSize().width) / 2-210, 
		(dimension.height - getSize().height) / 2 -195));
		setLayout(new BorderLayout());
		setSize(420,390);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                System.out.println("ddd");
                setLocation(p.x + e.getX() - point.x,
                        p.y + e.getY() - point.y);
            }
        });
		
		
		
		JPanel tutto=new JPanel();
		tutto.setLayout(new BorderLayout());
		JMenuBar menu=creaMenuBarIniziale();
		JPanel cerca=new JPanel();
		cerca.setLayout(new BorderLayout());
		JButton dee=new JButton();
		dee.setBorder(null);
		dee.setContentAreaFilled(false);
		ImageIcon ii=new ImageIcon(getClass().getResource("/resource/x.png"));
		dee.setIcon(ii);
		
		
		JButton dee1=new JButton();
		dee1.setBorder(null);
		dee1.setContentAreaFilled(false);
		ImageIcon ii1=new ImageIcon(getClass().getResource("/resource/_.png"));
		dee1.setIcon(ii1);
		
		JLabel eeee11=new JLabel("NUOVA VERSIONE DISPOBIBILE");
		eeee11.setFont(new Font("Georgia", Font.BOLD, 15));
		eeee11.setHorizontalAlignment(JLabel.CENTER);
		eeee11.setForeground(Color.BLACK);
		ImageIcon iii1=new ImageIcon(getClass().getResource("/resource/new2.png"));
		eeee11.setIcon(iii1);
		
		notifica=new JPanel();
		notifica.add(eeee11);
		
		class aggiornaOraListener implements MouseListener {

			public void mouseClicked(MouseEvent e) {
				
			
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				try {
					Desktop.getDesktop().browse(new URI("https://mamonegianluigi.000webhostapp.com/paginaAggiornamento.php"));
				} 
				catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					System.out.println("errore");
					e1.printStackTrace();
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				eeee11.setFont(new Font("Georgia", Font.BOLD, 16));
				eeee11.setForeground(Color.BLUE);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				eeee11.setFont(new Font("Georgia", Font.BOLD, 15));
				eeee11.setForeground(Color.BLACK);
			}
		}
		

		eeee11.addMouseListener(new aggiornaOraListener());
		
		cerca.add(menu,BorderLayout.WEST);
		//cerca.setBorder(BorderFactory.createLineBorder(Color.black,1));		
		cerca.add(notifica,BorderLayout.CENTER);
		
		JPanel drf=new JPanel();
		drf.setLayout(new BorderLayout());
		drf.add(dee,BorderLayout.EAST);
		drf.add(dee1,BorderLayout.CENTER);
		cerca.add(drf,BorderLayout.EAST);
		tutto.add(cerca,BorderLayout.NORTH);
		
		if(sistema.getAggiornamento())
			notifica.setVisible(true);
		else
			notifica.setVisible(false);
	
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				if(sistema.isCambiamento()){
					dispose();
					sistema.SvuotaParola();
					conferma=conferma();
					conferma.setVisible(true);
				}
				else{
					sistema.SvuotaParola();
					FileOutputStream out;
					ObjectOutputStream outStream;
					
					try {
//						out = new FileOutputStream(urlFile.getFile());
						out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
						outStream = new ObjectOutputStream(out);
						
						outStream.writeObject(sistema); 
						out.close();
						outStream.close();
						File f=new File ("C:\\AccountManager\\AccountManager.txt");
						f.delete();
						File f1=new File ("C:\\AccountManager\\AccountManager.doc");
						f1.delete();
					} 
					catch (IOException e1) {}
					finally {
						System.exit(0);
					}
				}
			}
		}
		
		class RiduciListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
		
				int state = getExtendedState();
				 
				state = Frame.ICONIFIED;
				setExtendedState(state);
			}
		}
		
		
		dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		
		JPanel pannelloBenvenuto=new JPanel();
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));		
		JLabel de=new JLabel();
		if(sistema.getAggiornamento())
			de.setIcon(new ImageIcon(urlLogo1));
		else
			de.setIcon(new ImageIcon(urlLogo));
	
		pannelloBenvenuto.add(de);
		
		JPanel userPass=new JPanel();
		userPass.setLayout(new GridLayout(2,2));
		JLabel username=new JLabel("Username");
		JLabel password=new JLabel("Password");
		final JTextField tusername=new JTextField(9);
		
		addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        tusername.requestFocusInWindow();
		    }
		});
	
		final JPasswordField tpassword=new JPasswordField(9);
		tpassword.setToolTipText("Password Utente");
		username.setForeground(Color.BLACK);
		password.setForeground(Color.BLACK);
		username.setFont(new Font("Georgia", Font.PLAIN, 20));
		password.setFont(new Font("Georgia", Font.PLAIN, 20));
		
		tusername.setForeground(Color.BLACK);
		
		tpassword.setForeground(Color.BLACK);
		
		tusername.setFont(new Font("Georgia", Font.PLAIN, 20));
		
		tpassword.setFont(new Font("Georgia", Font.PLAIN, 20));
		
		username.setHorizontalAlignment(JLabel.CENTER);
		password.setHorizontalAlignment(JLabel.CENTER);
		tusername.setHorizontalAlignment(JLabel.CENTER);
		tpassword.setHorizontalAlignment(JLabel.CENTER);
		
		userPass.add(username);
		userPass.add(tusername);
		userPass.add(password);
		userPass.add(tpassword);
		
		tusername.setToolTipText("Username Utente");
		
		JPanel bottoni= new JPanel();
		JButton buttonAccedi=new JButton("Accedi");
		buttonAccedi.setFont(new Font("Georgia", Font.PLAIN, 20));
		buttonAccedi.setForeground(Color.BLACK);	
		bottoni.add(buttonAccedi);
		
		JPanel pannelloLoginUtente=new JPanel();
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Login Utente");
		a.setTitleColor(Color.BLACK);
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		pannelloLoginUtente.setBorder(a);
		pannelloLoginUtente.add(userPass);
		pannelloLoginUtente.add(bottoni);
	
		JPanel pannelLoginDescrizione=new JPanel();
		pannelLoginDescrizione.setLayout(new BorderLayout());
		pannelLoginDescrizione.add(pannelloBenvenuto,BorderLayout.NORTH);
		pannelLoginDescrizione.add(pannelloLoginUtente,BorderLayout.CENTER);		
		
		JPanel pannelloRegistrazione=new JPanel();				
		JLabel messaggio = new JLabel("Sei un nuovo utente?");
		messaggio.setForeground(Color.BLACK);
		final JLabel registratiOra = new JLabel("Registrati ora");
		registratiOra.setForeground(Color.BLACK);
		messaggio.setFont(new Font("Georgia", Font.PLAIN, 18));
		registratiOra.setFont(new Font("Georgia", Font.PLAIN, 18));
		pannelloRegistrazione.add(messaggio);
		pannelloRegistrazione.add(registratiOra);
		TitledBorder a1=new TitledBorder(new EtchedBorder(), "Registrazione Utente");
		a1.setTitleColor(Color.BLACK);
		a1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		pannelloRegistrazione.setBorder(a1);
		
		
		JPanel pannelloDim=new JPanel();				
		JLabel messaggio1 = new JLabel("                                  Password dimenticata?");
		messaggio1.setForeground(Color.BLACK);
		final JLabel registratiOra1 = new JLabel("clicca Qui");
		registratiOra1.setForeground(Color.BLACK);
		messaggio1.setFont(new Font("Georgia", Font.PLAIN, 15));
		registratiOra1.setFont(new Font("Georgia", Font.PLAIN, 15));
		pannelloDim.add(messaggio1);
		pannelloDim.add(registratiOra1);
	
		
		tutto.add(pannelLoginDescrizione,BorderLayout.CENTER);
		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		p.add(pannelloRegistrazione,BorderLayout.CENTER);
		p.add(pannelloDim,BorderLayout.SOUTH);
		
		tutto.add(p,BorderLayout.SOUTH);
		add(tutto);
		
		tusername.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			
				
				if(e.getKeyChar()=='\n'){
					System.out.println("Prenuto il tasto invio");
					buttonAccedi.doClick();
				}
			}
		});
		
		tpassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			
				
				if(e.getKeyChar()=='\n'){
					System.out.println("Prenuto il tasto invio");
					buttonAccedi.doClick();
				}
			}
		});
		
		
		
		getRootPane().setDefaultButton(buttonAccedi);
	    buttonAccedi.requestFocus();
	
		
		class AccediListener implements ActionListener {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try
				{
					sistema.loginUtente(tusername.getText(),tpassword.getText());
					dispose();   
					utFr=new UtenteFrame(sistema);
					//def=new DefaultFrame(new JLabel("Accesso riuscito"),
						//	"Accesso riuscito",340,new ImageIcon(imgURLOK),
						// 	utFr); 
					utFr.setVisible(true);
					
				} 
				catch (DatiNonValidiException e1)
				{	  	
					setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",340,new ImageIcon(imgURLnonOK),
							new FrameIniziale(sistema));
					def.setVisible(true);
				}
			}
		}
		
		/**
		 * Serve a creare un link tra la scritta "Registrati ora" (per la registrazione) e il form di registrazione.
		 * Quando il mouse viene cliccato sulla scritta, viene aperto il form per la registrazione di un nuovo
		 * utente.
		 *
		 */
		class RegistratiOraListener implements MouseListener {

			public void mouseClicked(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				creaUtente=creaUtente();
				creaUtente.setVisible(true);
				setVisible(false);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}

			/**
			 * Evidenzia la scritta quando l'utente va con il mouse su di essa
			 */
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				registratiOra.setForeground(Color.BLUE);
			}
			/**
			 * Riporta la scritta al suo colore originale quando l'utente sposta il mouse
			 */
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				registratiOra.setForeground(Color.BLACK);
			}
		}
		
		class RegistratiOraListener1 implements MouseListener {

			public void mouseClicked(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				j=recuperoInfo();
				j.setVisible(true);
				setVisible(false);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}

			/**
			 * Evidenzia la scritta quando l'utente va con il mouse su di essa
			 */
			public void mouseEntered(MouseEvent e) {
				registratiOra1.setForeground(Color.RED);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			/**
			 * Riporta la scritta al suo colore originale quando l'utente sposta il mouse
			 */
			public void mouseExited(MouseEvent e) {
				registratiOra1.setForeground(Color.BLACK);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
		
		buttonAccedi.addActionListener(new AccediListener());
		buttonAccedi.addMouseListener(new AccediMo());
		registratiOra.addMouseListener(new RegistratiOraListener());
		registratiOra1.addMouseListener(new RegistratiOraListener1());
	}
	
	/**
	 * Costruisce la barra dei menù iniziale, dove sono presenti soltanto il menù file, per l'uscita dal programma
	 * e il menù Amministratore per l'accesso degli amministratori
	 */
	private JMenuBar creaMenuBarIniziale () {
		JMenuBar barra = new JMenuBar();
		JMenu file;
		file = new JMenu("File");
		file.setFont(new Font("Georgia", Font.ITALIC, 16));
		file.setForeground(Color.BLACK);
		JMenuItem salva = new JMenuItem("Salva");
		salva.setFont(new Font("Georgia", Font.ITALIC, 16));
		salva.setForeground(Color.BLACK);
		
		JMenuItem exit = new JMenuItem("Esci");
		exit.setFont(new Font("Georgia", Font.ITALIC, 16));
		exit.setForeground(Color.BLACK);
		file.add(salva);
		file.add(exit);		
		barra.add(file);
		
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				if(sistema.isCambiamento()){
					dispose();
					sistema.SvuotaParola();
					conferma=conferma();
					conferma.setVisible(true);
				}
				else{
					sistema.SvuotaParola();
					FileOutputStream out;
					ObjectOutputStream outStream;
					
					try {
//						out = new FileOutputStream(urlFile.getFile());
						out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
						outStream = new ObjectOutputStream(out);
						
						outStream.writeObject(sistema); 
						out.close();
						outStream.close();
						File f=new File ("C:\\AccountManager\\AccountManager.txt");
						f.delete();
						File f1=new File ("C:\\AccountManager\\AccountManager.doc");
						f1.delete();
					} 
					catch (IOException e1) {}
					finally {
						System.exit(0);
					}
				}
			}
		}
		
		class SalvaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				sistema.SvuotaParola();
				FileOutputStream out;
				ObjectOutputStream outStream;
				
				try {
//					out = new FileOutputStream(urlFile.getFile());
					out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
					outStream = new ObjectOutputStream(out);
					
					outStream.writeObject(sistema); 
					out.close();
					outStream.close();
					
					setVisible(false);
					def=new DefaultFrame(new JLabel("Salvataggio Effettuato"),
							"Salvato",390,
							new ImageIcon(imgURLOK),
							new FrameIniziale(sistema));
					def.setVisible(true);
				
				
				} 
				catch (IOException e1) {}
				
				
			}
		}
		
		exit.addActionListener(new ExitListener());
		salva.addActionListener(new SalvaListener());
		return barra;
	}
	
	private JFrame creaUtente() {
		final JFrame creaUtent=new JFrame();
		
		
		
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		creaUtent.setLocation(new Point((dimension.width - creaUtent.getSize().width) / 2-165, 
		(dimension.height - creaUtent.getSize().height) / 2 -125));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		creaUtent.setIconImage(ii8.getImage());
		
		creaUtent.setSize(330,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		creaUtent.setResizable(false);
		creaUtent.setLayout(new BorderLayout());
		creaUtent.setUndecorated(true);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		JLabel l=new JLabel("*campi obbligatori  ");
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
		
		JPanel dati = new JPanel();
		JLabel nome = new JLabel("Nome*");
		JLabel cognome = new JLabel("Cognome*");
		JLabel username = new JLabel("Username*");
		JLabel password = new JLabel("Password*");
		final JTextField nomeField = new JTextField(8);
		final JTextField cognomeField = new JTextField(8);
		final JTextField usernameField = new JTextField(8);
		final JTextField passwordField = new JTextField(3);
		nome.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognome.setFont(new Font("Georgia", Font.PLAIN, 18));
		username.setFont(new Font("Georgia", Font.PLAIN, 18));
		password.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		usernameField.setFont(new Font("Georgia", Font.PLAIN, 18));
		passwordField.setFont(new Font("Georgia", Font.PLAIN, 18));
		nome.setHorizontalAlignment(JLabel.CENTER);
		cognome.setHorizontalAlignment(JLabel.CENTER);
		username.setHorizontalAlignment(JLabel.CENTER);
		password.setHorizontalAlignment(JLabel.CENTER);
		nomeField.setHorizontalAlignment(JLabel.CENTER);
		cognomeField.setHorizontalAlignment(JLabel.CENTER);
		usernameField.setHorizontalAlignment(JLabel.CENTER);
		passwordField.setHorizontalAlignment(JLabel.CENTER);
		nome.setForeground(Color.BLACK);
		cognome.setForeground(Color.BLACK);
		username.setForeground(Color.BLACK);
		password.setForeground(Color.BLACK);
		nomeField.setForeground(Color.BLACK);
		cognomeField.setForeground(Color.BLACK);
		usernameField.setForeground(Color.BLACK);
		passwordField.setForeground(Color.BLACK);		
		dati.setLayout(new GridLayout(4, 2));
		dati.add(nome);
		dati.add(nomeField);
		dati.add(cognome);
		dati.add(cognomeField);
		dati.add(username);
		dati.add(usernameField);
		dati.add(password);
		dati.add(passwordField);
	
		JPanel bottoni= new JPanel();
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		
		JPanel registrazioneUtente=new JPanel();
		registrazioneUtente.setLayout(new BorderLayout());
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Registrazione Utente");
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		registrazioneUtente.setBorder(a);
		registrazioneUtente.add(l,BorderLayout.NORTH);
		registrazioneUtente.add(dati,BorderLayout.CENTER);
		registrazioneUtente.add(bottoni,BorderLayout.SOUTH);
		
		pannello.add(label,BorderLayout.NORTH);
		pannello.add(registrazioneUtente, BorderLayout.CENTER);
		
		JPanel tutto=new JPanel();
		tutto.setLayout(new BorderLayout());
		
		tutto.add(pannello,BorderLayout.CENTER);
		
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));
		creaUtent.add(tutto,BorderLayout.CENTER);
		
		
		class ButtonConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				try 
				{
					sistema.creaUtente(nomeField.getText(),cognomeField.getText(),
							usernameField.getText(),passwordField.getText());
					creaUtent.dispose();
					def=new DefaultFrame(new JLabel("Registrazione riuscita"),
							"Registrazione riuscita",340,new ImageIcon(imgURLOK),
							new FrameIniziale(sistema));
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{
					creaUtent.setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,new ImageIcon(imgURLnonOK),
							creaUtent);
					def.setVisible(true);
				}
			}
		}
		
		class ButtonAnnullaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				creaUtent.dispose();
				setVisible(true);
			}
		}
		buttonConferma.addActionListener(new ButtonConfermaListener());
		buttonAnnulla.addActionListener(new ButtonAnnullaListener());
		
		return creaUtent;
	}
	
	private JFrame recuperoInfo() {
		final JFrame recuperoInf=new JFrame();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		recuperoInf.setLocation(new Point((dimension.width - recuperoInf.getSize().width) / 2-165, 
		(dimension.height - recuperoInf.getSize().height) / 2 -105));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		recuperoInf.setIconImage(ii8.getImage());
		
		recuperoInf.setSize(330,210);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		recuperoInf.setResizable(false);
		recuperoInf.setLayout(new BorderLayout());
		recuperoInf.setUndecorated(true);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		JLabel l=new JLabel("*campi obbligatori  ");
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
		
		JPanel dati = new JPanel();
		JLabel nome = new JLabel("Nome*");
		JLabel cognome = new JLabel("Cognome*");
		JLabel username = new JLabel("Username*");
	
		final JTextField nomeField = new JTextField(8);
		final JTextField cognomeField = new JTextField(8);
		final JTextField usernameField = new JTextField(8);
	
		nome.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognome.setFont(new Font("Georgia", Font.PLAIN, 18));
		username.setFont(new Font("Georgia", Font.PLAIN, 18));

		nomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		usernameField.setFont(new Font("Georgia", Font.PLAIN, 18));

		nome.setHorizontalAlignment(JLabel.CENTER);
		cognome.setHorizontalAlignment(JLabel.CENTER);
		username.setHorizontalAlignment(JLabel.CENTER);

		nomeField.setHorizontalAlignment(JLabel.CENTER);
		cognomeField.setHorizontalAlignment(JLabel.CENTER);
		usernameField.setHorizontalAlignment(JLabel.CENTER);

		nome.setForeground(Color.BLACK);
		cognome.setForeground(Color.BLACK);
		username.setForeground(Color.BLACK);

		nomeField.setForeground(Color.BLACK);
		cognomeField.setForeground(Color.BLACK);
		usernameField.setForeground(Color.BLACK);
		
		dati.setLayout(new GridLayout(3, 2));
		dati.add(nome);
		dati.add(nomeField);
		dati.add(cognome);
		dati.add(cognomeField);
		dati.add(username);
		dati.add(usernameField);

	
		JPanel bottoni= new JPanel();
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		
		JPanel registrazioneUtente=new JPanel();
		registrazioneUtente.setLayout(new BorderLayout());
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Registrazione Utente");
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		registrazioneUtente.setBorder(a);
		registrazioneUtente.add(l,BorderLayout.NORTH);
		registrazioneUtente.add(dati,BorderLayout.CENTER);
		registrazioneUtente.add(bottoni,BorderLayout.SOUTH);
		
		pannello.add(label,BorderLayout.NORTH);
		pannello.add(registrazioneUtente, BorderLayout.CENTER);
		
		JPanel tutto=new JPanel();
		tutto.setLayout(new BorderLayout());
		
		tutto.add(pannello,BorderLayout.CENTER);
		
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));
		recuperoInf.add(tutto,BorderLayout.CENTER);
		
		
		class ButtonConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				try 
				{
					String pass=sistema.cercaUtente(cognomeField.getText(), nomeField.getText(),usernameField.getText());
					recuperoInf.dispose();
					def=new DefaultFrame(new JLabel("La password è : "+pass),
							"Recupero password",360,new ImageIcon(imgURLOK),
							new FrameIniziale(sistema));
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{
					recuperoInf.setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,new ImageIcon(imgURLnonOK),
							recuperoInf);
					def.setVisible(true);
				}
			}
		}
		
		class ButtonAnnullaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				recuperoInf.dispose();
				setVisible(true);
			}
		}
		buttonConferma.addActionListener(new ButtonConfermaListener());
		buttonAnnulla.addActionListener(new ButtonAnnullaListener());
		
		return recuperoInf;
	}
	
	private JFrame conferma () {
		final JFrame conf=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		conf.setLocation(new Point((dimension.width - 
				conf.getSize().width) / 2-240, 
		(dimension.height - conf.getSize().height) / 2 -65));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		conf.setIconImage(ii8.getImage());
		conf.setSize(480,130);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		conf.setResizable(false);
		conf.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		conf.setUndecorated(true);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Salvare le modifiche prima di Uscire ?");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		
		
		
		JLabel de=new JLabel();
		de.setIcon(new ImageIcon(imgURLvuoto));
		// inseriamo a nord del pannello la label "inserisci i dati richiesti" (spostata al centro)
	
	
		JButton butt1=new JButton("      Si      ");
		butt1.setFont(new Font("Georgia", Font.PLAIN,20));
		butt1.setForeground(Color.BLACK);
		JButton butt3=new JButton("      No      ");
		butt3.setFont(new Font("Georgia", Font.PLAIN,20));
		butt3.setForeground(Color.BLACK);
		JButton butt2=new JButton("Annulla");
		butt2.setFont(new Font("Georgia", Font.PLAIN, 20));
		butt2.setForeground(Color.BLACK);
		JPanel pa8=new JPanel();
		pa8.add(butt1);
		pa8.add(butt3);
		pa8.add(butt2);
		
		
		
		JPanel panne=new JPanel();
		panne.add(label,BorderLayout.NORTH);
		panne.add(de,BorderLayout.CENTER);
		panne.add(pa8,BorderLayout.SOUTH);
		pannello.add(panne);
		conf.add(pannello);
		
		
		TitledBorder a5=new TitledBorder(new EtchedBorder(), "Salva ed Esci");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		
		panne.setBorder(a5);
		pannello.setBorder(BorderFactory.createLineBorder(Color.black,2));	
		class Button1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {					
				sistema.SvuotaParola();
				FileOutputStream out;
				ObjectOutputStream outStream;
				
				try {
//					out = new FileOutputStream(urlFile.getFile());
					out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
					outStream = new ObjectOutputStream(out);
					
					outStream.writeObject(sistema); 
					out.close();
					outStream.close();
					File f=new File ("C:\\AccountManager\\AccountManager.txt");
					f.delete();
					File f1=new File ("C:\\AccountManager\\AccountManager.doc");
					f1.delete();
				} 
				catch (IOException e1) {}
				finally {
					System.exit(0);
				}
			
			}
		}
		
		class Button3Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {					
				sistema.SvuotaParola();
				System.exit(0);
			
			}
		}
		
		
		
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//sistema.SvuotaParola();
				conf.dispose();
				setVisible(true);
			}
		}
	
		
		
		butt1.addActionListener(new Button1Listener());
		butt3.addActionListener(new Button3Listener());
		
		butt2.addActionListener(new AnnullaListener());
		return conf;
	}
	
	private static final long serialVersionUID = 1L;
	private SistemaAccount sistema;
	private JFrame creaUtente;
	private JFrame j;
	private JFrame conferma;
	private UtenteFrame utFr;
	private DefaultFrame def;
	private JPanel notifica;
	private URL imgURLOK = getClass().getResource("/resource/ok.png");
	private URL imgURLnonOK = getClass().getResource("/resource/image.png");
	private URL imgURLvuoto = getClass().getResource("/resource/vuoto.png");
	//private URL urlFile = getClass().getResource("/resource/accountManager.dat");
	private URL urlLogo = getClass().getResource("/resource/accountManager2.png");
	private URL urlLogo1 = getClass().getResource("/resource/accountManager2Update.png");


}
