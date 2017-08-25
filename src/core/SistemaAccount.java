package core;

import java.awt.Desktop;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import exceptions.*;


public class SistemaAccount implements Serializable {

	
	public SistemaAccount(String nome){
		cambiamento=false;
		this.nome=nome;
		utenti = new ArrayList<Utente>();
		Utente defaultUtente= new Utente("Gianluigi","Mamone","admin","pass");
		utenti.add(defaultUtente);
		corrente = null;
		parola="";
		aggiornamento=false;
		registrazioni= new ArrayList<Registrazione>();
	}
	
	public Boolean getAggiornamento() {
		return aggiornamento;
	}

	public void setAggiornamento(Boolean aggiornamento) {
		this.aggiornamento = aggiornamento;
	}

	public String getNome() {
		return nome;
	}

	public String cercaUtente(String cognome,String nome,String us) throws DatiNonValidiException{
		if (nome.equals("") || cognome.equals("")|| us.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		
		if(utenti.size()==0)
			throw new DatiNonValidiException("Non vi sono utenti nel sistema ");
		for(Utente e:utenti)
			if(e.getCognome().equals(cognome) && e.getNome().equals(nome) && e.getUsername().equals(us))
				return e.getPassword();
		throw new DatiNonValidiException("Non vi è un utente con questi dati");	
		
	}
	
	public void setNome(String nome) {
		this.nome = nome;
		corrente.setCambiamentoU(true);
	}

	public void creaUtente(String nome, String cognome,String user,String pass) throws DatiNonValidiException {
	
		if (nome.equals("") || cognome.equals("")|| user.equals("")|| pass.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		
		for (Utente e : utenti)
			if (e.getUsername().equals(user)) 
				throw new DatiNonValidiException(
						"Esiste già  un account con lo stesso username");
		utenti.add(new Utente(nome,cognome,user,pass));
		cambiamento=true;

	}
		
	public void setUsername(String user) throws DatiNonValidiException {
		
		if (user.equals(""))
			throw new DatiNonValidiException("Inserisci il nuovo Username");
		
		for (Utente e : utenti)
			if (e.getUsername().equals(user)) 
				throw new DatiNonValidiException(
						"Esiste già  un account con lo stesso username");
		corrente.setUsername(user);
		corrente.setCambiamentoU(true);
	}
	
	public void setPasswordUtente (String pass) throws DatiNonValidiException {
		
		if (pass.equals(""))
			throw new DatiNonValidiException("Inserisci la nuova Password");
		corrente.setPassword(pass);
		corrente.setCambiamentoU(true);
	}
	
	public void setNomeUtente (String nome) throws DatiNonValidiException {
		
		if (nome.equals(""))
			throw new DatiNonValidiException("Inserisci il nuovo Nome");
		corrente.setNome(nome);
		corrente.setCambiamentoU(true);
	}
	
	public void setCognomeUtente (String cogno) throws DatiNonValidiException {
		
		if (cogno.equals(""))
			throw new DatiNonValidiException("Inserisci il nuovo Cognome");
		corrente.setCognome(cogno);
		corrente.setCambiamentoU(true);
	}
	
	public void loginUtente(String user, String pass) throws DatiNonValidiException {
		
		if (user.equals("") || pass.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		
		for (Utente e : utenti)
			if (e.getUsername().equals(user) && e.getPassword().equals(pass)) {
				corrente = e;
				registrazioni=e.getR();
				System.out.println(registrazioni.size());
				cambiamento=false;
				System.out.println(cambiamento);
				return;
				
			}
		throw new DatiNonValidiException();
	}
	
	public void logout() {
		for(Utente u:utenti)
			if(u.equals(corrente))
				u.setR(registrazioni);
		
		cambiamento=false;
		corrente = null;
		parola="";
		registrazioni=new ArrayList<>();
		
	}

	public Utente getUtenteCorrente()  {
		if (corrente instanceof Utente)
			return (Utente) corrente;
		else
			return null;
	}
	
	public void aggiungiRegistrazione(String nomeAccount,String username,String password,String emailAlternativa) throws DatiNonValidiException{
		if (nomeAccount.equals("") || username.equals("") || password.equals(""))
			throw new DatiNonValidiException("Completa i campi sono obbligatori");
		else{
			int i=0;
			for(Registrazione r:registrazioni)
				if(r.getNomeAccount().equalsIgnoreCase(nomeAccount))
					i=i+1;

			if(i>=4)
				throw new DatiNonValidiException("Sono già stati inseriti 4 account "+nomeAccount+" : Limite Superato");
			
		for(Registrazione r:registrazioni)
				if(r.getNomeAccount().equals(nomeAccount))
					if(r.getUsername().equals(username))
						throw new DatiNonValidiException("E' gia stato inserito un account "+nomeAccount+" con questo Username");
			registrazioni.add(new Registrazione(nomeAccount, username, password, emailAlternativa));
		
			cambiamento=true;
		}
	}
	
	public void rimuoviRegistrazione(int i){
		registrazioni.remove(i);
		cambiamento=true;
	}
	
	public ArrayList<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(ArrayList<Utente> utenti) {
		this.utenti = utenti;
		cambiamento=true;
	}

	public Utente getCorrente() {
		return corrente;
	}

	public void setCorrente(Utente corrente) {
		this.corrente = corrente;
		cambiamento=false;
	}

	public ArrayList<Registrazione> getRegistrazioni() {
		return registrazioni;
	}
	
	public ArrayList<Registrazione> getTutteRegistrazioni() throws CloneNotSupportedException {
		
		@SuppressWarnings("unchecked")
		ArrayList<Registrazione> r=ArrayListSort.sort(registrazioni);
		
		return r;
		
	}
	
	public void rimuoviUtente(){
		Utente u=corrente;
		logout();
		utenti.remove(u);
	}

	public void setRegistrazioni(ArrayList<Registrazione> registrazioni) {
		this.registrazioni = registrazioni;
	}

	public ArrayList<Registrazione> cercaRegistrazione(String nomeAccount) throws DatiNonValidiException{
		ArrayList<Registrazione> regi=new ArrayList<Registrazione>();
		for(Registrazione r:registrazioni)
			if(r.getNomeAccount().equalsIgnoreCase(nomeAccount))
				regi.add(r);
		if(regi.size()==0)
			throw new DatiNonValidiException("Non sono presenti Account " +nomeAccount);
		else 
			return regi;
	}
	

	
	
	public void setNomeAccount(String nomeAccount,int i) throws DatiNonValidiException {
		if(nomeAccount.equals(""))
			throw new DatiNonValidiException("Campo obbligatorio");
		registrazioni.get(i).setNomeAccount(nomeAccount);
		cambiamento=true;
		
	}

	public void setUsername(String username,int i) throws DatiNonValidiException {
		if(username.equals(""))
			throw new DatiNonValidiException("Campo obbligatorio");
		registrazioni.get(i).setUsername(username);
		cambiamento=true;
	}
	
	public void setPassword(String password,int i) throws DatiNonValidiException {
		if(password.equals(""))
			throw new DatiNonValidiException("Campo obbligatorio");
		registrazioni.get(i).setPassword(password);
		cambiamento=true;
	}

	public void setEmailAlternativaAccount(String emailAlternativa,int i) throws DatiNonValidiException {
		if(emailAlternativa.equals(""))
			throw new DatiNonValidiException("Campo obbligatorio");
		registrazioni.get(i).setEmailAlternativa(emailAlternativa);
		cambiamento=true;
	}
	
	public void caricaBackup(Scanner in){
		
		while(in.hasNext()){
			Registrazione r=Registrazione.read(in);
			registrazioni.add(r);		
		}
		cambiamento=true;
	}
	
	public boolean isCambiamento() {
		return cambiamento;
	}

	public void setCambiamento(boolean cambiamento) {
		this.cambiamento = cambiamento;
	}

	public ArrayList<String> suggerisci(String cerca){
		
		 ArrayList<Registrazione> registrazioni=getRegistrazioni();
		 ArrayList<String> suggerimenti=new ArrayList<String>();
		 TreeSet<String> ts = new TreeSet<String> ();
		 
		 for(Registrazione r:registrazioni)
			 ts.add(r.getNomeAccount());

		 int l = cerca.length ();
		 String cercaEnd = cerca.substring (0, l-1) + (char) (cerca.charAt (l-1)+1);

		 SortedSet<String> ss = ts.subSet (cerca, cercaEnd);

		 Iterator<String> it = ss.iterator ();

		 while (it.hasNext ())
		 {
			 String s = it.next ();
			 suggerimenti.add(s);
		 }
		 return suggerimenti;
	}
	
	public void AddCarattere(char c){
		parola=parola+c;
		System.out.println("Dopo aver aggiunto il carattere: "+parola);
	}
	
	public void SvuotaParola(){
		parola="";
	}
	
	public void RemoveCarattere(){

		if(parola.length()>=1){
			System.out.println("dopo rimosso il carattere"+parola.substring(0, parola.length()-1));
			parola=parola.substring(0, parola.length()-1);
		}
		else if(parola.length()==1){
			parola="";
			System.out.println("dopo rimosso ultimo carattere"+parola.substring(0, parola.length()));
		}
		//System.out.println("rimos :"+parola);
	}
	
	public String getParola(){
		return parola;
	}
	
	public void controllaAggiornamenti(){
		
		
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
			System.out.println("e1456");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println(con.getResponseMessage());
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
				aggiornamento=true;
			else
				aggiornamento=false;
		} catch (IOException e1) {
			System.out.println("e1");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ;
	}
	
	public void getPagineWeb(){
		try {
			Desktop.getDesktop().browse(new URI("https://mamonegianluigi.000webhostapp.com/paginaAggiornamento.php"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("e1asdfghjkkjhgfds");
			e.printStackTrace();
		}
	}
	
	
	private String nome;
	private ArrayList<Utente> utenti;
	private Utente corrente;
	private ArrayList<Registrazione> registrazioni;
	private static final long serialVersionUID = 1L;
	private Boolean aggiornamento;
	private String parola;
	private boolean cambiamento;
	
	
}

