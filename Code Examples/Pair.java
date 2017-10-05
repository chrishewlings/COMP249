public class Pair<T> {
	
	T x; 
	T y;

	public Pair( T t1, T t2) {
		x = t1;
		y = t2;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}