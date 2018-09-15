package dfs;

/**
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Created by DrunkPiano on 2017/5/1.
 */

public class PermutationInString {
    String sss;
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return true;
        if (s2 == null) return false;
        sss = s2;
        boolean[] used = new boolean[s1.length()];
        String s = "";
        return dfs(s, s1, used);
    }

    private boolean dfs(String s, String s1, boolean[] used) {
        if (s.length() == s1.length()) {
            if (isSubstring(s)) return true;
            else return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (!used[i]) {
                used[i] = true;
                if (dfs(s + s1.charAt(i), s1, used)) return true;
                used[i] = false;
            }
        }
        return false;
    }

    private boolean isSubstring(String s1) {
        for (int i = 0; i < sss.length() - s1.length() + 1; i++) {
            if (sss.substring(i, i + s1.length()).equals(s1)) return true;
        }
        return false;
    }

// The idea is to create one base array, another moving array
// tell the arrays are equal or not during each iteration

//    public boolean checkInclusion(String s1, String s2) {
//        int n1 = s1.length(), n2 = s2.length();
//        if (n1 > n2) return false;
//        int[] map1 = new int[26];
//        int[] map2 = new int[26];
//        for (int i = 0; i < n1; i++) {
//            map1[s1.charAt(i) - 'a']++;
//        }
//        for (int j = 0; j < n2; j++) {
//            map2[s2.charAt(j) - 'a']++;
//            if (j >= n1) {
//                //定位到从窗口出去的那个数，减去一次occurence
//                map2[s2.charAt(j - n1) - 'a']--;
//            }
//            if (Arrays.equals(map1, map2)) return true;
//        }
//        return false;
//    }

    public static void main(String args[]) {
//        System.out.println(new PermutationInString().isSubstring("da"));
        System.out.println(new PermutationInString().checkInclusion("adc", "dcda"));
    }
}
