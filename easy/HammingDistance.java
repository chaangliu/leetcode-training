package easy;

/**
 * Created by DrunkPiano on 31/05/2017.
 */

public class HammingDistance {
	public int hammingDistance(int x, int y) {
		String xs = Integer.toBinaryString(x);
		String ys = Integer.toBinaryString(y);
		int count = 0;
		for (int i = 0 ; i < Math.min(xs.length(), ys.length()); i++) {
			if (xs.charAt(xs.length() - 1- i) != ys.charAt(ys.length()-1  - i)) {
				count++;
			}
		}
		String targetS = xs.length() > ys.length() ? xs : ys;
		for (int j = 0 ; j < targetS.length() - Math.min(xs.length() , ys.length()); j++) {
			if (targetS.charAt(j) != '0') {
				count++;
			}
		}
		return count;
	}

	public static void main(String args[]) {
		System.out.println(new HammingDistance().hammingDistance(4, 14));
	}

}
