package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * For example, given numRows = 5,
 * Return
 * <p>
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * [1,5,10,10,5,1]
 * ]
 * <p>
 * Created by DrunkPiano on 2017/2/21.
 */

public class PascalsTriangle {

    /**
     * approach1. 递归
     *     f(i,j) = f(i−1,j−1)+f(i−1,j)
     *     f(i,j) = 1 where j=1 or j=i
     */

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, 1, numRows);
        return res;
    }

    private void helper(List<List<Integer>> res, int depth, int rowLimit) {
        if (depth > rowLimit) return;
        if (res.size() < depth) {
            res.add(new ArrayList<Integer>());
        }
        List<Integer> curRow = res.get(depth - 1);
        for (int i = 0; i < depth; i++) {
            if (i == 0 || i == depth - 1) curRow.add(1);
            else {
                List<Integer> upperRow = res.get(depth - 1 - 1);
                curRow.add(upperRow.get(i - 1) + upperRow.get(i));
            }
        }
        helper(res, depth + 1, rowLimit);
    }

    //我一开始想模仿二叉树level order traversal那么dfs，但是这么做行不通，因为这题是top-down的，而这么做会是bottom-up，会从底往上加数据
    private void dfs(List<List<Integer>> res, int depth, int rowLimit, int start) {
        if (depth > rowLimit) return;
        if (res.size() < depth) {
            res.add(new ArrayList<Integer>());
        }
        List<Integer> curRow = res.get(depth - 1);
        for (int i = start; i < depth; i++) {
            if (i == 0 || i == depth - 1) curRow.add(1);
            else {
                List<Integer> upperRow = res.get(depth - 1 - 1);
                curRow.add(upperRow.get(i - 1) + upperRow.get(i));
            }
            dfs(res, depth + 1, rowLimit, i);
        }
    }


    /**
     * approach2. 迭代
     */
    public List<List<Integer>> generate__ITERATION(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        //corner case
        if (numRows == 0) return result;

        List<Integer> preLine = new ArrayList<>();

        //first line
        preLine.add(1);
        result.add(new ArrayList<>(preLine));

        for (int i = 1; i < numRows ; i++) {
            List<Integer> currentLine = new ArrayList<>();
            currentLine.add(1);
            for (int j = 0; j < preLine.size() -1; j++) {
                currentLine.add(preLine.get(j) + preLine.get(j+1));

            }
            //last line
            currentLine.add(1);
            result.add(currentLine);
            preLine = currentLine;
        }
        return result;
    }

    public static void main(String args[]) {
        PascalsTriangle pascalsTriangle = new PascalsTriangle();
        System.out.println(pascalsTriangle.generate(5));
    }
}
