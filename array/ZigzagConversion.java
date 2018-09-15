package array;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * <p>
 * Created by DrunkPiano on 2017/5/6.
 */

public class ZigzagConversion {
    //    public String convert(String s, int numRows) {
//        boolean oddCol = true;
//        boolean space = true;
//        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();
//        int i = 0;
//        while (i < s.length()) {
//            ArrayList<Character> colItem = new ArrayList<>();
//            while (colItem.size() < numRows && i < s.length()) {
//                if (oddCol) {
//                    colItem.add(s.charAt(i++));
//                } else {
//                    if (space) {
//                        colItem.add(' ');
//                        space = !space;
//                    } else {
//                        colItem.add(s.charAt(i++));
//                        space = !space;
//                    }
//                }
//            }
//            space = true;
//            oddCol = !oddCol;
//            matrix.add(new ArrayList<Character>(colItem));
//            colItem.clear();
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int m = 0; m < numRows; m++)
//            for (int j = 0; j < matrix.size(); j++) {
////                char c = matrix.get(j).get(m) !=null ? matrix.get(j).get(m) : null;
//                ArrayList<Character> item = matrix.get(j);
//                if (m < item.size())
//                    if (item.get(m) != ' ') {
//                        sb.append(item.get(m));
//                    }
//            }
//        return sb.toString();
//    }
    public String convert(String s, int nRows) {
        if (nRows == 0 || nRows == 1) return s;
        StringBuilder[] sb = new StringBuilder[nRows];
        for (int i = 0; i < nRows; i++) sb[i] = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            for (int row = 0; row < nRows && i < s.length(); row++) {
                sb[row].append(s.charAt(i++));//vertically down
            }
            for (int row = nRows - 2; row > 0 && i < s.length(); row--) {
                sb[row].append(s.charAt(i++));//obliquely up
            }
        }

        for (int j = 1; j < nRows; j++) {
            sb[0].append(sb[j]);
        }
        return sb[0].toString();
    }

    public static void main(String args[]) {
        System.out.println(new ZigzagConversion().convert("PAYPALISHIRING", 3));
    }
}
