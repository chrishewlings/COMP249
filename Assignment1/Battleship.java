/** Defines a Battleship object.
 *@version 0.1
 * Names(s) and ID(s)	Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment # 		Assignment #1
 * Due Date				February 1, 2017
 */

import java.util.Scanner;
import java.util.Random;

public class Battleship
{
	// Defines constants for clarity.
	final static int TOTAL_SHIPS_PER_PLAYER = 6;
	final static int TOTAL_GRENADES_PER_PLAYER = 4;
	final static int GAMEBOARD_ROWS = 8;
	final static int GAMEBOARD_COLUMNS = 8;
	final static String ALPHA_TRANSLATE = "ABCDEFGH";

	public static Boolean didHumanHitGrenade = false;
	public static Boolean didComputerHitGrenade = false;

	/** Initialize the random number generator, as used by randomCell() method.
	*   It's initialized here instead of in the randomCell() method to avoid
	*   draining entropy excessively with multiple calls.
	*/
	static Random rng = new Random();	

	//Open a handle for keyboard input.
	static Scanner userInput = new Scanner(System.in);

	/**
	* Defines a new game board of gameCell objects, which will be acted upon by our other methods.
	* This does not fill the game board with anything, so use the initGameBoard() or call constructor method to do so.
	*/
	static gameCell[][] gameBoard = new gameCell[GAMEBOARD_ROWS][GAMEBOARD_COLUMNS];

	// METHODS

	// CONSTRUCTORS

	/**
	 * Initializes an empty game board, asks for user to place their ships, and picks positions for the computer.
	 */
	public Battleship()
	{
		initGameBoard();
		getUserChoices();
		populateComputerPositions();
	}

	// PRIVATE METHODS

	/**
	* Fills a new game board with empty cells.
	*/
	private void initGameBoard()
	{
		for(int row = 0; row < gameBoard.length  ; row++)
			for(int col = 0; col < gameBoard[row].length; col++)
				gameBoard[row][col] = new gameCell();
	}

	/** 
	* Takes a set of coordinates and acting player's name and fires at that position.
	*/
	private static String fireRocket(int row, int column, String player)
	{
		String message = "";
		String hitType = "";

		if(gameBoard[row][column].isOccupied() == true)
		{
			hitType = gameBoard[row][column].getType();
			message = String.format("\n%s HIT by %s!\n",hitType, player);
		}
		else
			message = String.format("\nMISS by %s!\n", player);
		
		if(gameBoard[row][column].isPositionCalled() == true)
			message = String.format("\nPosition already called.  Nothing happened.\n");

		System.out.println(message);
		gameBoard[row][column].setPositionCalled();
		return hitType;
	}

	/**
	* Returns a random set of x,y coordinates within bounds.
	* <p>
	* @return int[] 	Returns an two element array of int corresponding to an X and Y value.
	*/
	private static int[] randomCell()
	{
		int row = rng.nextInt(GAMEBOARD_ROWS);
		int column = rng.nextInt(GAMEBOARD_ROWS);
		int[] returnCoordinates = {row, column};
		return returnCoordinates;
	}

	/**
	* Uses Java's built-in regular expressions instead of stripping off the first index of string, converting it to char, etc...
	* <p>
	* @return Boolean	Returns true if X position is between A-H and Y position is between 1-8, else returns false.
	*/
	private static Boolean isInputValid(String whatPosition)
	{
		String pattern = "[a-hA-H][1-8]";
		
		if(whatPosition.matches(pattern)) // checks to see if the users input matches the bounds of the problem
		{
			int[] coordinates = letterNumberToRowAndColumn(whatPosition);
			int row = coordinates[0];
			int column = coordinates[1];
			return true;
		}
		else return false;
	}

	/**
	* Takes the users' input as a String, and translates it to a zero indexed row and column.
	* To do the opposite, see rowAndColumnToLetterNumber(int[] coordinates) .
	* <p>
	* @return int[]		Returns an array of two int corresponding to row & column on the game board.
	*/
	private static int[] letterNumberToRowAndColumn(String s)
	{
		char firstCharacter = Character.toUpperCase(s.charAt(0));
		int colNumber = ALPHA_TRANSLATE.indexOf(firstCharacter);

		int rowNumber = Integer.valueOf(s.substring(1)).intValue() - 1;
		int[] returnArray = { rowNumber, colNumber };

		return returnArray;
	}

