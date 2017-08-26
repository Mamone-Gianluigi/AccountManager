package graphics;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

import core.Registrazione;
import core.SistemaAccount;
import core.Utente;
import exceptions.DatiNonValidiException;




public class UtenteFrame extends JFrame {
	private int i=-1;
	private int j=-1;
	private Clipboard clipBoard;
	private JTextComponent textComponent;
    private TransferHandler handler;
   

	
	 public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	class CountDown implements ActionListener {
		 
			public void actionPerformed(ActionEvent event){
				if(i==0){
					System.out.println("0000 txt");
					i=i+1;
				}
				else if(i==1) {
					System.out.println("1111 txt");
					//PrintWriter p = new PrintWriter("AccountManager.txt");
					//p.print("");
					//p.close();
					File f=new File ("C:\\AccountManager\\AccountManager.txt");
					f.delete();
					i=-1;
					tt.stop();
					return;
				}
				else{
					tt.stop();
				}
				
				System.out.println("222222");
			}
	}
	 
	 class CountDown1 implements ActionListener {
		 
			public void actionPerformed(ActionEvent event){
				if(j==0){
					System.out.println("0000 doc");
					j=j+1;
				}
				else if(j==1) {
					System.out.println("1111 doc");
					File f=new File ("C:\\AccountManager\\AccountManager.doc");
					f.delete();
					j=-1;
					tt1.stop();
					return;
				} else{
					tt1.stop();
				}
				
				System.out.println("222222");
			}
	}
	 private Timer tt; 
	 private Timer tt1; 
	
	 
	 private static Point point = new Point();
	 public UtenteFrame (SistemaAccount sistema) {
		 numero_account=0;
	
		this.sistema=sistema;
		val=false;
	    suggeri=new ArrayList<MyLabel>();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((dimension.width - getSize().width) / 2-450, 
		(dimension.height - getSize().height) / 2 -325));
		
		setSize(900,650);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	//	setResizable(false); // Il frame non deve essere ridimensionabile
		  ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
			 
		     

		     setIconImage(ii8.getImage());
			
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
		
		setLayout(new BorderLayout());
		//menù
		menu=creaMenuBarIniziale();
		
		//pannello benvenuto utente
		descrizione=new JPanel();
		JLabel descrizione1=new JLabel("Benvenuto ");
		descrizione1.setFont(new Font("Georgia", Font.ITALIC, 20));
		descrizione1.setForeground(Color.BLACK);
		
		String nome=sistema.getUtenteCorrente().getNome();
	

		
		JLabel descrizione2=new JLabel(nome);
		descrizione2.setFont(new Font("Georgia", Font.ITALIC, 22));
		descrizione2.setForeground(Color.RED);
		
	
		
		JLabel de=new JLabel();
		if(sistema.getAggiornamento())
			logo=new ImageIcon(getClass().getResource("/resource/accountManager1Update.png"));
		else
			logo=new ImageIcon(getClass().getResource("/resource/accountManager1.png"));
		
		de.setIcon(logo);
		descrizione.add(de);
		
		ImageIcon img=new ImageIcon(getClass().getResource("/resource/cerca.png"));
		JButton cercaim=new JButton(img);
		cercaim.setBorderPainted(false);
		cercaim.setContentAreaFilled(false);
		cercaim.setFocusPainted(false);
		
		
		
		cercaField=new JTextField(10);
		
		
		
		cercaField.setToolTipText("Nome Account");
		cercaField.setFont(new Font("Georgia", Font.ITALIC, 26));
		cercaField.setForeground(Color.BLACK);
		
	  ClipboardPopupMenu cb=  ClipboardPopupMenu.installForComponent(cercaField);
	  cb.addCopyFunction("Copia");
	  cb.addCutFunction("Taglia",this);
	  cb.addPasteFunction("Incolla");
	  cb.addClearFunction("Chiudi");
	  

	  
		
		
		JButton cerca=new JButton("Cerca Account");
		cerca.setFont(new Font("Georgia", Font.ITALIC, 22));
		cerca.setForeground(Color.BLACK);
		

		addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        cercaField.requestFocusInWindow();
		    }
		});
		
		/*cercaField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				
				System.out.println("entro");
				a.setVisible(true);
				if(e.getKeyChar()=='\n'){
					
					cerca.doClick();
					
				}
			}
		});*/
		
		
		cercaField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			
				char c=e.getKeyChar();
				int a77= (int)c;
				if(a77==127 || a77==8){
					System.out.println("Parola prima : "+sistema.getParola());
					sistema.RemoveCarattere();
					if(val==true){
						System.out.println("qui");
						System.out.println("Parola dopo : "+sistema.getParola());
						a.setVisible(false);
						val=false;
					}
					
					System.out.println(sistema.getParola()+"--");
				}
				else if(a77==10){
					System.out.println("pressione");
					a.setVisible(true);
				}
				else{
                    sistema.AddCarattere(c);
                    if(val==true){
						a.setVisible(false);
						val=false;
					}
				}
				if(sistema.getParola().length()>=1){
					
					ArrayList<String> sugge=sistema.suggerisci(sistema.getParola());
					System.out.println("qww");
					System.out.println(sugge.size()+"sez");
					suggeri=new ArrayList<MyLabel>();
					w1.remove(pannelloRegistrazione);
					pannelloRegistrazione=new JPanel();
					w1.add(pannelloRegistrazione,BorderLayout.SOUTH);
					
					
					
					System.out.println(sugge+"suggeriemtni trovati");
					System.out.println(suggeri.size()+"numer my");
					int i;
					for(i=0; i<sugge.size(); i++){
						
						MyLabel sugg1 = new MyLabel(sugge.get(i));
						sugg1.setFont(new Font("Georgia", Font.PLAIN, 16));
						suggeri.add(sugg1);
						pannelloRegistrazione.add(sugg1);
						setVisible(true);
						if((i % 2) == 0){
						   sugg1.setForeground(Color.BLACK);
						   class sugg1Listener implements MouseListener {
								
								public void mouseClicked(MouseEvent e) {
									setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
									cercaField.setText(sugg1.getText());
								
									//creaUtente=creaUtente();
									//creaUtente.setVisible(true);
									//setVisible(false);
								}
								public void mousePressed(MouseEvent e) {}
								public void mouseReleased(MouseEvent e) {}
					
								public void mouseEntered(MouseEvent e) {
									sugg1.setForeground(Color.BLUE);
									setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								}
								/**
								 * Riporta la scritta al suo colore originale quando l'utente sposta il mouse
								 */
								public void mouseExited(MouseEvent e) {
									sugg1.setForeground(Color.BLACK);
									setCursor(DEFAULT_CURSOR);
								}
							}
						   sugg1.addMouseListener(new sugg1Listener());
						}
					    else{
					    	
						   sugg1.setForeground(Color.RED);
						   class sugg1Listener implements MouseListener {
								
								public void mouseClicked(MouseEvent e) {
									setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
									cercaField.setText(sugg1.getText());
								}
								public void mousePressed(MouseEvent e) {}
								public void mouseReleased(MouseEvent e) {}
					
								public void mouseEntered(MouseEvent e) {
									sugg1.setForeground(Color.BLUE);
									setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								}
								/**
								 * Riporta la scritta al suo colore originale quando l'utente sposta il mouse
								 */
								public void mouseExited(MouseEvent e) {
									sugg1.setForeground(Color.RED);
									setCursor(DEFAULT_CURSOR);
								}
							}		
						   sugg1.addMouseListener(new sugg1Listener());
					    }
						
						
				
						
					}
					
					if(sugge.size()==0)
						pannelloRegistrazione.setVisible(false);
					if(sugge.size()>0)
						pannelloRegistrazione.setVisible(true);
				}
				else{
					pannelloRegistrazione.setVisible(false);
					
				}
				//suu.setText(sistema.getParola());
				System.out.println(suggeri.size()+"dopo my");
				
			}
		});
		
	
		
		getRootPane().setDefaultButton(cerca);
	    cerca.requestFocus();
		
		JPanel rigaCerca=new JPanel();
		
		JLabel label2 =new JLabel();
		label2.setFont(new Font("Georgia", Font.PLAIN, 18));
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setForeground(Color.BLUE);		
		JCheckBox sett1Button = new JCheckBox();
		JCheckBox sett2Button = new JCheckBox();
		JCheckBox sett3Button = new JCheckBox();
		JCheckBox sett4Button = new JCheckBox();
		sett1Button.setFont(new Font("Georgia", Font.PLAIN, 15));
		sett1Button.setForeground(Color.BLACK);
		sett1Button.setHorizontalAlignment(JCheckBox.LEFT);
		sett2Button.setFont(new Font("Georgia", Font.PLAIN, 15));
		sett2Button.setForeground(Color.BLACK);
		sett2Button.setHorizontalAlignment(JCheckBox.LEFT);
		sett3Button.setFont(new Font("Georgia", Font.PLAIN, 15));
		sett3Button.setForeground(Color.BLACK);
		sett3Button.setHorizontalAlignment(JCheckBox.LEFT);
		sett4Button.setFont(new Font("Georgia", Font.PLAIN, 15));
		sett4Button.setForeground(Color.BLACK);
		sett4Button.setHorizontalAlignment(JCheckBox.LEFT);
		JPanel scegliSettore=new JPanel();
		scegliSettore.setLayout( new GridLayout(1,2));
		JPanel scegliSettore1=new JPanel();
		scegliSettore1.setLayout( new GridLayout(1,2));
		//scegliSettore.add(label2);
		scegliSettore.add(sett1Button);
		scegliSettore.add(sett2Button);
		scegliSettore1.add(sett3Button);
		scegliSettore1.add(sett4Button);
	
		sett1Button.setVisible(false);
		sett2Button.setVisible(false);
		sett3Button.setVisible(false);
		sett4Button.setVisible(false);
		label2.setVisible(false);
		
		

		//pannelloRegistrazione.setLayout( new GridLayout(2,1));
		/*JLabel sugg1 = new JLabel("www");
		sugg1.setForeground(Color.BLACK);
		sugg1.setFont(new Font("Georgia", Font.PLAIN, 16));

		JLabel sugg2= new JLabel("www");
		sugg2.setForeground(Color.RED);
		sugg2.setFont(new Font("Georgia", Font.PLAIN, 16));
		
		JLabel sugg3 = new JLabel("rghczdvxfbgzbnvmcnbvgfdsa");
		sugg3.setForeground(Color.BLACK);
		sugg3.setFont(new Font("Georgia", Font.PLAIN, 16));
		
		JLabel sugg4 = new JLabel("rghgfdsa");
		sugg4.setForeground(Color.RED);
		sugg4.setFont(new Font("Georgia", Font.PLAIN, 16));
		
		JLabel sugg5 = new JLabel("rghfmgnhgfgbcxnvxsa");
		sugg5.setForeground(Color.BLACK);
		sugg5.setFont(new Font("Georgia", Font.PLAIN, 16));
		
		JLabel sugg6 = new JLabel("rghgfdsa");
		sugg6.setForeground(Color.RED);
		sugg6.setFont(new Font("Georgia", Font.PLAIN, 16));
		
		JLabel sugg7 = new JLabel("rghgfdsdfghfgdsa");
		sugg7.setForeground(Color.BLACK);
		sugg7.setFont(new Font("Georgia", Font.PLAIN, 16));
		
		JLabel sugg8 = new JLabel("rghgfdsa");
		sugg8.setForeground(Color.RED);
		sugg8.setFont(new Font("Georgia", Font.PLAIN, 16));
		*/



		
		//TitledBorder a1=new TitledBorder(new EtchedBorder(), "Registrazione Utente");
		//a1.setTitleColor(Color.BLACK);
		//a1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		//pannelloRegistrazione.setBorder(a1);
		
		
		
		rigaCerca.add(cercaim);
		rigaCerca.add(cercaField);
		rigaCerca.add(cerca);
	
		/*pannelloRegistrazione.add(sugg1);
		pannelloRegistrazione.add(sugg2);
		pannelloRegistrazione.add(sugg3);
		pannelloRegistrazione.add(sugg4);
		pannelloRegistrazione.add(sugg5);
		pannelloRegistrazione.add(sugg6);
		pannelloRegistrazione.add(sugg7);
		pannelloRegistrazione.add(sugg8);
*/
		//rigaCerca.add(pannelloRegistrazione);
		
	
	
	
		
		
		
		JLabel tipo=new JLabel("  Nome Account : ");
		MyLabel tipoText=new MyLabel("");
		JLabel user=new JLabel("  Username : ");
		MyLabel userText=new MyLabel("");
		JLabel pass=new JLabel("  Password : ");
		MyLabel passText=new MyLabel("");
		JLabel alt=new JLabel("  Email Alternativa : ");
		MyLabel altText=new MyLabel("");
		JButton buttonRiga1=new JButton("Copia");
		JButton buttonRiga2=new JButton("Copia");
		JButton buttonRiga3=new JButton("Copia");
		JButton buttonRiga4=new JButton("Copia");

			
		
		class Copia1 implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				tipoText.select(0, tipoText.getText().length());
				System.out.println(tipoText.getSelectedText());
				JButton buttonRiga1 = new JButton(new DefaultEditorKit.CopyAction());
			    buttonRiga1.setText("Copia");
			    clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    textComponent = tipoText;
			    handler = textComponent.getTransferHandler();
			    handler.exportToClipboard(textComponent, clipBoard, TransferHandler.COPY);
			}
		}
		
		class Copia2 implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				userText.select(0, userText.getText().length());
				System.out.println(userText.getSelectedText());
				JButton buttonRiga1 = new JButton(new DefaultEditorKit.CopyAction());
			    buttonRiga1.setText("Copia");
			    clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    textComponent = userText;
			    handler = textComponent.getTransferHandler();
			    handler.exportToClipboard(textComponent, clipBoard, TransferHandler.COPY);
			}
		}
		
		class Copia3 implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				passText.select(0, passText.getText().length());
				System.out.println(passText.getSelectedText());
				JButton buttonRiga1 = new JButton(new DefaultEditorKit.CopyAction());
			    buttonRiga1.setText("Copia");
			    clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    textComponent = passText;
			    handler = textComponent.getTransferHandler();
			    handler.exportToClipboard(textComponent, clipBoard, TransferHandler.COPY);
			}
		}
		
		class Copia4 implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				altText.select(0, altText.getText().length());
				System.out.println(altText.getSelectedText());
				JButton buttonRiga1 = new JButton(new DefaultEditorKit.CopyAction());
			    buttonRiga1.setText("Copia");
			    clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    textComponent = altText;
			    handler = textComponent.getTransferHandler();
			    handler.exportToClipboard(textComponent, clipBoard, TransferHandler.COPY);
			}
		}
		buttonRiga1.addActionListener(new Copia1());
		buttonRiga2.addActionListener(new Copia2());
		buttonRiga3.addActionListener(new Copia3());
		buttonRiga4.addActionListener(new Copia4());
		
	    buttonRiga1.addFocusListener(new FocusListener() {
	    	
            @Override
            public void focusGained(FocusEvent e) {
            	System.out.println("ddd");
                //tipoText.select(0, tipoText.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                tipoText.select(0, 0);
            }
        });
        
		
		
		
        ClipboardPopupMenu cb4=  ClipboardPopupMenu.installForComponent(tipoText);
		cb4.addCopyFunction("Copia");
		cb4.addClearFunction("Chiudi");
		ClipboardPopupMenu cb1 = ClipboardPopupMenu.installForComponent(userText);
		cb1.addCopyFunction("Copia");
		cb1.addClearFunction("Chiudi");
		ClipboardPopupMenu cb2=  ClipboardPopupMenu.installForComponent(passText);
		cb2.addCopyFunction("Copia");
		cb2.addClearFunction("Chiudi");
		ClipboardPopupMenu cb11 = ClipboardPopupMenu.installForComponent(altText);
		cb11.addCopyFunction("Copia");
		cb11.addClearFunction("Chiudi");
	
		
		tipo.setFont(new Font("Georgia", Font.ITALIC, 18));
		tipo.setForeground(Color.BLACK);
		tipoText.setFont(new Font("Georgia", Font.ITALIC, 18));
		tipoText.setForeground(Color.RED);
		user.setFont(new Font("Georgia", Font.ITALIC, 18));
		user.setForeground(Color.BLACK);
		userText.setFont(new Font("Georgia", Font.ITALIC, 18));
		userText.setForeground(Color.RED);
		pass.setFont(new Font("Georgia", Font.ITALIC, 18));
		pass.setForeground(Color.BLACK);
		passText.setFont(new Font("Georgia", Font.ITALIC, 18));
		passText.setForeground(Color.RED);
		alt.setFont(new Font("Georgia", Font.ITALIC, 18));
		alt.setForeground(Color.BLACK);
		altText.setFont(new Font("Georgia", Font.ITALIC, 18));
		altText.setForeground(Color.RED);
		buttonRiga1.setFont(new Font("Georgia", Font.ITALIC, 19));
		buttonRiga1.setForeground(Color.BLACK);	
		buttonRiga1.setBounds(393, 15, 100, 100);
		buttonRiga2.setFont(new Font("Georgia", Font.ITALIC, 19));
		buttonRiga2.setForeground(Color.BLACK);	
		buttonRiga3.setFont(new Font("Georgia", Font.ITALIC, 19));
		buttonRiga3.setForeground(Color.BLACK);	
		buttonRiga4.setFont(new Font("Georgia", Font.ITALIC, 19));
		buttonRiga4.setForeground(Color.BLACK);	
		
		
		
		alt.setVisible(false);
		altText.setVisible(false);
		tipo.setVisible(false);
		tipoText.setVisible(false);
		user.setVisible(false);
		userText.setVisible(false);
		pass.setVisible(false);
		passText.setVisible(false);
		buttonRiga1.setVisible(false);
		buttonRiga2.setVisible(false);
		buttonRiga3.setVisible(false);
		buttonRiga4.setVisible(false);
	
		JPanel rigaStampa1=new JPanel();
		rigaStampa1.setLayout(new BorderLayout());
		
		JPanel rigaStampa2=new JPanel();
		rigaStampa2.setLayout(new BorderLayout());
		
		JPanel rigaStampa3=new JPanel();
		rigaStampa3.setLayout(new BorderLayout());
		
		JPanel rigaStampa4=new JPanel();
		rigaStampa4.setLayout(new BorderLayout());
		
		
		//JPanel pannelloRigaStampa1=new JPanel(); 
		//JPanel pannelloRigaStampa2=new JPanel(); 
		//JPanel pannelloRigaStampa3=new JPanel(); 
		//JPanel pannelloRigaStampa4=new JPanel(); 
		
		
		//pannelloRigaStampa1.add(tipo);
		//pannelloRigaStampa1.add(tipoText);

		
		//pannelloRigaStampa2.add(user);
		//pannelloRigaStampa2.add(userText);
		//pannelloRigaStampa2.add(buttonRiga2);
		
	//	pannelloRigaStampa3.add(pass);
		//pannelloRigaStampa3.add(passText);
		//pannelloRigaStampa3.add(buttonRiga3);
		
		//pannelloRigaStampa4.add(alt);
		//pannelloRigaStampa4.add(altText);
		//pannelloRigaStampa4.add(buttonRiga4);
		
		JPanel pannelloBottone1=new JPanel(); 
		JPanel pannelloBottone2=new JPanel(); 
		JPanel pannelloBottone3=new JPanel();		
		JPanel pannelloBottone4=new JPanel(); 		
		
		pannelloBottone1.add(buttonRiga1);
		pannelloBottone2.add(buttonRiga2);
		pannelloBottone3.add(buttonRiga3);
		pannelloBottone4.add(buttonRiga4);
		
		
		
		
		//rigaStampa1.add(pannelloRigaStampa1,BorderLayout.WEST);
		//rigaStampa1.add(pannelloBottone1,BorderLayout.EAST);
		//rigaStampa2.add(pannelloRigaStampa2,BorderLayout.WEST);
		//rigaStampa2.add(pannelloBottone2,BorderLayout.EAST);
		//rigaStampa3.add(pannelloRigaStampa3,BorderLayout.WEST);
		//rigaStampa3.add(pannelloBottone3,BorderLayout.EAST);
		//rigaStampa4.add(pannelloRigaStampa4,BorderLayout.WEST);
		//rigaStampa4.add(pannelloBottone4,BorderLayout.EAST);

		

		rigaStampa1.add(tipo,BorderLayout.WEST);
		rigaStampa1.add(tipoText,BorderLayout.CENTER);
		rigaStampa1.add(pannelloBottone1,BorderLayout.EAST);
		rigaStampa2.add(user,BorderLayout.WEST);
		rigaStampa2.add(userText,BorderLayout.CENTER);
		rigaStampa2.add(pannelloBottone2,BorderLayout.EAST);
		rigaStampa3.add(pass,BorderLayout.WEST);
		rigaStampa3.add(passText,BorderLayout.CENTER);
		rigaStampa3.add(pannelloBottone3,BorderLayout.EAST);
		rigaStampa4.add(alt,BorderLayout.WEST);
		rigaStampa4.add(altText,BorderLayout.CENTER);
		rigaStampa4.add(pannelloBottone4,BorderLayout.EAST);
	
	
		
		
		
		JButton buttonVisualizza=new JButton("Visualizza Account ");
		buttonVisualizza.setFont(new Font("Georgia", Font.ITALIC, 19));
		buttonVisualizza.setForeground(Color.BLACK);
		
		JButton aggiungiRegistrazi=new JButton(" Aggiungi Account  ");
		aggiungiRegistrazi.setFont(new Font("Georgia", Font.ITALIC, 19));
		aggiungiRegistrazi.setForeground(Color.BLACK);
		JButton rimuoviRegistrazi=new JButton(" Rimuovi Account  ");
		rimuoviRegistrazi.setFont(new Font("Georgia", Font.ITALIC, 19));
		rimuoviRegistrazi.setForeground(Color.BLACK);
		JButton modificRegistrazi=new JButton(" Modifica Account  ");
		modificRegistrazi.setFont(new Font("Georgia", Font.ITALIC, 19));
		modificRegistrazi.setForeground(Color.BLACK);
		
		JButton salva=new JButton(" Crea file sul Desktop ");
		salva.setFont(new Font("Georgia", Font.ITALIC, 19));
		salva.setForeground(Color.BLACK);
		
		JButton scarica=new JButton("      Crea Backup      ");
		scarica.setFont(new Font("Georgia", Font.ITALIC, 19));
		scarica.setForeground(Color.BLACK);
		
		JButton carica=new JButton(" Ripristina Backup ");
		carica.setFont(new Font("Georgia", Font.ITALIC, 19));
		carica.setForeground(Color.BLACK);
		
		JButton stampa=new JButton("        Stampa file         ");
		stampa.setFont(new Font("Georgia", Font.ITALIC, 19));
		stampa.setForeground(Color.BLACK);
		
		JCheckBox txt = new JCheckBox(".txt");
		JCheckBox doc=new JCheckBox(".doc");
		txt.setFont(new Font("Georgia", Font.ITALIC, 16));
		txt.setForeground(Color.BLACK);
		doc.setFont(new Font("Georgia", Font.ITALIC, 16));
		doc.setForeground(Color.BLACK);
		ButtonGroup group = new ButtonGroup();
		group.add(txt);
		group.add(doc);
		txt.setSelected(true);
		
		
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		JPanel b=new JPanel();
		b.setLayout(new BorderLayout());
		
	
		w1=new JPanel();
		w1.setLayout(new BorderLayout());
		JPanel w82=new JPanel();
		
		
		JPanel wd=new JPanel();
		wd.add(descrizione1);
		wd.add(descrizione2);
		
		JPanel wder=new JPanel();
		wder.setLayout(new BorderLayout());
		
		numero_account=sistema.getRegistrazioni().size();
		
		JPanel infoBasso=new JPanel();
		infoBasso.setLayout(new BorderLayout());
		
	
		
		JPanel numeroAcc=new JPanel();
		JLabel wdd=new JLabel("Account Inseriti: ");
		wdd.setFont(new Font("Georgia", Font.ITALIC, 13));
		wdd.setForeground(Color.BLACK);
		JLabel wdd1=new JLabel(""+numero_account);
		wdd1.setFont(new Font("Georgia", Font.ITALIC, 13));
		wdd1.setForeground(Color.RED);
		numeroAcc.add(wdd);
		numeroAcc.add(wdd1);
		
		JPanel aut=new JPanel();
		JLabel wdd44=new JLabel("Author: ");
		wdd44.setFont(new Font("Georgia", Font.ITALIC, 13));
		wdd44.setForeground(Color.BLACK);
		JLabel wdd11=new JLabel("Mamone Gianluigi");
		wdd11.setFont(new Font("Georgia", Font.ITALIC, 13));
		wdd11.setForeground(Color.RED);
		aut.add(wdd44);
		aut.add(wdd11);
		
		JPanel ver=new JPanel();
		JLabel wdd440=new JLabel("Account Manager - Versione 4.2 ");
		wdd440.setFont(new Font("Georgia", Font.ITALIC, 13));
		wdd440.setForeground(Color.BLACK);
		ver.add(wdd440);
		
		
		
		infoBasso.add(numeroAcc,BorderLayout.EAST);
		infoBasso.add(ver,BorderLayout.CENTER);
		infoBasso.add(aut,BorderLayout.WEST);
		
		
		//wder.add(wdd,BorderLayout.EAST);
		JPanel wd1=new JPanel();
		wd1.setLayout(new BorderLayout());
		wd1.add(wd,BorderLayout.CENTER);
		//wd1.add(wdd,BorderLayout.EAST);
		
		w1.add(wd1,BorderLayout.NORTH);
		//w1.add(pannelloRegistrazione,BorderLayout.SOUTH);
		w1.add(rigaCerca,BorderLayout.CENTER);
		w1.add(wder,BorderLayout.SOUTH);
		b.add(descrizione,BorderLayout.NORTH);
		b.add(w1,BorderLayout.CENTER);
		
		pannello.add(b,BorderLayout.NORTH);
		
		JPanel a45=new JPanel();
		a45.setLayout(new BorderLayout());
		
		
		a=new JPanel();
		
		a.setLayout(new GridLayout(7,1));
		
		a.add(label2);
		a.add(scegliSettore);
		a.add(scegliSettore1);
		a.add(rigaStampa1);
		a.add(rigaStampa2);
		a.add(rigaStampa3);
		a.add(rigaStampa4);
		
		//a45.add(pannelloRegistrazione,BorderLayout.NORTH);
		a45.add(a,BorderLayout.CENTER);
		
		JPanel pp=new JPanel();
		pp.add(salva);
		JPanel pp1=new JPanel();
		pp1.add(stampa);
		
	
		
		JPanel bottoni2=new JPanel();
		bottoni2.setLayout(new GridLayout(2,1));
		bottoni2.add(pp);
		bottoni2.add(pp1);
		
		
		JPanel bottoni3=new JPanel();
		bottoni3.add(txt);
		bottoni3.add(doc);
		
		JLabel dee=new JLabel();
		ImageIcon ii=new ImageIcon(getClass().getResource("/resource/tttt.png"));
		dee.setIcon(ii);
		JLabel deee=new JLabel();
		ImageIcon iii=new ImageIcon(getClass().getResource("/resource/wod.png"));
		deee.setIcon(iii);
		
		JPanel bottoni4=new JPanel();
		bottoni4.add(dee);
		bottoni4.add((deee));
		
		/*JPanel bottoni8=new JPanel();
		bottoni8.setLayout(new BorderLayout());
		bottoni8.add(bottoni3,BorderLayout.SOUTH);
		bottoni8.add(bottoni4,BorderLayout.CENTER);*/
		
		JPanel bottoni8=new JPanel();
		bottoni8.add(txt);
		bottoni8.add(dee);
		bottoni8.add(doc);
		bottoni8.add(deee);
		
		JPanel pp4=new JPanel();
		pp4.add(bottoni2);
		pp4.add(bottoni8);
		
		TitledBorder a4a=new TitledBorder(new EtchedBorder(), "Crea - Ripristina Backup");
		a4a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a4a.setTitleColor(Color.BLACK);
		

		JPanel bottoniback=new JPanel();
		bottoniback.setLayout(new GridLayout(2,1));
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		p1.add(carica);
		p2.add(scarica);
		bottoniback.add(p2);
		bottoniback.add(p1);
		
		JLabel dr=new JLabel();
		ImageIcon it=new ImageIcon(getClass().getResource("/resource/backup.png"));
		dr.setIcon(it);
		
		JPanel ff=new JPanel();
		ff.add(bottoniback);
		ff.add(dr);
		ff.setBorder(a4a);
		
		JPanel bottoni5=new JPanel();
		//bottoni5.setLayout(new GridLayout(2,1));
		//bottoni5.add(bottoni2);
		//bottoni5.add(bottoniback);
		bottoni5.setLayout(new BorderLayout());
		bottoni5.add(pp4,BorderLayout.CENTER);
		bottoni5.add(ff,BorderLayout.WEST);
	
		
		
		
		
		TitledBorder aa=new TitledBorder(new EtchedBorder(), "Crea - Salva");
		aa.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		aa.setTitleColor(Color.BLACK);
		pp4.setBorder(aa);
		TitledBorder a188=new TitledBorder(new EtchedBorder(), "Formato file");
		a188.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a188.setTitleColor(Color.BLACK);
		bottoni8.setBorder(a188);
		pannello.add(a45,BorderLayout.CENTER);

		pannello.add(bottoni5,BorderLayout.SOUTH);
		JPanel wder1=new JPanel();
		wder1.setLayout(new BorderLayout());
		wder1.add(pannello, BorderLayout.CENTER);
		wder1.add(infoBasso,BorderLayout.SOUTH);
		
		
		
		
		class StampaListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// verifico che la funzionalità Desktop sia supportata!
			    if (!java.awt.Desktop.isDesktopSupported())
			    {
			      System.out.println ("Funzionalità Desktop Non supportata!");
			      return;
			    }
			    File f=null;
			    try
			    {
					if(txt.isSelected())
						f=new File("C:\\AccountManager\\AccountManager.txt");
					else if(doc.isSelected())
						f=new File("C:\\AccountManager\\AccountManager.doc");
					
					
				    
				     // ottengo un'istanza del Desktop corrente
				    Desktop d = java.awt.Desktop.getDesktop();
				    PrintWriter pp;	
					pp = new PrintWriter(f);
				    pp.print("Utente -> "+"Nome : "+sistema.getUtenteCorrente().getNome());
				    pp.println(" - Cognome : "+sistema.getUtenteCorrente().getCognome());
					for(Registrazione r : sistema.getRegistrazioni()){
					    	
			            	String linea="-------------------------------------------------------------";
					    	pp.println(linea);
					    	String riga1="| Nome Account      : "+r.getNomeAccount();
					    	pp.print(riga1);
					    	for(int i=0; i<linea.length()-riga1.length()-1; i++)
					    		pp.print(" ");
					    	pp.println("|");
					    	
					    	String riga2="| Username          : "+r.getUsername();
					    	pp.print(riga2);
					    	for(int i=0; i<linea.length()-riga2.length()-1; i++)
					    		pp.print(" ");
					    	pp.println("|");
					    	String riga3="| Password          : "+r.getPassword();
					    	pp.print(riga3);
					    	for(int i=0; i<linea.length()-riga3.length()-1; i++)
					    		pp.print(" ");
					    	pp.println("|");
					    	String riga4="| Email Alternativa : "+r.getEmailAlternativa();
					    	pp.print(riga4);
					    	for(int i=0; i<linea.length()-riga4.length()-1; i++)
					    		pp.print(" ");
					    	pp.println("|");
					    	
					    	pp.println(linea);
					    }
					
				
				

				      d.print(f);
				      pp.close();
				      
				      if(txt.isSelected()){
				    	  setI(0);
				    	  tt = new Timer(60000, new CountDown());
				    	  tt.start();
				      }
				      if(doc.isSelected()){
				    	  setJ(0);
				    	  tt1 = new Timer(60000, new CountDown1());
				    	  tt1.start();
				      }
				      
				      
						
						
				   
			    
			    }
			   catch (NullPointerException npe)
			   {	
				  
				   System.out.println("creato");
			    }
			    catch (IllegalArgumentException iae)
			    {
			      System.out.println (" Il file specificato non esiste! ");
			    }
			    catch (UnsupportedOperationException uoe)
			    {
			      System.out.println (" La piattaforma non supporta l'azione di stampa! ");
			    }
			    catch (IOException ioe)
			    {
			      System.out.println (" Il file specificato non ha associato applicazione che supportano la stampa ");
			    }
			    catch (SecurityException se)
			    {
			      System.out.println (" Il security manager installato non consente l'accesso al file o alla stampante ");
			    }	
			    
			 
				
			    
					
			}
		
		}
			
		

		class SalvaListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				String U=System.getProperty("user.home") + "/Desktop"; 
				File f=null;
				if(txt.isSelected())
					f=new File(U,"AccountManager_"+sistema.getUtenteCorrente().getCognome()+"."+"txt");
				else if(doc.isSelected())
					f=new File(U,"AccountManager_"+sistema.getUtenteCorrente().getCognome()+"."+"doc");
				
			PrintWriter p;
				try {
					
					p = new PrintWriter(f);
					 p.print("Utente -> "+"Nome : "+sistema.getUtenteCorrente().getNome());
					    p.println(" - Cognome : "+sistema.getUtenteCorrente().getCognome());
				      
				    for(Registrazione r : sistema.getRegistrazioni()){
				    	
				    	String linea="-------------------------------------------------------------";
				    	p.println(linea);
				    	String riga1="| Nome Account      : "+r.getNomeAccount();
				    	p.print(riga1);
				    	for(int i=0; i<linea.length()-riga1.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	
				    	String riga2="| Username          : "+r.getUsername();
				    	p.print(riga2);
				    	for(int i=0; i<linea.length()-riga2.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	String riga3="| Password          : "+r.getPassword();
				    	p.print(riga3);
				    	for(int i=0; i<linea.length()-riga3.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	String riga4="| Email Alternativa : "+r.getEmailAlternativa();
				    	p.print(riga4);
				    	for(int i=0; i<linea.length()-riga4.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	
				    	p.println(linea);
				    }
					p.close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		class CaricaListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				a.setVisible(false);
				sistema.SvuotaParola();
				cercaField.setText("");
				pannelloRegistrazione.setVisible(false);
				setVisible(false);
				caricabackup=caricaBackup();
				caricabackup.setVisible(true);
				
		
			}
		}
		
		class ScaricaListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
					a.setVisible(false);
					sistema.SvuotaParola();
					cercaField.setText("");
					pannelloRegistrazione.setVisible(false);
					setVisible(false);
					scaricaBackup=scaricaBackup();
					scaricaBackup.setVisible(true);
				
		
			}
		}
		
		
		class AggiungiListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				a.setVisible(false);
				setVisible(false);
				sistema.SvuotaParola();
				cercaField.setText("");
				pannelloRegistrazione.setVisible(false);
				aggiungiRegistrazione=inserisciRegistrazione();
				aggiungiRegistrazione.setVisible(true);
			}
		}
		
		class RimuoviListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				a.setVisible(false);
				sistema.SvuotaParola();
				cercaField.setText("");
				pannelloRegistrazione.setVisible(false);
				try {
					rimuoviRegistrazione=rimuoviRegistrazione();
					rimuoviRegistrazione.setVisible(true);
				} catch (DatiNonValidiException e) {
					def=new DefaultFrame(new JLabel(e.getMessage()),
							"Errore",390,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
						def.setVisible(true);
				}
				setVisible(false);
			}
		}
		
		class VisualizzaListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				a.setVisible(false);
				
				sistema.SvuotaParola();
				cercaField.setText("");
				pannelloRegistrazione.setVisible(false);
				
					setVisible(false);
					try {
						visualizzaTutti=visualizza();
						visualizzaTutti.setVisible(true);
					} catch (DatiNonValidiException e) {
						def=new DefaultFrame(new JLabel(e.getMessage()),
								"Errore",390,
								new ImageIcon(imgURLnonOK),
								new UtenteFrame(sistema));
							def.setVisible(true);
					}

			
			}
		}
		
		class ModificaListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				a.setVisible(false);
				sistema.SvuotaParola();
				cercaField.setText("");
				pannelloRegistrazione.setVisible(false);
				try {
					modificaRegistrazione=modificaRegistrazione();
					modificaRegistrazione.setVisible(true);
				} catch (DatiNonValidiException e) {
					def=new DefaultFrame(new JLabel(e.getMessage()),
							"Errore",390,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
						def.setVisible(true);
				}
				setVisible(false);
			}
		}
		
		class CercaListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
				//sistema.SvuotaParola();
				user.setVisible(false);
				pass.setVisible(false);
				userText.setVisible(false);
				passText.setVisible(false);
				tipoText.setVisible(false);
				altText.setVisible(false);
				tipo.setVisible(false);
				alt.setVisible(false);
				buttonRiga1.setVisible(false);
				buttonRiga2.setVisible(false);
				buttonRiga3.setVisible(false);
				buttonRiga4.setVisible(false);
				
				try {
					val=true;
					r = sistema.cercaRegistrazione(cercaField.getText());
					a.setVisible(true);
					if(r.size()==1){
						user.setVisible(true);
						pass.setVisible(true);
						userText.setVisible(true);
						passText.setVisible(true);
						tipoText.setVisible(true);
						
					
						altText.setVisible(true);
						tipo.setVisible(true);
						alt.setVisible(true);
						buttonRiga1.setVisible(true);
						buttonRiga2.setVisible(true);
						buttonRiga3.setVisible(true);
						buttonRiga4.setVisible(true);
						userText.setText(r.get(0).getUsername());
						passText.setText(r.get(0).getPassword());
						altText.setText(r.get(0).getEmailAlternativa());
						tipoText.setText(r.get(0).getNomeAccount());
						
					
						
						sett1Button.setVisible(false);
						sett2Button.setVisible(false);
						sett3Button.setVisible(false);
						sett4Button.setVisible(false);
						label2.setVisible(false);
					}
					else{
						label2.setText("Selezione Username dell'Account "+r.get(0).getNomeAccount());
						if(r.size()==2){
							ButtonGroup group = new ButtonGroup();
							sett1Button.setText(r.get(0).getUsername());
							sett2Button.setText(r.get(1).getUsername());
							group.add(sett1Button);
							group.add(sett2Button);
							group.clearSelection(); // fa in modo che nessun radio button sia selezionato inizialmente 
							sett1Button.setVisible(true);
							sett2Button.setVisible(true);
							sett3Button.setVisible(false);
							sett4Button.setVisible(false);
							label2.setVisible(true);
						}
						if(r.size()==3){
							ButtonGroup group = new ButtonGroup();
							sett1Button.setText(r.get(0).getUsername());
							sett2Button.setText(r.get(1).getUsername());
							sett3Button.setText(r.get(2).getUsername());
							group.add(sett1Button);
							group.add(sett2Button);
							group.add(sett3Button);
							group.clearSelection(); // fa in modo che nessun radio button sia selezionato inizialmente 
							sett1Button.setVisible(true);
							sett2Button.setVisible(true);
							sett3Button.setVisible(true);
							sett4Button.setVisible(false);
							label2.setVisible(true);
						}
						if(r.size()==4){
							ButtonGroup group = new ButtonGroup();
							sett1Button.setText(r.get(0).getUsername());
							sett2Button.setText(r.get(1).getUsername());
							sett3Button.setText(r.get(2).getUsername());
							sett4Button.setText(r.get(3).getUsername());
							group.add(sett1Button);
							group.add(sett2Button);
							group.add(sett3Button);
							group.add(sett4Button);
							group.clearSelection(); // fa in modo che nessun radio button sia selezionato inizialmente 
							sett1Button.setVisible(true);
							sett2Button.setVisible(true);
							sett3Button.setVisible(true);
							sett4Button.setVisible(true);
							label2.setVisible(true);
						}
							
					}
					
				} catch (DatiNonValidiException e1) {
					setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",390,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
				
				
			}
		}
		
		
		class AccountButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
			
				if(sett1Button.isSelected()){
					user.setVisible(true);
					pass.setVisible(true);
					userText.setVisible(true);
					passText.setVisible(true);
					tipoText.setVisible(true);
					altText.setVisible(true);
					tipo.setVisible(true);
					alt.setVisible(true);
					buttonRiga1.setVisible(true);
					buttonRiga2.setVisible(true);
					buttonRiga3.setVisible(true);
					buttonRiga4.setVisible(true);
					userText.setText(r.get(0).getUsername());
					passText.setText(r.get(0).getPassword());
					altText.setText(r.get(0).getEmailAlternativa());
					tipoText.setText(r.get(0).getNomeAccount());
					
				}
				else if(sett2Button.isSelected()){
					user.setVisible(true);
					pass.setVisible(true);
					userText.setVisible(true);
					passText.setVisible(true);
					tipoText.setVisible(true);
					altText.setVisible(true);
					tipo.setVisible(true);
					alt.setVisible(true);
					buttonRiga1.setVisible(true);
					buttonRiga2.setVisible(true);
					buttonRiga3.setVisible(true);
					buttonRiga4.setVisible(true);
	
					userText.setText(r.get(1).getUsername());
					passText.setText(r.get(1).getPassword());
					altText.setText(r.get(1).getEmailAlternativa());
					tipoText.setText(r.get(1).getNomeAccount());

					
					
				}
				else if (sett3Button.isSelected()){
					user.setVisible(true);
					pass.setVisible(true);
					userText.setVisible(true);
					passText.setVisible(true);
					tipoText.setVisible(true);
					altText.setVisible(true);
					tipo.setVisible(true);
					alt.setVisible(true);
					buttonRiga1.setVisible(true);
					buttonRiga2.setVisible(true);
					buttonRiga3.setVisible(true);
					buttonRiga4.setVisible(true);
					userText.setText(r.get(2).getUsername());
					passText.setText(r.get(2).getPassword());
					altText.setText(r.get(2).getEmailAlternativa());
					tipoText.setText(r.get(2).getNomeAccount());
				}
				else if(sett4Button.isSelected()){
					user.setVisible(true);
					pass.setVisible(true);
					userText.setVisible(true);
					passText.setVisible(true);
					tipoText.setVisible(true);
					altText.setVisible(true);
					tipo.setVisible(true);
					alt.setVisible(true);
					buttonRiga1.setVisible(true);
					buttonRiga2.setVisible(true);
					buttonRiga3.setVisible(true);
					buttonRiga4.setVisible(true);
					userText.setText(r.get(3).getUsername());
					passText.setText(r.get(3).getPassword());
					altText.setText(r.get(3).getEmailAlternativa());
					tipoText.setText(r.get(3).getNomeAccount());
				}
				else{
					user.setVisible(false);
					pass.setVisible(false);
					userText.setVisible(false);
					passText.setVisible(false);
					tipo.setVisible(false);
					alt.setVisible(false);
					tipoText.setVisible(false);
					altText.setVisible(false);
					buttonRiga1.setVisible(false);
					buttonRiga2.setVisible(false);
					buttonRiga3.setVisible(false);
					buttonRiga4.setVisible(false);
				}
					
			}
		}
		
		JPanel tutto = new JPanel();
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));
		tutto.setLayout(new BorderLayout());
		
		
		JPanel sinistra=new JPanel();
		
		TitledBorder a9=new TitledBorder(new EtchedBorder(), " Gestione ");
		a9.setTitlePosition(TitledBorder.ABOVE_TOP);
		a9.setTitleJustification(TitledBorder.CENTER);
		a9.setTitlePosition(1);
		a9.setTitleFont(new Font("Georgia", Font.ITALIC, 18));
		a9.setTitleColor(Color.BLUE);
		sinistra.setLayout(new GridLayout(4,1));
		sinistra.setBorder(a9);
		JPanel w=new JPanel();
		JPanel w2=new JPanel();
		JPanel w3=new JPanel();
		JPanel w4=new JPanel();
		w.add(aggiungiRegistrazi);
		w2.add(modificRegistrazi);
		w3.add(rimuoviRegistrazi);
		sinistra.add(w);
		sinistra.add(w2);
		sinistra.add(w3);
		sinistra.add(w4);
		
		
		w4.add(buttonVisualizza);
	
		pannello.add(sinistra,BorderLayout.WEST);


		JButton eeee=new JButton();
		eeee.setBorder(null);
		eeee.setContentAreaFilled(false);
		ImageIcon er=new ImageIcon(getClass().getResource("/resource/x.png"));
		eeee.setIcon(er);
		JButton dee1=new JButton();
		dee1.setBorder(null);
		dee1.setContentAreaFilled(false);
		ImageIcon ii1=new ImageIcon(getClass().getResource("/resource/_.png"));
		dee1.setIcon(ii1);
		
		JPanel cercaeee=new JPanel();
		cercaeee.setLayout(new BorderLayout());

		JLabel eeee11=new JLabel("NUOVA VERSIONE DISPOBIBILE");
		eeee11.setFont(new Font("Georgia", Font.BOLD, 20));
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
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					System.out.println("errore");
					e1.printStackTrace();
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				eeee11.setFont(new Font("Georgia", Font.BOLD, 22));
				eeee11.setForeground(Color.BLUE);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				eeee11.setFont(new Font("Georgia", Font.BOLD, 20));
				eeee11.setForeground(Color.BLACK);
			}
		}
		

		eeee11.addMouseListener(new aggiornaOraListener());

		cercaeee.add(menu,BorderLayout.WEST);
		//cerca.setBorder(BorderFactory.createLineBorder(Color.black,1));	
		cercaeee.add(notifica,BorderLayout.CENTER);

		
		JPanel drf=new JPanel();
		drf.setLayout(new BorderLayout());
		drf.add(eeee,BorderLayout.EAST);
		drf.add(dee1,BorderLayout.CENTER);
		cercaeee.add(drf,BorderLayout.EAST);
		
		

		if(sistema.getAggiornamento())
			notifica.setVisible(true);
		else
			notifica.setVisible(false);

		
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Utente : "+sistema.getUtenteCorrente().isCambiamentoU());
				dispose();
				if(sistema.getUtenteCorrente().isCambiamentoU()){
					conferma=conferma();
					conferma.setVisible(true);
				}
				else{
					System.out.println();
					System.exit(0);
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
		
		
		
		
		dee1.addActionListener(new RiduciListener());
		
		
		eeee.addActionListener(new ExitListener());
		
		tutto.add(cercaeee,BorderLayout.NORTH);
		
		tutto.add(wder1,BorderLayout.CENTER);
		add(tutto);
		buttonVisualizza.addActionListener(new VisualizzaListener());
		salva.addActionListener(new SalvaListener());
		carica.addActionListener(new CaricaListener());
		scarica.addActionListener(new ScaricaListener());
		sett1Button.addActionListener(new AccountButtonListener());
		sett2Button.addActionListener(new AccountButtonListener());
		sett3Button.addActionListener(new AccountButtonListener());
		sett4Button.addActionListener(new AccountButtonListener());
		cerca.addActionListener(new CercaListener());
		rimuoviRegistrazi.addActionListener(new RimuoviListener());
		aggiungiRegistrazi.addActionListener(new AggiungiListener());
		stampa.addActionListener(new StampaListener());
		modificRegistrazi.addActionListener(new ModificaListener());
	}
	
	private JMenuBar creaMenuBarIniziale () {
		JMenuBar barra = new JMenuBar();
		JMenu file;
		JMenu utente;
		JMenu informazioni;
		
		

		file = new JMenu("File");
		file.setFont(new Font("Georgia", Font.ITALIC, 13));
		file.setForeground(Color.BLACK);

		utente = new JMenu("Utente");
		utente.setFont(new Font("Georgia", Font.ITALIC, 13));
		utente.setForeground(Color.BLACK);
		
		informazioni = new JMenu("Informazioni");
		informazioni.setFont(new Font("Georgia", Font.ITALIC, 13));
		informazioni.setForeground(Color.BLACK);
		
		JMenuItem modifica = new JMenuItem("Modifica informazioni"); // item del menu File
		modifica.setForeground(Color.BLACK);
		modifica.setFont(new Font("Georgia", Font.ITALIC, 13));
		
		JMenuItem elimina = new JMenuItem("Elimina"); // item del menu File
		elimina.setForeground(Color.BLACK);
		elimina.setFont(new Font("Georgia", Font.ITALIC, 13));
		
		JMenuItem exit = new JMenuItem("Esci"); // item del menu File
		exit.setForeground(Color.BLACK);
		exit.setFont(new Font("Georgia", Font.ITALIC, 13));
		
		JMenuItem salva = new JMenuItem("Salva"); // item del menu File
		salva.setForeground(Color.BLACK);
		salva.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem logOut = new JMenuItem("LogOut"); // item del menu File
		logOut.setFont(new Font("Georgia", Font.ITALIC, 13));
		logOut.setForeground(Color.BLACK);
		file.add(logOut);
		file.add(salva);
		file.add(exit);
		
		utente.add(modifica);
		utente.add(elimina);
		
		JMenuItem aggiorna = new JMenuItem("Aggiorna AM"); // item del menu File
		aggiorna.setForeground(Color.BLACK);
		aggiorna.setFont(new Font("Georgia", Font.ITALIC, 13));
		
		informazioni.add(aggiorna);
		
		barra.add(file);
		barra.add(utente);
		barra.add(informazioni);
		
				
	
		class SalvaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				sistema.SvuotaParola();
				FileOutputStream out;
				ObjectOutputStream outStream;
				sistema.getUtenteCorrente().setR(sistema.getRegistrazioni());
				
				try {
//					out = new FileOutputStream(urlFile.getFile());
					out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
					
					outStream = new ObjectOutputStream(out);
					sistema.getUtenteCorrente().setCambiamentoU(false);
					
					outStream.writeObject(sistema); 
					out.close();
					outStream.close();
					
					setVisible(false);
					def=new DefaultFrame(new JLabel("Salvataggio Effettuato"),
							"Salvato",390,
							new ImageIcon(imgURLOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				
				
				} 
				catch (IOException e1) {}
				
				
			}
			
		}
		
	
		salva.addActionListener(new SalvaListener());
		
		
		class EliminaListener implements ActionListener {	
			public void actionPerformed(ActionEvent e) {
				String cartellaUtente = System.getProperty("user.home");
				String nomeFile = cartellaUtente + "\\Desktop"; 
				File f=null;
				f=new File(nomeFile,"AccountManager_"+sistema.getUtenteCorrente().getCognome()+"."+"txt");
				
				PrintWriter p;
				try 
				{
					p = new PrintWriter(f);
					for(Registrazione r : sistema.getRegistrazioni()){
						String linea="-------------------------------------------------------------";
						p.println(linea);
						String riga1="| Nome Account      : "+r.getNomeAccount();
						p.print(riga1);
				    	for(int i=0; i<linea.length()-riga1.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	
				    	String riga2="| Username          : "+r.getUsername();
				    	p.print(riga2);
				    	for(int i=0; i<linea.length()-riga2.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	String riga3="| Password          : "+r.getPassword();
				    	p.print(riga3);
				    	for(int i=0; i<linea.length()-riga3.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	String riga4="| Email Alternativa : "+r.getEmailAlternativa();
				    	p.print(riga4);
				    	for(int i=0; i<linea.length()-riga4.length()-1; i++)
				    		p.print(" ");
				    	p.println("|");
				    	
				    	p.println(linea);
				    }
					p.close();
				}
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				sistema.rimuoviUtente();
				dispose();   
				
				
				sistema.SvuotaParola();
				FileOutputStream out;
				ObjectOutputStream outStream;
				
				
				try {
//					out = new FileOutputStream(urlFile.getFile());
					out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
					
					outStream = new ObjectOutputStream(out);
					
					sistema.getUtenteCorrente().setCambiamentoU(false);
				
					
					outStream.writeObject(sistema); 
					out.close();
					outStream.close();
				
				
				
				} 
				catch (IOException e1) {}
				
				
				
				
				
				def=new DefaultFrame(new JLabel("L'utente è stato eliminato - Creato File sul Desktop con tutti gli account"),
						"Utente eliminato",650,new ImageIcon(imgURLOK),
					 	new FrameIniziale(sistema));
				def.setVisible(true);
				
				
			}
		}
		
		elimina.addActionListener(new EliminaListener());
		
		class ModificaListener implements ActionListener {	
			public void actionPerformed(ActionEvent e) {
					a.setVisible(false);
					cercaField.setText("");
					sistema.SvuotaParola();
					pannelloRegistrazione.setVisible(false);
					modiUtente=modiUtente();
					modiUtente.setVisible(true);
					dispose();
			}
		}
		
		class LogOutListener implements ActionListener {	
			public void actionPerformed(ActionEvent e) {
			
				
				if(sistema.getUtenteCorrente().isCambiamentoU()){
					dispose();
					sistema.SvuotaParola();
					confermaL=confermaL();
					confermaL.setVisible(true);
				}
				else{
					
					String utente =sistema.getUtenteCorrente().getNome();
					sistema.logout();
					dispose();
					def=new DefaultFrame(new JLabel("Arrivederci "+utente+", a presto "),
						"LogOut",390,
						new ImageIcon(imgURLOK),
						new FrameIniziale(sistema));
					def.setVisible(true);
				}
	
	
				
				
				
				
			}
			
		}
		modifica.addActionListener(new ModificaListener());
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if( sistema.getUtenteCorrente().isCambiamentoU() ){

					System.out.println("utente :"+sistema.getUtenteCorrente().isCambiamentoU());
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
						sistema.getUtenteCorrente().setCambiamentoU(false);
					
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
		
		
		
		exit.addActionListener(new ExitListener());
		logOut.addActionListener(new LogOutListener());
		
		class AggiornaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				a.setVisible(false);
				cercaField.setText("");
				sistema.SvuotaParola();
				pannelloRegistrazione.setVisible(false);
				
				
				HttpURLConnection.setFollowRedirects(false);
				HttpURLConnection con = null;
				try {
					con = (HttpURLConnection) new
					URL("https://mamonegianluigi.000webhostapp.com/paginaAggiornamento.php").openConnection();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con.setRequestMethod("HEAD");
				} catch (ProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					System.out.println(con.getResponseMessage());
					if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
						System.out.println("Sono presenti aggiornamenti");
						aggiornaFrame=aggiornamenti();
						aggiornaFrame.setVisible(true);
					}
					else{
						dispose();
						def=new DefaultFrame(new JLabel("Non sono presenti Aggiornamenti"),
								"Upadater",370,new ImageIcon(imgURLnonOK),
							 	new UtenteFrame(sistema)); 
						def.setVisible(true);
						System.out.println("Non sono presenti aggiornamenti");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
	
			
			}
		
		}
		aggiorna.addActionListener(new AggiornaListener());
		
		return barra;
	}
	

	private JFrame inserisciRegistrazione() {
		final JFrame inserisciRegistra=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciRegistra.setLocation(new Point((dimension.width - 
				inserisciRegistra.getSize().width) / 2-250, 
		(dimension.height - inserisciRegistra.getSize().height) / 2 -135));
		
		inserisciRegistra.setSize(500,270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//inserisciRegistra.setResizable(false);
		inserisciRegistra.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		inserisciRegistra.setUndecorated(true);
		

		
		inserisciRegistra.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		inserisciRegistra.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = inserisciRegistra.getLocation();
		         
		                inserisciRegistra.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
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
		
				int state = inserisciRegistra.getExtendedState();
				 
				state = Frame.ICONIFIED;
				inserisciRegistra.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());

		
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		inserisciRegistra.setIconImage(ii8.getImage());
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		pannello.add(drf1,BorderLayout.NORTH); // inseriamo a nord del pannello la label "inserisci i dati richiesti" (spostata al centro)
		
		JPanel dati = new JPanel(); // creiamo un panel dati in cui inseriremo le label e i text field riguardanti le informazione della nuova partita da inserire
		JLabel squadraCasa = new JLabel("Tipologia Account*");
		JLabel squadraOspite = new JLabel("Username*");
		JLabel campionato= new JLabel("Password*");
		JLabel lega = new JLabel("Email Alternativa");
		final JTextField squadraCasaField = new JTextField(8);
		final JTextField squadraOspiteField = new JTextField(8);
		final JTextField campionatoField = new JTextField(8);
		final JTextField legaField = new JTextField(8);
		squadraCasaField.setToolTipText("Nome Account");
		squadraOspiteField.setToolTipText("Username Account");
		campionatoField.setToolTipText("Password Account");
		legaField.setToolTipText("Email Alternativa Account");
		squadraCasa.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraOspite.setFont(new Font("Georgia", Font.PLAIN, 18));
		campionato.setFont(new Font("Georgia", Font.PLAIN, 18));
		lega.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraCasaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraOspiteField.setFont(new Font("Georgia", Font.PLAIN, 18));
		campionatoField.setFont(new Font("Georgia", Font.PLAIN, 18));
		legaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraCasa.setHorizontalAlignment(JLabel.CENTER);
		squadraOspite.setHorizontalAlignment(JLabel.CENTER);
		campionato.setHorizontalAlignment(JLabel.CENTER);
		lega.setHorizontalAlignment(JLabel.CENTER);
		squadraCasaField.setHorizontalAlignment(JLabel.CENTER);
		squadraOspiteField.setHorizontalAlignment(JLabel.CENTER);
		campionatoField.setHorizontalAlignment(JLabel.CENTER);
		legaField.setHorizontalAlignment(JLabel.CENTER);
		squadraCasa.setForeground(Color.BLACK);
		squadraOspite.setForeground(Color.BLACK);
		campionato.setForeground(Color.BLACK);
		lega.setForeground(Color.BLACK);
		squadraCasaField.setForeground(Color.BLACK);
		squadraOspiteField.setForeground(Color.BLACK);
		campionatoField.setForeground(Color.BLACK);
		legaField.setForeground(Color.BLACK);
		dati.setLayout(new GridLayout(4, 1)); // settiamo il pannello dati come layout a griglia
		// inseriamo le lebel e i textfield corrispondenti nel pannello dati
		dati.add(squadraCasa);
		//dati.add(squadraCasaField);
		dati.add(squadraOspite);
		//dati.add(squadraOspiteField);
		dati.add(campionato);
		//dati.add(campionatoField);
		dati.add(lega);
		//dati.add(legaField);
		
		JPanel dati1=new JPanel();
		dati1.setLayout(new GridLayout(4,1));
		dati1.add(squadraCasaField);
		dati1.add(squadraOspiteField);
		dati1.add(campionatoField);
		dati1.add(legaField);
		
		JLabel l=new JLabel("*campi obbligatori  ");
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
		
		JPanel bottoni= new JPanel(); // criamo un panel bottoni dove inseriremo i button 
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		// inseriamo i button Annulla e Conferma nel panel bottoni
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		
		JPanel inserisciPar=new JPanel();
		inserisciPar.setLayout(new BorderLayout()); // creiamo un panel inserisciPar e lo settiamo come layout a bordi
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Inserisci Account");
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		inserisciPar.setBorder(a);
		//inseriamo nel panel inserisciPar a nord la label "campi obbligatori" (spostata a destra), al centro il panel dati, a sud il panel bottoni
		inserisciPar.add(l,BorderLayout.NORTH);
		inserisciPar.add(dati1,BorderLayout.CENTER);
		inserisciPar.add(dati,BorderLayout.WEST);
		inserisciPar.add(bottoni,BorderLayout.SOUTH);
		//Aggiungiamo al centro del panel pannello, il panel inserisciPar 
		pannello.add(inserisciPar, BorderLayout.CENTER);
		//Aggiungiamo al centro del frame il panel pannello
		
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciRegistra.dispose();
				
				setVisible(true);
			}
		}
	
		class ConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				try 
				{	
					sistema.aggiungiRegistrazione(squadraCasaField.getText(),
							squadraOspiteField.getText(),campionatoField.getText(),
							legaField.getText());
					inserisciRegistra.dispose();
					def=new DefaultFrame(new JLabel("L'account è stato inserito correttamente"),
							"Inserimento Riuscito",390,
							new ImageIcon(imgURLOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{
					inserisciRegistra.setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",540,
							new ImageIcon(imgURLnonOK),
							inserisciRegistra);
					def.setVisible(true);
				}
			}
		}
		
		buttonConferma.addActionListener(new ConfermaListener());
		buttonAnnulla.addActionListener(new AnnullaListener());
		
		JPanel tutto=new JPanel();
		tutto.setLayout(new BorderLayout());

		
		
		
		tutto.add(pannello,BorderLayout.CENTER);
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));		
		inserisciRegistra.add(tutto);
		return inserisciRegistra;
	}
	
	
	public JFrame rimuoviRegistrazione() throws DatiNonValidiException{
		
		if(sistema.getRegistrazioni().size()==0)
			throw new DatiNonValidiException("Non sono presenti account");
		else{
			final JFrame rimuoviAcc=new JFrame(); // il frame viene creato solo se sono presenti politiche di sconto nel sistema		
				
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			rimuoviAcc.setLocation(new Point((dimension.width - 
				rimuoviAcc.getSize().width) / 2-350, 
				(dimension.height - rimuoviAcc.getSize().height) / 2 -210));
			rimuoviAcc.setSize(700,420);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//rimuoviAcc.setResizable(false);
			rimuoviAcc.setLayout(new BorderLayout());
			rimuoviAcc.setUndecorated(true);
			

			
			rimuoviAcc.addMouseListener(new MouseAdapter() {
			            public void mousePressed(MouseEvent e) {
			                point.x = e.getX();
			                point.y = e.getY();
			            }
			        });
			rimuoviAcc.addMouseMotionListener(new MouseMotionAdapter() {
			            public void mouseDragged(MouseEvent e) {
			                Point p = rimuoviAcc.getLocation();
			         
			                rimuoviAcc.setLocation(p.x + e.getX() - point.x,
			                        p.y + e.getY() - point.y);
			            }
			        });
					
				
			ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
			rimuoviAcc.setIconImage(ii8.getImage());
			
			JLabel scegli =new JLabel("Seleziona l'account da eliminare");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
				
			
			JLabel scegli2 =new JLabel("Tipo Account - Username - Password - Email Alternativa");
			scegli2.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli2.setHorizontalAlignment(JLabel.CENTER);
			scegli2.setForeground(Color.RED);
			JPanel nord=new JPanel();
			nord.setLayout(new GridLayout(2,1));
			nord.add(scegli);
			nord.add(scegli2);
			final JList<String> listaAccount;
			final DefaultListModel<String> listModel;
			listModel = new DefaultListModel<>();
			listaAccount = new JList<String>(listModel);
			listaAccount.setLayout(null);	
				
				
			JScrollPane scrollPane = new JScrollPane(listaAccount,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				
				
					
			JPanel pannello=new JPanel();
			pannello.setLayout(new BorderLayout());
			pannello.add(nord,BorderLayout.NORTH);
			pannello.add(scrollPane,BorderLayout.CENTER);
				
			JPanel tutto=new JPanel();
			tutto.setLayout(new BorderLayout());
			tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));		
			tutto.add(pannello,BorderLayout.CENTER);
			
			
			TitledBorder a5=new TitledBorder(new EtchedBorder(),"Rimuovi account");
			a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a5.setTitleColor(Color.BLACK);
			pannello.setBorder(a5);
				
			listModel.removeAllElements();
			listaAccount.setFont(new Font("Georgia", Font.PLAIN, 16));
			//listaAccount.setForeground(Color.BLACK);
			int i=0;
			for(Registrazione re: sistema.getRegistrazioni()){	
				i=i+1;
				String a= i+") "+re.getNomeAccount()+" - "+
						re.getUsername()+" - "+re.getPassword()+ " - " + re.getEmailAlternativa();
					listModel.addElement(a);
			}
				
			JPanel bottoni= new JPanel();
			final JButton buttonConferma=new JButton("Conferma");
			buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonConferma.setForeground(Color.BLACK);
			JButton buttonIndietro=new JButton("Indietro");
			buttonIndietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonIndietro.setForeground(Color.BLACK);
			bottoni.add(buttonIndietro);
			bottoni.add(buttonConferma);	
			pannello.add(bottoni, BorderLayout.SOUTH);
			
			if(sistema.getRegistrazioni().size()==0)
				buttonConferma.setVisible(false);
			else
				buttonConferma.setVisible(true);
			

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
			
					int state = rimuoviAcc.getExtendedState();
					 
					state = Frame.ICONIFIED;
					rimuoviAcc.setExtendedState(state);
				}
			}
			
		//	dee.addActionListener(new ExitListener());
			dee1.addActionListener(new RiduciListener());
			
			
			class ConfermaListener implements ActionListener{
				public void actionPerformed(ActionEvent e){
					try{
						if (listModel.size() > 0) { 
							int index = listaAccount.getSelectedIndex();
							sistema.rimuoviRegistrazione(index);
							numero_account=sistema.getRegistrazioni().size();
					
							listModel.removeElementAt(index);
							if(listModel.size()==0)
								buttonConferma.setVisible(false);
						}
					}
					catch(ArrayIndexOutOfBoundsException e1){
						rimuoviAcc.setVisible(false);
						def=new DefaultFrame(new JLabel("Selezionare un account da eliminare"),
							"Errore",450,new ImageIcon(imgURLnonOK),
							rimuoviAcc);
						def.setVisible(true);
					}
				}
			}
			
			JLabel label =new JLabel("Rimozione Account");
			label.setFont(new Font("Georgia", Font.PLAIN, 18));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(Color.BLACK);
			
			JPanel drf1=new JPanel();
			drf1.setLayout(new BorderLayout());
			drf1.add(label,BorderLayout.CENTER);
			drf1.add(drf,BorderLayout.EAST);
			
			tutto.add(drf1,BorderLayout.NORTH); //
			
			/**
			 * Il listene per il il pulsante Indietro.
			 * Se l'amministratore non vuole confermare di rimuovere una politica, selezionando il pulsante Indietro, 
			 * la finestra riguardante la la rimozione della politica di sconto si chiude 
			 * ritornando alla schermate iniziale dell'amministratore.
			 *
			 */
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					rimuoviAcc.dispose();
					new UtenteFrame(sistema).setVisible(true);	
				}
			}
				
			buttonConferma.addActionListener(new ConfermaListener());
			buttonIndietro.addActionListener(new IndietroListener());
			rimuoviAcc.add(tutto);
			return rimuoviAcc;
		}
	}
	
	
	public JFrame modificaRegistrazione() throws DatiNonValidiException{
		
		if(sistema.getRegistrazioni().size()==0)
			throw new DatiNonValidiException("Non sono presenti account");
		else{
			final JFrame modificAcc=new JFrame(); // il frame viene creato solo se sono presenti politiche di sconto nel sistema		
				
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			modificAcc.setLocation(new Point((dimension.width - 
				modificAcc.getSize().width) / 2-350, 
				(dimension.height - modificAcc.getSize().height) / 2 -210));
			modificAcc.setSize(700,420);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//modificAcc.setResizable(false);
			modificAcc.setLayout(new BorderLayout());
			modificAcc.setUndecorated(true);
			

			
			modificAcc.addMouseListener(new MouseAdapter() {
			            public void mousePressed(MouseEvent e) {
			                point.x = e.getX();
			                point.y = e.getY();
			            }
			        });
			modificAcc.addMouseMotionListener(new MouseMotionAdapter() {
			            public void mouseDragged(MouseEvent e) {
			                Point p = modificAcc.getLocation();
			         
			              modificAcc.setLocation(p.x + e.getX() - point.x,
			                        p.y + e.getY() - point.y);
			            }
			        });
					
			
			ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
			modificAcc.setIconImage(ii8.getImage());
			JLabel scegli =new JLabel("Seleziona l'account da modificare");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
			
			JLabel scegli2 =new JLabel("Tipo Account - Username - Password - Email Alternativa");
			scegli2.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli2.setHorizontalAlignment(JLabel.CENTER);
			scegli2.setForeground(Color.RED);
				
			JList<String> listaAccount;
			DefaultListModel<String> listModel;
			listModel = new DefaultListModel<>();
			listaAccount = new JList<String>(listModel);
			listaAccount.setLayout(null);	
				
				
			JScrollPane scrollPane = new JScrollPane(listaAccount,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				
				
					
			JPanel pannello=new JPanel();
			pannello.setLayout(new BorderLayout());
			JPanel nord=new JPanel();
			nord.setLayout(new GridLayout(2,1));
			nord.add(scegli);
			nord.add(scegli2);
			pannello.add(nord,BorderLayout.NORTH);
			pannello.add(scrollPane,BorderLayout.CENTER);
				
			
			TitledBorder a5=new TitledBorder(new EtchedBorder(),"Modifica account");
			a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a5.setTitleColor(Color.BLACK);
			pannello.setBorder(a5);
				
			listModel.removeAllElements();
			listaAccount.setFont(new Font("Georgia", Font.PLAIN, 16));
			//listaAccount.setForeground(Color.R);
			int i=0;
			for(Registrazione re: sistema.getRegistrazioni()){
				i=i+1;
					String a= i+") "+re.getNomeAccount()+" - "+
					re.getUsername()+" - "+re.getPassword()+ " - " + re.getEmailAlternativa();
				
					
					listModel.addElement(a);
			}
				
			JPanel bottoni= new JPanel();
			final JButton buttonConferma=new JButton("Conferma");
			buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonConferma.setForeground(Color.BLACK);
			JButton buttonIndietro=new JButton("Indietro");
			buttonIndietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonIndietro.setForeground(Color.BLACK);
			bottoni.add(buttonIndietro);
			bottoni.add(buttonConferma);	
			pannello.add(bottoni, BorderLayout.SOUTH);
			
			if(sistema.getRegistrazioni().size()==0)
				buttonConferma.setVisible(false);
			else
				buttonConferma.setVisible(true);
			
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
			
					int state = modificAcc.getExtendedState();
					 
					state = Frame.ICONIFIED;
					modificAcc.setExtendedState(state);
				}
			}
			
		//	dee.addActionListener(new ExitListener());
			dee1.addActionListener(new RiduciListener());
			
		
			
		//	pannello.add(label,BorderLayout.NORTH);
			class ConfermaListener implements ActionListener{
				public void actionPerformed(ActionEvent e){
					try{
						if (listModel.size() > 0) { 
							modificaRegistrazione.setVisible(false);
							
							modiRegistrazione=modiRegistrazione(listaAccount.getSelectedIndex());
							modiRegistrazione.setVisible(true);
						}
					}
					catch(ArrayIndexOutOfBoundsException e1){
						modificAcc.setVisible(false);
						def=new DefaultFrame(new JLabel("Selezionare un account da modificare"),
							"Errore",450,new ImageIcon(imgURLnonOK),
							modificAcc);
						def.setVisible(true);
					}
				}
			}
			
			/**
			 * Il listene per il il pulsante Indietro.
			 * Se l'amministratore non vuole confermare di rimuovere una politica, selezionando il pulsante Indietro, 
			 * la finestra riguardante la la rimozione della politica di sconto si chiude 
			 * ritornando alla schermate iniziale dell'amministratore.
			 *
			 */
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					modificAcc.dispose();
					setVisible(true);		
				}
			}
			JPanel tutto = new JPanel();
			tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));		
			tutto.setLayout(new BorderLayout());
			
			JLabel label =new JLabel("Modifica Account");
			label.setFont(new Font("Georgia", Font.PLAIN, 18));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(Color.BLACK);
			
			JPanel drf1=new JPanel();
			drf1.setLayout(new BorderLayout());
			drf1.add(label,BorderLayout.CENTER);
			drf1.add(drf,BorderLayout.EAST);
			
			tutto.add(drf1,BorderLayout.NORTH); //
			tutto.add(pannello,BorderLayout.CENTER);
			modificAcc.add(tutto);
			buttonConferma.addActionListener(new ConfermaListener());
			buttonIndietro.addActionListener(new IndietroListener());
			return modificAcc;
		}
	}

	private JFrame modiRegistrazione(int ee) {
		final JFrame inserisciRegistra=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciRegistra.setLocation(new Point((dimension.width - 
				inserisciRegistra.getSize().width) / 2-325, 
		(dimension.height - inserisciRegistra.getSize().height) / 2 -135));
		
		inserisciRegistra.setSize(650,270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//inserisciRegistra.setResizable(false);
		inserisciRegistra.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		inserisciRegistra.setUndecorated(true);
		

		
		inserisciRegistra.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		inserisciRegistra.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = inserisciRegistra.getLocation();
		         
		               inserisciRegistra.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		inserisciRegistra.setIconImage(ii8.getImage());
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Modifica Account");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		
	 // inseriamo a nord del pannello la label "inserisci i dati richiesti" (spostata al centro)
		
		JPanel dati = new JPanel(); // creiamo un panel dati in cui inseriremo le label e i text field riguardanti le informazione della nuova partita da inserire
		JLabel squadraCasa = new JLabel("  Nome Account :");
		JLabel squadraOspite = new JLabel("  Username :");
		JLabel campionato= new JLabel("  Password :");
		JLabel lega = new JLabel("  Email Alternativa :");
		Registrazione r=sistema.getRegistrazioni().get(ee);
		JLabel squadraCasaField = new JLabel(r.getNomeAccount());
		JLabel squadraOspiteField = new JLabel(r.getUsername());
		JLabel campionatoField = new JLabel(r.getPassword());
		JLabel legaField = new JLabel(r.getEmailAlternativa());
		squadraCasa.setFont(new Font("Georgia", Font.PLAIN, 20));
		squadraOspite.setFont(new Font("Georgia", Font.PLAIN, 20));
		campionato.setFont(new Font("Georgia", Font.PLAIN, 20));
		lega.setFont(new Font("Georgia", Font.PLAIN, 20));
	
		squadraCasaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraOspiteField.setFont(new Font("Georgia", Font.PLAIN, 18));
		campionatoField.setFont(new Font("Georgia", Font.PLAIN, 18));
		legaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraCasa.setForeground(Color.BLACK);
		squadraOspite.setForeground(Color.BLACK);
		campionato.setForeground(Color.BLACK);
		lega.setForeground(Color.BLACK);
		squadraCasaField.setForeground(new Color(64,64,64));
		squadraOspiteField.setForeground(new Color(64,64,64));
		campionatoField.setForeground(new Color(64,64,64));
		legaField.setForeground(new Color(64,64,64));
		JButton butt1=new JButton("Modifica");
		butt1.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt1.setForeground(Color.BLACK);
		JButton butt2=new JButton("Modifica");
		butt2.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt2.setForeground(Color.BLACK);
		JButton butt3=new JButton("Modifica");
		butt3.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt3.setForeground(Color.BLACK);
		JButton butt4=new JButton("Modifica");
		butt4.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt4.setForeground(Color.BLACK);
		JPanel destra=new JPanel();
		destra.setLayout(new GridLayout(4,1));
		JPanel dati1=new JPanel();
		dati1.setLayout(new GridLayout(4,1));
		JPanel pa5=new JPanel();
		JPanel pa6=new JPanel();
		JPanel pa7=new JPanel();
		JPanel pa8=new JPanel();
		pa5.add(butt1);
		pa6.add(butt2);
		pa7.add(butt3);
		pa8.add(butt4);
		destra.add(pa5);
		destra.add(pa6);
		destra.add(pa7);
		destra.add(pa8);
		
		squadraCasa.setHorizontalAlignment(JLabel.LEFT);
		squadraOspite.setHorizontalAlignment(JLabel.LEFT);
		campionato.setHorizontalAlignment(JLabel.LEFT);
		lega.setHorizontalAlignment(JLabel.LEFT);
		
		dati.setLayout(new GridLayout(4, 1)); // settiamo il pannello dati come layout a griglia
		// inseriamo le lebel e i textfield corrispondenti nel pannello dati
		dati1.add(squadraCasa);
		dati1.add(squadraOspite);
		dati1.add(campionato);
		dati1.add(lega);
		dati.add(squadraCasaField);
		dati.add(squadraOspiteField);
		dati.add(campionatoField);
		dati.add(legaField);
		
		
		JPanel centro=new JPanel();
		centro.setLayout(new BorderLayout());
		centro.add(dati1,BorderLayout.WEST);
		centro.add(dati,BorderLayout.CENTER);
		centro.add(destra,BorderLayout.EAST);
	
		
		JPanel bottoni= new JPanel(); // criamo un panel bottoni dove inseriremo i button 
		JButton buttonAnnulla=new JButton("Indietro");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		// inseriamo i button Annulla e Conferma nel panel bottoni
		bottoni.add(buttonAnnulla);
	
		
		JPanel inserisciPar=new JPanel();
		inserisciPar.setLayout(new BorderLayout()); // creiamo un panel inserisciPar e lo settiamo come layout a bordi
		TitledBorder a5=new TitledBorder(new EtchedBorder(), "Modifica Account");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		inserisciPar.setBorder(a5);
		//inseriamo nel panel inserisciPar a nord la label "campi obbligatori" (spostata a destra), al centro il panel dati, a sud il panel bottoni
		inserisciPar.add(centro,BorderLayout.CENTER);
		inserisciPar.add(bottoni,BorderLayout.SOUTH);
		//Aggiungiamo al centro del panel pannello, il panel inserisciPar 
		pannello.add(inserisciPar, BorderLayout.CENTER);
		//Aggiungiamo al centro del frame il panel pannello
		JPanel tutto=new JPanel();
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));		
		tutto.setLayout(new BorderLayout());
		tutto.add(pannello,BorderLayout.CENTER);
		inserisciRegistra.add(tutto);
		
		
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
		
				int state = inserisciRegistra.getExtendedState();
				 
				state = Frame.ICONIFIED;
				inserisciRegistra.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		pannello.add(drf1,BorderLayout.NORTH); //
		
		//pannello.add(label,BorderLayout.NORTH);
		
		class Button1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modifica=modifica("Nome Account",ee);
				modifica.setVisible(true);
				modiRegistrazione.dispose();
			}
		}
		
		
		class Button2Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modifica=modifica("Username",ee);
				modifica.setVisible(true);
				modiRegistrazione.dispose();
			}
		}
		
		class Button3Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modifica=modifica("Password",ee);
				modifica.setVisible(true);
				modiRegistrazione.dispose();
			}
		}
		
		class Button4Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modifica=modifica("Email Alternativa",ee);
				modifica.setVisible(true);
				modiRegistrazione.dispose();
			}
		}
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciRegistra.dispose();
				setVisible(true);
			}
		}
	
		
		
		butt1.addActionListener(new Button1Listener());
		butt2.addActionListener(new Button2Listener());
		butt3.addActionListener(new Button3Listener());
		butt4.addActionListener(new Button4Listener());
		
		buttonAnnulla.addActionListener(new AnnullaListener());
		return inserisciRegistra;
	}
	
	public JFrame modifica(String string,int ee){
		
		
			JFrame modificAcc=new JFrame(); // il frame viene creato solo se sono presenti politiche di sconto nel sistema		
				
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			modificAcc.setLocation(new Point((dimension.width - 
				modificAcc.getSize().width) / 2-175, 
				(dimension.height - modificAcc.getSize().height) / 2 -80));
			modificAcc.setSize(370,160);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//modificAcc.setResizable(false);
			modificAcc.setUndecorated(true);
			

			
			modificAcc.addMouseListener(new MouseAdapter() {
			            public void mousePressed(MouseEvent e) {
			                point.x = e.getX();
			                point.y = e.getY();
			            }
			        });
			modificAcc.addMouseMotionListener(new MouseMotionAdapter() {
			            public void mouseDragged(MouseEvent e) {
			                Point p = modificAcc.getLocation();
			         
			               modificAcc.setLocation(p.x + e.getX() - point.x,
			                        p.y + e.getY() - point.y);
			            }
			        });
					
			
			ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
			modificAcc.setIconImage(ii8.getImage());
			JLabel scegli =new JLabel("Modifica "+string);
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
			
			JTextField scegli1 =new JTextField(20);
			scegli1.setToolTipText(string);
			
			scegli1.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli1.setHorizontalAlignment(JLabel.CENTER);
			scegli1.setForeground(Color.BLACK);
			JPanel centro=new JPanel();
			centro.add(scegli1);
			JPanel bottoni = new JPanel();
			JButton buttonConferma=new JButton("Conferma");
			buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonConferma.setForeground(Color.BLACK);
			JButton buttonAnnulla=new JButton("Annulla");
			buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonAnnulla.setForeground(Color.BLACK);
			bottoni.add(buttonAnnulla);
			bottoni.add(buttonConferma);
			JPanel tutto=new JPanel();
			tutto.setLayout(new BorderLayout());
			JPanel tutto1=new JPanel();
			tutto1.setLayout(new GridLayout(3,1));
			tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));
			tutto1.add(scegli);
			tutto1.add(centro);
			tutto1.add(bottoni);
			
			TitledBorder a=new TitledBorder(new EtchedBorder(), "Modifica "+string);
			a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a.setTitleColor(Color.BLACK);
			tutto1.setBorder(a);
			
			tutto.add(tutto1,BorderLayout.CENTER);
			modificAcc.add(tutto);
			
			class ConfermaListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					
					if(string.equals("Nome Account")){
						try {
							
							
							sistema.setNomeAccount(scegli1.getText(),ee);
							
							modificAcc.dispose();
							
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel("Modifica effettuata"),
									"Modifica effettuale",340,new ImageIcon(imgURLOK),
									modiRegistrazione); 
							def.setVisible(true);
						} catch (DatiNonValidiException e1) {
							modificAcc.setVisible(false);
							
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Modifica non effettuale",340,new ImageIcon(imgURLnonOK),
									modificAcc); 
							def.setVisible(true);
						}
					}
					else if(string.equals("Username")){
						try {
							sistema.setUsername(scegli1.getText(),ee);
							modificAcc.dispose();
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel("Modifica effettuata"),
									"Modifica effettuale",340,new ImageIcon(imgURLOK),
									modiRegistrazione); 
							def.setVisible(true);
						} catch (DatiNonValidiException e1) {
							modificAcc.setVisible(false);
							
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Modifica non effettuale",340,new ImageIcon(imgURLnonOK),
									modificAcc); 
							def.setVisible(true);
						}
					}
					
					else if(string.equals("Password")){
						try {
							sistema.setPassword(scegli1.getText(),ee);
							modificAcc.dispose();
							
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel("Modifica effettuata"),
									"Modifica effettuale",340,new ImageIcon(imgURLOK),
									modiRegistrazione); 
							def.setVisible(true);
						} catch (DatiNonValidiException e1) {
							modificAcc.setVisible(false);
							
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Modifica non effettuale",340,new ImageIcon(imgURLnonOK),
									modificAcc); 
							def.setVisible(true);
						}
					}
					else if(string.equals("Email Alternativa")){
						try {
							sistema.setEmailAlternativaAccount(scegli1.getText(),ee);
							modificAcc.dispose();
							
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel("Modifica effettuata"),
									"Modifica effettuale",340,new ImageIcon(imgURLOK),
									modiRegistrazione); 
							def.setVisible(true);
						} catch (DatiNonValidiException e1) {
							modificAcc.setVisible(false);
							
							modiRegistrazione=modiRegistrazione(ee);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Modifica non effettuale",340,new ImageIcon(imgURLnonOK),
									modificAcc); 
							def.setVisible(true);
						}
					}				
					
					
				}
			}
			
			class AnnullaListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					modificAcc.dispose();
					modiRegistrazione.setVisible(true);
				}
			}
			buttonConferma.addActionListener(new ConfermaListener());
			buttonAnnulla.addActionListener(new AnnullaListener());
			return modificAcc;
			
	}
	
	public JFrame visualizza () throws DatiNonValidiException{
		
		if(sistema.getRegistrazioni().size()==0)
			throw new DatiNonValidiException("Non sono presenti Account");
		else{
			dispose();
			
			JFrame visualizz=new JFrame();
				
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizz.setLocation(new Point((dimension.width - 
				visualizz.getSize().width) / 2-430, 
					(dimension.height - visualizz.getSize().height) / 2 -200));
			
			ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
			visualizz.setIconImage(ii8.getImage());
			visualizz.setSize(860,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//visualizz.setResizable(false);
			visualizz.setLayout(new BorderLayout());
			visualizz.setUndecorated(true);
			

			
			visualizz.addMouseListener(new MouseAdapter() {
			            public void mousePressed(MouseEvent e) {
			                point.x = e.getX();
			                point.y = e.getY();
			            }
			        });
			visualizz.addMouseMotionListener(new MouseMotionAdapter() {
			            public void mouseDragged(MouseEvent e) {
			                Point p = visualizz.getLocation();
			         
			                visualizz.setLocation(p.x + e.getX() - point.x,
			                        p.y + e.getY() - point.y);
			            }
			        });
					
					
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			bottoni.add(indietro);
					
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					
			JPanel tutto=new JPanel();
			tutto.setLayout(new BorderLayout());
			tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));	
			visualizz.add(tutto,BorderLayout.CENTER);
			tutto.add(scroll,BorderLayout.CENTER);
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
					
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Account");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello.setBorder(t);
					
			JPanel pannelloEventi=new JPanel();
			TitledBorder t2=new TitledBorder(new EtchedBorder(), "Visualizza Account");
			t2.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t2.setTitleColor(Color.BLACK);
			pannelloEventi.setBorder(t2);
		
			ArrayList<Registrazione> eventi=null;
		
				try {
					eventi = sistema.getTutteRegistrazioni();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			
			pannelloEventi.setLayout(new GridLayout(eventi.size()+1,1));
			pannello.add(pannelloEventi,BorderLayout.CENTER);
					
			JPanel rigaTitoli=new JPanel();
			rigaTitoli.setLayout(new GridLayout(1,4));
			JLabel l1=new JLabel(" Tipo Account ");
			JLabel l2=new JLabel(" Username ");
			JLabel l3=new JLabel(" Password ");
			JLabel l4=new JLabel(" Email Alternativa "); //poi verrà eliminta
			
			l1.setFont(new Font("Georgia", Font.PLAIN, 14));
			l1.setHorizontalAlignment(JLabel.CENTER);
			l1.setForeground(Color.BLACK);
			l2.setHorizontalAlignment(JLabel.CENTER);
			l2.setFont(new Font("Georgia", Font.PLAIN, 14));
			l2.setForeground(Color.BLACK);
			l3.setFont(new Font("Georgia", Font.PLAIN, 14));
			l3.setHorizontalAlignment(JLabel.CENTER);
			l3.setForeground(Color.BLACK);
			l4.setFont(new Font("Georgia", Font.PLAIN, 14));
			l4.setHorizontalAlignment(JLabel.CENTER);
			l4.setForeground(Color.BLACK);
			
			rigaTitoli.add(l1);
			rigaTitoli.add(l2);
			rigaTitoli.add(l3);
			rigaTitoli.add(l4);
		
			pannelloEventi.add(rigaTitoli);
					
			for(int i=0; i<eventi.size(); i++){
				Registrazione ev=eventi.get(i);
	
				JPanel rigaEvento=new JPanel();
				rigaEvento.setLayout(new GridLayout(1,6));
				//SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
				//String a=sdf.format(ev.getDataInizioEventoCalcistico().getTime());
				
				JLabel l7=new JLabel(ev.getNomeAccount());
				JLabel l8=new JLabel(ev.getUsername());
				JLabel l9=new JLabel(ev.getPassword());
				JLabel l10=new JLabel(""+ev.getEmailAlternativa()); //poi verrà eliminta
			
				l7.setFont(new Font("Georgia", Font.PLAIN, 14));
				l7.setForeground(Color.BLACK);
				l8.setFont(new Font("Georgia", Font.PLAIN, 14));
				l8.setForeground(Color.BLACK);
				l9.setFont(new Font("Georgia", Font.PLAIN, 14));
				l9.setForeground(Color.BLACK);
				l10.setFont(new Font("Georgia", Font.PLAIN, 14));
				l10.setForeground(Color.BLACK);
				l7.setHorizontalAlignment(JLabel.CENTER);
				l8.setHorizontalAlignment(JLabel.CENTER);
				l9.setHorizontalAlignment(JLabel.CENTER);
				l10.setHorizontalAlignment(JLabel.CENTER);
				rigaEvento.add(l7);
				rigaEvento.add(l8);
				rigaEvento.add(l9);
				rigaEvento.add(l10);
						
				pannelloEventi.add(rigaEvento);
			}
			
			
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
			
					int state = visualizz.getExtendedState();
					 
					state = Frame.ICONIFIED;
					visualizz.setExtendedState(state);
				}
			}
			
		//	dee.addActionListener(new ExitListener());
			dee1.addActionListener(new RiduciListener());
			
			JLabel label =new JLabel("Visualizza Account");
			label.setFont(new Font("Georgia", Font.PLAIN, 18));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(Color.BLACK);
			
			JPanel drf1=new JPanel();
			drf1.setLayout(new BorderLayout());
			drf1.add(label,BorderLayout.CENTER);
			drf1.add(drf,BorderLayout.EAST);
			
			tutto.add(drf1,BorderLayout.NORTH); //
			
			/**
			 * Il listener del pulsante Indietro. 
			 * Selezionando il pulsante Indietro, la finestra riguardante la visualizzazione degli eventi calcistici per capienza stadi 
			 * si chiude, ritornando alla schermate iniziale dell'amministratore.			
			 *
			 */
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					visualizz.dispose();					
					new UtenteFrame(sistema);
					setVisible(true);
				}
			}
			indietro.addActionListener(new IndietroListener());	
			
			
			
			return visualizz;
		}
	}
	
	private JFrame modiUtente() {
		final JFrame inserisciRegistra=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciRegistra.setLocation(new Point((dimension.width - 
				inserisciRegistra.getSize().width) / 2-325, 
		(dimension.height - inserisciRegistra.getSize().height) / 2 -135));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		inserisciRegistra.setIconImage(ii8.getImage());
		inserisciRegistra.setSize(550,270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//inserisciRegistra.setResizable(false);
		inserisciRegistra.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		inserisciRegistra.setUndecorated(true);
		

		
		inserisciRegistra.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		inserisciRegistra.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = inserisciRegistra.getLocation();
		         
		                inserisciRegistra.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Modifica Utente");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		pannello.add(label,BorderLayout.NORTH); // inseriamo a nord del pannello la label "inserisci i dati richiesti" (spostata al centro)
		
		JPanel dati = new JPanel(); // creiamo un panel dati in cui inseriremo le label e i text field riguardanti le informazione della nuova partita da inserire
		JLabel squadraCasa = new JLabel("  Nome :");
		JLabel squadraOspite = new JLabel("  Cognome :");
		JLabel campionato= new JLabel("  Username :");
		JLabel lega = new JLabel("  Password :");
		Utente r=sistema.getUtenteCorrente();
		JLabel squadraCasaField = new JLabel(r.getNome());
		JLabel squadraOspiteField = new JLabel(r.getCognome());
		JLabel campionatoField = new JLabel(r.getUsername());
		JLabel legaField = new JLabel(r.getPassword());
		squadraCasa.setFont(new Font("Georgia", Font.PLAIN, 20));
		squadraOspite.setFont(new Font("Georgia", Font.PLAIN, 20));
		campionato.setFont(new Font("Georgia", Font.PLAIN, 20));
		lega.setFont(new Font("Georgia", Font.PLAIN, 20));
	
		squadraCasaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraOspiteField.setFont(new Font("Georgia", Font.PLAIN, 18));
		campionatoField.setFont(new Font("Georgia", Font.PLAIN, 18));
		legaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraCasa.setForeground(Color.BLACK);
		squadraOspite.setForeground(Color.BLACK);
		campionato.setForeground(Color.BLACK);
		lega.setForeground(Color.BLACK);
		squadraCasaField.setForeground(new Color(64,64,64));
		squadraOspiteField.setForeground(new Color(64,64,64));
		campionatoField.setForeground(new Color(64,64,64));
		legaField.setForeground(new Color(64,64,64));
		JButton butt1=new JButton("Modifica");
		butt1.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt1.setForeground(Color.BLACK);
		JButton butt2=new JButton("Modifica");
		butt2.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt2.setForeground(Color.BLACK);
		JButton butt3=new JButton("Modifica");
		butt3.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt3.setForeground(Color.BLACK);
		JButton butt4=new JButton("Modifica");
		butt4.setFont(new Font("Georgia", Font.PLAIN, 15));
		butt4.setForeground(Color.BLACK);
		JPanel destra=new JPanel();
		destra.setLayout(new GridLayout(4,1));
		JPanel dati1=new JPanel();
		dati1.setLayout(new GridLayout(4,1));
		JPanel pa5=new JPanel();
		JPanel pa6=new JPanel();
		JPanel pa7=new JPanel();
		JPanel pa8=new JPanel();
		pa5.add(butt1);
		pa6.add(butt2);
		pa7.add(butt3);
		pa8.add(butt4);
		destra.add(pa5);
		destra.add(pa6);
		destra.add(pa7);
		destra.add(pa8);
		
		squadraCasa.setHorizontalAlignment(JLabel.LEFT);
		squadraOspite.setHorizontalAlignment(JLabel.LEFT);
		campionato.setHorizontalAlignment(JLabel.LEFT);
		lega.setHorizontalAlignment(JLabel.LEFT);
		
		dati.setLayout(new GridLayout(4, 1)); // settiamo il pannello dati come layout a griglia
		// inseriamo le lebel e i textfield corrispondenti nel pannello dati
		dati1.add(squadraCasa);
		dati1.add(squadraOspite);
		dati1.add(campionato);
		dati1.add(lega);
		dati.add(squadraCasaField);
		dati.add(squadraOspiteField);
		dati.add(campionatoField);
		dati.add(legaField);
		
		
		JPanel centro=new JPanel();
		centro.setLayout(new BorderLayout());
		centro.add(dati1,BorderLayout.WEST);
		centro.add(dati,BorderLayout.CENTER);
		centro.add(destra,BorderLayout.EAST);
	
		
		JPanel bottoni= new JPanel(); // criamo un panel bottoni dove inseriremo i button 
		JButton buttonAnnulla=new JButton("Indietro");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		// inseriamo i button Annulla e Conferma nel panel bottoni
		bottoni.add(buttonAnnulla);
	
		
		JPanel inserisciPar=new JPanel();
		inserisciPar.setLayout(new BorderLayout()); // creiamo un panel inserisciPar e lo settiamo come layout a bordi
		TitledBorder a5=new TitledBorder(new EtchedBorder(), "Modifica Utente");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		inserisciPar.setBorder(a5);
		//inseriamo nel panel inserisciPar a nord la label "campi obbligatori" (spostata a destra), al centro il panel dati, a sud il panel bottoni
		inserisciPar.add(centro,BorderLayout.CENTER);
		inserisciPar.add(bottoni,BorderLayout.SOUTH);
		//Aggiungiamo al centro del panel pannello, il panel inserisciPar 
		pannello.add(inserisciPar, BorderLayout.CENTER);
		//Aggiungiamo al centro del frame il panel pannello
		JPanel tutto=new JPanel();
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));		
		tutto.setLayout(new BorderLayout());
		tutto.add(pannello,BorderLayout.CENTER);
		inserisciRegistra.add(tutto);
		class Button1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modificaU=modifica("Nome");
				modificaU.setVisible(true);
				modiUtente.dispose();
			}
		}
		
		
		class Button2Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modificaU=modifica("Cognome");
				modificaU.setVisible(true);
				modiUtente.dispose();
			}
		}
		
		class Button3Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modificaU=modifica("Username");
				modificaU.setVisible(true);
				modiUtente.dispose();
			}
		}
		
		class Button4Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modificaU=modifica("Password");
				modificaU.setVisible(true);
				modiUtente.dispose();
			}
		}
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciRegistra.dispose();
				setVisible(true);
			}
		}
	
		
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
		
				int state = inserisciRegistra.getExtendedState();
				 
				state = Frame.ICONIFIED;
				inserisciRegistra.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		

		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		tutto.add(drf1,BorderLayout.NORTH); //
		
		
		butt1.addActionListener(new Button1Listener());
		butt2.addActionListener(new Button2Listener());
		butt3.addActionListener(new Button3Listener());
		butt4.addActionListener(new Button4Listener());
		
		buttonAnnulla.addActionListener(new AnnullaListener());
		return inserisciRegistra;
	}
	
	public JFrame modifica(String string){
		
		
		JFrame modificAcc=new JFrame(); // il frame viene creato solo se sono presenti politiche di sconto nel sistema		
			
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		modificAcc.setLocation(new Point((dimension.width - 
			modificAcc.getSize().width) / 2-175, 
			(dimension.height - modificAcc.getSize().height) / 2 -80));
		modificAcc.setSize(370,160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//modificAcc.setResizable(false);
		modificAcc.setUndecorated(true);
		

		
	modificAcc.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		modificAcc.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = modificAcc.getLocation();
		         
		               modificAcc.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		modificAcc.setIconImage(ii8.getImage());
			
		JLabel scegli =new JLabel("Modifica "+string);
		scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
		scegli.setHorizontalAlignment(JLabel.CENTER);
		scegli.setForeground(Color.BLACK);
		
		JTextField scegli1 =new JTextField(20);
		scegli1.setToolTipText(string);
		scegli1.setFont(new Font("Georgia", Font.PLAIN, 18));
		scegli1.setHorizontalAlignment(JLabel.CENTER);
		scegli1.setForeground(Color.BLACK);
		JPanel centro=new JPanel();
		centro.add(scegli1);
		JPanel bottoni = new JPanel();
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		JPanel tutto=new JPanel();
		tutto.setLayout(new BorderLayout());
		JPanel tutto1=new JPanel();
		tutto1.setLayout(new GridLayout(3,1));
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));
		tutto1.add(scegli);
		tutto1.add(centro);
		tutto1.add(bottoni);
		
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Modifica "+string);
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		tutto1.setBorder(a);
		
		tutto.add(tutto1,BorderLayout.CENTER);
		modificAcc.add(tutto);
		
		class ConfermaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				if(string.equals("Nome")){
				
						sistema.getUtenteCorrente().setNome(scegli1.getText());
						modificAcc.dispose();
						
						modiUtente=modiUtente();
						def=new DefaultFrame(new JLabel("Modifica effettuata"),
								"Modifica effettuale",340,new ImageIcon(imgURLOK),
								modiUtente); 
						def.setVisible(true);
					
				}
				else if(string.equals("Cognome")){
					
						sistema.getUtenteCorrente().setCognome(scegli1.getText());
						modificAcc.dispose();
						
						modiUtente=modiUtente();
						def=new DefaultFrame(new JLabel("Modifica effettuata"),
								"Modifica effettuale",340,new ImageIcon(imgURLOK),
								modiUtente); 
						def.setVisible(true);
					
				}
				
				else if(string.equals("Username")){
					
						sistema.getUtenteCorrente().setUsername(scegli1.getText());
						modificAcc.dispose();
						
						modiUtente=modiUtente();
						def=new DefaultFrame(new JLabel("Modifica effettuata"),
								"Modifica effettuale",340,new ImageIcon(imgURLOK),
								modiUtente); 
						def.setVisible(true);
					
				}
				else if(string.equals("Password")){
				
						sistema.getUtenteCorrente().setPassword(scegli1.getText());
						modificAcc.dispose();
				
						modiUtente=modiUtente();
						def=new DefaultFrame(new JLabel("Modifica effettuata"),
								"Modifica effettuale",340,new ImageIcon(imgURLOK),
								modiUtente); 
						def.setVisible(true);
					
				}				
				
				
			}
		}
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				modificAcc.dispose();
				modiUtente.setVisible(true);
			}
		}
		buttonConferma.addActionListener(new ConfermaListener());
		buttonAnnulla.addActionListener(new AnnullaListener());
		return modificAcc;
		
	}
	
	
	
	private JFrame caricaBackup () {
		final JFrame caricaba=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		caricaba.setLocation(new Point((dimension.width - 
				caricaba.getSize().width) / 2-215, 
		(dimension.height - caricaba.getSize().height) / 2 -100));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		caricaba.setIconImage(ii8.getImage());
		
		caricaba.setSize(430,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//caricaba.setResizable(false);
		caricaba.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		caricaba.setUndecorated(true);
		

		
		caricaba.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		caricaba.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = caricaba.getLocation();
		         
		                caricaba.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Scegli il nome del file di backup");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		pannello.add(label,BorderLayout.NORTH); // inseriamo a nord del pannello la label "inserisci i dati richiesti" (spostata al centro)
	
	
		JPanel dati = new JPanel(); // creiamo un panel dati in cui inseriremo le label e i text field riguardanti le informazione della nuova partita da inserire
		dati.setLayout(new BorderLayout());
		JLabel squadraCasa = new JLabel("  Nome File :");
		squadraCasa.setFont(new Font("Georgia", Font.PLAIN, 20));
		squadraCasa.setForeground(Color.BLACK);
		JLabel squadraCasa1 = new JLabel("N.B : Il file deve trovarsi sul desktop, non inserire l'estensione : .dat");
		squadraCasa1.setFont(new Font("Georgia", Font.PLAIN, 12));
		squadraCasa1.setHorizontalAlignment(JLabel.RIGHT);
		squadraCasa1.setForeground(Color.BLACK);
		JTextField cercaField=new JTextField(10);
		cercaField.setFont(new Font("Georgia", Font.ITALIC, 26));
		cercaField.setForeground(Color.BLACK);
		JButton butt1=new JButton("Ripristina Backup");
		butt1.setFont(new Font("Georgia", Font.PLAIN,20));
		butt1.setForeground(Color.BLACK);
		JButton butt2=new JButton("Annulla");
		butt2.setFont(new Font("Georgia", Font.PLAIN, 20));
		butt2.setForeground(Color.BLACK);
		JPanel pa8=new JPanel();
		pa8.add(butt1);
		pa8.add(butt2);
		
		JPanel cerca=new JPanel();
		JLabel dee=new JLabel();
		ImageIcon ii=new ImageIcon(getClass().getResource("/resource/cerca2.png"));
		
		cerca.add(squadraCasa);
		cerca.add(cercaField);
		dee.setIcon(ii);
		cerca.add(dee);
		JPanel cerca1=new JPanel();
		cerca1.setLayout(new GridLayout(2,1));
		cerca1.add(cerca);
		cerca1.add(pa8);
		JPanel panne=new JPanel();
		panne.add(label,BorderLayout.NORTH);
		panne.add(cerca1,BorderLayout.CENTER);
		panne.add(squadraCasa1,BorderLayout.SOUTH);
		pannello.add(panne);
		caricaba.add(pannello);
		
		
		TitledBorder a5=new TitledBorder(new EtchedBorder(), "Ripristina Backup");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		
		panne.setBorder(a5);
		pannello.setBorder(BorderFactory.createLineBorder(Color.black,2));	
		class Button1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String nomefile=System.getProperty("user.home") + "/Desktop/"+cercaField.getText()+".dat"; 
				try {
					Scanner in=new Scanner(new File(nomefile));
					sistema.caricaBackup(in);
					caricaba.dispose();
				
					def=new DefaultFrame(new JLabel("Il backup è stato ripristinato correttamente"),
							"Caricamento effettuato",440,new ImageIcon(imgURLOK),
							new UtenteFrame(sistema)); 
					def.setVisible(true);
			
				} catch (FileNotFoundException e1) {
					caricaba.setVisible(false);
					def=new DefaultFrame(new JLabel("Impossibile trovare il file "+cercaField.getText()+".dat"),
							"Errore",440,new ImageIcon(imgURLnonOK),
							caricaBackup()); 
					def.setVisible(true);
				}
			
			}
		}
		
		
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				caricaba.dispose();
				setVisible(true);
			}
		}
	
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
		
				int state = caricaba.getExtendedState();
				 
				state = Frame.ICONIFIED;
				caricaba.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		
		JLabel label111 =new JLabel("Backup");
		label111.setFont(new Font("Georgia", Font.PLAIN, 18));
		label111.setHorizontalAlignment(JLabel.CENTER);
		label111.setForeground(Color.BLACK);
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label111,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		pannello.add(drf1,BorderLayout.NORTH); //
		
		butt1.addActionListener(new Button1Listener());
	
		
		butt2.addActionListener(new AnnullaListener());
		return caricaba;
	}
	
	private JFrame scaricaBackup () {
		final JFrame scaricaba=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		scaricaba.setLocation(new Point((dimension.width - 
				scaricaba.getSize().width) / 2-215, 
		(dimension.height - scaricaba.getSize().height) / 2 -95));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		scaricaba.setIconImage(ii8.getImage());
		scaricaba.setSize(430,190);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//scaricaba.setResizable(false);
		scaricaba.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		scaricaba.setUndecorated(true);
		

		
		scaricaba.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		scaricaba.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = scaricaba.getLocation();
		         
		                scaricaba.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Scegli il nome del file di backup");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		pannello.add(label,BorderLayout.NORTH); // inseriamo a nord del pannello la label "inserisci i dati richiesti" (spostata al centro)
	
	
		JPanel dati = new JPanel(); // creiamo un panel dati in cui inseriremo le label e i text field riguardanti le informazione della nuova partita da inserire
		dati.setLayout(new BorderLayout());
		JLabel squadraCasa = new JLabel("  Nome File :");
		squadraCasa.setFont(new Font("Georgia", Font.PLAIN, 20));
		squadraCasa.setForeground(Color.BLACK);
		
		JTextField cercaField=new JTextField(10);
		cercaField.setFont(new Font("Georgia", Font.ITALIC, 26));
		cercaField.setForeground(Color.BLACK);
		JButton butt1=new JButton("Crea Backup");
		butt1.setFont(new Font("Georgia", Font.PLAIN,20));
		butt1.setForeground(Color.BLACK);
		JButton butt2=new JButton("Annulla");
		butt2.setFont(new Font("Georgia", Font.PLAIN, 20));
		butt2.setForeground(Color.BLACK);
		JPanel pa8=new JPanel();
		pa8.add(butt1);
		pa8.add(butt2);
		
		JPanel cerca=new JPanel();
		cerca.add(squadraCasa);
		cerca.add(cercaField);
		
		JPanel panne=new JPanel();
		panne.add(label,BorderLayout.NORTH);
		panne.add(cerca,BorderLayout.CENTER);
		
		panne.add(pa8,BorderLayout.SOUTH);
		pannello.add(panne);
		scaricaba.add(pannello);
		
		
		TitledBorder a5=new TitledBorder(new EtchedBorder(), "Crea Backup");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		
		
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
		
				int state = scaricaba.getExtendedState();
				 
				state = Frame.ICONIFIED;
				scaricaba.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		
		JLabel label111 =new JLabel("Backup");
		label111.setFont(new Font("Georgia", Font.PLAIN, 18));
		label111.setHorizontalAlignment(JLabel.CENTER);
		label111.setForeground(Color.BLACK);
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label111,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		pannello.add(drf1,BorderLayout.NORTH); //
		
		
		panne.setBorder(a5);
		pannello.setBorder(BorderFactory.createLineBorder(Color.black,2));	
		class Button1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {					
					String U=System.getProperty("user.home") + "/Desktop"; 
					File f=new File(U,cercaField.getText()+"."+"dat");
										
					PrintWriter p;
						
						try {
							p = new PrintWriter(f);
							for(Registrazione r : sistema.getRegistrazioni()){
					    	
					    	
					    	p.println(r.getNomeAccount());
					    	p.println(r.getUsername());
					    	p.println(r.getPassword());
					    	p.println(r.getEmailAlternativa());
					    	
					   
					    	
					    	
					    	
					    }
							scaricaba.dispose();
					    	def=new DefaultFrame(new JLabel("Backup effettuato,"
					    			+ " il file di Backup: '"+f.getName()+"' si trova sul Desktop"),
							"Backup effettuato",650,new ImageIcon(imgURLOK),
							new UtenteFrame(sistema)); 
					    	def.setVisible(true);
							p.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		      
					    
					
			
			}
		}
		
		
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				scaricaba.dispose();
				setVisible(true);
			}
		}
	
		
		
		butt1.addActionListener(new Button1Listener());
	
		
		butt2.addActionListener(new AnnullaListener());
		return scaricaba;
	}
	
	
	private JFrame confermaL () {
		final JFrame conf=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		conf.setLocation(new Point((dimension.width - 
				conf.getSize().width) / 2-240, 
		(dimension.height - conf.getSize().height) / 2 -80));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		conf.setIconImage(ii8.getImage());
		conf.setSize(480,160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//conf.setResizable(false);
		conf.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		conf.setUndecorated(true);
		

		
		conf.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		conf.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = conf.getLocation();
		         
		                conf.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Salvare le modifiche prima effettuare il LogOut ?");
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
		
				int state = conf.getExtendedState();
				 
				state = Frame.ICONIFIED;
				conf.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		

		JLabel label111 =new JLabel("LogOut");
		label111.setFont(new Font("Georgia", Font.PLAIN, 18));
		label111.setHorizontalAlignment(JLabel.CENTER);
		label111.setForeground(Color.BLACK);
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label111,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		pannello.add(drf1,BorderLayout.NORTH); //
		
		
		JPanel panne=new JPanel();
		panne.add(label,BorderLayout.NORTH);
		panne.add(de,BorderLayout.CENTER);
		panne.add(pa8,BorderLayout.SOUTH);
		pannello.add(panne,BorderLayout.CENTER);
		conf.add(pannello);
		
		
		TitledBorder a5=new TitledBorder(new EtchedBorder(), "LogOut");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		
		panne.setBorder(a5);
		pannello.setBorder(BorderFactory.createLineBorder(Color.black,2));	
		class Button1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {					
				sistema.SvuotaParola();
				
				
				
				FileOutputStream out;
				ObjectOutputStream outStream;
				sistema.getUtenteCorrente().setR(sistema.getRegistrazioni());
				
				try {
					out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
					outStream = new ObjectOutputStream(out);
					
					
			
					outStream.writeObject(sistema); // Scriviamo sul file lo stato attuale del sistema
					File f=new File ("C:\\AccountManager\\AccountManager.txt");
					f.delete();
					File f1=new File ("C:\\AccountManager\\AccountManager.doc");
					f1.delete();
					// Infine chiudiamo i flussi
					out.close();
					outStream.close();
					String utente =sistema.getUtenteCorrente().getNome();
					sistema.logout();
					conf.dispose();
					def=new DefaultFrame(new JLabel("Arrivederci "+utente+", a presto "),
						"LogOut",390,
						new ImageIcon(imgURLOK),
						new FrameIniziale(sistema));
					def.setVisible(true);
				} 
				catch (IOException e1) {}
			
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
	
	private JFrame conferma () {
		final JFrame conf=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		conf.setLocation(new Point((dimension.width - 
				conf.getSize().width) / 2-240, 
		(dimension.height - conf.getSize().height) / 2 -80));
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		conf.setIconImage(ii8.getImage());
		conf.setSize(480,160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//conf.setResizable(false);
		conf.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		conf.setUndecorated(true);
		

		
		conf.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                point.x = e.getX();
		                point.y = e.getY();
		            }
		        });
		conf.addMouseMotionListener(new MouseMotionAdapter() {
		            public void mouseDragged(MouseEvent e) {
		                Point p = conf.getLocation();
		         
		                conf.setLocation(p.x + e.getX() - point.x,
		                        p.y + e.getY() - point.y);
		            }
		        });
				
		
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
		
				int state = conf.getExtendedState();
				 
				state = Frame.ICONIFIED;
				conf.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		

		JLabel label111 =new JLabel("Salva ed Esci");
		label111.setFont(new Font("Georgia", Font.PLAIN, 18));
		label111.setHorizontalAlignment(JLabel.CENTER);
		label111.setForeground(Color.BLACK);
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label111,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		pannello.add(drf1,BorderLayout.NORTH); //
		
		
		JPanel panne=new JPanel();
		panne.add(label,BorderLayout.NORTH);
		panne.add(de,BorderLayout.CENTER);
		panne.add(pa8,BorderLayout.SOUTH);
		pannello.add(panne,BorderLayout.CENTER);
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
				sistema.getUtenteCorrente().setR(sistema.getRegistrazioni());
				try {
//					out = new FileOutputStream(urlFile.getFile());
					out = new FileOutputStream("C:\\AccountManager\\accountManager.dat");
					outStream = new ObjectOutputStream(out);
					
					sistema.getUtenteCorrente().setCambiamentoU(false);
			
				
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
	
	
	private JFrame aggiornamenti() {
		final JFrame inserisciRegistra=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciRegistra.setLocation(new Point((dimension.width - 
				inserisciRegistra.getSize().width) / 2-325, 
		(dimension.height - inserisciRegistra.getSize().height) / 2 -90));
		
		inserisciRegistra.setSize(550,180);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//inserisciRegistra.setResizable(false);
		inserisciRegistra.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		inserisciRegistra.setUndecorated(true);
		
		ImageIcon ii8=new ImageIcon(getClass().getResource("/resource/icona1.png"));
		inserisciRegistra.setIconImage(ii8.getImage());
		
		inserisciRegistra.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
		inserisciRegistra.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = inserisciRegistra.getLocation();
         
                inserisciRegistra.setLocation(p.x + e.getX() - point.x,
                        p.y + e.getY() - point.y);
            }
        });
		
		
		/*
		try {
			Desktop.getDesktop().browse(new URI("http://gianluigimamone.altervista.org/ciao.txt"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		JLabel componente=new JLabel("E' Disponibile Una Nuova Versione Di Account Manager");
		componente.setFont(new Font("Georgia", Font.PLAIN, 18));
		componente.setForeground(Color.BLACK);
		
		JButton aggiorna = new JButton("Scarica La Nuova Versione");
		aggiorna.setFont(new Font("Georgia", Font.PLAIN, 14));
		aggiorna.setForeground(Color.BLACK);
		JButton aggiorna1 = new JButton("Chiudi");
		aggiorna1.setFont(new Font("Georgia", Font.PLAIN, 14));
		aggiorna1.setForeground(Color.BLACK);
		
		/**
		 * Il listener per il pulsante Chiudi.
		 * Selezionando tale pulsante la finestra viene chiusa aprendo la schermata riguardante il frame che l'ha creata
		 */
		class AggiornaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
			
				sistema.getPagineWeb();
				
			}
		}
		
		class Aggiorna1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciRegistra.dispose();
				new UtenteFrame(sistema).setVisible(true);
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
		

		aggiorna.addMouseListener(new AccediMo());
		aggiorna.addActionListener(new AggiornaListener());

		aggiorna1.addMouseListener(new AccediMo());
		aggiorna1.addActionListener(new Aggiorna1Listener());
		
		getRootPane().setDefaultButton(aggiorna);
	    aggiorna.requestFocus();
	
		JPanel pannello=new JPanel();
		JPanel pannello1=new JPanel();
		JPanel pannello2=new JPanel();
		ImageIcon icon=new ImageIcon(getClass().getResource("/resource/icoUp1.png"));
		componente.setIcon(icon);
		pannello1.add(componente);
		pannello2.add(aggiorna1);
		pannello2.add(aggiorna);
		pannello.setLayout(new GridLayout(2,1));
		pannello.add(pannello1);
		pannello.add(pannello2);
		
		TitledBorder t=new TitledBorder(new EtchedBorder(),"Aggiornamenti");
		t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		t.setTitleColor(Color.BLACK);
		pannello.setBorder(t);
		JPanel tutto=new JPanel();
		tutto.setLayout(new BorderLayout());
		tutto.setBorder(BorderFactory.createLineBorder(Color.black,2));		
		tutto.add(pannello,BorderLayout.CENTER);
		inserisciRegistra.add(tutto);
	
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
		
				int state = inserisciRegistra.getExtendedState();
				 
				state = Frame.ICONIFIED;
				inserisciRegistra.setExtendedState(state);
			}
		}
		
	//	dee.addActionListener(new ExitListener());
		dee1.addActionListener(new RiduciListener());
		
		JLabel label111 =new JLabel("Aggiornamenti");
		label111.setFont(new Font("Georgia", Font.PLAIN, 18));
		label111.setHorizontalAlignment(JLabel.CENTER);
		label111.setForeground(Color.BLACK);
		
		JPanel drf1=new JPanel();
		drf1.setLayout(new BorderLayout());
		drf1.add(label111,BorderLayout.CENTER);
		drf1.add(drf,BorderLayout.EAST);
		
		tutto.add(drf1,BorderLayout.NORTH); //
		
		
		
		return inserisciRegistra;
	}
	
	public void sistemaTutto(){
		a.setVisible(false);
		sistema.SvuotaParola();
		cercaField.setText("");
		pannelloRegistrazione.setVisible(false);
	}
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private SistemaAccount sistema;
	
	private URL imgURLOK = getClass().getResource("/resource/ok.png");
	private URL imgURLvuoto = getClass().getResource("/resource/vuoto.png");
	private URL imgURLnonOK = getClass().getResource("/resource/image.png");
	
	
	private DefaultFrame def;
	private JMenuBar menu;
	private JFrame aggiungiRegistrazione;
	private JFrame rimuoviRegistrazione;
	private JFrame modificaRegistrazione;
	private JPanel descrizione; 
	private JFrame modifica;
	private JFrame modiUtente;
	private JFrame modificaU;
	private JFrame modiRegistrazione;
	private ArrayList<Registrazione> r;
	private JFrame visualizzaTutti;
	private JFrame scaricaBackup;
	private JFrame aggiornaFrame;
	private JFrame caricabackup;
	private JFrame conferma;
	private JFrame confermaL;
	private JTextField cercaField;
	private ArrayList<MyLabel> suggeri;
	private JPanel pannelloRegistrazione=new JPanel();
	private JPanel w1;
	private JPanel a;
	private JPanel notifica;
	private boolean val=false;
	private ImageIcon logo;
	private int numero_account;

}
