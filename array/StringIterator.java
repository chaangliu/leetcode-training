package array;

/**
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 * <p>
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
public class StringIterator {
	class Pair {
		char letter;
		int count;
	}

	Queue<Pair> queue = new LinkedList<>();

	public StringIterator(String compressedString) {

		int i = 0;
		while (i < compressedString.length()) {
			char letter = compressedString.charAt(i);
			i++;
			StringBuilder sb = new StringBuilder();
			while (i < compressedString.length() && compressedString.charAt(i) >= '0' && compressedString.charAt(i) <= '9') {
				sb.append(compressedString.charAt(i));
				i++;
			}
			int count = Integer.valueOf(sb.toString());
			Pair p = new Pair();
			p.letter = letter;
			p.count = count;
			queue.offer(p);
		}
	}

	public char next() {
		if (hasNext()) {
			Pair p = queue.peek();
			if (p.count > 0) {
				p.count = p.count - 1;
				if (p.count == 0){
					queue.poll();
				}
				return p.letter;
			}
			return ' ';
		} else return ' ';
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}

	public static void main(String args[]) {
		StringIterator obj = new StringIterator("a1234567890b1234567890");
		char param_1 = obj.next();
		boolean param_2 = obj.hasNext();
	}
}