	/**
	* Takes a zero indexed row and column and interprets it.
	* To do the opposite, see int[] letterNumberToRowAndColumn(String s).
	* <p>
	* @return String 	Returns a human readable String corresponding to visual representation of game board.
	*/
	private static String rowAndColumnToLetterNumber(int[] coordinates)
	{
		int row = coordinates[0];
		int column = coordinates[1];
		char colLetter = ALPHA_TRANSLATE.charAt(column); // grab the nth index of ALPHA_TRANSLATE; 0=A, 1=B, etc.
		row = row + 1; // change the first index back to 1
		
		return String.format("%c%d",colLetter,row);
	}

	/**
	* Takes random coordinates and assigns positions for the computer to occupy.
	*/
	private static void populateComputerPositions()
	{
		int computer_shipsLeft = TOTAL_SHIPS_PER_PLAYER;
		int computer_grenadesLeft = TOTAL_GRENADES_PER_PLAYER;

		while(computer_shipsLeft > 0)
		{
			int[] coordinates = randomCell();
			int x = coordinates[0];
			int y = coordinates[1];
			if(gameBoard[x][y].getType() == "_")
			{
				gameBoard[x][y].occupyCell("Ship", "Computer");
				computer_shipsLeft--;
			}
		}

		while(computer_grenadesLeft > 0)
		{
			int[] coordinates = randomCell();
			int x = coordinates[0];
			int y = coordinates[1];
			if(gameBoard[x][y].getType() == "_")
			{
				gameBoard[x][y].occupyCell("Grenade", "Computer");
				computer_grenadesLeft--;
			}

		}
	}

	/**
	* Asks the human player to type in positions for their ships & grenades.
	*/
	private static void getUserChoices()
	{
		int human_shipsLeft = TOTAL_SHIPS_PER_PLAYER;
		int human_grenadesLeft = TOTAL_GRENADES_PER_PLAYER;

		while(human_shipsLeft > 0)
		{
			System.out.printf("PLACING SHIPS: %d SHIPS REMAIN\n", human_shipsLeft);
			System.out.printf("Type in a coordinate of form [A-H][1-8], without brackets. Example: B3, C7.\n");

			String userSelection = userInput.nextLine();
			if(isInputValid(userSelection) == false)
			{
				System.out.printf("\n*** Input out of bounds! Please try again. ***\n\n");				
			} else if( (isInputValid(userSelection) == true) )
				{
					int[] targetCoordinates = letterNumberToRowAndColumn(userSelection);
					int row = targetCoordinates[0];
					int column = targetCoordinates[1];		

					if( (gameBoard[row][column].isOccupied() == true) )
					{
						System.out.println("\n*** Cell already occupied! Please try again. ***\n\n");
					} else 
						{
							gameBoard[row][column].occupyCell("Ship", "Human");
							human_shipsLeft--;
						}
				}
		}


		while(human_grenadesLeft > 0)
		{
			System.out.printf("PLACING GRENADES: %d GRENADES REMAIN\n", human_grenadesLeft);
			System.out.printf("Type in a coordinate of form [A-H][1-8], without brackets. Example: B3, C7.\n");
			
			String userSelection = userInput.nextLine();
			if(isInputValid(userSelection) == true)
			{
				int[] targetCoordinates = letterNumberToRowAndColumn(userSelection);
				int row = targetCoordinates[0];
				int column = targetCoordinates[1];

				gameBoard[row][column].occupyCell("Grenade","Human");
				human_grenadesLeft--;
			} else 
				{
					System.out.printf("\n*** Incorrect input or cell already occupied!! Please try again. ***\n\n");
				}
		}
		
	}

	// PUBLIC METHODS //

	/**
	* Calculates how many ships the computer player has left on the board.
	* <p>
	* @return int 	Returns an int from 0-6 corresponding to the number of ships the computer player has remaining.
	*/
	public int getComputerRemainingShips()
	{
		int computerShipsLeft = 0;

		for(int row = 0; row < gameBoard.length; row++)
				for(int col = 0; col < gameBoard[row].length; col++)
				{
					if( (gameBoard[row][col].hasShip() == true) && (gameBoard[row][col].getOwner() == "Computer") && !(gameBoard[row][col].isPositionCalled() == true) )
						computerShipsLeft++;
				}	
		return computerShipsLeft;
	}

