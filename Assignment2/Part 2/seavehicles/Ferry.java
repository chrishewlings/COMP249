/** Defines a Ferry subclass extending PublicTransportation,
 * 
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */

package seavehicles;
import publictransportation.PublicTransportation;

public class Ferry extends PublicTransportation {
  private int buildYear;
  private String shipName;

	/**
	* Default empty Ferry constructor
	*/
	public Ferry() {
		super();
	}

	/**
	* Default Parametrized Ferry constructor
	*/
	public Ferry(double ticketPrice, int numStops, int buildYear, String shipName) {
		super(ticketPrice, numStops);
		this.setBuildYear(buildYear);
		this.setShipName(shipName);
	}

 	/**
	* Ferry Copy Constructor
	*/
	public Ferry(Ferry ferry)
	{
	  this(ferry.getTicketPrice(), ferry.getNumStops(), ferry.getBuildYear(), ferry.getShipName());
	}

	/**
	* Returns value of buildYear
	* @return int
	*/
	public int getBuildYear()
	{
		return buildYear;
	}

	/**
	* Sets new value of buildYear
	* @param int
	*/
	public void setBuildYear(int buildYear)
	{
		this.buildYear = buildYear;
	}

	/**
	* Returns value of shipName
	* @return String
	*/
	public String getShipName()
	{
		return shipName;
	}

	/**
	* Sets new value of shipName
	* @param String
	*/
	public void setShipName(String shipName)
	{
		this.shipName = shipName;
	}

	/**
  	* Override equals() method from Object class
	* @return Boolean
	*/
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
	  	return false;
		}
		else if(obj.getClass() != this.getClass() )
	  	{
	    	return false;
	  	}

	 	Ferry toCompare = (Ferry) obj;
	  	if( (toCompare.getTicketPrice() == this.getTicketPrice()) && (toCompare.getNumStops() == this.getNumStops()) && (toCompare.getBuildYear() == this.getBuildYear()) && (toCompare.getShipName() == this.getShipName()) )
	  	{
	    	return true;
	  	}
	  	else return false;
	}

	/**
	* Create string representation of Ferry for printing
	* @return String
	*/
	public String toString()
	{
	   	String returnString = String.format("This ferry, named %s, has %d stops, and costs $%.2f.  It was built in %d.", getShipName(), getNumStops(), getTicketPrice(), getBuildYear() );
	   	return returnString;
	}
}
