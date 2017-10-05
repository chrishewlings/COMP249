public class Box<T> {
		private T x;
	
		public Box() {
			x = null;
		}
		
		public Box(T y) {
			x = y;
		}
	
		public T get() {
			return x;
		}
	
		public void set(T y) {
			x = y;
		}
		
		public String toString() {
			return "This box contains a " + this.x.getClass() + " with a value of " + this.get();
		}
	
	}