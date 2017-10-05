public class LinkedListDriver {
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		list.addToEnd("Hello");
		list.addToEnd("World");
		list.addToEnd("123");
		list.addToEnd("456");
		list.display();


		LinkedList<Number> list2 = new LinkedList<>();
		list2.addToEnd(3);
		list2.addToEnd(7);
		list2.addToEnd(9.2);
		list2.addToEnd(10.3);
		list2.display();
		}

}