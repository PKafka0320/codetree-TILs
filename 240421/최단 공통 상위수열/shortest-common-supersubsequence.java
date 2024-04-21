import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String s, t;
    static int[][] dp;

    public static void main(String[] args) {
        s = " " + sc.next();
        t = " " + sc.next();
        int n = s.length() - 1;
        int m = t.length() - 1;

        // dp[i][j] : 문자열 s 의 i 번째, 문자열 t 의 j 번째까지 확인했을때, 상위 수열을 갖는 문자열의 최소 길이
        dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}