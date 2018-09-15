package array;

/**
 * Input:
 * nums =
 * [[1,2],
 * [3,4]]
 * r = 1, c = 4
 * Output:
 * [[1,2,3,4]]
 * Explanation:
 * The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is an 1 * 4 matrix, fill it row by row by using the previous list.
 * <p>
 * Created by DrunkPiano on 2017/4/30.
 */

public class Reshape {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) return nums;
        int[][] res = new int[r][c];
        int[] temp = new int[m * n];
        int k = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                temp[k++] = nums[i][j];
            }
        k = 0;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                res[i][j] = temp[k++];
            }

        return res ;
    }

    public static void main(String args[]){
        int nums [][]  ={{1,2},{3,4},{5,6}};
        new Reshape().matrixReshape(nums,2,4);
        System.out.println();
    }

}

