public class Recur {
	public static String reverse(String str) {

		if(str.length() == 0) {
			return str;
		}
		
		return reverse(str.substring(1)) + str.charAt(0);
	}

	public static void main(String[] args) {
		String str = reverse("Hello World");
		System.out.println(str);
	}
}