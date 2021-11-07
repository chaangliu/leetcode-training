/**
子字符串 是字符串中的一个连续（非空）的字符序列。
元音子字符串 是 仅 由元音（'a'、'e'、'i'、'o' 和 'u'）组成的一个子字符串，且必须包含 全部五种 元音。
给你一个字符串 word ，统计并返回 word 中 元音子字符串的数目 。
输入：word = "cuaieuouac"
输出：7
解释：下面列出 word 中的元音子字符串（斜体加粗部分）：
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
**/
class Solution {
public:
   /**
    * 题意：找出包含且仅包含aeiou这五种字母的substring的个数。
    * 解法：这题容易想到是sliding window，而且会发现left和right都需要变化，这种情况可以采用atMost的方法，
    * 类似SubarraysWithKDifferentIntegers。
   **/
    int isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    int atMost(string word, int N) {
        int left = 0, right = 0, res = 0;
        unordered_map<char, int> map;
        while (right < word.size()) {
            if (!isVowel(word[right])) {
                left = right + 1;
                map.clear(); right++;
                continue;
            }
            map[word[right]]++;
            while(map.size() > N) {
                if (--map[word[left]] == 0) map.erase(word[left]); // 少了一个unique vowel
                left++;
            } 
            res += right - left + 1;
            right++;
        }
        return res;
    }

    int countVowelSubstrings(string word) {
        return atMost(word, 5) - atMost(word, 4);
    }
};