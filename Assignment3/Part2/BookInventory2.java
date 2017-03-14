/** Driver class for Assignment 3 part 2.
 *
 * ----------------------------------------------------------------
 * Assignment #3
 * Part: 2
 * Written by: Chris Hewlings: 29145958 /  Leo Sudarma : 40046196
 * COMP249
 * Due Date       March 14, 2017
 * ----------------------------------------------------------------
 */

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;


public class BookInventory2 {

		static Book[] bkArr;
		static int bkArrSize;
		static Scanner keyb = new Scanner(System.in);
		
		/**
		 * Takes an open FileInputStream and scans it in line by line,
		 * parses it, and assigns each line to an element of bkArr. 
		 * 
		 * @param inputStream an open FileInputStream to be scanned and parsed
		 */
		static void importBooksFromFile(FileInputStream inputStream) {
			
			bkArrSize = countLines("./Sorted_Book_Info.txt");
			bkArr = new Book[bkArrSize];
			Scanner lines = new Scanner(inputStream);
			
			
			for( int i = 0; i < bkArrSize; i++ ) {
				long isbn = lines.nextLong();
				String title = lines.next();
				int issueYear = lines.nextInt();
				String authorName = lines.next();
				double price = lines.nextDouble();
				int numPages = lines.nextInt();
				
				bkArr[i] = new Book(title, authorName, issueYear, numPages, isbn, price);
				lines.nextLine();
			}
			lines.close();
		}
		
		
		/**
		 * Takes an open (for appending) FileOutputStream object and appends 
		 * entries one by one based on the users input, and loops. When the user enters 0
		 * as the next ISBN, the loop terminates and returns to the calling function. 
		 * 
		 * If the user enters an illegal value, or one that cannot be implicitly cast to 
		 * the correct type, the program throws a RuntimeException and terminates 
		 * the entry of new data elements.
		 * 
		 * @param outputStream	an open FileOutputStream object to be appended to.
		 */
		static void addRecords(FileOutputStream outputStream) {
			
			PrintWriter pw = new PrintWriter( outputStream );
			
			int newRecords = 1;
			
			long newIsbn = -1;
			
			String newTitle;
			String newAuthorName;
			
			int newIssueYear;
			int newNumPages;
			
			double newPrice;
			
			while(newIsbn != 0){
				
				try {
				
					System.out.println("Adding new record " + newRecords);
					
					System.out.printf("Enter ISBN for record %d (enter 0 to stop): ", newRecords);
					newIsbn = keyb.nextLong();
					if(newIsbn == 0) {
						break;
					}
					
					System.out.printf("Enter title for record %d: ", newRecords);
					newTitle = keyb.next();
					
					System.out.printf("Enter author name for record %d: ", newRecords);
					newAuthorName = keyb.next();
					
					System.out.printf("Enter issuing year for record %d: ", newRecords);
					newIssueYear = keyb.nextInt();
					
					System.out.printf("Enter number of pages for record %d: ", newRecords);
					newNumPages = keyb.nextInt();
					
					System.out.printf("Enter price for record %d: ", newRecords);
					newPrice = keyb.nextDouble();
					
					Book newEntry = new Book(newTitle, newAuthorName, newIssueYear, newNumPages, newIsbn, newPrice);
					pw.println(newEntry.toString());
					newRecords++;
					
				} catch(InputMismatchException e) {
					System.out.println("Incorrect input type given.  Cancelling record entry.");
					break;
				}
				
			}
			pw.close();
		}
		
		/**
		 * Takes two indices for a Book[] and searches them for a given ISBN via binary search.
		 * Prints out an iteration count representing how many attempts were made to find the element.
		 * 
		 * If no element is found matching the given ISBN, returns null.
		 * 
		 * @param bkArr an array of type Book[]
		 * @param startIndex the first index to search
		 * @param endIndex	the last index to search
		 * @param targetIsbn	an ISBN number
		 * @return Returns the Book element in bkArr that matches the specified ISBN
		 */
		static Book binaryBookSearch(Book[] bkArr, int startIndex, int endIndex, long targetIsbn) {
			Book result = new Book();
			
			int iterationsRequired = 1;
						
			while(startIndex <= endIndex ) {
				int midPoint = ( startIndex + endIndex ) /2 ;
				
				if ( targetIsbn > bkArr[midPoint].getIsbn() ) {
					startIndex = midPoint + 1;
					iterationsRequired++;
				} else if ( targetIsbn < bkArr[midPoint].getIsbn() ) {
					endIndex = midPoint - 1;
					iterationsRequired++;
				} else {
					result = bkArr[midPoint];
					break;
				}
			}
			
			// Check to see if a result was found
			// if not, return null
			if(result.getIsbn() == 0 ) {
				return null;
			}
			
			System.out.println("Iterations required (binary) : " + iterationsRequired);
			
			return result;
		}
		
		/**
		 * Takes two indices for a Book[] and searches them for a given ISBN via
		 * sequential search. Displays an iteration count representing how many attempts
		 * were made to find the given element. 
		 * 
		 * If no element is found matching the given ISBN, returns null.
		 * 
		 * @param bkArr	an array of type Book[]
		 * @param startIndex	the first index to search
		 * @param endIndex	the last index to search
		 * @param isbn	an ISBN number
		 * @return Returns the Book element in bkArr that matches the specified ISBN
		 */
		static Book sequentialBookSearch(Book[] bkArr, int startIndex, int endIndex, long isbn) {
			
			Book result = new Book();
			int iterationsRequired = -1;
			
			for(int i = startIndex; i < endIndex; i++) {
				if(bkArr[i].getIsbn() == isbn) {
					result = bkArr[i];
					iterationsRequired = i - startIndex;
				}
			}
			System.out.println("Iterations Required (sequential) : " + iterationsRequired);
			
			return result;
		}
		
