/** Driver class for Assignment 2.
 *
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */
package driver;
import publictransportation.PublicTransportation;
import landvehicles.*;
import seavehicles.*;
import airvehicles.*;

class TransitDriver {
  public static void main(String[] args)
  {

    // Creating various objects from our classes

    CityBus citybus1 = new CityBus(3.0, 4, 478, 1955, "Green", "Jerry");
    CityBus citybus2 = new CityBus(3.0, 4, 478, 1955, "Green", "Jerry");
    Metro metro1 = new Metro(2.5, 14, 212,1965, "Orange", "Steve", 8, "Montreal");
    Metro metro2 = new Metro(3.5, 22, 387,1965, "Yellow", "Sacha", 8, "Toronto");
    Tram tram1 = new Tram(1.5, 11, 356, 1922, "Blue", "Alexander", 45);
    Tram tram2 = new Tram(1.75, 6, 78, 1987, "Silver", "Jane", 57);
    Ferry ferry1 = new Ferry(4.5, 3, 1987, "Barchetta");
    Ferry ferry2 = new Ferry(5.0, 5, 1982, "Strangiato");
    AirCraft aircraft1 = new AirCraft(2.5, 4, "Weekly", "Balloon"); // Need to use Strings here because we can't access enums outside package
    PublicTransportation publictransportation1 = new PublicTransportation(2.0, 13);

    // Displaying the properties of the created objects
    System.out.printf("\nTesting .toString() method...\n");
    System.out.printf("-------------------------------\n");

    System.out.println("City Bus 1: " + citybus1);
    System.out.println("City Bus 2: " + citybus2);
    System.out.println("Metro 1 : " + metro1);
    System.out.println("Metro 2 : " + metro2);
    System.out.println("Tram 1 : " + tram1);
    System.out.println("Tram 2 : " + tram2);
    System.out.println("Ferry 1 : " + ferry1);
    System.out.println("Ferry 2 : " + ferry2);
    System.out.println("Aircraft 1 : " + aircraft1);
    System.out.println("Public Transportation 1 : " + publictransportation1);

    // Comparing equality of objects
    System.out.printf("\nTesting .equals() method...\n");
    System.out.printf("-----------------------------\n");

    if(citybus1.equals(citybus2))
    {
      System.out.println("City Bus 1 = City Bus 2.");
    }

    if(! metro1.equals(metro2))
    {
      System.out.println("Metro 1 != Metro 2.");
    }

    // Creating an array of objects

    PublicTransportation[] testArray = { citybus1,citybus2,metro1,metro2,tram1,tram2,ferry1,ferry2,aircraft1,publictransportation1};

    // Looping through the array to find the lowest and the highest cost.
    System.out.printf("\nFinding the lowest and highest cost in array...\n");
    System.out.printf("-------------------------------------------------\n");

    int lowestIndex = 0;
    int highestIndex = 0;
    double lowestCost = Integer.MAX_VALUE;
    double highestCost = 0;

    for(int i = 0; i < testArray.length; i++)
    {
      if(testArray[i].getTicketPrice() < lowestCost)
      {
        lowestCost = testArray[i].getTicketPrice();
        lowestIndex = i;
      }

      if(testArray[i].getTicketPrice() > highestCost)
      {
        highestCost = testArray[i].getTicketPrice();
        highestIndex = i;
      }
    }

    System.out.printf("The highest cost is $%.2f, from index %d, corresponding to object \"%s\".\n\n", highestCost, highestIndex, testArray[highestIndex]);
    System.out.printf("The lowest cost is $%.2f, from index %d, corresponding to object \"%s\"\n\n", lowestCost, lowestIndex, testArray[lowestIndex]);
  }
}
