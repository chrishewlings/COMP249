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
		this.numVehicles = numVehicles;
    this.nameOfCity = nameOfCity;
	}

  /**
  * Metro Copy constructor
  */
  public Metro(Metro metro){
    this(metro.ticketPrice, metro.numStops, metro.routeNum, metro.beginOperationYear, metro.lineName, metro.driverName, metro.numVehicles, metro.nameOfCity);
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
        // No need for getter and setter methods here, because we inherit protected variables from parent class(es).
        if( (toCompare.ticketPrice == this.ticketPrice) && (toCompare.numStops == this.numStops) && (toCompare.routeNum == this.routeNum) && (toCompare.beginOperationYear == this.beginOperationYear) && (toCompare.lineName == this.lineName) && (toCompare.driverName == this.driverName) && (toCompare.numVehicles == this.numVehicles) && (toCompare.nameOfCity == this.nameOfCity) )
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
    // No need for getter and setter methods here, because we inherit protected variables from parent class(es).
    String returnString = String.format("This %s city metro has %d stops, and costs $%.2f. It has %d cars.  Its route number is %d, currently being driven by %s on line %s, and it has been in operation since %d.", nameOfCity, numStops, ticketPrice, numVehicles, routeNum, driverName, lineName, beginOperationYear);
    return returnString;
	}
}
