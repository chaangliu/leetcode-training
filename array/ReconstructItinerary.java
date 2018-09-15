package array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * <p>
 * Example 2:
 * tickets = [
 * ["JFK","SFO"],
 * ["JFK","ATL"],
 * ["SFO","ATL"],
 * ["ATL","JFK"],
 * ["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 * <p>
 * Created by DrunkPiano on 30/05/2017.
 */

public class ReconstructItinerary {


	//用hashmap模拟priority queue
//	public List<String> findItinerary(String[][] tickets) {
//		List<String> res = new ArrayList<>();
//		Map<String, List<String>> map = new HashMap<>();
//		for (int i = 0; i < tickets.length; i++) {
//			List<String> list = map.getOrDefault(tickets[i][0], new ArrayList<String>());
//			list.add(tickets[i][1]);
//			map.put(tickets[i][0], list);
//		}
//
//		for (List<String> list : map.values()) {
//			Collections.sort(list);
//		}
//
//		res.add("JFK");
//		List<String> cur = new ArrayList<>();
//		if (tickets.length == 0) return cur;
//		dfs(res , tickets.length, map, "JFK");
//		return res;
//	}
//
//	private void dfs(List<String> res , int len, Map<String, List<String>> map, String depart) {
//		List<String> list = map.get(depart);
//		if (list == null) return;
//
//		for (int i = 0 ; i < list.size() ; i ++){
//			String s = list.remove(i);
//			dfs();
//		}
//
//	}


	//总觉得可以，但是无法找到第二个解的答案
//	public List<String> findItinerary(String[][] tickets) {
//		List<String> cur = new ArrayList<>();
//		if (tickets.length == 0) return cur;
//		List<List<String>> all = new ArrayList<>();
//		boolean used[] = new boolean[tickets.length];
//		boolean included[] = new boolean[tickets.length];
//		dfs(tickets, "JFK", all, cur, used);
//		return all.get(0);
//	}
//
//	private void dfs(String[][] tickets, String depart, List<List<String>> all, List<String> cur, boolean used[]) {
//		if (cur.size() == tickets.length + 1) {
//			all.add(new ArrayList<>(cur));
//			return;
//		}
//		for (int i = 0; i < tickets.length; i++) {
//			if (!used[i] && tickets[i][0].equals(depart)) {
//				cur.add(tickets[i][0]);
//				if (cur.size() == tickets.length) {
//					cur.add(tickets[i][1]);
//				}
//				depart = tickets[i][1];
//				used[i] = true;
//				dfs(tickets, depart, all, cur, used);
//				used[i] = false;
//				if (cur.size() == tickets.length + 1) {
//					cur.remove(cur.size() - 1);
//				}
//				cur.remove(cur.size() - 1);
//			}
//		}
//	}


	public static void main(String args[]) {
//		String[][] s = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
//		String[][] s = {
//				{"JFK", "SFO"},
//				{"JFK", "ATL"},
//				{"SFO", "ATL"},
//				{"ATL", "JFK"},
//				{"ATL", "SFO"}};
		String[][] s = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
		new ReconstructItinerary().findItinerary(s);
	}

//	public List<String> findItinerary(String[][] tickets) {
//		Map<String, List<String>> map = new HashMap<>();
//
//		for (String[] ticket : tickets) {
//			List<String> dests = map.get(ticket[0]);
//			if (dests == null) {
//				dests = new ArrayList<>();
//				dests.add(ticket[1]);
//				map.put(ticket[0], dests);
//			} else {
//				dests.add(ticket[1]);
//			}
//		}
//
//		for (List<String> dests : map.values()) {
//			Collections.sort(dests);
//		}
//
//		List<String> res = new ArrayList<>();
//		res.add("JFK");
//
//		dfs(res, map, "JFK", tickets.length);
//
//		return res;
//	}
//
//	//
//	public void dfs(List<String> res, Map<String, List<String>> map, String src, int length) {
//		if (res.size() == length + 1) {
//			return;
//		}
//
//		List<String> dests = map.get(src);
//
//		if (dests != null && dests.size() > 0) {
//			for (int i = 0; i < dests.size(); i++) {
//				String dest = dests.remove(i);
//				res.add(dest);
//				dfs(res,  map, dest, length);
//				if (res.size() == length + 1) return;
//				dests.add(i, dest);
//				res.remove(res.size() - 1);
//			}
//		}
//	}

	Map<String, PriorityQueue<String>> flights;
	LinkedList<String> path;

	public List<String> findItinerary(String[][] tickets) {
		flights = new HashMap<>();
		path = new LinkedList<>();
		for (String[] ticket : tickets) {
			flights.putIfAbsent(ticket[0], new PriorityQueue<String>());
			flights.get(ticket[0]).add(ticket[1]);
		}
		dfs("JFK");
		return path;
	}

	public void dfs(String departure) {
		PriorityQueue<String> arrivals = flights.get(departure);
		while (arrivals != null && !arrivals.isEmpty())
			dfs(arrivals.poll());
		path.addFirst(departure);
	}
}
