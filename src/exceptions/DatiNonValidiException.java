package exceptions;

/**
 * L'eccezione viene lanciata nel caso in cui i dati inseriti non siano corretti (es. dati login non validi)
 */
public class DatiNonValidiException extends Exception{

	/**
	 * Crea una nuova eccezione DatiNonValidiException
	 */
	public DatiNonValidiException() {
		super("I dati inseriti non sono validi");
	}
	
	/**
	 * Crea una nuova eccezione DatiNonValidiException stampando il testo indicato
	 * @param msg Il testo da stampare (Precondizione: msg != null)
	 */
	public DatiNonValidiException(String msg) {
		super(msg);
	}
	
	private static final long serialVersionUID = 1L;

}
