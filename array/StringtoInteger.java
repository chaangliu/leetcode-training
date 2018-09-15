package array;

/**
 * Created by DrunkPiano on 11/05/2017.
 */

public class StringtoInteger {
	//	5. 有效字符后面的省略。 The string can contain additional characters [after those] that form the integral number, which are ignored and have no effect on the behavior of this function.
//	6. 首次出现的sequence不是有效数字，返回0。
//	7. str为空或者全是空格，返回0。
//	8. outOfRange的时候返回INT_MIN/INT_MAX
	public int myAtoi(String str) {
		int total = 0, sign = 1, index = 0;

		if (str == null || str.length() == 0) return 0;

		//jump through spaces
		while (index < str.length() && str.charAt(index) == ' ') index++;

		//judge the sign
		if (str.charAt(index) == '+' || str.charAt(index) == '-') {
			sign = str.charAt(index) == '+' ? 1 : -1;
			index++;
		}

		//计算方法是进位
		while (index < str.length()) {
			//易错
			int digit = str.charAt(index) - '0';
			if (digit < 0 || digit > 9) break;
			if (total > Integer.MAX_VALUE / 10 || total == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10) {
				total = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
				break;
			}
			total = total * 10 + digit;
			index++;
		}
		return total * sign;
	}

	public static void main(String args[]) {
//		new StringtoInteger().myAtoi("2147483648");
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE);
		String str = " ";
		int index = 0;
		//2. Remove Spaces
		while (index < str.length() && str.charAt(index) == ' ')
			index++;
	}
}
