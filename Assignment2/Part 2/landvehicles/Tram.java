/** Defines a Tram subclass extending CityBus.
 * 
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */

package landvehicles;

public class Tram extends CityBus {
    private int maxSpeed;

	/**
	* Default empty Tram constructor
	*/
	public Tram() {
		super();
	}

	/**
	* Default Tram constructor
	*/
  	public Tram(double ticketPrice, int numStops, long routeNum, int beginOperationYear, String lineName, String driverName, int maxSpeed) {
		super(ticketPrice, numStops, routeNum, beginOperationYear, lineName, driverName);
		this.setMaxSpeed(maxSpeed);
 	}

  	/**
  	* Tram Copy constructor
  	*/
  	public Tram(Tram tram){
    	this(tram.getTicketPrice(), tram.getNumStops(), tram.getRouteNum(), tram.getBeginOperationYear(), tram.getLineName(), tram.getDriverName(), tram.getMaxSpeed());
  	}

	/**
	* Returns value of maxSpeed
	* @return int
	*/
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	* Sets new value of maxSpeed
	* @param int
	*/
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
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

      Tram toCompare = (Tram) obj;
      if( (toCompare.getTicketPrice() == this.getTicketPrice()) && (toCompare.getNumStops() == this.getNumStops()) && (toCompare.getRouteNum() == this.getRouteNum()) && (toCompare.getBeginOperationYear() == this.getBeginOperationYear()) && (toCompare.getLineName() == this.getLineName()) && (toCompare.getDriverName() == this.getDriverName()) && (toCompare.getMaxSpeed() == this.getMaxSpeed() ))
      {
        return true;
      }
      else return false;
  }

	/**
	* Create string representation of Tram for printing
	* @return String
	*/
	public String toString() {
    	String returnString = String.format("This tram has %d stops, and costs $%.2f. Its maximum speed is %dkm/h.  Its route type is %d, currently being driven by %s on line %s, and it has been in operation since %d.", getNumStops(), getTicketPrice(), getMaxSpeed(), getRouteNum(), getDriverName(), getLineName(), getBeginOperationYear());
    	return returnString;
	}
}
