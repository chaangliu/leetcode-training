package easy;

/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 * <p>
 * Note:
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 * Example 2:
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 * Created by DrunkPiano on 02/06/2017.
 */

public class NumberComplement {
//	public int findComplement(int num) {
//		String originBinary = Integer.toBinaryString(num);
//		String flippedBinary = "";
//		for (int i = 0; i < originBinary.length(); i++) {
//			flippedBinary += originBinary.charAt(i) == '1' ? 0 : 1;
//		}
//		int count = 0;
//		while (count < flippedBinary.length() && flippedBinary.charAt(count) == '0') {
//			count++;
//		}
//		flippedBinary = flippedBinary.substring(count, flippedBinary.length());
//
//		int res = 0;
//		for (int i = 0; i < flippedBinary.length(); i++) {
//			res += (flippedBinary.charAt(flippedBinary.length() - 1 - i) == '0' ? 0 : 1) * Math.pow(2, i);
//		}
//		return res;
//	}

	public int findComplement(int num)
	{
		int i = 0;
		int j = 0;

		while (i < num)
		{
			i += Math.pow(2, j);
			j++;
		}

		return i - num;
	}

	public static void main(String args[]) {
		new NumberComplement().findComplement(5);
	}
}
