package jianzhioffer;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqueChar2 {

    ///这题牛客的case必须在默认情况返回一个#..否则AC不了

    LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        map.put(ch, map.getOrDefault(ch, 0) + 1);
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return '#';
    }
}