	/**
	* Calculates how many ships the human player has left on the board.
	* <p> 
	* @return int 	Returns an int from 0-6 corresponding to the number of ships the human player has remaining.
	*/
	public int getHumanRemainingShips()
	{
		int humanShipsLeft = 0;
		for(int row = 0; row < gameBoard.length; row++)
				for(int col = 0; col < gameBoard[row].length; col++)
				{
					if( (gameBoard[row][col].hasShip() == true) && (gameBoard[row][col].getOwner() == "Human") && !(gameBoard[row][col].isPositionCalled() == true) )
						humanShipsLeft++;
				}	
		return humanShipsLeft;
	}

	/**
	* Makes the computer play a single turn and fire at random coordinates. 
	*/
	public static void computerTurn()
	{
		if(didComputerHitGrenade == true)
		{
			didComputerHitGrenade = false;
			System.out.println("Computer misses a turn.");
			return;
		}

		int[] coordinates = randomCell();
		int row = coordinates[0];
		int column = coordinates[1];
		System.out.printf("Computer targets cell %s", rowAndColumnToLetterNumber(coordinates));
		String hitWhat = fireRocket(row,column, "Computer");
		if(hitWhat == "Grenade")
			didComputerHitGrenade = true;

	}
	
	/** 
	* Prompts the user for a set of coordinates and fires at it if the set is deemed valid.
	*/
	public static void getUserTarget()
	{
		if(didHumanHitGrenade == true)
		{
			didHumanHitGrenade = false;
			System.out.println("Human misses a turn.");
			return;
		}

		Boolean userChoiceIsValid = false;
		String userTarget = "";
		int[] coordinates;
		int row, column;
		do 
		{
			showGameBoard();
			System.out.println("Enter a coordinate to target in the form [A-H][1-8]. Ex. A3, B5 : ");
			userTarget = userInput.nextLine();
			if(isInputValid(userTarget) == true)
			{
				userChoiceIsValid = true;
				coordinates = letterNumberToRowAndColumn(userTarget);
				row = coordinates[0];
				column = coordinates[1];
				String hitWhat = fireRocket(row,column, "Human");
				if(hitWhat == "Grenade")
				{
					didHumanHitGrenade = true;
				}
			} else
				{
					System.out.println("Invalid choice, please make another selection.");
				}

		} while(userChoiceIsValid == false);
	}

	/**
	* Dumps the contents of the game board to the console. Mainly for debugging,
	* but used once at the end to show the owner and position of all ships/grenades
	* in a clearer form than the showGameBoard() method. 
	*/
	public static void dumpRows()
	{
		System.out.println("\nGrenade and Ship Placement:\n");

		// Prints the row letters.

		System.out.printf("   ");
		for(int row = 0; row < gameBoard.length; row++)
		{
			System.out.printf(ALPHA_TRANSLATE.charAt(row) + "  ");
		}
		System.out.printf("\n");

		// Prints the column numbers and the cells contents.

		for(int row = 0; row < gameBoard.length; row++)
		{
			
			System.out.printf((row+1) + " ");
			for(int col = 0; col < gameBoard[row].length; col++)
			{
				System.out.printf(gameBoard[row][col].getOwner().substring(0,1));
				System.out.printf(gameBoard[row][col].getType().substring(0,1));
				System.out.printf(" ");
			}
			System.out.println("");
		}
	}

	/**
	* Displays the game board during gameplay.
	*/
	public static void showGameBoard()
	{

		System.out.printf("  ");
		for(int row = 0; row < gameBoard.length; row++)
		{
			System.out.printf(ALPHA_TRANSLATE.charAt(row) + " ");
		}
		System.out.printf("\n");

		// Prints the column numbers and the cells contents.

		for(int row = 0; row < gameBoard.length; row++)
		{
			
			System.out.printf((row+1) + " ");
			for(int col = 0; col < gameBoard[row].length; col++)
			{
				System.out.print(gameBoard[row][col]);
				System.out.printf(" ");		
			}	
			System.out.println("");
		}
		
	}

}