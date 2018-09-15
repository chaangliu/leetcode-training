package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * <p>
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * <p>
 * Created by DrunkPiano on 2017/2/7.
 */

public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        List<Integer> rowIndex = new ArrayList<>();
        List<Integer> colIndex = new ArrayList<>();

        for(int i = 0 ; i < row ; i ++)
            for(int j = 0 ; j < col ; j ++){
                if (matrix[i][j] == 0){
                    if(!rowIndex.contains(i))
                        rowIndex.add(i);
                    if (!colIndex.contains(j))
                        colIndex.add(j);
                }
            }

        for(int i = 0 ; i < rowIndex.size() ; i ++){
            for(int j = 0 ; j < col ; j ++)
            matrix[rowIndex.get(i)][j] = 0 ;
        }
        for (int i = 0 ; i < colIndex.size(); i ++){
            for (int j = 0 ; j < row ; j ++)
                matrix[j][colIndex.get(i)] = 0;
        }
    }

}


/**
 * 1 2 3
 * 4 4 0
 * 7 6 9
 * <p>
 * [0][1]
 **/