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
    public List<List<Integer>> generate(int numRows) {
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
