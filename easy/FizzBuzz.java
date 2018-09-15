package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrunkPiano on 11/06/2017.
 */

public class FizzBuzz {

	public List<String> fizzBuzz(int n) {
		List<String> res = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				res.add("FizzBuzz");
			} else if (i % 3 == 0) {
				res.add("Fizz");
			} else if (i % 5 == 0) {
				res.add("Buzz");
			} else {
				res.add(String.valueOf(i));
			}
		}
		return res;
	}

	public static void main(String args[]) {
		new FizzBuzz().fizzBuzz(15);
	}

}
