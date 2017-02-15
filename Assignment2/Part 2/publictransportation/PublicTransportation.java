/** Defines a PublicTransportation base class which is shared 
 * by multiple packages (airvehicles, landvehicles, seavehicles),
 * as well as any pertinent shared attributes and methods.
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */

package publictransportation;

public class PublicTransportation {
  private double ticketPrice;
  private int numStops;

  // Constructors

	/**
	* Default empty PublicTransportation constructor
	*/
	public PublicTransportation() {
    	this.setTicketPrice(0.0);
		this.setNumStops(0);
	}

	/**
	* Parametrized PublicTransportation constructor
	*/
	public PublicTransportation(double ticketPrice, int numStops) {
		this.setTicketPrice(ticketPrice);
		this.setNumStops(numStops);
	}

  /**
  * PublicTransportation Copy Constructor
  */
  public PublicTransportation(PublicTransportation publicTransportation)
  {
    this.setTicketPrice( publicTransportation.getTicketPrice() );
    this.setNumStops( publicTransportation.getNumStops() );
  }

  // Getters and Setters

	/**
	* Returns value of ticketPrice
	* @return double
	*/
	public double getTicketPrice() {
		return ticketPrice;
	}

	/**
	* Sets new value of ticketPrice
	* @param double
	*/
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	/**
	* Returns value of numStops
	* @return int
	*/
	public int getNumStops() {
		return numStops;
	}

	/**
	* Sets new value of numStops
	* @param int
	*/
	public void setNumStops(int numStops) {
		this.numStops = numStops;
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
    } else if(obj.getClass() != this.getClass() )
      {
        return false;
      }

      PublicTransportation toCompare = (PublicTransportation) obj;
      if( (toCompare.getTicketPrice() == this.getTicketPrice() ) && (toCompare.getNumStops() == this.getNumStops() ) )
      {
        return true;
      }
      else return false;
  }

  /**
	* Create string representation of PublicTransportation for printing
	* @return String
	*/
	public String toString() {
		return "PublicTransportation [ticketPrice=" + getTicketPrice() + ", numStops=" + getNumStops() + "]";
	}



}
