package hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 * <p>
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 * <p>
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 * Example 1:
 * <p>
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * Example 2:
 * <p>
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
 * Example 3:
 * <p>
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'
 * folder[i] always starts with character '/'
 * Each folder name is unique.
 * 20191020
 */
public class RemoveSubFoldersfromtheFilesystem {
    /**
     * 题意：给你一些folders的绝对路径，让你过滤掉其中的子目录。
     * 我的做法: sort + hashSet
     */
    public List<String> removeSubfolders(String[] folder) {
        List<String> res = new ArrayList<>();
        Arrays.sort(folder);
        HashSet<String> set = new HashSet<String>();
        for (String f : folder) {
            String[] s = f.split("/");//  "/a/b" => ["a","b"]
            StringBuilder sb = new StringBuilder();
            boolean broken = false;
            for (String cell : s) {//"a"
                if (cell.equals("")) continue;
                sb.append("/");
                sb.append(cell);
                if (set.contains(sb.toString())) {
                    broken = true;
                    break;
                }
            }
            if (!broken) {
                set.add(f);
                res.add(f);
            }
        }
        return res;
    }

    /**
     * rock的做法1, 无需split
     */
    public List<String> removeSubfolders_(String[] folder) {
        Arrays.sort(folder, Comparator.comparing(s -> s.length()));
        Set<String> seen = new HashSet<>();
        outer:
        for (String f : folder) {
            for (int i = 2; i < f.length(); ++i)
                if (f.charAt(i) == '/' && seen.contains(f.substring(0, i)))
                    continue outer;
            seen.add(f);
        }
        return new ArrayList<>(seen);
    }

    /**
     * rock的做法2，利用startWith，O(1)space
     */
    public List<String> removeSubfolders__(String[] folder) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(folder);
        String parent = " /"; // dummy value.
        for (String f : folder) {
            if (!f.startsWith(parent)) {
                parent = f + '/';
                ans.add(f);
            }
        }
        return ans;
    }
}
