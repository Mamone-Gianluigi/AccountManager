package core;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class Registrazione implements Serializable , Comparable ,Cloneable{

	
	private static final long serialVersionUID = 1L;
	public Registrazione(String nomeAccount,String username,String password,String emailAlternativa) {
		this.nomeAccount=nomeAccount;
		this.username=username;
		this.password=password;
		this.emailAlternativa=emailAlternativa;
	}
	
	public String getNomeAccount() {
		return nomeAccount;
	}
	public void setNomeAccount(String nomeAccount) {
		this.nomeAccount = nomeAccount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAlternativa() {
		return emailAlternativa;
	}
	public void setEmailAlternativa(String emailAlternativa) {
		this.emailAlternativa = emailAlternativa;
	}

	public String toString() {
		return "Registrazione [nomeAccount=" + nomeAccount + ", username=" + username + ", password=" + password
				+ ", emailAlternativa=" + emailAlternativa + "]";
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registrazione other = (Registrazione) obj;
		return emailAlternativa.equals(other.emailAlternativa)
				&& nomeAccount.equals(other.nomeAccount) &&
				password.equals(other.password) && 
				username.equals(other.username);
	}

	public Object clone() throws CloneNotSupportedException {
		try {
			Registrazione copia = (Registrazione)super.clone(); 
			return copia;
		}
		catch (CloneNotSupportedException e){
			return null;
		}
	}
	
	@Override
	public int compareTo(Object o) {
		Registrazione reg=(Registrazione) o;
		return nomeAccount.compareToIgnoreCase(reg.nomeAccount);
			
		
		
	}
	
	public static Registrazione read(Scanner in) {
		if (!in.hasNext())
			return null;
		String nomeAccount=in.nextLine();
		String username=in.nextLine();
		String password=in.nextLine();
		String emailAlternativa=in.nextLine();
		return new Registrazione(nomeAccount,username,password,emailAlternativa);
	}


	private String nomeAccount;
	private String username;
	private String password;
	private String emailAlternativa;

	
}
