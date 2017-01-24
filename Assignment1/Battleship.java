import java.util.Scanner;
import java.util.Random;

	/** 
	* @author	Chris Hewlings <chris.hewlings@gmail.com>
	*/

public class Battleship
{
	// Defines constants for clarity.
	final static int TOTAL_SHIPS_PER_PLAYER = 6;
	final static int TOTAL_GRENADES_PER_PLAYER = 4;
	final static int GAMEBOARD_ROWS = 8;
	final static int GAMEBOARD_COLUMNS = 8;
	final static String ALPHA_TRANSLATE = "abcdefgh";

	/** Initialize the random number generator, as used by randomCell() method.
	*   It's initialized here instead of in the randomCell() method to avoid
	*   draining entropy excessively with multiple calls.
	*/
	static Random rng = new Random();	

	//Open a handle for keyboard input.
	static Scanner userInput = new Scanner(System.in);

	/**
	* Defines a new game board of gameCell objects, which will be acted upon by our other methods.
	* This does not fill the game board with anything, so use the initGameBoard() method to do so.
	*/
	static gameCell[][] gameBoard = new gameCell[GAMEBOARD_ROWS][GAMEBOARD_COLUMNS];

	/**
	* Returns a random set of x,y coordinates within bounds.
	* <p>
	* @return Description Returns an two element array of int corresponding to an X and Y value.
	*/
	public static int[] randomCell()
	{
		int row = rng.nextInt(GAMEBOARD_ROWS);
		int column = rng.nextInt(GAMEBOARD_ROWS);
		int[] returnCoordinates = {row, column};
		return returnCoordinates;
	}

	/**
	* Takes random coordinates and assigns positions for the computer to occupy.
	*/
	public static void populateComputerPositions()
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
				//gameBoard[x][y].setType("Ship");
				//gameBoard[x][y].setOwner("Computer");
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
				//gameBoard[x][y].setType("Grenade");
				//gameBoard[x][y].setOwner("Computer");
				gameBoard[x][y].occupyCell("Grenade", "Computer");
				computer_grenadesLeft--;
			}

		}
	}

	/**
	* Fills a new game board with empty cells.
	*/
	public static void initGameBoard()
	{
		for(int row = 0; row < gameBoard.length  ; row++)
			for(int col = 0; col < gameBoard[row].length; col++)
				gameBoard[row][col] = new gameCell();
	}

	/**
	* Uses Java's built-in regular expressions instead of stripping off the first index of string, converting it to char, etc...
	* <p>
	* @return Description Returns true if X position is between A-H and Y position is between 1-8, else returns false.
	*/
	static Boolean isInputValid(String whatPosition)
	{
		String pattern = "[a-hA-H][1-8]";
		
		if(whatPosition.matches(pattern)) // checks to see if the users input matches the bounds of the problem
		{
			int[] coordinates = parseCoordinates(whatPosition);
			int row = coordinates[0];
			int column = coordinates[1];


			if(gameBoard[row][column].isOccupied() == false) // makes sure that cell isn't already occupied. 
				return true;
			else return false;
		}
		else return false;
	}


	/**
	* Takes the users' input as a String, runs it through validation via isInputValid(), and translates it.
	* <p>
	* @return Description Returns an array of two int corresponding to row & column on the game board.
	*/
	public static int[] parseCoordinates(String s)
	{
		char firstCharacter = Character.toLowerCase(s.charAt(0));
		int colNumber = ALPHA_TRANSLATE.indexOf(firstCharacter);

		int rowNumber = Integer.valueOf(s.substring(1)).intValue() - 1;
		int[] returnArray = { rowNumber, colNumber };

		return returnArray;
	}
	/**
	* Asks the human player to type in positions for their ships & grenades.
	*/
	public static void getUserChoices()
	{
		int human_shipsLeft = TOTAL_SHIPS_PER_PLAYER;
		int human_grenadesLeft = TOTAL_GRENADES_PER_PLAYER;

		while(human_shipsLeft > 0)
		{
			System.out.printf("PLACING SHIPS: %d SHIPS REMAIN\n", human_shipsLeft);
			System.out.printf("Type in a coordinate of form [A-H][1-8], without brackets. Example: B3, C7.\n");

			String userSelection = userInput.nextLine();
			if(isInputValid(userSelection) == true)
			{
				int[] targetCoordinates = parseCoordinates(userSelection);
				int row = targetCoordinates[0];
				int column = targetCoordinates[1];

				gameBoard[row][column].occupyCell("Ship", "Human");
				human_shipsLeft--;
			} else 
				{
					System.out.printf("\n*** Incorrect input, or cell already occupied! Please try again. ***\n\n");
				}
		}


		while(human_grenadesLeft > 0)
		{
			System.out.printf("PLACING GRENADES: %d GRENADES REMAIN\n", human_grenadesLeft);
			System.out.printf("Type in a coordinate of form [A-H][1-8], without brackets. Example: B3, C7.\n");
			
			String userSelection = userInput.nextLine();
			if(isInputValid(userSelection) == true)
			{
				int[] targetCoordinates = parseCoordinates(userSelection);
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

	public static void getUserTarget()
	{
		Boolean userChoiceIsValid = false;
		String userTarget = "";
		int[] coordinates;
		int row, column;
		do 
		{
			System.out.println("Enter a coordinate to target in the form [A-H][1-8]. Ex. A3, B5 : ");
			userTarget = userInput.nextLine();
			if(isInputValid(userTarget) == true)
			{
				coordinates = parseCoordinates(userTarget);
				row = coordinates[0];
				column = coordinates[1];
				gameBoard[row][column].setPositionUsed();
			}
		} while(userChoiceIsValid == false);
	}
	/**
	* Dumps the contents of the game board to the console. Mainly for debugging. 
	*/
	public static void dumpRows(gameCell[][] gameBoard)
	{
		

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
	public static void showGameBoard(gameCell[][] gameBoard)
	{

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
				System.out.print(gameBoard[row][col]);
				System.out.printf(" ");		
			}	
			System.out.println("");
		}
		
	}



	public static void main(String[] args)
	{
		
		initGameBoard();
		getUserChoices();
		populateComputerPositions();
		//dumpRows(gameBoard);
		showGameBoard(gameBoard);
		
	}
}