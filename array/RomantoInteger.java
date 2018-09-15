package array;

/**
 * Created by DrunkPiano on 16/05/2017.
 */

public class RomantoInteger {
	public int romanToInt(String s) {
		int total = 0;
		for (int i = 0; i < s.length(); i++) {
			total = total * 10 + s.charAt(i)-'0';
		}

		return total;
	}

	public static void main(String args[]){
		System.out.print(new RomantoInteger().romanToInt("123"));
	}
}
