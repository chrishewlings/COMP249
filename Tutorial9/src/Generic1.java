
public class Generic1 {
		
	public static void main(String[] args) {
			
		Box<Integer> myIntegerBox = new Box<Integer>();
		
		myIntegerBox.set(new Integer(10));
		
		System.out.println(myIntegerBox);
		
		Box<String> myStringBox = new Box<String>();
		myStringBox.set(new String("For sale, baby shoes, never worn"));
		System.out.println(myStringBox);
	}
}

