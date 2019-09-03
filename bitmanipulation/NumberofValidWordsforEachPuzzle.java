package bitmanipulation;

/**
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].
 * <p>
 * <p>
 * Example :
 * <p>
 * Input:
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * Output: [1,1,3,2,4,0]
 * Explanation:
 * 1 valid word for "aboveyz" : "aaaa"
 * 1 valid word for "abrodyz" : "aaaa"
 * 3 valid words for "abslute" : "aaaa", "asas", "able"
 * 2 valid words for "absoryz" : "aaaa", "asas"
 * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 * There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] are English lowercase letters.
 * Each puzzles[i] doesn't contain repeated characters.
 * 20190903
 */
public class NumberofValidWordsforEachPuzzle {
    /**
     * 看了好久好久。。看完我只想说，bit manipulation 太难了，自闭中
     * 思路是压缩words[]数组和puzzles[]数组为一个二进制数字，并且利用位运算实现对比效率O(1)
     * 下面是同事发在讨论区的c++代码
     */
//    class Solution {
//        vector<int> base;
//        void born(vector<string>& words){
//            for(auto& s: words){
//                set<char> tmp;
//                int bit = 0;
//                for(auto c:s){
//                    tmp.insert(c);
//                    bit = bit | (1<<(c-'a'));
//                }
//                if(tmp.size() >7)continue;
//                base.push_back(bit);
//            }
//        }
//        public:
//        vector<int> findNumOfValidWords(vector<string>& words, vector<string>& puzzles) {
//            vector<int> ans;
//            born(words);
//
//            for(auto& s: puzzles){
//                int num = 0;
//                int bit = 0;
//                for(auto c: s){
//                    bit = bit | (1<<(c-'a'));
//                }
//                int firstBit = 1 << (s[0] - 'a');
//                for(auto v: base){
//                    if((v & bit) == v && ((firstBit & v) == firstBit)){
//                        num++;
//                    }
//                }
//                ans.push_back(num);
//            }
//
//            return ans;
//        }
//    };

    /**
     * 花花视频里的代码，但是似乎有bug
     */
//    class Solution {
//        public:
//        vector<int> ans;  // 存储答案
//        unordered_map<int, int> freq;  // "字符串word转化为一个数(位运算): 该模式出现的次数"  的映射
//
//        vector<int> findNumOfValidWords(vector<string> &words, vector<string> &puzzles) {
//            // 统计每个word模式的数量, map形式保存, 位运算进行操作
//            for (string &word : words) {
//                int mask = 0;
//                for (char c : word) {
//                    mask |= 1 << (c - 'a');
//                    freq[mask]++;
//                }
//            }
//
//            for (const string &p : puzzles) {
//                int total = 0;
//            const int l = p.length() - 1;
//                for (int i = 0; i < (1 << l); ++i) {//0~2^7 - 1
//                    int mask = 1 << (p[0] - 'a');// must contain first char
//                    for (int j = 0; j < l; ++j) {
//                        if (i && (1 << j)) mask |= 1 << (p[j + 1] - 'a');//枚举后6位
//                    }
//                    auto it = freq.find(mask);
//                    if (it != freq.end()) total += it->second;
//                }
//                ans.push_back(total);
//            }
//            return ans;
//        }
//    };
}
