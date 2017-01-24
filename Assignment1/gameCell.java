public class gameCell
{
	private String type;
	private String owner;
	private Boolean positionCalled;
	private Boolean occupied;

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

	// getters

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

	// setters

	public void setType(String type)
	{
		this.type = type;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public void setPositionUsed()
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

	// overridden 

	public String toString()
	{
		String returnString = "";

		if( (isPositionCalled() == true) && (isOccupied() == true) )
		{
			if( (hasShip() == true) && getOwner() == "Human" )
				returnString = "s";
			if( (hasShip() == true) && getOwner() == "Computer" )
				returnString = "S";
			if( (hasGrenade() == true) && getOwner() == "Human" )
				returnString = "g";
			if( (hasGrenade() == true && getOwner() == "Computer") )
				returnString = "G";
		} else if( (isPositionCalled() == true)  && (isOccupied() == false) )
				{
					returnString = "*";
				}
			else returnString = "_";
		return returnString;
	}


}