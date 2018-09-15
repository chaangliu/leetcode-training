package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrunkPiano on 14/05/2017.
 * Input:
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 * Explanation:
 * 3
 * /   \
 * 1     5
 * /
 * 10
 * Kill 5 will also kill 10.
 */

public class KillProcess {
//	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
//		List<Integer> res = new ArrayList<>();
//		Queue<Integer> killList = new LinkedList<>();
//		killList.offer(kill);
//		while (!killList.isEmpty()) {
//			int temp = killList.poll();
//			res.add(temp);
//			for (int i = 0; i < pid.size(); i++) {
//				if (ppid.get(i) == temp) {
//					killList.offer(pid.get(i));
//				}
//			}
//		}
//		return res;
//	}

//	#DFS
public List < Integer > killProcess(List < Integer > pid, List < Integer > ppid, int kill) {
	List < Integer > l = new ArrayList < > ();
	if (kill == 0)
		return l;
	l.add(kill);
	for (int i = 0; i < ppid.size(); i++)
		if (ppid.get(i) == kill)
			l.addAll(killProcess(pid, ppid, pid.get(i)));
	return l;
}

	public static void main(String args[]){
		int [] pid1 = {1, 3, 10, 5};
		List<Integer> pid = new ArrayList<>();
		for (int i = 0 ; i < pid1.length ; i ++){
			pid.add(pid1[i]);
		}
		int [] ppid1 = {3, 0, 5, 3};
		List<Integer> ppid = new ArrayList<>();
		for (int i = 0 ; i < ppid1.length ; i ++){
			ppid.add(ppid1[i]);
		}
		System.out.print(new KillProcess().killProcess(pid , ppid , 3));
	}
}
