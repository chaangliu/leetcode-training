package easy;

import linkedlist.ListNode;

/**
 * Created by DrunkPiano on 02/12/2017.
 */

public class Singleton {
//	APPROACH 1
//	private Singleton() {
//	}
//
//	private Singleton mInstance;
//
//	public Singleton getInstance() {
//		if (mInstance == null) {
//			mInstance = new Singleton();
//		}
//		return mInstance;
//	}

	//	APPROACH 2
//	private Singleton() {
//	}
//
//	private Singleton mInstance = new Singleton();
//
//	public Singleton getInstance() {
//		return mInstance;
//	}

	//	APPROACH 3
	private Singleton() {
	}

	//注意是static的
	private static Singleton mInstance = new Singleton();

	public static Singleton getInstance() {
		synchronized (Singleton.class) {
			return mInstance;
		}
	}

	//	[73, 74, 75, 71, 69, 72, 76, 73]
	public int[] dailyTemperatures(int[] temperatures) {
		if (temperatures == null || temperatures.length == 0) return temperatures;
		int[] res = new int[temperatures.length];
		for (int i = 0; i < temperatures.length; i++) {
			for (int j = i + 1; j < temperatures.length; j++) {
				if (temperatures[i] < temperatures[j]) {
					res[i] = j - i;
					break;
				}
			}
		}
		return res;
	}

	//	private static int [] arr = {1,2,3};
	private void removeTest(int[] arr, ListNode node, int i, String s, String [] strArr) {
		arr[2] = 0;
		node.val = 888;
		i = 999;
		s += "CODE";
		strArr[0] = "CODE";
	}

	public static void main(String args[]) {
		int[] arr = {1, 2, 3};
		ListNode node = new ListNode(-1);
		String s = "RUN";
		int i = 3;
		String [] strArray = {"RUN"};
		new Singleton().removeTest(arr, node, i, s, strArray);
		System.out.println(arr);
		System.out.println(arr);
	}
}
