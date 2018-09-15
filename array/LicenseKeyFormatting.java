package array;

/**
 * Input: S = "2-4A0r7-4k", K = 3
 * <p>
 * Output: "24-A0R-74K"
 * <p>
 * Explanation: The string S has been split into three parts, each part has 3 characters except the first part as it could be shorter as said above.
 * Created by DrunkPiano on 04/06/2017.
 */

public class LicenseKeyFormatting {

	public String licenseKeyFormatting(String S, int K) {
		S = S.toUpperCase();
		int i = S.length() - 1;
		String newS = "";
		int count = 0;
		while (i >= 0) {
			while (S.charAt(i) == '-') {
				//this should be while , not if. and the result could be -1.
				i--;
				if (i == -1) {
					if (newS.startsWith("-")) {
						newS = newS.substring(1, newS.length());
					}
					return newS;
				}
			}
			newS = S.charAt(i) + newS;
			count++;
			i--;

			if (count == K & i != -1) {
				newS = '-' + newS;
				count = 0;
			}
		}
		return newS;
	}

	public static void main(String args[]) {

//		new LicenseKeyFormatting().licenseKeyFormatting("2-4A0r7-4k", 3);
//		System.out.print(new LicenseKeyFormatting().licenseKeyFormatting("---------qWVCLcAmMarhppUSUeFtXKPaDBxidMCIbKVkNKKGATRrhDWIFyUkwKZeJqiHdwThRefpRGavUrixNRqPSIYdLiwSYdfHNhmWDLho", 11));
//		char a = '4';
//		String s = "k";
//		System.out.println(a + s);
//		System.out.println(s + a);
	}

}