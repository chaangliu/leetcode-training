package array;

import java.util.ArrayList;
import java.util.List;

import basics.Interval;


/**
 * Created by DrunkPiano on 21/06/2017.
 */

public class InsertInterval {


	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<>();
		//corner case1
		if (intervals.isEmpty()) {
			res.add(newInterval);
			return res;
		}
		while (!intervals.isEmpty()) {
			if (intervals.get(0).start < newInterval.start && intervals.get(0).end < newInterval.start) {
				res.add(intervals.remove(0));
			} else {
				break;
			}
		}

		if (intervals.isEmpty()) {
			res.add(newInterval);
			return res;
		}
		//这里开始的intervals list第一个元素的end一定比newInterval的start大
		//1. won't overlapping
		if (intervals.get(0).start > newInterval.end) {
			res.add(newInterval);
			res.addAll(intervals);
			return res;
		}

		//2. wrapped by the 1st interval
		if (intervals.get(0).start <= newInterval.start && intervals.get(0).end >= newInterval.end) {
			res.addAll(intervals);
			return res;
		}

		//3. overlapping
		Interval head = intervals.get(0);
		if (head == null) return res;

		int start = Math.min(head.start, newInterval.start);
		int end;
		while (newInterval.end >= head.end && intervals.size() > 1) {
			intervals.remove(0);
			head = intervals.get(0);
		}

		if (newInterval.end < intervals.get(0).start) {
			end = newInterval.end;
			res.add(new Interval(start, end));
			res.addAll(intervals);
			return res;
		}

		if (newInterval.end >= intervals.get(0).start) {
			end = Math.max(intervals.remove(0).end, newInterval.end);
			res.add(new Interval(start, end));
			res.addAll(intervals);
			return res;
		}
		// should have covered all possible cases
		return null;
	}


	public static void main(String args[]) {
		List<Interval> list = new ArrayList<>();
		Interval interval1 = new Interval(1, 5);
		Interval interval2 = new Interval(2, 7);
		list.add(interval1);
//		list.add(interval2);
		Interval newInterval = new Interval(0, 3);
		List<Interval> res = new InsertInterval().insert(list, newInterval);
		System.out.println(res);
	}
}
