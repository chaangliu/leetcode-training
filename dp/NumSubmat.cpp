/**
 * 给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
   示例 1：
   输入：mat = [[1,0,1],
               [1,1,0],
               [1,1,0]]
   输出：13
   解释：
   有 6 个 1x1 的矩形。
   有 2 个 1x2 的矩形。
   有 3 个 2x1 的矩形。
   有 1 个 2x2 的矩形。
   有 1 个 3x1 的矩形。
   矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
**/
class Solution {
/**
 * 题意：求全是1的矩形的个数
 * 解法，dp[i][j]记录截至A[i][j]，第i行1的个数。然后枚举矩形右下角，向上扩展矩形右上角，找一个最短板，加上即可。
 参考：https://leetcode-cn.com/problems/count-submatrices-with-all-ones/solution/5454-tong-ji-quan-1-zi-ju-xing-by-lin-miao-miao/
**/
public:
    int numSubmat(vector<vector<int>>& mat) {
        int n = mat.size();
        int m = mat[0].size();
        vector<vector<int> > left(n,vector<int>(m));
        int now = 0;
        for(int i=0;i<n;i++){
            now = 0;
            for(int j=0;j<m;j++){
                if(mat[i][j] == 1) now ++;
                else now = 0;
                left[i][j] = now;
            }
        }
        int ans = 0,minx;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                minx = 9999;
                for(int k=i;k>=0;k--){
                    minx = min(left[k][j],minx);
                    ans += minx;
                }
            }
        }
        return ans;
    }
};