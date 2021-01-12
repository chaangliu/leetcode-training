    /**
    * https://www.e-learn.cn/content/qita/2293425
    * 题意：把n个骰子扔到地上，所有骰子朝上一面的点数之后为s. 输入n，打印出s所有可能的值出现的概率。（每个骰子6个面，点数从1到6）
    * dp[i][j]表示i个骰子扔出和为j的情况总数。
    * dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + .. + dp[i - 1][j - 6]
    */

    #include <iostream>
    #include <cmath>
    using namespace std;
    int **dp;
    void printProbability(int n){ //n是骰子数
    if(n < 1) return ;
    int total = pow(6,n);//总共permutations数量
    dp = new int*[n+1];
    for(int i=0;i<=n;i++) dp[i] = new int[6*n+1]; //dp[i][j]表示i个骰子扔出和为j的情况总数。

    for(int i=0;i<=n;++i) //注意 c<=n
        for(int j=0;j<=6*n;++j)
            dp[i][j] = 0;

    for(int i=1;i<=n;++i) //注意 i<=n
        for(int j=i;j<=i*6;++j){ //注意 j<=6i
            if(i==1 || j==1)
                dp[i][j] = 1;//有1个骰子，dp(1,1)=dp(1,2)=dp(1,3)=dp(1,4)=dp(1,5)=dp(1,6)=1
            else{
                int sum = 0;
                for(int k=1;k<=6 && k<j;++k)
                    sum += dp[i-1][j-k];//dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + .. + dp[i - 1][j - 6]
                dp[i][j] = sum;
            }
        }

    for (int i = 1; i<= n; i++) {
         cout << "a[" << i <<"]: ";
         for (int j = i; j <= 6*i; j++) {
             cout << dp[i][j] << " ";
        }
        cout<<endl;
     }
    }

    int main(){
    printProbability(3);

    return 0;
    }