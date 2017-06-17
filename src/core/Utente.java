package core;

import java.io.Serializable;
import java.util.ArrayList;

/** La classe implementa il concetto di Account di un'applicazione informatica
 * ovvero di un'entità  che interagisce con il sistema, caratterizzata da un identificativo
 * univoco, da un nome, da un cognome, da un username e da una password 
 */
public class Utente implements Cloneable , Serializable {

	
	
	public Utente(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		r=new ArrayList<>();
	}

	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Registrazione> getR() {
		return (ArrayList<Registrazione>) r.clone();
	}



	public void setR(ArrayList<Registrazione> r) {
		this.r = r;
	}



	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String nuovaPassword) {
		this.password = nuovaPassword;
	}

	public String toString() {
		return this.getClass().getName() + " [nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	public boolean equals (Object other) {
		if (other == null) 
			return false;
		if (getClass() != other.getClass())
			return false;
		Utente o = (Utente)other;
		return (nome.equals(o.nome) && cognome.equals(o.cognome) 
					&& username.equals(o.username));
	}

	public Object clone() throws CloneNotSupportedException {
		try {
			Utente copia = (Utente)super.clone(); 
			return copia;
		}
		catch (CloneNotSupportedException e){
			return null;
		}
	}
	
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private ArrayList<Registrazione> r;
	private static final long serialVersionUID = 1L;

}
