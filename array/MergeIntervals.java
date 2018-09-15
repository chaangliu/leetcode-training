package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by DrunkPiano on 26/05/2017.
 */

public class MergeIntervals {

	static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	class MyComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
		if (intervals == null || intervals.size() == 0) return res;
		Collections.sort(intervals, new MyComparator());
		int curStart = 0, curEnd = 0;
		int index = 0;
		boolean isOverlapping = false;
		while (index + 1 < intervals.size()) {
			if (!isOverlapping) {
				curStart = intervals.get(index).start;
				curEnd = intervals.get(index).end;
			}
			if (intervals.get(index + 1).start <= intervals.get(index).end
					|| intervals.get(index + 1).start <= curEnd) {
				curEnd = Math.max(intervals.get(index + 1).end, curEnd);
				isOverlapping = true;
			} else {
				isOverlapping = false;
				res.add(new Interval(curStart, curEnd));
			}
			index++;
		}
		if (isOverlapping) {
			res.add(new Interval(curStart, Math.max(intervals.get(intervals.size() - 1).end, curEnd)));
		} else {
			res.add(intervals.get(intervals.size() - 1));
		}
		return res;
	}


//	public List<Interval> merge(List<Interval> intervals) {
//		List<Interval> res = new ArrayList<>();
//		if (intervals == null || intervals.size() == 0) return res;
//		int maxEnd = 0;
//		for (Interval in : intervals) {
//			maxEnd = Math.max(in.end, maxEnd);
//		}
//		int[] nums = new int[maxEnd + 1];
//		for (Interval in : intervals) {
//			nums[in.start] = 2 ;
//			nums[in.end] = 3;
//		}
//		int start = 0, end = 0;
//		boolean isPair = false;
//		for (int i = 0; i < nums.length ; i ++){
//			if (nums[i] == 2 && !isPair){
//				start =  i ;
//			}
//			if (nums[i] == 3 ){
//				end = i ;
//			}
//		}
////		for (int i = 0; i < nums.length; i++) {
////			if (i == 0 && nums[i] == 1 || i > 0 && nums[i - 1] == 0 && nums[i] == 1) {
////				start = i;
////			}
////			if (i == 1 && i == nums.length || i < nums.length - 1 && nums[i] == 1 && nums[i + 1] == 0) {
////				end = i;
////			}
////
////			if (start < end) {
////				Interval interval = new Interval(start, end);
////				res.add(interval);
////			}
////		}
//
//		if (nums[0] == 1) start = 0;
//		for (int i = 0; i < nums.length; i++) {
//
//			if (i < nums.length - 1 && nums[i + 1] != nums[i]) {
//				if (nums[i + 1] == 0) {
//					end = i;
//				} else {
//					start = i + 1;
//				}
//				if (start < end) {
//					Interval in = new Interval(start, end);
//					res.add(in);
//				}
//			}
//
//
//		}
//		if (start > end) {
//			if (nums[nums.length - 1] == 1) {
//				end = nums.length - 1;
//			} else {
//				end = nums.length - 2;
//			}
//			Interval in = new Interval(start, end);
//			res.add(in);
//		}
//		return res;
//	}

	public static void main(String args[]) {
//		[1,3],[2,6],[8,10],[15,18]
//		Interval interval0 = new Interval(1, 4);
//		Interval interval1 = new Interval(2, 3);
//
//		Interval interval0 = new Interval(1, 3);
// 		Interval interval1 = new Interval(2, 6);
//		Interval interval2 = new Interval(8, 10);
//		Interval interval3 = new Interval(15, 18);
//
		Interval interval0 = new Interval(2, 3);
		Interval interval4 = new Interval(5, 5);
		Interval interval1 = new Interval(2, 2);
		Interval interval2 = new Interval(3, 4);
		Interval interval3 = new Interval(3, 4);

		List<Interval> list = new ArrayList<>();
		list.add(interval0);
		list.add(interval4);
		list.add(interval1);
		list.add(interval2);
		list.add(interval3);

		new MergeIntervals().merge(list);
	}


}
