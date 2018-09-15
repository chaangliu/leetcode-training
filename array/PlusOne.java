package array;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * <p>
 * Created by DrunkPiano on 2016/12/6.
 */

public class PlusOne {
    public static int[] plusOne(int[] digits) {
        int carry = 0 ;
        for(int i = digits.length-1 ; i >= 0 ; i --){
            digits[i]  = (digits[i]+1)%10 ;
            if(digits[i] == 0){
                carry = 1 ;
            }
            else{
                carry = 0;
            }
            if(carry == 0 ){
                break;
            }
        }
        if(carry == 1){
            int [] newArray = new int[digits.length+1];
            System.arraycopy(digits,0,newArray,1,digits.length);
            newArray[0] = 1;
            return newArray;
        }
        return digits;
    }

    public static void main(String args[]){
        int [] array = {9,9,9};
        int [] array2 = plusOne(array);
        for(int i = 0 ; i < array2.length ; i ++)
        System.out.println(array2[i]);
    }
}
