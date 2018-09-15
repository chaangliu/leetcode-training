package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrunkPiano on 09/07/2017.
 */

public class SolveTheEquation {
//	public String solveEquation(String equation) {
//		if (equation == null || equation.length() == 0) return "";
//		int len = equation.length();
//		char[] c = equation.toCharArray();
//		int pivot = 0;
//		for (int i = 0; i < len; i++) {
//			if (c[i] == '=') {
//				pivot = i;
//				c[i] = '-';
//			}
//			if (i > pivot) {
//				//=右边第一位是-。暂不考虑右边第一位是+的情况。
//				if (i == pivot + 1 && c[i] == '-') {
//					c[i] = '1';
//				} else {
//					if (c[i] == '+') {
//						c[i] = '-';
//					} else if (c[i] == '-') {
//						c[i] = '+';
//					}
//				}
//			}
//		}
//		int divider = 0 ;
//		for (int i = 0 ; i < len ; i ++){
//			if (c[i] == 'x'){
//				if (i - 1 >=0){
////					if (c[i - 1] >='1' && c[i - 1]<='9')
////					写到这里我发现，coefficient还可能不止一位。。
////					再写下去没劲了。。跟写作文似的
//				}
//			}
//		}
//	}

	public String coeff(String x) {
		if (x.length() > 1 && x.charAt(x.length() - 2) >= '0' && x.charAt(x.length() - 2) <= '9')
			return x.replace("x", "");
		return x.replace("x", "1");
	}
	public String solveEquation(String equation) {
		String[] lr = equation.split("=");
		int lhs = 0, rhs = 0;
		for (String x: breakIt(lr[0])) {
			if (x.indexOf("x") >= 0) {
				lhs += Integer.parseInt(coeff(x));
			} else
				rhs -= Integer.parseInt(x);
		}
		for (String x: breakIt(lr[1])) {
			if (x.indexOf("x") >= 0)
				lhs -= Integer.parseInt(coeff(x));
			else
				rhs += Integer.parseInt(x);
		}
		if (lhs == 0) {
			if (rhs == 0)
				return "Infinite solutions";
			else
				return "No solution";
		}
		return "x=" + rhs / lhs;
	}
	public List< String > breakIt(String s) {
		List < String > res = new ArrayList< >();
		String r = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				if (r.length() > 0)
					res.add(r);
				r = "" + s.charAt(i);
			} else
				r += s.charAt(i);
		}
		res.add(r);
		return res;
	}

	public static void main(String args[]){
		new SolveTheEquation().solveEquation("x+15-3+x=6+x-2");
	}

}
