

class LLIDriver {
	public static void main(String[] args) {
		LinkedListWithIterator<String> list = new LinkedListWithIterator<>();
		list.add("Hello");
		list.add("World");
		list.add("Pirates");
		list.add("Wizards");
		//list.list();

		list.addToBeginning("Shirley");
		list.addToBeginning("Britta");

		/*LinkedListWithIterator.ListIterator iterator = list.new ListIterator();
		while(iterator.hasNext() == true) {
			System.out.println(iterator.next());
		}*/
		list.list();

	}
}