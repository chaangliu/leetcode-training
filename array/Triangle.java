package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * <p>
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/2/23.
 */

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        //第一列和最后一列的path sum
        for (int i = 1; i < triangle.size(); i++) {
            triangle.get(i).set(0, triangle.get(i - 1).get(0) + triangle.get(i).get(0));
            int cell_size = triangle.get(i).size();
            triangle.get(i).set(cell_size - 1, triangle.get(i - 1).get(cell_size - 2) + triangle.get(i).get(cell_size - 1));
        }

        //第三行开始
        for (int i = 2; i < triangle.size(); i++)
            //每一行的第二个数到倒数第二个数
            for (int j = 1; j < triangle.get(i).size() - 1; j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j)));
            }

        int min = triangle.get(triangle.size() - 1).get(0);
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            if (min > triangle.get(triangle.size() - 1).get(i))
                min = triangle.get(triangle.size() - 1).get(i);
        }
        return min;
    }

    public static void main(String args[]) {
        ArrayList<Integer> cell1 = new ArrayList<>();
        cell1.add(-1);

        ArrayList<Integer> cell2 = new ArrayList<>();
        cell2.add(2);
        cell2.add(3);

        ArrayList<Integer> cell3 = new ArrayList<>();
        cell3.add(1);
        cell3.add(-1);
        cell3.add(-1);

        List<List<Integer>> result = new ArrayList<>();
        result.add(cell1);
        result.add(cell2);
        result.add(cell3);

        Triangle triangle = new Triangle();
        triangle.minimumTotal(result);
    }


}
