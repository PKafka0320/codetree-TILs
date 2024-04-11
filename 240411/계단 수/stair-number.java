import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int dp[][];
    static int mod = 1000000007;

    public static void main(String[] args) {
        n = sc.nextInt();
        dp = new int[n + 1][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j > 0) {
                    dp[i + 1][j - 1] = (dp[i + 1][j - 1] + dp[i][j]) % mod;
                }
                if (j < 9) {
                    dp[i + 1][j + 1] = (dp[i + 1][j + 1] + dp[i][j]) % mod;
                }
            }
        }

        // for (int i = 1; i <= n; i++) {
        //     for (int j = 0; j <= 9; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        int ans = 0;
        for (int i = 0; i <= 9; i++) {
            ans = (ans + dp[n][i]) % mod;
        }

        System.out.println(ans);
    }
}