		/**
		 * Takes an (open) FileInputStream object and reads through it line by line
		 * and prints it to the console in text mode
		 * 
		 * @param inputStream an open FileInputStream object 
		 */
		static void displayFileContents(FileInputStream inputStream) {
			Reader reader = new InputStreamReader(inputStream);
			BufferedReader buf = new BufferedReader(reader);
			
			String nextLine = null;
			
			boolean eof = false;
			
			while(eof == false) {
				
				try {
					nextLine = buf.readLine();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				if(nextLine != null) {
					System.out.println(nextLine);
				} else eof = true;
				
			}
		}
		
		/**
		 * Takes a filename by String and checks for the existence of a file.
		 * 
		 * @param fileName File name in string form
		 * @return returns true if file already exists, otherwise false.
		 */
		static boolean doesFileExist(String fileName) {
			File file = new File(fileName);
			if(file.exists() )
				return true;
			else
				return false;
		}
		
		/**
		 * @param fileName File name in String form
		 * @return Returns an open FileInputStream object for use with other class methods.
		 */
		static FileInputStream openFileForReading(String fileName) {
			FileInputStream inputStream = null;
			File file = new File(fileName);
			
			try {
				inputStream = new FileInputStream(file);
			} catch(IOException e) {
				System.out.println("Error accessing file");
				System.exit(1);
			} 
			return inputStream;
		}
		
		/**
		 * @param fileName File name in String form
		 * @return	Returns an open FileOutputStream (for appending) object for use with other methods.
		 */
		static FileOutputStream openFileForAppending(String fileName) {
			FileOutputStream outputStream = null;

			File file = new File(fileName);
			
			try {
				outputStream = new FileOutputStream(file, true);
			} catch(IOException e) {
				System.out.println("Error accessing file");
				System.exit(2);
			}
			return outputStream;
		}
		
		/**
		 * @param fileName	File name in String form
		 * @return	Returns the count of lines in a text file in integer form.
		 */
		static int countLines(String fileName) {
			
			FileInputStream inputStream = openFileForReading(fileName);
			//Scanner lines = new Scanner(inputStream);
			BufferedReader reader = null;
			int lineCount = 0;
			
			try{
				reader = new BufferedReader(new FileReader(fileName));
				
				while( reader.readLine() != null) {
					lineCount++; 
				}
				reader.close();
				
			} catch(FileNotFoundException fnf) {
				System.out.println("File not found! Wrong filename passed as parameter?");
				System.exit(1);
			} catch(IOException ioe) {
				System.out.println("IOException");
				ioe.printStackTrace();
				System.exit(2);
			} 
			
			return lineCount;
			
			
		}
		
		static void outputBinaryFile(Book[] bkArr) {
			FileOutputStream binaryOutputStream = null;
			ObjectOutputStream objOutputStream = null;
			try {
				binaryOutputStream = new FileOutputStream("./Books.dat");
				objOutputStream = new ObjectOutputStream(binaryOutputStream);
				objOutputStream.writeObject(bkArr);
				objOutputStream.close();
				binaryOutputStream.close();
				System.out.println("Serialized object written to Books.dat");
			} catch(IOException ioe) {
				System.out.println("An unexpected error occurred outputting serialized data.");
				ioe.printStackTrace();
				System.exit(3);
			}
		}
		
		public static void main(String[] args) {
			
			
			System.out.println("ISBN Searcher");
			
			FileInputStream inputStream = openFileForReading("./Sorted_Book_Info.txt");
			FileOutputStream outputStream = openFileForAppending("./Sorted_Book_Info.txt");
			
			// Add new records to Sorted_Book_Info.txt
			addRecords(outputStream);
			System.out.println("Done entering new records.\n");
			
			// Read the new contents of Sorted_Book_Info.txt
			// Note that we have to re-open the stream following this,
			// as displayFileContents closes it.
			System.out.println("Printing contents of Sorted_Book_Info.txt:");
			System.out.println("==========================================");
			
			displayFileContents(inputStream);
			
			// Scan contents of Sorted_Book_Info.txt and import it into bkArr
			inputStream = openFileForReading("./Sorted_Book_Info.txt");
			importBooksFromFile(inputStream);
			
			// Ask user for target ISBN number.
			System.out.print("Enter an ISBN to search for in the array: ");
			long targetIsbn = keyb.nextLong();
			
			// Search through the array with binary search
			binaryBookSearch(bkArr, 0, bkArrSize, targetIsbn);
			// Search through the array with sequential search
			sequentialBookSearch(bkArr,0, bkArrSize, targetIsbn);
			
			// Write serialized form of Book[] to <Books.dat>
			outputBinaryFile(bkArr);
			
			// Close any remaining open file/input handles
			try{
				inputStream.close();
				outputStream.close();
				keyb.close();
			} catch(IOException ioe) {
				System.out.println("Unable to close one or more files, are they locked?");
				ioe.printStackTrace();
				System.exit(4);
			}
			
			// Print closing message
			System.out.println("\nSuccessfully completed ISBN Add & Search!\n");
			
		}
}