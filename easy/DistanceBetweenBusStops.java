package easy;

/**
 * A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.
 * <p>
 * The bus goes along both directions i.e. clockwise and counterclockwise.
 * <p>
 * Return the shortest distance between the given start and destination stops.
 * Example 1:
 * Input: distance = [1,2,3,4], start = 0, destination = 1
 * Output: 1
 * Explanation: Distance between 0 and 1 is 1 or 9, minimum is 1.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: distance = [1,2,3,4], start = 0, destination = 2
 * Output: 3
 * Explanation: Distance between 0 and 2 is 3 or 7, minimum is 3.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: distance = [1,2,3,4], start = 0, destination = 3
 * Output: 4
 * Explanation: Distance between 0 and 3 is 6 or 4, minimum is 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^4
 * distance.length == n
 * 0 <= start, destination < n
 * 0 <= distance[i] <= 10^4
 * 20190908
 */
public class DistanceBetweenBusStops {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int sum = 0;
        int val = 0;
        if (start > destination) {
            int temp = destination;
            destination = start;
            start = temp;
        }
        for (int i = 0; i < distance.length; i++) {
            if (i >= start && i < destination) {
                val += distance[i];
            }
            sum += distance[i];
        }
        return Math.min(val, sum - val);
    }

    /**
     * 如果有多个输入，可以用prefix sum,如下
     */
    public int distanceBetweenBusStops_(int[] distance, int start, int destination) {
        int n = distance.length;
        int sum = start + destination;
        start = Math.min(start, destination);
        destination = sum - start;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            prefixSum[i + 1] = prefixSum[i] + distance[i];
        }
        int forwardDistance = prefixSum[destination] - prefixSum[start];
        return Math.min(forwardDistance, prefixSum[n] - forwardDistance);
    }
}
