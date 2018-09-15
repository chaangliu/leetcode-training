package easy;

import java.util.List;

/**
 * n = 2
 * logs =
 * ["0:start:0",
 * "1:start:2",
 * "1:end:5",
 * "0:end:6"]
 * Output:[3, 4]
 * Created by DrunkPiano on 16/07/2017.
 */

public class ExculsiveTimeOfFunctions {
	public int[] exclusiveTime(int n, List<String> logs) {
		int res[] = new int[n];
		boolean needCompareDiff = true;
		for (int i = 0; i < logs.size(); i++) {
			String[] item = logs.get(i).split(":");
			for (int j = i + 1; j < logs.size(); j++) {
				String[] later = logs.get(j).split(":");
				if (!later[0].equals(item[0]) && needCompareDiff) {
					res[Integer.parseInt(item[0])] += Integer.parseInt(later[3]) - Integer.parseInt(item[3]);
					needCompareDiff = false;
				}
				if (later[1].equals(item[1]) && later[2].equals("end")) {
					res[Integer.parseInt(item[0])] += Integer.parseInt(later[3]) - Integer.parseInt(logs.get(j - 1).split(":")[3]);
				}
			}
		}
		return res;
	}
}
