package greedy;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * <p>
 * Note:
 * <p>
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 * Example 1:
 * <p>
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 * <p>
 * Input:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 * 20190820
 * 20200203 --reivew
 */
public class GasStation {
    /**
     * 题意：给你每个位置的cost和能加的gas，找到一个位置，从这个位置开始clockwise走可以完成环绕一周回到当前位置。
     * 跟前不久周赛的一个题有点像，顺序地traverse，不满足的时候选择一个新的起点继续traverse。问题是起点怎么选？
     * 我的做法虽然AC了但不是最优的，我是从每个可能的起点开始遍历，看能否回到当前位置；
     * 讨论区的思路：GREEDY，在不满足的时候，从不满足的位置下一个位置继续。
     * 这基于一个前提：从A起步如果到不了B，那么从AB之间的任何一点起步都到不了B。=> 所以从B的下一个位置开始即可。
     * 时间: O(n) one pass
     * 假设AB之间有个点P，走到P时断了，说明从P出发亏空得太多了，AP之间积累的汽油都无法弥补，更别提从P开始了。
     * 这个有点绕，最好用例子来帮助思考。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (sumGas < sumCost) {
            return -1;
        } else {
            return start;
        }
    }

    /**
     * brute force
     * O(n^2)
     */
    public int canCompleteCircuit__MYCODE(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (cost[i] <= gas[i]) {
                int res = isValid(gas, cost, i);
                if (res != -1) return res;
            }
        }
        return -1;
    }

    private int isValid(int[] gas, int[] cost, int start) {
        int cur = gas[start] - cost[start];
        int i = 0;
        while (i < gas.length) {
            i++;
            int idx = (start + i) % gas.length;
            cur += gas[idx] - cost[idx];
            if (cur < 0) return -1;
        }
        return start;
    }
}
