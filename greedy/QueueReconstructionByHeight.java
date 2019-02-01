package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 * <p>
 * Note:
 * The number of people is less than 1,100.
 * <p>
 * <p>
 * Example
 * <p>
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 20190201
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
        //「矮的随便插队，不会影响高的」，所以现安排高的人
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? -o1[0] + o2[0] : o1[1] - o2[1];//按照身高倒序、人数正序排列
            }
        });
        List<int[]> res = new LinkedList<>();
        for (int[] cur : people) {
            res.add(cur[1], cur);
        }
        return res.toArray(new int[people.length][]);
    }

    public static void main(String args[]) {
        int[][] ppl = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        new QueueReconstructionByHeight().reconstructQueue(ppl);
    }
}
