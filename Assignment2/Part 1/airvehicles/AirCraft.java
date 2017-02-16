/** Defines a AirCraft subclass extending PublicTransportation
 * containing 2 enumerated types (classTypes and maintenanceTypes)
 *
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */

package airvehicles;
import publictransportation.PublicTransportation;

public class AirCraft extends PublicTransportation {

  public enum classTypes { Helicopter,Airline,Glider,Balloon; };
  public enum maintenanceTypes { Weekly,Monthly,Yearly; };

  classTypes classType;
  maintenanceTypes maintenanceType;

  public AirCraft()
  {
    super();
  }

  public AirCraft(double ticketPrice, int numStops, maintenanceTypes maintenanceType, classTypes classType)
  {
    this.ticketPrice = ticketPrice;
    this.numStops = numStops;
    this.maintenanceType = maintenanceType;
    this.classType = classType;
  }

  /**
	* Returns value of maintenanceType
	*/
  public maintenanceTypes getMaintenanceType()
  {
    return maintenanceType;
  }
  /**
  * Sets new value of maintenanceType
  */
  public void setMaintenanceType(maintenanceTypes maintenanceType)
  {
    this.maintenanceType = maintenanceType;
  }

  /**
	* Returns value of classType
	*/
  public classTypes getClassType()
  {
    return classType;
  }

  /**
	* Sets new value of classType
	*/
  public void setClassType(classTypes classType)
  {
    this.classType = classType;
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

      AirCraft toCompare = (AirCraft) obj;
      // No need for getter and setter methods here, because we inherit protected variables from parent class(es).
      if( (toCompare.ticketPrice == this.ticketPrice) && (toCompare.numStops == this.numStops) && (toCompare.maintenanceType == this.maintenanceType) && (toCompare.classType == this.classType) )
      {
        return true;
      }
      else return false;
  }

	/**
	* Create string representation of AirCraft for printing
	* @return Stringified representation of AirCraft object.
	*/

	public String toString()
	{
      // No need for getter and setter methods here, because we inherit protected variables from parent class(es).
    	String returnString = String.format("This aircraft has %d stops, and costs $%.2f.  Its maintenance type is %s and its class type is %s.", numStops, ticketPrice, maintenanceType, classType);
    	return returnString;
	}


}
