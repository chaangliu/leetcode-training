package array;

/**
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Created by DrunkPiano on 2017/3/15.
 */

public class ImplementstrStr {
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        if(needle.length()==0) return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int needleIndex = 0;
            int haystackIndex = i;
            while (needle.charAt(needleIndex) == haystack.charAt(haystackIndex)) {
                needleIndex++;
                haystackIndex++;
                if (needleIndex == needle.length()) {
                    return haystackIndex - needleIndex;
                }
            }
        }
        return -1;
    }

}

