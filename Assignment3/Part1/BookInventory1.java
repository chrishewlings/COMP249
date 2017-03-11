import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;


public class BookInventory1 {

		static Book[] bkArr;
		static int bkArrSize;
		static long[] uniqueIsbns;
		
		
		static boolean isIsbnPresent(long isbn) {
			for(int i = 0; i < uniqueIsbns.length; i++) {
				if(isbn == uniqueIsbns[i]) {
					return true;
				}
			}
			
			return false;
		}
		
		static boolean isIsbnUnique(long currentIsbn) {
			int instances = 0;
			for(int i = 0; i < bkArr.length; i++)
			{
				if(bkArr[i].getIsbn() == currentIsbn)
					instances++;
			}
			if(instances > 1) 
				return false;
			else return true;
		}
		
		static boolean doesIsbnExist(long currentIsbn) {
			for(int i = 0; i < bkArr.length; i++)
			{
				if(bkArr[i].getIsbn() == currentIsbn )
				{
					return true;
				}
			}
			return false;
		}
			
		static long getNewIsbn(long duplicateIsbn) {
			
			long newIsbn;
			
			System.out.printf("ISBN #%d is already present in the book array. Please assign a new one: ", duplicateIsbn );
			Scanner keyb = new Scanner(System.in);
			newIsbn = keyb.nextLong();
			try 
			{
				if(doesIsbnExist(newIsbn) == true)
				{
					throw new DuplicateISBNException("ISBN already exists in the array");
				}
			} catch(DuplicateISBNException dup)
				{
					getNewIsbn(newIsbn);
				}
			return newIsbn;
		}
		
		
		static void fixInventory(FileInputStream inputStream, FileOutputStream outputStream) {
			
			bkArrSize = countLines("./Initial_Book_Info.txt");
			bkArr = new Book[bkArrSize];
			
			uniqueIsbns = new long[bkArrSize]; // unique ISBNs can't exceed bkArray size
			
			
			Scanner lines = new Scanner(inputStream);
			
			// Scanning in file line by line and constructing bkArr 
			
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
			
			// Check array for duplicates
			
			for(int i = 0; i < bkArr.length; i++) {
				long currentIsbn = bkArr[i].getIsbn();
				
				while(true)
				{
					try
					{
						if( isIsbnUnique(currentIsbn) == false) {
							throw new DuplicateISBNException("Duplicate ISBN found!");
						}	
					} catch( DuplicateISBNException dup) 
						{
							long newIsbn = getNewIsbn(currentIsbn);
							
							bkArr[i].setIsbn(newIsbn);
						}
					break;
				}
			}
		
		}
		
		
		static void displayFileContents(FileInputStream inputStream) {
			Scanner inputLines = new Scanner(inputStream);
			
			while(inputLines.hasNextLine() == true) {
				String line = inputLines.nextLine();
				System.out.println(line);
			}
			inputLines.close();
		}
		
		static boolean doesFileExist(String fileName) {
			File file = new File(fileName);
			if(file.exists() )
				return true;
			else
				return false;
		}
		
		static long getFileSize(String fileName) {
			if(doesFileExist(fileName)) {
				File file = new File(fileName);
				return file.length();
			} else return -1;
		}
		
		static String getOutputFileName() {
			Scanner userInput = new Scanner(System.in);
			boolean validResponse = false;
			String response; 
			
			System.out.print("Enter an output filename: ");
			
			do {
				response = userInput.nextLine();
				if(doesFileExist(response) == true)
					System.out.printf("%s already exists, and is %d bytes long. Please make another selection.", response, getFileSize(response));
				else if(doesFileExist(response) == false)
					validResponse = true;
				} while(validResponse != true);
			
			userInput.close();
			return response;
			
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
		
		static int countLines(String fileName) {
			
			FileInputStream inputStream = openFileForReading(fileName);
			Scanner lines = new Scanner(inputStream);
			
			int lineCount = 0;
			
			while( lines.hasNextLine() ) {
				lines.nextLine();
				lineCount++; 
			}
			
			if( (lineCount == 0) || (lineCount == 1) ) {
				System.out.println("The file contains only 0 or 1 record(s). Exiting...");
				lines.close();
				System.exit(0);
			}
				
			
			lines.close();
			return lineCount;
			
		}
		
		static FileOutputStream openFileForWriting(String fileName) {
			FileOutputStream outputStream = null;
			File file = new File(fileName);
			
			try {
				outputStream = new FileOutputStream(file);
			} catch(IOException e) {
				System.out.println("Error accessing file");
				System.exit(2);
			}
			return outputStream;
		}
		
		static void parseValues(FileInputStream inputStream) {
			
			
		}
		
		public static void main(String[] args) {
			
			
			System.out.println("Duplicate ISBN Remover");
			
			FileInputStream inputStream = openFileForReading("./Initial_Book_Info.txt");
			FileOutputStream outputStream = openFileForWriting("output.txt"); 
			 
			
			fixInventory(inputStream, outputStream);
			
			
			for(int i = 0; i < bkArr.length; i++)
				System.out.println(bkArr[i]);
				
			
		}
}