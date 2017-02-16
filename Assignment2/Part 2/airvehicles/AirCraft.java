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

  /* because our enums are now package-private instead of public
   * we can't import them into TransitDriver and thus
   * can't directly use them in our parametrized constructor.
   *
   * The modification below is so we can pass String variables to our constructor
   * instead of instances of classTypes/maintenanceTypes. See also setMaintenanceTypes/setClassTypes
   */

  enum classTypes
  {
    Helicopter("Helicopter"),
    Airline("Airline"),
    Glider("Glider"),
    Balloon("Balloon");

    private String value;

    private classTypes(String value)
    {
      this.value = value;
    }

    public String getValue()
    {
      return value;
    }

  };

  enum maintenanceTypes
  {
    Weekly("Weekly"),
    Monthly("Monthly"),
    Yearly("Yearly");

    private String value;

    private maintenanceTypes(String value)
    {
      this.value = value;
    }

    public String getValue()
    {
      return value;
    }

  };

  classTypes classType;
  maintenanceTypes maintenanceType;

  /**
   * Default empty AirCraft constructor
   */
  public AirCraft()
  {
    super();
  }

  /**
   * Default parametrized AirCraft constructor
   */
  public AirCraft(double ticketPrice, int numStops, String maintenanceType, String classType)
  {
    this.setTicketPrice(ticketPrice);
    this.setNumStops(numStops);
    this.setMaintenanceType(maintenanceType);
    this.setClassType(classType);
  }

  /**
   *  AirCraft copy constructor
   */
   public AirCraft(AirCraft aircraft)
   {
     this(aircraft.getTicketPrice(), aircraft.getNumStops(), aircraft.getMaintenanceType(), aircraft.getClassType() );
   }

  /**
   * Clone method added to accomodate class polymorphism
   */
   public AirCraft clone()
   {
    return new AirCraft(this);
   }

  /**
	* Returns value of maintenanceType
	*/
  public String getMaintenanceType()
  {
    return maintenanceType.getValue();
  }
  /**
  * Sets new value of maintenanceType
  * Because both of our enums are now pcakage-private, we have to use a type that
  * is shared both by this class and the parent class, i.e. String
  */
  public void setMaintenanceType(String maintenanceType)
  {
    switch(maintenanceType)
    {
      case "Weekly":
        this.maintenanceType = maintenanceTypes.Weekly;
        break;
      case "Monthly":
        this.maintenanceType = maintenanceTypes.Monthly;
        break;
      case "Yearly":
        this.maintenanceType = maintenanceTypes.Yearly;
        break;
      default:
        System.out.println("Error");
        System.exit(255);
    }
  }

  /**
	* Returns value of classType
	*/
  public String getClassType()
  {
    return classType.getValue();
  }

  /**
	* Sets new value of classType
  * Because both of our enums are now package-private, we have to use a type that
  * is shared both by this class and the parent class, i.e. String
	*/
  public void setClassType(String classType)
  {
    switch(classType)
    {
      case "Helicopter":
        this.classType = classTypes.Helicopter;
        break;
      case "Airline":
        this.classType = classTypes.Airline;
        break;
      case "Glider":
        this.classType = classTypes.Glider;
        break;
      case "Balloon":
        this.classType = classTypes.Balloon;
        break;
      default:
        System.out.println("Error");
        System.exit(255);
    }
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
      // Getter and setter methods are required here, because we no longer implicitly inherit parent variables (due to private modifier)
      if( (toCompare.getTicketPrice() == this.getTicketPrice()) && (toCompare.getNumStops() == this.getNumStops()) && (toCompare.getMaintenanceType() == this.getMaintenanceType()) && (toCompare.getClassType() == this.getClassType()) )
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
      // Getter and setter methods are required here, because we no longer implicitly inherit parent variables (due to private modifier)
    	String returnString = String.format("This aircraft has %d stops, and costs $%.2f.  Its maintenance type is %s and its class type is %s.", getNumStops(), getTicketPrice(), getMaintenanceType(), getClassType());
    	return returnString;
	}


}
