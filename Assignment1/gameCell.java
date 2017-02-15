/** Defines a gameCell object to be used in the 2D array gameBoard defined in class Battleship
 *@version 0.1
 * Names(s) and ID(s)	Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment # 		Assignment #1
 * Due Date				February 1, 2017
 */


public class gameCell
{
	private String type;
	private String owner;
	private Boolean positionCalled;
	private Boolean occupied;

	// CONSTRUCTORS

	public gameCell()
	{
		type = "_";
		owner = "_";
		positionCalled = false;
		occupied = false;
	}

	public gameCell(String type, String owner)
	{
		this.type = type;
		this.owner = owner;
		this.positionCalled = false;
		this.occupied = false;
	}

	// GETTERS

	public String getType()
	{
		return this.type;
	}

	public String getOwner()
	{
		return this.owner;
	}

	public Boolean isPositionCalled()
	{
		return this.positionCalled;
	}

	public Boolean isOccupied()
	{
		return this.occupied;
	}

	public Boolean hasShip()
	{
		if(this.type == "Ship")
			return true;
		else return false;
	}

	public Boolean hasGrenade()
	{
		if(this.type == "Grenade")
			return true;
		else return false;
	}

	// SETTERS

	public void setType(String type)
	{
		this.type = type;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public void setPositionCalled()
	{
		this.positionCalled = true;
	}

	public void setOccupied()
	{
		this.occupied = true;
	}

	public void occupyCell(String type, String owner)
	{
		if(this.isOccupied() == false)
		{
			this.setType(type);
			this.setOwner(owner);
			this.setOccupied();
		} else
			{
				System.out.println("Cell already occupied.  Please select again.");
			}
	}

	// OVERRIDDEN METHODS 

	public String toString()
	{
		String returnString = "";

		if( (this.isPositionCalled() == true) && (this.isOccupied() == true) )
		{
			if( (this.hasShip() == true) && this.getOwner() == "Human" )
				returnString = "s";
			if( (this.hasShip() == true) && this.getOwner() == "Computer" )
				returnString = "S";
			if( (this.hasGrenade() == true) && this.getOwner() == "Human" )
				returnString = "g";
			if( (this.hasGrenade() == true && this.getOwner() == "Computer") )
				returnString = "G";
		} else if( (this.isPositionCalled() == true)  && (this.isOccupied() == false) )
				{
					returnString = "*";
				}
			else returnString = "_";
		return returnString;
	}


}