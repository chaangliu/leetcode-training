package binarysearch.templateii;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * Example:
 * Given n = 5, and version = 4 is the first bad version.
 * <p>
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * <p>
 * Then 4 is the first bad version.
 * 20190628
 */
public class FirstBadVersion {
    /**
     * 我的做法：用到连续的两个数值，运用模板2。
     * 注意这里r取n，也就是最后一个可用的index；不用担心isBadVersion(mid + 1)越界，因为l < r， mid + 1最多也就等于r
     */
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            boolean pre = isBadVersion(mid), cur = isBadVersion(mid + 1);
            if (!pre && cur) return mid + 1;
            if (!pre && !cur) l = mid + 1;
            if (pre && cur) r = mid;
        }
        return l;
    }

    /**
     * 答案的做法：不用中途return，直到while退出循环才return left，总能保证left是第一个满足的
     */
    public int firstBadVersion_(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    boolean isBadVersion(int version) {
        return false;
    }
}
