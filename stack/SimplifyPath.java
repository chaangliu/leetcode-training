package stack;

import java.util.Stack;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 20201103
 */
public class SimplifyPath {
    /**
     * 题意：以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
     * 解法：split加stack
     * 直接scan比较麻烦， 技巧是，用split("/")直接得到folder的名字，包括.和..。
     */
    public String simplifyPath(String path) {
        String[] A = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String a : A) {
            if (a.length() == 0) continue;
            if (a.equals(".")) continue;
            if (a.equals("..")) {
                if (stack.empty()) continue;
                stack.pop();
            } else {
                stack.push(a);
            }
        }
        String res = "";
        while (!stack.empty()) {
            res = "/" + stack.pop() + res;
        }
        return res.length() == 0 ? "/" : res;
    }
}
