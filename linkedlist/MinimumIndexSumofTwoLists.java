package linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 * Created by DrunkPiano on 28/05/2017.
 */

public class MinimumIndexSumofTwoLists {
	public String[] findRestaurant(String[] list1, String[] list2) {
		int minSum = Integer.MAX_VALUE;
		HashMap<String, Integer> map = new HashMap<>();
		//SparseArray
		HashMap<String, Integer> curMin = new HashMap<>();
		for (int i = 0; i < list1.length; i++) {
			map.put(list1[i], i);
		}

		for (int j = 0; j < list2.length; j++) {
			if (map.get(list2[j]) != null) {
				if (j + map.get(list2[j]) <= minSum) {
					minSum = j + map.get(list2[j]);
					curMin.put(list2[j], minSum);
				}
			}
		}
		ArrayList<String> res = new ArrayList<>();
		Iterator iterator = curMin.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if ((Integer) entry.getValue() == minSum) {
				res.add((String) entry.getKey());
			}
		}
		return res.toArray(new String[res.size()]);
	}

	public static void main(String args[]) {
		String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
		String[] list2 = {"KFC", "Shogun", "Burger King"};
		System.out.println(new MinimumIndexSumofTwoLists().findRestaurant(list1, list2));
	}
}
