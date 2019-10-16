package dp;

public class Largest1BorderedSquare {
    /**
     * 题意：找出最大的边框全是1的正方形。
     * 解法：brute force的话，对于每个格子，去找四条边的最大长度。复杂度O(N^4)，明显，有很多重复计算。
     * 如何减少重复计算？借助两个辅助数组hor和ver，hor从左往右,ver从上到下分别记录的格子迄今为止有多少个连续的1。
     * 然后从右下角开始(当然也可以从左上角，但是效率没有右下角高)，判断同样的格子hor, ver的最小数字small，表示right,bottom最少都有small，然后找left, top是否满足；若不满足就缩小small。
     */
    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] hor = new int[row][col], ver = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    hor[i][j] = grid[i][j];
                } else {
                    hor[i][j] = grid[i][j] == 0 ? 0 : hor[i][j - 1] + 1;
                }
            }
        }
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (j == 0) {
                    ver[j][i] = grid[j][i];
                } else {
                    ver[j][i] = grid[j][i] == 0 ? 0 : ver[j - 1][i] + 1;
                }
            }
        }
        int res = 0;
        for (int i = row - 1; i >= 0; i--)//从从右下角开始遍历，这样可以剪枝，减少内部while满足的次数
            for (int j = col - 1; j >= 0; j--) {
                int small = Math.min(ver[i][j], hor[i][j]);
                while (small > res) {//这个while我想到的时候觉得复杂度挺高的，没敢往下想；实际上可以大胆地去想。
                    if (hor[i - small + 1][j] >= small && ver[i][j - small + 1] >= small) {
                        res = small;
                        break;
                    } else
                        small--;
                }
            }
        return res * res;
    }
}
