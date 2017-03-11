
public class DuplicateISBNException extends Exception {

	String exceptMessage;
	
	public DuplicateISBNException(String message){
		super(message);
		exceptMessage = message;
	}
	
	public String getMessage() {
		return exceptMessage;
	}
	
}
