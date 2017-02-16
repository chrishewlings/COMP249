/** Defines a CityBus subclass extending PublicTransportation,
 * and parent to Tram and Metro, containing methods and attributes common to subclasses
 * to itself and its two subclasses.
 *
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */

package landvehicles;
import publictransportation.PublicTransportation;


public class CityBus extends PublicTransportation {
  protected long routeNum;
  protected int beginOperationYear;
  protected String lineName;
  protected String driverName;

	/**
	* Default empty CityBus constructor
	*/
	public CityBus() {
		super();
	}

	/**
	* Default Parametrized CityBus constructor
	*/
	public CityBus(double ticketPrice, int numStops, long routeNum, int beginOperationYear, String lineName, String driverName) {
		super(ticketPrice, numStops);
		this.routeNum = routeNum;
		this.beginOperationYear = beginOperationYear;
		this.lineName = lineName;
		this.driverName = driverName;
	}

  /**
  * CityBus Copy Constructor
  */
  public CityBus(CityBus cityBus)
  {
    this(cityBus.ticketPrice, cityBus.numStops, cityBus.routeNum, cityBus.beginOperationYear, cityBus.lineName, cityBus.driverName);
  }

  // Getters and Setters

	/**
	* Returns value of routeNum
	*/
	public long getrouteNum() {
		return routeNum;
	}

	/**
	* Sets new value of routeNum
	*/
	public void setrouteNum(long routeNum) {
		this.routeNum = routeNum;
	}

	/**
	* Returns value of beginOperationYear
	*/
	public int getBeginOperationYear() {
		return beginOperationYear;
	}

	/**
	* Sets new value of beginOperationYear
	*/
	public void setBeginOperationYear(int beginOperationYear) {
		this.beginOperationYear = beginOperationYear;
	}

	/**
	* Returns value of lineName
	*/
	public String getLineName() {
		return lineName;
	}

	/**
	* Sets new value of lineName
	*/
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	* Returns value of driverName
	*/
	public String getDriverName() {
		return driverName;
	}

	/**
	* Sets new value of driverName
	* @param
	*/
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

  /**
  * Override equals() method from Object class
  * @return Returns true if obj1 == obj2, otherwise false.
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

      CityBus toCompare = (CityBus) obj;
      // No need for getter and setter methods here, because we inherit protected variables from parent class(es).
      if( (toCompare.ticketPrice == this.ticketPrice) && (toCompare.numStops == this.numStops) && (toCompare.routeNum == this.routeNum) && (toCompare.beginOperationYear == this.beginOperationYear) && (toCompare.lineName == this.lineName) && (toCompare.driverName == this.driverName))
      {
        return true;
      }
      else return false;
  }

	/**
	* Create string representation of CityBus for printing
	* @return Stringified representation of CityBus object.
	*/

	public String toString() {
      // No need for getter and setter methods here, because we inherit protected variables from parent class(es).
    	String returnString = String.format("This city bus has %d stops, and costs $%.2f.  Its route number is %d, currently being driven by %s on line %s, and it has been in operation since %d.", numStops, ticketPrice, routeNum, driverName, lineName, beginOperationYear);
    	return returnString;
	}
}
