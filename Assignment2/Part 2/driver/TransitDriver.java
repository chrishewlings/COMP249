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

/* TransitDriver class modified to accomodate package privacy changes.
 * Causes some trouble especially with the enums declared in the AirCraft class
 * but allows for more flexibility if the implementation of the classes
 * needs to be changed in the future, e.g. because we're using methods
 * instead of inherited attributes we can freely change implementation
 * and just change methods instead of being forced to change types and program logic.
 */



class TransitDriver {

  static PublicTransportation[] copyCityBus(PublicTransportation[] inputArray)
  {
    int newArrayLength = inputArray.length;
    PublicTransportation[] outputArray = new PublicTransportation[newArrayLength];

    // Loops through the array and calls the .clone() method on each object.
    for(int i = 0; i < newArrayLength; i++)
    {
      outputArray[i] = inputArray[i].clone();
    }
    return outputArray;
  }
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



    // Creating an array of objects
    System.out.printf("\nCreating an array of PublicTransportation Objects...\n");
    System.out.printf("------------------------------------------------------\n");
    PublicTransportation[] firstArray = { citybus1,citybus2,metro1,metro2,tram1,tram2,ferry1,ferry2,aircraft1,publictransportation1};

    // Print original array
    System.out.printf("\nPrinting first array to screen...\n");
    System.out.printf("---------------------------------\n");
    for(int i = 0 ; i < firstArray.length; i++)
    {
      System.out.println(firstArray[i]);
    }

    // Call copyCityBus() on first array to create a clone of it
    System.out.printf("\nMaking a copy of our original array...\n");
    System.out.printf("----------------------------------------\n");
    PublicTransportation[] copiedArray = copyCityBus(firstArray);

    // Print copied array to screen.
    for( int i = 0; i < copiedArray.length; i++ )
    {
      System.out.println(copiedArray[i]);
    }

    System.out.println("All done!");
  }
}
