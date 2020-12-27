package easy;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> st = new HashMap<>(), ts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (st.containsKey(a)) {
                if (st.get(a) != b) return false;
            } else {
                st.put(a, b);
            }
            if (ts.containsKey(b)) {
                if (ts.get(b) != a) return false;
            } else {
                ts.put(b, a);
            }
        }
        return true;
    }
}
