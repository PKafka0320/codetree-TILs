import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int[][][] dp;

    public static void main(String[] args) {
        n = sc.nextInt();
        dp = new int[n + 1][3][3];

        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        int mod = 1000000007;

        for (int i = 2; i <= n; i++) {
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % mod;
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][0][2] = dp[i - 1][0][1];
            dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2] 
                + dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % mod;
            dp[i][1][1] = dp[i - 1][1][0];
            dp[i][1][2] = dp[i - 1][1][1];
            dp[i][2][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2] 
                + dp[i - 1][2][0] + dp[i - 1][2][1] + dp[i - 1][2][2]) % mod;
            dp[i][2][1] = dp[i - 1][2][0];
            dp[i][2][2] = dp[i - 1][2][1];
        }

        int ans = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                // System.out.print(dp[n][r][c] + " ");
                ans = (ans + dp[n][r][c]) % mod;
            }
            // System.out.println();
        }

        System.out.println(ans);
    }
}