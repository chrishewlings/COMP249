/** Defines a Metro subclass extending CityBus.
 *
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */


package landvehicles;
import publictransportation.PublicTransportation;

public class Metro extends CityBus {
  private int numVehicles;
  private String nameOfCity;

	/**
	* Default empty Metro constructor
	*/
	public Metro() {
		super();
	}

  /**
	* Default Parametrized Metro constructor
	*/
	public Metro(double ticketPrice, int numStops, long routeNum, int beginOperationYear, String lineName, String driverName, int numVehicles, String nameOfCity) {
	  super(ticketPrice, numStops, routeNum, beginOperationYear, lineName, driverName);
	  this.setNumVehicles(numVehicles);
      this.setNameOfCity(nameOfCity);
	}

  /**
  * Metro Copy constructor
  */
  public Metro(Metro metro){
    this(metro.getTicketPrice(), metro.getNumStops(), metro.getRouteNum(), metro.getBeginOperationYear(), metro.getLineName(), metro.getDriverName(), metro.getNumVehicles(), metro.getNameOfCity());
  }


	/**
	* Returns value of numVehicles
	*/
	public int getNumVehicles() {
		return numVehicles;
	}

	/**
	* Sets new value of numVehicles
	*/
	public void setNumVehicles(int numVehicles) {
		this.numVehicles = numVehicles;
	}

	/**
	* Returns value of nameOfCity
	*/
	public String getNameOfCity() {
		return nameOfCity;
	}

	/**
	* Sets new value of nameOfCity
	*/
	public void setNameOfCity(String nameOfCity) {
		this.nameOfCity = nameOfCity;
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
      } else if(obj.getClass() != this.getClass() )
        {
          return false;
        }

        Metro toCompare = (Metro) obj;
        // Getter and setter methods are required here, because we no longer implicitly inherit parent variables (due to private modifier)
        if( (toCompare.getTicketPrice() == this.getTicketPrice()) && (toCompare.getNumStops() == this.getNumStops()) && (toCompare.getRouteNum() == this.getRouteNum()) && (toCompare.getBeginOperationYear() == this.getBeginOperationYear()) && (toCompare.getLineName() == this.getLineName()) && (toCompare.getDriverName() == this.getDriverName()) && (toCompare.getNumVehicles() == this.getNumVehicles()) && (toCompare.getNameOfCity() == this.getNameOfCity()) )
        {
          return true;
        }
        else return false;
    }

	/**
	* Create string representation of Metro for printing
	* @return Stringified representation of Metro object.
	*/
	public String toString() {
    // Getter and setter methods are required here, because we no longer implicitly inherit parent variables (due to private modifier)
    String returnString = String.format("This %s city metro has %d stops, and costs $%.2f. It has %d cars.  Its route type is %d, currently being driven by %s on line %s, and it has been in operation since %d.", getNameOfCity(), getNumStops(), getTicketPrice(), getNumVehicles(), getRouteNum(), getDriverName(), getLineName(), getBeginOperationYear());
    return returnString;
	}
}
