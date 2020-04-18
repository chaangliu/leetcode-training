package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不能再向左弹。
 * 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
 * 示例 1：
 * 输入：jump = [2, 5, 1, 1, 1, 1]
 * 输出：3
 * 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
 * 限制：
 * 1 <= jump.length <= 10^6
 * 1 <= jump[i] <= 10000
 * 20200418
 */
public class MinJumpOfSpringGame {
    /**
     * 题意：在index处最多可以向右跳A[index]或者向左跳任意格子，问最早什么时候能跳到A.length之外。
     * 力扣春季赛第四题，我一看就觉得像是jump game ii；
     * 跟那题的不同之处就在于，如果你在到达当前层的最后一个元素之前就能跳出去，就需要res+1，相当于你需要从右边向左跳一次。
     * 但是模仿了写了之后，一直wa，春季赛不给看wa的case。另外，看数据量，即便不WA，也很可能TLE吧。
     */
    public int minJum_WA(int[] nums) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(nums[0], 0));
        int res = 0, lastIndex = 0;
        if (nums.length == 1) return 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair p = queue.poll();
                lastIndex = Math.max(p.num + p.idx, lastIndex);
                if (lastIndex >= nums.length) return i == size - 1 ? res : res + 1;
                if (i != size - 1) continue;
                for (int j = p.idx + 1; j <= lastIndex; j++) {
                    queue.offer(new Pair(nums[j], j));
                }
            }
        }
        return res;
    }

    class Pair {
        int num, idx;

        Pair(int n, int index) {
            this.num = n;
            this.idx = index;
        }
    }
}
