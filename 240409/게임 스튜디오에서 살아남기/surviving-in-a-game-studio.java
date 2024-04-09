import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static long[][][] dp; //i 번째 날까지 받은 T의 횟수와 연속된 B의 횟수가 가능한 경우의 수

    public static void main(String[] args) {
        n = sc.nextInt();
        dp = new long[n + 1][4][4];

        dp[1][0][0] = 1; //첫 번째 날에 G를 받은 경우
        dp[1][1][0] = 1; //첫 번째 날에 T를 받은 경우
        dp[1][0][1] = 1; //첫 번째 날에 B를 받은 경우

        int mod = 1000000007;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i + 1][j + 1][0] = (dp[i + 1][j + 1][0] + dp[i][j][k]) % mod; //T를 받는 경우
                    dp[i + 1][j][0] = (dp[i + 1][j][0] + dp[i][j][k]) % mod; //G를 받는 경우
                    dp[i + 1][j][k + 1] = (dp[i + 1][j][k + 1] + dp[i][j][k]) % mod; //B를 받는 경우
                }
            }
        }

        long ans = 0;
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