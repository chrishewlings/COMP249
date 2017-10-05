import java.util.Scanner;

public class Tutorial4
{
	public static void main(String[] args)
	{
		boolean exp1 = 1+2 > 4-2 && 12 < 23;
		boolean exp2 = 1+2 > 4-2 || 12 < 23;
		boolean exp3 = 1+2 > 4-2 && 12 > 23;
		boolean exp4 = 1+2 > 4-2 || 12 > 23;
		
		System.out.printf("exp1 is %b\n", exp1);
		System.out.printf("exp2 is %b\n", exp2);
		System.out.printf("exp3 is %b\n", exp3);
		System.out.printf("exp4 is %b\n", exp4);
		
	}
}