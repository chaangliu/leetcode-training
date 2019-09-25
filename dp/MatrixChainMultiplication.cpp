#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

#include <iostream>
#include <set>
#include <string>
#include <climits>

#include <iostream>
#include <set>
#include <string>
#include <climits>


#include <vector>
#include <unordered_map>


//经典矩阵连乘问题，我这里仅实现求最小连乘结果；下面的链接还可以把怎么分割打印出来，很巧妙
//参考https://www.jianshu.com/p/59f6fac01315，OJ:https://onlinejudge.u-aizu.ac.jp/problems/ALDS1_10_B
//矩阵连乘问题，递归方程采用课本《计算机算法设计与分析》P47的递归方程。

#include <iostream>

using namespace std;

void CalculateMin(int i, int j, int dimen[], int **dp);

/**
 * 区间dp(最内层的for循环放到CalculateMin函数里处理)写法
 */
void matrixChain(int n, int dimen[], int **dp) {
    for (int len = 0; len <= n; ++len) {
        for (int i = 1; i + len <= n; ++i) {
            int j = i + len;
            if (i == j) {
                dp[i][j] = 0;
            } else {
                CalculateMin(i, j, dimen, dp);//实现转移方程
            }
        }
    }
}


void CalculateMin(int i, int j, int dimen[], int **dp) {
    dp[i][j] = dp[i][i] + dp[i + 1][j] + dimen[i - 1] * dimen[i] * dimen[j];
    for (int k = i + 1; k < j; k++) {//k用于尝试不同的断开位置
        int curr = dp[i][k] + dp[k + 1][j] + dimen[i - 1] * dimen[k] * dimen[j];
        if (curr < dp[i][j]) {//当前k值作为Ai连乘到Aj的断开位置能获得更少计算量
            dp[i][j] = curr; //刷新最优值
        }
    }
}

int main() {
    int n;
    cin >> n;
    int *tempP = new int[2 * n];
    int *dimen = new int[n + 1];
    int **dp = new int *[n + 1];
    for (int i = 1; i <= n; i++) {
        dp[i] = new int[n + 1];
    }
    for (int i = 0; i < 2 * n; i++) {
        cin >> tempP[i];
    }
    for (int i = 0; i <= n; i++) {
        dimen[i] = i == 0 ? tempP[0] : tempP[i * 2 - 1];
    }
    matrixChain(n, dimen, dp);
    cout << dp[1][n] << endl; //最优值存储在m[1][n]
    return 0;
}

///----------––––––––-以下是链接里的code，能递归打印分割方式。https://www.jianshu.com/p/59f6fac01315----------––––––––


#include <iostream>

using namespace std;

void MatrixChain(int, int[], int **, int **);

void print(int, int, int **);

void CalculateMin(int i, int j, int p[], int **m, int **s);

int main() {
    int n;
    cin >> n;
    int *tempP = new int[2 * n]; //p[0]为A1的行数；p[i]为Ai的列数

    int *p = new int[n + 1];    //p[0]为A1的行数；p[i]为Ai的列数
    int **m = new int *[n + 1]; //m[i][j]为Ai连乘到Aj的最少乘次数
    int **s = new int *[n + 1]; //s[i][j]为 Ai连乘到Aj的最优解的第一层断开位置
    for (int i = 1; i <= n; i++) {
        m[i] = new int[n + 1];
        s[i] = new int[n + 1];
    }

    for (int i = 0; i < 2 * n; i++) {
        cin >> tempP[i];
    }

    for (int i = 0; i <= n; i++) {
        p[i] = i == 0 ? tempP[0] : tempP[i * 2 - 1];
    }

    MatrixChain(n, p, m, s);
    cout << m[1][n] << endl; //最优值存储在m[1][n]
    print(1, n, s); //打印最优计算方式

    return 0;
}

void MatrixChain(int n, int p[], int **m, int **s) {
    //以下双重循环对m[][]的上三角进行填表
    //填表顺序为自底向上，自左向右
    for (int i = n; i >= 1; i--) //i表示行
    {
        for (int j = i; j <= n; j++) //j表示列，因为只填上三角，所以j的初值为i
        {
            //以下按照矩阵连乘问题的递归公式来求每一个m[i][j]
            if (i == j) {
                m[i][j] = 0;
            } else {
                CalculateMin(i, j, p, m, s);
            }
        }
    }
}

void CalculateMin(int i, int j, int p[], int **m, int **s) {
    m[i][j] = m[i][i] + m[i + 1][j] + p[i - 1] * p[i] * p[j]; //若i与j不相等，m[i][j]的初值为断开位置为i时的最优值

    s[i][j] = i;                    //记录当前断开位置位置为i
    for (int k = i + 1; k < j; k++) //k用于尝试不同的断开位置
    {
        int curr = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
        if (curr < m[i][j]) //当前k值作为Ai连乘到Aj的断开位置能获得更少计算量
        {
            m[i][j] = curr; //刷新最优值
            s[i][j] = k;   //刷新相应断开位置
        }
    }
}

void print(int i, int j, int **s) {
    if (i == j)
        cout << "A" << i;
    else {
        cout << "(";
        print(i, s[i][j], s);
        print(s[i][j] + 1, j, s);
        cout << ")";
    }
}
