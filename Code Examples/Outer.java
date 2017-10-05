import java.lang.*;
import java.util.*;
import java.io.*;

public class Outer {

	public static void main(String[] args) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream("file.dat"));
			//SerialData serData = new SerialData();
			//out.writeObject(serData);
			SerialData serData = (SerialData)in.readObject();
			System.out.println(serData.i);
			System.out.println(serData.j);
			in.close();
		} catch(Exception e) {
			System.out.println("ruh roh");
		}

	}
}