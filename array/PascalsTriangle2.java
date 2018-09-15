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

public class PascalsTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> preLine = new ArrayList<>();
        //corner case
        if (rowIndex == 0) return preLine;


        //first line
        preLine.add(1);
        for (int i = 1; i <= rowIndex ; i++) {
            List<Integer> currentLine = new ArrayList<>();
            currentLine.add(1);
            for (int j = 0; j < preLine.size() -1; j++) {
                currentLine.add(preLine.get(j) + preLine.get(j+1));

            }
            //last number
            currentLine.add(1);
            preLine = currentLine;
        }
        return preLine;
    }

    public static void main(String args[]) {
        PascalsTriangle2 pascalsTriangle = new PascalsTriangle2();
        System.out.println(pascalsTriangle.getRow(5));
    }
}
