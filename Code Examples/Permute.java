class Permute {
	public static void permute(String beginningStr, String endStr) {
	if( endStr.length() <= 1) {
		System.out.println(beginningStr + endStr);
	} else {
			for(int i = 0; i < endStr.length(); i++) {
				String newString = endStr.substring(0,i) + endStr.substring(i+1);
				permute(beginningStr + endStr.charAt(i), newString);
			}
		}
	}

	public static void main(String[] args) {
		permute("", "Word");
	}
}