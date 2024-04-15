import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String a, b;
    static int[][] dp;

    public static void main(String[] args) {
        a = " " + sc.next();
        b = " " + sc.next();
        int n = a.length() - 1;
        int m = b.length() - 1;

        // dp[i][j] : 첫 번째 문자열의 i번째, 두 번재 문자열의 j번째까지 확인했을때 최장 공통 부분 수열의 길이
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}