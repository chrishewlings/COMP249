public class LinkedListWithIterator<T> {
	
	Node<T> head;
	int count = 0;

	private class Node<T> {
		private T item;
		private Node next;

		public Node(T item) {
			this.item = item;
			this.next = null;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node nextNode) {
			this.next = nextNode;
		}

		public T getData() {
			return item;
		}
	}

	public class ListIterator {
		private Node<T> position;
		private Node<T> previous;

		public ListIterator() {
			position = head;
			previous = null;
		}

		public void restart() {
			position = head;
			previous = null;
		}

		public T next() {
			{
				if(!hasNext()) {
					System.out.println("Nothing found in next item");
				}
				T toReturn = position.item;
				previous = position;
				position = position.next;
				return toReturn;
			}
		}

		public boolean hasNext() {
			return (position != null);
		}

		public T peek() {
			if(!hasNext()) {
				System.out.println("Nothing found in next item");
			}
			return position.item;
		}
	}

	public ListIterator iterator() {
		return new ListIterator();
	}

	public LinkedListWithIterator() {
		head = null;
	}

	public void add(T item) {
		if( head == null) {
			head = new Node(item);
			return;
		}

		Node newItem = new Node(item);
		Node current = head;

		while(current.getNext() != null) {
			current = current.getNext();
		}

		current.setNext(newItem);
		count++;
	}

	public void addToBeginning(T item) {
		if( head == null) {
			head = new Node(item);
			return;
		}

		Node newNode = new Node(item);
		newNode.setNext(head);
		head = newNode;
		count++;
	}

	public void list() {
		if( head == null ) {
			System.out.println("List is empty");
			return;
		}

		Node current = head;
		while(current.getNext() != null) {
			System.out.println(current.getData());
			current = current.getNext();
		}
		System.out.println(current.getData());
		System.out.println("Count is : " + (count+1));
	}
}