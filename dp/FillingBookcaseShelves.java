package dp;

import test.Test;

/**
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 * <p>
 * We want to place these books in order onto bookcase shelves that have total width shelf_width.
 * <p>
 * We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.
 * <p>
 * Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
 * <p>
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= books.length <= 1000
 * 1 <= books[i][0] <= shelf_width <= 1000
 * 1 <= books[i][1] <= 1000
 * <p>
 * 20190704
 */
public class FillingBookcaseShelves {

    /**
     * 经典书架问题，问怎么摆放书本需要的书架高度最小。
     * dp[i]表示前i本书放在架子上的最小高度
     * 1. book[i]放在新的一层：dp[i] = dp[i - 1] + height
     * 2. book[i]放在上一层：dp[i] = min(dp[j] + max(height[j + 1], height[j + 2].. height[i])), where width[j + 1] + widht[j + 2].. +width[i] <= shelf_width
     * 这题思路理解起来不难，但是被序号搞死。。仔细体会一下。
     * ---------------------------------------------
     * A. 下标从0开始的错误代码：
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        int dp[] = new int[books.length + 1];
        dp[0] = 0;
        for (int i = 0; i < books.length; i++) {
            int width = books[i][0];//错误1 会造成重复plus
            int height = books[i][1];
            dp[i + 1] = dp[i] + height;
            for (int j = i; j > 0; j--) {//错误2 漏掉j = 0
                width += books[j][0];
                if (width > shelf_width) break;
                height = Math.max(height, books[j][1]);
                dp[i + 1] = Math.min(dp[i + 1], dp[j - 1] + height);//错误3 dp[j]其实就相当于books[0, j - 1]的最优秀情况，无需-1
            }
        }
        return dp[dp.length - 1];
    }

    /**
     * B. 下标从0开始的正确代码：
     * 这种会比下标从1开始的方案循环次数多
     */
    public int minHeightShelves_(int[][] books, int shelf_width) {
        int dp[] = new int[books.length + 1];
        dp[0] = 0;
        for (int i = 0; i < books.length; i++) {
            int width = 0;//上一层的累计宽度
            int height = 0;
            dp[i + 1] = dp[i] + books[i][1];
            for (int j = i; j >= 0; j--) {
                width += books[j][0];
                if (width > shelf_width) break;
                System.out.println("当前最高：" + height + ", 新书高度：" + books[j][1]);
                height = Math.max(height, books[j][1]);//books[j -1]到books[i]中的最高一本书的高度
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + height);//dp[j]对应的是books[0]到books[j - 1]这本书为止的最小书架高度
            }
        }
        return dp[dp.length - 1];
    }

    /**
     * C. 下标从1开始的正确代码（推荐，更容易理解，大部分人用了这种）：
     * 书本下标比dp下标小1，我们假设以后遇到这种题都这么处理
     */
    public int minHeightShelves__(int[][] books, int shelf_width) {
        int[] dp = new int[books.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= books.length; ++i) {
            int width = books[i - 1][0];
            int height = books[i - 1][1];
            dp[i] = dp[i - 1] + height;
            for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; --j) {//j > 0, 所以i = 1的时候不会执行这一层。
                System.out.println("--当前最高：" + height + ", 新书高度：" + books[j - 1][1]);
                height = Math.max(height, books[j - 1][1]);//books[j -1]到books[i]中的最高一本书的高度
                width += books[j - 1][0];
                dp[i] = Math.min(dp[i], dp[j - 1] + height);//dp[j - 1]对应的是books[0]到books[j - 2]这本书为止的最小书架高度（重点）
            }
        }
        return dp[books.length];
    }

    /**
     * D. 这题跟maxSumAfterPartitioning很像，思路上，一种是从后往前推，一种是把新的物品放到最后一层。
     * 以下是我模仿maxSumAfterPartitioning那题的写法：
     */
    public int minHeightShelves___(int[][] books, int shelf_width) {
        int[] dp = new int[books.length];
        for (int i = 0; i < books.length; ++i) {
            int width = 0;
            int height = 0;
            dp[i] = books[i][1] + (i - 1 >= 0 ? dp[i - 1] : 0);
            for (int k = 1; i - k + 1 >= 0 && width + books[i - k + 1][0] <= shelf_width; k++) {
                height = Math.max(height, books[i - k + 1][1]);
                width += books[i - k + 1][0];
                dp[i] = Math.min(dp[i], (i - k >= 0 ? dp[i - k] : 0) + height);
            }
        }
        return dp[books.length - 1];
    }

    public static void main(String args[]) {
        int[][] arr = new int[][]{{7, 3}, {8, 7}, {2, 7}, {2, 5}};
        new FillingBookcaseShelves().minHeightShelves_(arr, 10);
        new FillingBookcaseShelves().minHeightShelves___(arr, 10);
    }
}
