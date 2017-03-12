import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;


public class BookInventory2 {

		static Book[] bkArr;
		static int bkArrSize;
		
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
		}
		
		static void addRecords(FileOutputStream outputStream) {
			Scanner keyb = new Scanner(System.in);
			
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
		
		static Book binaryBookSearch(Book[] bkArr, int startIndex, int endIndex, long targetIsbn) {
			Book result = new Book();
			int iterationsRequired = 0;
						
			while(startIndex <= endIndex ) {
				int midPoint = ( startIndex + endIndex ) /2 ;
				
				if ( targetIsbn > bkArr[midPoint].getIsbn() ) {
					startIndex = midPoint - 1;
					System.out.println(startIndex);
					iterationsRequired++;
				} else if ( targetIsbn < bkArr[midPoint].getIsbn() ) {
					endIndex = midPoint - 1;
					System.out.println(endIndex);
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
		
		static boolean doesFileExist(String fileName) {
			File file = new File(fileName);
			if(file.exists() )
				return true;
			else
				return false;
		}
		
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
		
		static FileOutputStream openFileForAppending(String fileName) {
			FileOutputStream outputStream = null;
			PrintWriter pw = null;
			File file = new File(fileName);
			
			try {
				outputStream = new FileOutputStream(file, true);
			} catch(IOException e) {
				System.out.println("Error accessing file");
				System.exit(2);
			}
			return outputStream;
		}
		
		static int countLines(String fileName) {
			
			FileInputStream inputStream = openFileForReading(fileName);
			Scanner lines = new Scanner(inputStream);
			
			int lineCount = 0;
			
			while( lines.hasNextLine() ) {
				lines.nextLine();
				lineCount++; 
			}
			
			lines.close();
			return lineCount;
		}
		
		
		public static void main(String[] args) {
			
			
			System.out.println("ISBN Searcher");
			
			FileInputStream inputStream = openFileForReading("./Sorted_Book_Info.txt");
			
			FileOutputStream fout = openFileForAppending("./Sorted_Book_Info.txt");
			
			importBooksFromFile(inputStream);
			
			
			//addRecords(fout);
			
			//displayFileContents(inputStream);
			

			//for(int i = 0; i < bkArr.length; i++)
			//	System.out.println(bkArr[i]);
			sequentialBookSearch(bkArr, 0, 14,929568679);
			binaryBookSearch(bkArr,0, 14, 929568679);
			
		}
}