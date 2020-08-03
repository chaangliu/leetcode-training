package dfs;

/**
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 * <p>
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 * <p>
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 * <p>
 * At the end, return the modified image.
 * <p>
 * Example 1:
 * Input:
 * image =
 * [[1,1,1],
 * [1,1,0],
 * [1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output:
 * [[2,2,2],
 * [2,2,0],
 * [2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 * Note:
 * <p>
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 * <p>
 * 20190301
 */
public class FloodFill {
    /**
     * 题意：实现涂色功能，把相同的颜色涂成目标颜色。
     * 注意处理相同颜色的情况，颜色相同是，不需要涂色的，也不需要visited数组！！！！
     */
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (newColor == image[sr][sc]) return image;
        dfs(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int newColor, int orginalColor) {
        int m = image.length, n = image[0].length;
        if (sr >= m || sr < 0 || sc >= n || sc < 0 || image[sr][sc] != orginalColor) return;
        image[sr][sc] = newColor;
        for (int[] d : dirs) {
            dfs(image, sr + d[0], sc + d[1], newColor, orginalColor);
        }
    }

    /**
     * 另一种写法，continue：
     */
    class Solution {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            if (newColor == image[sr][sc]) return image;
            dfs(image, sr, sc, newColor, image[sr][sc]);
            image[sr][sc] = newColor; // dont forget
            return image;
        }

        private void dfs(int[][] image, int sr, int sc, int newColor, int orginalColor) {
            int m = image.length, n = image[0].length;
            for (int[] d : dirs) {
                int nr = sr + d[0], nc = sc + d[1]; //这里一定要重新申请变量nr，不能直接sr+=d[0]，否则无法通过！！
                if (nr >= m || nr < 0 || nc >= n || nc < 0 || image[nr][nc] != orginalColor) continue;
                image[nr][nc] = newColor;
                dfs(image, nr, nc, newColor, orginalColor);
            }
        }
    }

    /**
     * 回溯写法：
     */
    class Solution1 {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            if (newColor == image[sr][sc]) return image;
            dfs(image, sr, sc, newColor, image[sr][sc]);
            image[sr][sc] = newColor; // dont forget
            return image;
        }

        private void dfs(int[][] image, int sr, int sc, int newColor, int orginalColor) {
            int m = image.length, n = image[0].length;
            for (int[] d : dirs) {
                sr += d[0];
                sc += d[1]; //backtracking
                if (sr >= m || sr < 0 || sc >= n || sc < 0 || image[sr][sc] != orginalColor) {
                    sr -= d[0];
                    sc -= d[1];
                    continue;
                }
                image[sr][sc] = newColor;
                dfs(image, sr, sc, newColor, orginalColor);
                sr -= d[0];
                sc -= d[1];
            }
        }
    }
}
