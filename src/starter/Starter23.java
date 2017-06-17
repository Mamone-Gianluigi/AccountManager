package starter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;

import core.SistemaAccount;
import exceptions.DatiNonValidiException;
import graphics.FrameIniziale;



public class Starter23 {
	
	//private static FrameIniziale inizio;
	
	public static void main(String[] args) throws IOException, DatiNonValidiException, CloneNotSupportedException, ClassNotFoundException {
		SistemaAccount sistema;
		
		File file = new File("C:\\AccountManager\\accountManager.dat");
		//File file = new File("accountManager.dat");
		FileInputStream in;
		ObjectInputStream inStream;
		
		if (file.exists()) {
			System.out.println("--");
			in = new FileInputStream(file);
			inStream = new ObjectInputStream(in);

			sistema = (SistemaAccount)inStream.readObject();
		
			System.out.println(sistema.getParola());

			in.close();
			inStream.close();
		}
		else
			sistema = new SistemaAccount("Country Sport");
		
		/*sistema.stampaEventiCalcisticiPerCapienza();
		System.out.println("");
		sistema.stampaEventiCalcisticiData();
		System.out.println("");
		
		*/
		
		JFrame inizio=new FrameIniziale(sistema);
		inizio.setVisible(true);
		
		//sistema.stampaStadiCapienza();
	}
}
