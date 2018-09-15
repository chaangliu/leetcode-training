package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * <p>
 * Created by DrunkPiano on 12/06/2017.
 */

public class RepeatedDNASequences {
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> res = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length() - 9; i++) {
			String key = s.substring(i, i + 10);
			map.put(key, map.getOrDefault(key, 0) + 1);
			if (map.get(key) == 2) {
				res.add(key);
			}
		}
		return res;
	}

	public static void main(String args[]) {
//		new RepeatedDNASequences().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
		System.out.println(new RepeatedDNASequences().findRepeatedDnaSequences("AAAAAAAAAAA"));
	}
}

