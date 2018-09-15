package array;

/**
 * Created by DrunkPiano on 2017/5/7.
 */

public class SquirrelSimulation {
    // nuts tree nuts tree
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int sum = 0;
        int extra = Integer.MAX_VALUE;
        for (int i = 0; i < nuts.length; i++) {
            int nut2tree = dist(nuts[i], tree);
            int nut2squirrel = dist(nuts[i], squirrel);
            sum += nut2tree ;
            extra = Math.min(extra, nut2squirrel - nut2tree);
        }
        return sum * 2 + extra;
    }


    private int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
