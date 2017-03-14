/** Driver class for Assignment 3 part 1.
 *
 * ----------------------------------------------------------------
 * Assignment #3
 * Part: 1
 * Written by: Chris Hewlings: 29145958 /  Leo Sudarma : 40046196
 * COMP249
 * Due Date       March 14, 2017
 * ----------------------------------------------------------------
 */

import java.io.*;
import java.util.Scanner;

public class BookInventory1 {

		static Book[] bkArr;
		static int bkArrSize;
		static Scanner keyb = new Scanner(System.in);
		
		
		/**
		 * 
		 * Checks to see if the provided ISBN number is unique (i.e. not duplicated)
		 * in the static object bkArr.
		 * 
		 * @param currentIsbn	The ISBN number to check for uniqueness
		 * @return returns true if there is only one instance, otherwise returns false.
		 */
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
		
		/**
		 * Checks to see if an ISBN number is present in bkArr.
		 * 
		 * @param currentIsbn	The ISBN number to check for existence in bkArr
		 * @return	returns true if the ISBN is present, otherwise returns false.
		 */
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
			
		/**
		 * Called if a duplicate ISBN is found when scanning bkArr.
		 * Prompts the user for a new ISBN to replace the duplicate entry. 
		 * If the user enters an ISBN that already exists, throws DuplicateISBNException
		 * and re-calls itself with the entered value. 
		 * 
		 * @param duplicateIsbn	the ISBN that is duplicated in bkArr
		 * @return	returns a new unique ISBN number in long form. 
		 */
		static long getNewIsbn(long duplicateIsbn) {
			
			long newIsbn;
			
			System.out.printf("ISBN #%d is already present in the book array. Please assign a new one: ", duplicateIsbn );
			newIsbn = keyb.nextLong();
			try 
			{
				if(doesIsbnExist(newIsbn) == true)
				{
					throw new DuplicateISBNException("ISBN already exists in the array");
				}
			} catch(DuplicateISBNException dup)
				{
					// Recursively call the same method if the provided ISBN isn't unique
					getNewIsbn(newIsbn);
				}
			return newIsbn;
		}
		
		
		/**
		 * Takes input from an incorrectly formatted file, parses the values in the file,
		 * and writes them into the static variable bkArr, then checks for duplicate/incorrect entries.
		 * Throws DuplicateISBNException to handle correcting duplicate entries.
		 * 
		 * @param inputStream an open FileInputStream object from which to get book inventory values
		 * @param outputStream an open FileOutputStream object to write out values after they've been corrected
		 */
		static void fixInventory(FileInputStream inputStream, FileOutputStream outputStream) {
			
			bkArrSize = countLines("./Initial_Book_Info.txt");
			bkArr = new Book[bkArrSize];
			
			
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
			lines.close();
			
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
			
		// Finally, write the contents to the output file stream
			PrintWriter pw = new PrintWriter(outputStream);
			for(int i = 0; i < bkArrSize; i++) {
				pw.println(bkArr[i]);
			}
			// Flush the output to the file to guarantee contents are written
			// even if the program ends abnormally further on in execution
			pw.flush();
		
			System.out.println("Done entering new ISBN values.\n");
		}
		
		
		/**
		 * Reads the contents of an open FileInputStream and displays them to the console
		 * This method *closes* the passed stream upon completion.
		 * 
		 * @param inputStream takes an open FileInputStream
		 */
		static void displayFileContents(FileInputStream inputStream) {
			Scanner inputLines = new Scanner(inputStream);
			
			while(inputLines.hasNextLine() == true) {
				String line = inputLines.nextLine();
				System.out.println(line);
			}
			inputLines.close();
		}
		
		/**
		 * Checks to see if the named file already exists.
		 * 
		 * @param fileName	the file name in String form
		 * @return	returns true if the file exists, otherwise false
		 */
		static boolean doesFileExist(String fileName) {
			File file = new File(fileName);
			if(file.exists() )
				return true;
			else
				return false;
		}
		
		/**
		 * Takes a file name as a String and returns its size in bytes
		 * 
		 * @param fileName	the file name in String form
		 * @return	returns the file size in bytes as a long
		 */
		static long getFileSize(String fileName) {
			if(doesFileExist(fileName)) {
				File file = new File(fileName);
				return file.length();
			} else return -1;
		}
		
		/**
		 * Prompts the user for entry to choose the name for an output file, checks
		 * if it is unique (i.e. doesn't already exist). 
		 * 
		 * @return returns the selected output file name as a String
		 */
		static String getOutputFileName() {
			boolean validResponse = false;
			String response; 
			
			System.out.print("Enter an output filename: ");
			
			do {
				response = keyb.nextLine();
				if(doesFileExist(response) == true)
					System.out.printf("%s already exists, and is %d bytes long. Please make another selection.", response, getFileSize(response));
				else if(doesFileExist(response) == false)
					validResponse = true;
				} while(validResponse != true);
			
			return response;
			
			}
		
		/**
		 * Opens a file for reading and handles exceptions accordingly.
		 * 
		 * @param fileName	the file name in String form
		 * @return returns an open FileInputStream object to be manipulated by other methods.
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
		 * Takes an open FileInputStream and counts the number of lines in the file. 
		 * 
		 * @param fileName the file name in String form
		 * @return returns the number of lines in a text file
		 */
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
		
		/**
		 * Opens a file for writing and handles exceptions accordingly.
		 * 
		 * @param fileName	the file name as a String
		 * @return returns an open FileOutputStream object
		 */
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
		
		
		public static void main(String[] args) {
			
			System.out.println("Duplicate ISBN Remover");
			
			// Open input file for reading
			FileInputStream inputStream = openFileForReading("./Initial_Book_Info.txt");
			
			// Prompt user for output filename
			String outputFileName = getOutputFileName();
			
			// Open output file for reading
			
			FileOutputStream outputStream = openFileForWriting(outputFileName); 
			 
			// Check and fix inventory values from file
			fixInventory(inputStream, outputStream);
			
			// Close our output streams.
			try{
				outputStream.close();
				inputStream.close();
			} catch(IOException e) {
				System.out.println("Unable to close input/output streams, are the files locked?");
				e.printStackTrace();
				System.exit(1);
			}
		
			// Show the contents of the original file
			System.out.println("Here are the contents of the file Initial_Book_Info.txt");
			System.out.println("=======================================================");
			displayFileContents(openFileForReading("./Initial_Book_Info.txt"));
			System.out.println("");
			
			// Show the contents of our output file
			System.out.println("Here are the contents of our corrected output file " + outputFileName);
			System.out.println("======================================================================");
			displayFileContents(openFileForReading(outputFileName));
			System.out.println("");
			
			// Print closing message
			System.out.println("\nSuccessfully completed Duplicate ISBN Removal!\n");
			
		}
}