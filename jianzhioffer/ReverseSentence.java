package jianzhioffer;

/**
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。
 * 后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence {
    //这题9月面试的时候问到，我直接用split来做，面试官一直提示我直接操作字符；现在想想他想看的是剑指offer上的解法，先翻转每个单词，再翻转整个句子；或者先翻转整个句子，再翻转每个单词。
    //证明一件事情：无论你想的答案复杂度如何，面试官想看的永远是他看过的那个答案。
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0) return str;
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length - 1);
        //记录blank位置比记录start和end方便
        int blankIndex = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverse(chars, blankIndex + 1, i - 1);
                blankIndex = i;
            }
        }
        reverse(chars,blankIndex + 1,chars.length - 1);//易错：最后一个单词单独进行反转，因为最后没有空格
        return new String(chars);
    }

    private void reverse(char[] chars, int low, int high) {
        while (low < high) {
            char tmp = chars[low];
            chars[low] = chars[high];
            chars[high] = tmp;
            low++;
            high--;
        }
    }

    public static void main(String args[]){
        new ReverseSentence().ReverseSentence("Wonderful");
    }
}
