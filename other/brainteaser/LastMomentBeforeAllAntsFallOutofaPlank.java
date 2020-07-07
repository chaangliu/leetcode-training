package other.brainteaser;

/**
 * 有一块木板，长度为 n 个 单位 。一些蚂蚁在木板上移动，每只蚂蚁都以 每秒一个单位 的速度移动。其中，一部分蚂蚁向 左 移动，其他蚂蚁向 右 移动。
 *
 * 当两只向 不同 方向移动的蚂蚁在某个点相遇时，它们会同时改变移动方向并继续移动。假设更改方向不会花费任何额外时间。
 *
 * 而当蚂蚁在某一时刻 t 到达木板的一端时，它立即从木板上掉下来。
 *
 * 给你一个整数 n 和两个整数数组 left 以及 right 。两个数组分别标识向左或者向右移动的蚂蚁在 t = 0 时的位置。请你返回最后一只蚂蚁从木板上掉下来的时刻。
 * 20200705
 */
public class LastMomentBeforeAllAntsFallOutofaPlank {
    /**
     * 题意：给你一些蚂蚁的初始位置和方向和木板长度，蚂蚁相撞会掉头；问最后一只蚂蚁从木板上掉下来的时刻。
     * 这题标签是brain teaser，想象蚂蚁🐜可以互相穿透。参考中文题解。
     */
    public int getLastMoment(int n, int[] left, int[] right) {
        int max = -1;
        for(int i = 0; i < left.length;i++){
            max = Math.max(max,left[i]);
        }
        for(int i = 0; i < right.length;i++){
            max = Math.max(max,n-right[i]);
        }
        return max;
    }
}
