package easy;

/**
 * Created by DrunkPiano on 07/07/2017.
 */

public class DetectCapital {

	public static void main(String []args){
		System.out.print(new DetectCapital().detectCapitalUse("USA"));
	}
	public boolean detectCapitalUse(String word) {
		if (word == null || word.length() <= 1) {
			return true;
		}
		for (int i = 0; i < word.length(); i++) {
			//首字母小写
			if (isLowerCaseLetter(word.charAt(0))) {
				if (!isLowerCaseLetter(word.charAt(i))) {
					return false;
				}
			}
			//首字母大写
			else if (isUpperCaseLetter(word.charAt(0))) {
				if (isUpperCaseLetter(word.charAt(1))) {
					if (i > 1 && !isUpperCaseLetter(word.charAt(i))) {
						return false;
					}
				} else if (isLowerCaseLetter(word.charAt(1))) {
					if (i > 1 && !isLowerCaseLetter(word.charAt(i))) {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean isLowerCaseLetter(char c) {
		return c <= 'z' && c >= 'a';
	}

	private boolean isUpperCaseLetter(char c) {
		return c <= 'Z' && c >= 'A';
	}

}
