/** Exception class to control and handle duplicated ISBN values from <Initial_Book_Info.txt>
 *
 * ----------------------------------------------------------------
 * Assignment #3
 * Part: 1
 * Written by: Chris Hewlings: 29145958 /  Leo Sudarma : 40046196
 * COMP249
 * Due Date       March 14, 2017
 * ----------------------------------------------------------------
 */
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
