package design;

import java.util.HashMap;
import java.util.Map;

public class EncodeAndDecodeTinyURL {

    /**
     * Approach1
     * 这个做法的思路是用一个数据库存储id - LongUrl，缺点是对于重复输入的LongUrl也会占用id
     * 参考的是https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/
     */
    public class Codec {
        int id = 1;
        //key: id, value: longUrl
        Map<Integer, String> database = new HashMap<>();
        Map<Character, Integer> idxMap = new HashMap<>();
        String arr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] charArr = arr.toCharArray();

        Codec() {
            for (int i = 0; i < arr.length(); i++) {
                idxMap.put(arr.charAt(i), i);
            }
        }

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {

            int n = id;
            StringBuilder shortUrl = new StringBuilder();
            // Convert given integer id to a base 62 number
            // 稍微修改下，就可以实现各种进制转换
            while (n != 0) {
                shortUrl.append(charArr[n % 62]);
                n = n / 62;//这样确实能保证每个id对应hash出不同的结果，比如0是a，1是b；62是ab，63是bb；124是ac, 125是bc；可以看成62进制，id按照62份来分，每一份得到一个hash的unit
            }
            //这里不能用longUrl做key，因为string做hash很容易hash冲突(但是冲突的话也只是效率比较低而已，所以确实可以优化)
            //附：String的Hash算法：h = 31*h + val[off++]; 意思就是一个字符串的hashcode，就是逐个按照字符的utf-16的编码值求和。

            database.put(id, longUrl);
            id++;
            //得到一个62进制数
            return shortUrl.toString();
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            //把base 62的数转换成10进制
            int id = 0;
            //ac = 124，2(c) * 62^1(idx) + 0(a) * 62^0(idx) = 62；
            for (int i = shortUrl.length() - 1; i >= 0; i--) {
                id += idxMap.get(shortUrl.charAt(i)) * Math.pow(62, i);
            }
            return database.get(id);
        }
    }

    /**
     * 这个做法是stefanpochmann的，https://leetcode.com/problems/encode-and-decode-tinyurl/discuss/100268/Two-solutions-and-thoughts
     * 做法是在62个字符里挑选6个数组成一个tinyUrl，keyToURL的Map里不存在就可以。
     */
    public class TinyUrlEncodeDecode {

        private static final String SEED = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        private static final String BASE = "http://tinyurl.com/";

        private Map<String, String> keyToURL = new HashMap<>();
        private Map<String, String> urlToKey = new HashMap<>();

        public String encode(String longUrl) {
            if (longUrl == null || longUrl.isEmpty()) {
                return null;
            }
            if (urlToKey.containsKey(longUrl)) {
                return BASE + urlToKey.get(longUrl);
            }

            StringBuilder key;

            // keep generating keys until a unique one is found
            do {
                key = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    int r = (int) (Math.random() * SEED.length());
                    key.append(SEED.charAt(r));
                }
            } while (keyToURL.containsKey(key));

            keyToURL.put(key.toString(), longUrl);
            urlToKey.put(longUrl, key.toString());

            return BASE + key;
        }

        public String decode(String shortUrl) {
            if (shortUrl == null || shortUrl.isEmpty()) {
                return "";
            }
            String[] shortUrlSplits = shortUrl.split("/");
            return keyToURL.get(shortUrlSplits[shortUrlSplits.length - 1]);
        }
    }


    public static void main(String args[]) {
        EncodeAndDecodeTinyURL test = new EncodeAndDecodeTinyURL();
//        EncodeAndDecodeTinyURL.Codec codec = test.new Codec();
//        String short1 = codec.encode("www.google.com");
//        System.out.println(short1);
//        System.out.println(codec.decode(short1));
//
//        String short2 = codec.encode("www.google.com");
//        System.out.println(short2);
//        System.out.println(codec.decode(short2));

        TinyUrlEncodeDecode tre = test.new TinyUrlEncodeDecode();
        String tinyURL = tre.encode("http://thisislongurl.com/abcd/123");
        String longURL = tre.decode(tinyURL);

        System.out.println("tinyURL = " + tinyURL);
        System.out.println("longURL = " + longURL);
    }
}
