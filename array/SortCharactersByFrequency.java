package array;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by DrunkPiano on 29/06/2017.
 */

public class SortCharactersByFrequency {
	class Wrapper {
		public char c;
		public int freq;

		public Wrapper(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}
	}

	public String frequencySort(String s) {
		if (s==null || s.length() == 0) return "";
		Map<Character, Integer> map = new HashMap<>();
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			int count = map.getOrDefault(s.charAt(i), 0);
			map.put(s.charAt(i), ++count);
			set.add(s.charAt(i));
		}
		PriorityQueue<Wrapper> pq = new PriorityQueue<>(new Comparator<Wrapper>() {
			@Override
			public int compare(Wrapper o1, Wrapper o2) {
				return o1.freq - o2.freq;
			}
		});
		for (char c : set) {
			pq.offer(new Wrapper(c, map.get(c)));
		}
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Wrapper w = pq.poll();
			for (int i = 0; i < w.freq; i++) {
				sb.insert(0, w.c);
			}
		}
		return sb.toString();
	}

	public static void main(String args[]){
		new SortCharactersByFrequency().frequencySort("raaeaedere");
	}
}
