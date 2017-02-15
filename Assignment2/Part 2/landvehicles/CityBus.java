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
  private long routeNum;
  private int beginOperationYear;
  private String lineName;
  private String driverName;

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
		this.setRouteNum(routeNum);
		this.setBeginOperationYear(beginOperationYear);
		this.setLineName(lineName);
		this.setDriverName(driverName);
	}

  /**
  * CityBus Copy Constructor
  */
  public CityBus(CityBus cityBus)
  {
    this(cityBus.getTicketPrice(), cityBus.getNumStops(), cityBus.getRouteNum(), cityBus.getBeginOperationYear(), cityBus.getLineName(), cityBus.getDriverName() );
  }

  // Getters and Setters

	/**
	* Returns value of routeNum
	* @return long
	*/
	public long getRouteNum() {
		return routeNum;
	}

	/**
	* Sets new value of routeNum
	* @param long
	*/
	public void setRouteNum(long routeNum) {
		this.routeNum = routeNum;
	}

	/**
	* Returns value of beginOperationYear
	* @return int
	*/
	public int getBeginOperationYear() {
		return beginOperationYear;
	}

	/**
	* Sets new value of beginOperationYear
	* @param int
	*/
	public void setBeginOperationYear(int beginOperationYear) {
		this.beginOperationYear = beginOperationYear;
	}

	/**
	* Returns value of lineName
	* @return String
	*/
	public String getLineName() {
		return lineName;
	}

	/**
	* Sets new value of lineName
	* @param String
	*/
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	* Returns value of driverName
	* @return String
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

      CityBus toCompare = (CityBus) obj;
      if( (toCompare.getTicketPrice() == this.getTicketPrice()) && (toCompare.getNumStops() == this.getNumStops()) && (toCompare.getRouteNum() == this.getRouteNum()) && (toCompare.getBeginOperationYear() == this.getBeginOperationYear()) && (toCompare.getLineName() == this.getLineName()) && (toCompare.getDriverName() == this.getDriverName()))
      {
        return true;
      }
      else return false;
  }

	/**
	* Create string representation of CityBus for printing
	* @return String
	*/

	public String toString() {
    	String returnString = String.format("This city bus has %d stops, and costs $%.2f.  Its route type is %d, currently being driven by %s on line %s, and it has been in operation since %d.", getNumStops(), getTicketPrice(), getRouteNum(), getDriverName(), getLineName(), getBeginOperationYear());
    	return returnString;
	}
}
