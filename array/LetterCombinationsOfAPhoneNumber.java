package array;

import java.util.ArrayList;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * <p>
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/2/15.
 */

public class LetterCombinationsOfAPhoneNumber {
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> newResult  = new ArrayList<>();
        if(digits.length()==0) return result;
        result.add("");
        int digitLen = digits.length();
        for(int i = 0 ; i < digitLen ; i ++){
            String letter = getLetters(digits.charAt(i));
            newResult = new ArrayList<>();
            //1st :1
            for(int j = 0 ; j < result.size() ; j++ ){
                for(int k = 0 ; k < letter.length() ; k ++){
                    newResult.add(result.get(j) + letter.charAt(k));
                }
            }
            result = newResult;
        }
        return result;
    }

    private String getLetters(char digit) {
        switch (digit) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
            case '0':
                return "";
            default:
                return "";
        }
    }

    public static void main(String args[]){
        LetterCombinationsOfAPhoneNumber letterCombinationsOfAPhoneNumber = new LetterCombinationsOfAPhoneNumber();
        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinations("23"));
    }
}
