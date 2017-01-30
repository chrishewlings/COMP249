/** Driver class for Battleship game.
 *@version 0.1
 * Names(s) and ID(s)	Chris Hewlings: 29145958  /  Leo Sudarma 40046196
 * COMP249
 * Assignment # 		Assignment #1
 * Due Date				February 1, 2017
 */

public class ShipDriver
{
	
	/**
	* Executes the main game loop.
	* <p>
	* @param gameObj	Takes a parameter of type Battleship
	* @return String	Returns a String value with the name of the winner.
	*/
	public static String gameLoop(Battleship gameObj)
	{
		Boolean winFlag = false;
		String winningPlayer;
		do 
		{
			gameObj.getUserTarget();
			gameObj.computerTurn();

			if( (gameObj.getComputerRemainingShips() == 0) || (gameObj.getHumanRemainingShips() == 0) )
				winFlag = true;

		} while(winFlag == false);
		winningPlayer = (gameObj.getComputerRemainingShips() == 0) ? "Human" : "Computer";
		return winningPlayer;

	}

	public static void main(String[] args)
	{
		Battleship gameObj = new Battleship();
		
		String gameWinner = gameLoop(gameObj);
		System.out.printf("Thanks for playing! The winner of this round was : %s\n", gameWinner);
	}
}
