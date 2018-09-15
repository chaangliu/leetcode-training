package easy;

/**
 * 2^2 + 2^2 = 8
 * 4^2 + 5^2 = 41
 * <p>
 * 25 = 5 * 5 + 0 * 0
 * 25 = 3 * 3 + 4 * 4
 * <p>
 * Created by DrunkPiano on 02/07/2017.
 */

public class SumofSquareNumbers {
	public boolean judgeSquareSum(int c) {
		if (isSquareNumber(c)) {
			return true;
		}
		int a = (int) Math.sqrt(c);
		while (!isSquareNumber(c - a* a) && a > 0) {
			a--;
		}
		return a != 0;
	}

	private boolean isSquareNumber(int c) {
		int a = (int) Math.sqrt(c);
		return a * a == c;
	}

	public static void main(String args[]){
		System.out.println(new SumofSquareNumbers().judgeSquareSum(18));
	}
}
