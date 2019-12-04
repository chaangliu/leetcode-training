package dfs.divideandconquer;

/**
 * (This problem is an interactive problem.)
 * On the sea represented by a cartesian plane, each ship is located at an integer point, and each integer point may contain at most 1 ship.
 * You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true if and only if there is at least one ship in the rectangle represented by the two points, including on the boundary.
 * Given two points, which are the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle.  It is guaranteed that there are at most 10 ships in that rectangle.
 * Submissions making more than 400 calls to hasShips will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * Example :
 * Input:
 * ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
 * Output: 3
 * Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
 * Constraints:
 * On the input ships is only given to initialize the map internally. You must solve this problem "blindfolded". In other words, you must find the answer using the given hasShips API, without knowing the ships position.
 * 0 <= bottomLeft[0] <= topRight[0] <= 1000
 * 0 <= bottomLeft[1] <= topRight[1] <= 1000
 * 20191203
 */
public class NumberofShipsinaRectangle {

    class Solution {
        /**
         * 题意：一个笛卡尔平面（最大1000 * 1000），你可以调用hasShips()检查某个范围内(包含边界，所以可以是一个点)是否有船；要求最多调用400次hasShips()。
         * 这题的题图有误导，让你还以为是例子中有5艘ship，实际上给你example里面缺少了(0,0),(4,4)，所以包含边界确实只有3艘船
         * 解法，分治，每次把平面分成4份来检查。
         * 一个pitfall是，要把四个小部分的平面错开，否则在左上右下相差1的时候会死循环。比如(1,1)(2,2)，mid=(1,1)，如果你下次还用mid, (2,2)来做递归，就会死循环。
         */
        public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
            return divideAndConquer(sea, topRight, bottomLeft);
        }

        private int divideAndConquer(Sea sea, int[] topRight, int[] bottomLeft) {
            if (topRight[0] < bottomLeft[0] || topRight[1] < bottomLeft[1] || !sea.hasShips(topRight, bottomLeft)) {
                return 0;
            }
            if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
                return 1;
            }
            int cnt = 0;
            int midX = bottomLeft[0] + (topRight[0] - bottomLeft[0]) / 2;
            int midY = bottomLeft[1] + (topRight[1] - bottomLeft[1]) / 2;
            cnt += divideAndConquer(sea, new int[]{midX, midY}, bottomLeft);//左下角
            cnt += divideAndConquer(sea, new int[]{topRight[0], midY}, new int[]{midX + 1, bottomLeft[1]});//右下角
            cnt += divideAndConquer(sea, new int[]{midX, topRight[1]}, new int[]{bottomLeft[0], midY + 1});//左上角
            cnt += divideAndConquer(sea, topRight, new int[]{midX + 1, midY + 1});//右上角
            return cnt;
        }
    }

    // This is Sea's API interface.
    // You should not implement it, or speculate about its implementation
    abstract class Sea {
        abstract boolean hasShips(int[] topRight, int[] bottomLeft);
    }
}
