public class LinkedList<T> {
	Node head;
	int count;

	class Node<T> {
		T data;
		Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}

		public Node getNext() {
			return this.next;
		}

		public void setNext(Node nextNode) {
			this.next = nextNode;
		}

		public T getData() {
			return data;
		}
	}

	public LinkedList() {
		head = null;
	}

	public void addToEnd(T dataValue) {
		if( head == null) {
			head = new Node(dataValue);
			return;
		}

		Node current = head;
		if(current != null) {
			while(current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(new Node(dataValue));	
		}

		Node nextNode = new Node(dataValue);
		current.setNext(nextNode);
	}

	public void display() {
		Node current = head;
		if( current == null) {
			System.out.println("List is empty");
		}

		while(current.getNext() != null) {
			System.out.println(current.getData());
			current = current.getNext();
		}
		if( (current.getNext() == null) && (current != null)) {
			System.out.println(current.getData());
		}

	}

}