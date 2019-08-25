package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * You are asked to design a file system which provides two functions:
 * <p>
 * create(path, value): Creates a new path and associates a value to it if possible and returns True. Returns False if the path already exists or its parent path doesn't exist.
 * get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 * <p>
 * Implement the two functions.
 * <p>
 * Please refer to the examples for clarifications.
 * Example 1:
 * <p>
 * Input:
 * ["FileSystem","create","get"]
 * [[],["/a",1],["/a"]]
 * Output:
 * [null,true,1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 * <p>
 * fileSystem.create("/a", 1); // return true
 * fileSystem.get("/a"); // return 1
 * Example 2:
 * <p>
 * Input:
 * ["FileSystem","create","create","get","create","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 * <p>
 * fileSystem.create("/leet", 1); // return true
 * fileSystem.create("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.create("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of calls to the two functions is less than or equal to 10^4 in total.
 * 2 <= path.length <= 100
 * 1 <= value <= 10^9
 * 20190825
 */
public class DesignFileSystem {
    /**
     * 模拟文件系统。虽然是medium但感觉没什么技巧可言。可以用Trie?有点麻烦吧
     */
    class FileSystem {
        Map<String, Integer> map = new HashMap<>();

        public FileSystem() {

        }

        public boolean create(String path, int value) {
            if (path.endsWith("/")) return false;
            int pivot = -1;
            for (int i = path.length() - 1; i >= 0; i--) {
                if (path.charAt(i) == '/') {
                    pivot = i;
                    break;
                }
            }
            String parent = path.substring(0, pivot);
            if (parent.length() == 0 || map.containsKey(parent)) {
                map.put(path, value);
                return true;
            }
            return false;
        }

        public int get(String path) {
            return map.getOrDefault(path, -1);
        }
    }

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.create(path,value);
 * int param_2 = obj.get(path);
 */
}
