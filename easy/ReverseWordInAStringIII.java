package easy;

/**
 * Example 1:
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * <p>
 * <p>
 * Created by DrunkPiano on 31/05/2017.
 */

public class ReverseWordInAStringIII {
	public String reverseWords(String s) {
		int start = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				sb.append(reverse(s.substring(start, i))).append(' ');
				start = i + 1;
			}
		}
		sb.append(reverse(s.substring(start, s.length())));
		return sb.toString();
	}

	private String reverse(String word) {
		StringBuilder sb = new StringBuilder();
		for (int i = word.length() - 1; i >= 0; i--) {
			sb.append(word.charAt(i));
		}
//		几种reverseString的方法：http://blog.csdn.net/hhxin635612026/article/details/26944053
//		StringBuffer sb = new StringBuffer(string);
//		for (int i = 0, j = sb.length() - 1; i < sb.length() >>> 1; i++, j--) {
//			char temp = sb.charAt(i);
//			sb.setCharAt(i, sb.charAt(j));
//			sb.setCharAt(j, temp);
//		}
		return sb.toString();
	}

	public static void main(String args[]){
		System.out.println(new ReverseWordInAStringIII().reverseWords("Let's take LeetCode contest"));
	}
}
