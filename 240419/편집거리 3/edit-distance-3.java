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

        // dp[i][j] : A의 i번째 문자까지, B의 j번째 문자까지 고려했을때의 최소 편집거리
        dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        // for (int i = 0; i <= n; i++) {
        //     for (int j = 0 ; j <= m; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        System.out.println(dp[n][m]);
    }
}