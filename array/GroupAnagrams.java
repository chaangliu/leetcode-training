package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DrunkPiano on 24/05/2017.
 * for:
 * ["eat", "tea", "tan", "ate", "nat", "bat"]
 * return:
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 */

public class GroupAnagrams {
//	public List<List<String>> groupAnagrams(String[] strs) {
//		Map<int[], List<String>> hashMap = new HashMap<>();
//		for (String s : strs) {
//			boolean addedToExist = false ;
//			int[] map = new int[26];
//			for (int i = 0; i < s.length(); i++) {
//				map[s.charAt(i) - 'a']++;
//			}
//			if (hashMap.isEmpty()) {
//				hashMap.put(map, new ArrayList<String>());
//			}
//			Iterator iter = hashMap.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry entry = (Map.Entry) iter.next();
//				int[] key = (int[]) entry.getKey();
//				if (Arrays.equals(key, map)) {
//					List<String> list = hashMap.get(entry.getKey());
//					list.add(s);
//					addedToExist = true;
////					hashMap.put(key, list);//这里是否不需要put，因为list是引用
//				}
//			}
//			if (!addedToExist){
//				List<String> list = new ArrayList<>();
//				list.add(s);
//				hashMap.put(map , list);
//			}
//
//		}
//
//		List<List<String>> res = new ArrayList<>();
//		Iterator iter = hashMap.entrySet().iterator();
//		while (iter.hasNext()) {
//			Map.Entry<int[], ArrayList<String>> entry = (Map.Entry<int[], ArrayList<String>>) iter.next();
//			List<String> list = entry.getValue();
//			res.add(list);
//		}
//		return res;
//	}

	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0 ) return new ArrayList<>();
		Map<String , List<String>> map = new HashMap<>();
		for (String s : strs){
			char [] c = s.toCharArray();
			Arrays.sort(c);
			String key = String.copyValueOf(c);
			if (!map.containsKey(key)){
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(s);
		}
		return new ArrayList<>(map.values());
	}

		public static void main(String args[]) {
		String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(new GroupAnagrams().groupAnagrams(s));
	}
}
