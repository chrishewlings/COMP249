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
		this.maxSpeed = maxSpeed;
 	}

  /**
  * Tram Copy constructor
  */
  public Tram(Tram tram){
    this(tram.ticketPrice, tram.numStops, tram.routeNum, tram.beginOperationYear, tram.lineName, tram.driverName, tram.maxSpeed);
  }

	/**
	* Returns value of maxSpeed
	* @return
	*/
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	* Sets new value of maxSpeed
	* @param
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
      if( (toCompare.ticketPrice == this.ticketPrice) && (toCompare.numStops == this.numStops) && (toCompare.routeNum == this.routeNum) && (toCompare.beginOperationYear == this.beginOperationYear) && (toCompare.lineName == this.lineName) && (toCompare.driverName == this.driverName) && (toCompare.maxSpeed == this.maxSpeed))
      {
        return true;
      }
      else return false;
  }

	/**
	* Create string representation of Tram for printing
	* @return
	*/
	public String toString() {
    	String returnString = String.format("This tram has %d stops, and costs $%.2f. Its maximum speed is %dkm/h.  Its route type is %d, currently being driven by %s on line %s, and it has been in operation since %d.", numStops, ticketPrice, maxSpeed, routeNum, driverName, lineName, beginOperationYear);
    	return returnString;
	}
}
