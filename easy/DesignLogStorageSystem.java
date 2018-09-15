package easy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DrunkPiano on 02/07/2017.
 */

public class DesignLogStorageSystem {
//	public static class LogSystem {
//		//		List<String> stamps = new ArrayList<>();
//		HashMap<String[], Integer> map = new HashMap<>();
//
//		public LogSystem() {
//
//		}
//
//		public void put(int id, String timestamp) {
//			map.put(timestamp.split(":"), id);
//		}
//
//		public List<Integer> retrieve(String s, String e, String gra) {
//			int graIndex;
//			switch (gra) {
//				case "Year":
//					graIndex = 0;
//					break;
//				case "Month":
//					graIndex = 1;
//					break;
//				case "Day":
//					graIndex = 2;
//					break;
//				case "Hour":
//					graIndex = 3;
//					break;
//				case "Minute":
//					graIndex = 4;
//					break;
//				case "Second":
//					graIndex = 5;
//					break;
//				default:
//					graIndex = 0;
//			}
//			//01这种能parseInt吗,可以
//			String[] sArr = s.split(":");
//			String[] eArr = e.split(":");
//			ArrayList<Integer> res = new ArrayList<>();
//			boolean sTrue = false;
//			boolean eTrue = false;
//			for (String[] key : map.keySet()) {
//				for (int i = 0; i <= graIndex; i++) {
//					if (Integer.parseInt(key[i]) < Integer.parseInt(sArr[i])) {
//						break;
//					}
//					if (i < graIndex && Integer.parseInt(key[i]) > Integer.parseInt(sArr[i])) {
//						sTrue = true;
//						break;
//					}
//					if (i == graIndex && Integer.parseInt(key[i]) >= Integer.parseInt(sArr[i])) {
//						sTrue = true;
//					}
//				}
//				if (!sTrue){
//					continue;
//				}
//				for (int i = 0; i <= graIndex; i++) {
//					if (Integer.parseInt(key[i]) > Integer.parseInt(eArr[i])) {
//						break;
//					}
//
//					if (i < graIndex && Integer.parseInt(key[i]) < Integer.parseInt(eArr[i])) {
//						eTrue = true;
//						break;
//					}
//					if (i == graIndex && Integer.parseInt(key[i]) <= Integer.parseInt(eArr[i])) {
//						eTrue = true;
//					}
//				}
//				if (eTrue) {
//					res.add(map.get(key));
//				}
//				sTrue = false;
//				eTrue = false;
//			}
//			return res;
//		}
//	}

	public static class LogSystem {

		List<String[]> timestamps = new LinkedList<>();
		List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
		int[] indices = new int[]{4,7,10,13,16,19};

		public void put(int id, String timestamp) {
			timestamps.add(new String[]{Integer.toString(id), timestamp});
		}

		public List<Integer> retrieve(String s, String e, String gra) {
			List<Integer> res = new LinkedList<>();
			int idx = indices[units.indexOf(gra)];
			for (String[] timestamp : timestamps) {
				if (timestamp[1].substring(0, idx).compareTo(s.substring(0, idx)) >= 0 &&
						timestamp[1].substring(0, idx).compareTo(e.substring(0, idx)) <= 0) {
					res.add(Integer.parseInt(timestamp[0]));
				}
			}
			return res;
		}
	}
	public static void main(String args[]) {
		LogSystem obj = new LogSystem();
		obj.put(1, "2017:01:01:23:59:59");
		obj.put(2, "2017:01:01:22:59:59");
		obj.put(3, "2016:01:01:00:00:00");
		List<Integer> param_2 = obj.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
		System.out.println(param_2);
	}
}
