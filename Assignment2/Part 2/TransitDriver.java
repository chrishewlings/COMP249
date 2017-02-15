/** Driver class for Assignment 2.
 * 
 *@version 0.1
 * Names(s) and ID(s) Chris Hewlings: 29145958  /  Leo Sudarma : 40046196
 * COMP249
 * Assignment #     Assignment #2
 * Due Date       February 21, 2017
 */

import publictransportation.PublicTransportation;
import landvehicles.*;
import seavehicles.*;
import airvehicles.*;
import java.lang.reflect.*;

class TransitDriver {
  public static void main(String[] args)
  {
    long route = 30971259 ;

    CityBus citybus = new CityBus(1.0, 4, route, 1955, "Green", "Jerry");
    Metro metro = new Metro(0.5, 4, route,1965, "Orange", "Steve", 8, "Montreal");
    Tram tram = new Tram(1.5, 4, route, 1922, "Yellow", "Sarah", 45);
    Ferry ferry = new Ferry(2.0, 4, 1987, "Barchetta");
    AirCraft aircraft = new AirCraft(2.5, 4, "Weekly", "Balloon");

    System.out.println(citybus);
    System.out.println(metro);
    System.out.println(tram);
    System.out.println(ferry);
    System.out.println(aircraft);
  }
}
