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
     * 注意floodfill需要一个visited[]数组
     */
    public int[][] floodFill_20190301(int[][] image, int sr, int sc, int newColor) {
        helper(image, sr, sc, newColor, new boolean[image.length][image[0].length], image[sr][sc]);
        return image;
    }

    private void helper(int[][] image, int sr, int sc, int newColor, boolean[][] visited, int originalColor) {
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        if (sr - 1 >= 0 && !visited[sr - 1][sc] && image[sr - 1][sc] == originalColor) helper(image, sr - 1, sc, newColor, visited, originalColor);
        if (sr + 1 < image.length && !visited[sr + 1][sc] && image[sr + 1][sc] == originalColor) helper(image, sr + 1, sc, newColor, visited, originalColor);
        if (sc - 1 >= 0 && !visited[sr][sc - 1] && image[sr][sc - 1] == originalColor) helper(image, sr, sc - 1, newColor, visited, originalColor);
        if (sc + 1 < image[0].length && !visited[sr][sc + 1] && image[sr][sc + 1] == originalColor) helper(image, sr, sc + 1, newColor, visited, originalColor);
    }


    /**
     * --------------------------------------------------------------------------------------------------------
     */


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) {
            return image;
        }
        dfs(image, sr, sc, newColor, new boolean[image.length][image[0].length], image[sr][sc]);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int newColor, boolean[][] visited, int originalColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || visited[sr][sc] || image[sr][sc] != originalColor) {
            return;
        }
        //铁头娃
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        dfs(image, sr - 1, sc, newColor, visited, originalColor);
        dfs(image, sr + 1, sc, newColor, visited, originalColor);
        dfs(image, sr, sc - 1, newColor, visited, originalColor);
        dfs(image, sr, sc + 1, newColor, visited, originalColor);
    }

    public static void main(String args[]) {
        int[][] a = {{0, 0, 0}, {0, 1, 1}};
        new FloodFill().floodFill_20190301(a, 1, 1, 1);
    }
}
