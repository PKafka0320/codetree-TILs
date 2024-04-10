import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int m;
    static int[] s;
    static int[] e;
    static int[] v;
    static int[][] dp;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        s = new int[n + 1];
        e = new int[n + 1];
        v = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt();
            e[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        //i 번째 날짜에 입은 옷이 j일때 최대로 얻을 수 있는 만족도의 합
        dp = new int[m + 1][n + 1];
        for (int r = 1; r < m; r++) {
            for (int i = 1; i <= n; i++) {
                if (r < s[i] || e[i] < r) continue;

                for (int j = 1; j <= n; j++) {
                    if (r < s[j] || e[j] < r) continue;

                    dp[r + 1][j] = Math.max(dp[r + 1][j], dp[r][i] + Math.abs(v[i] - v[j]));
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[m][i]);
        }

        System.out.println(ans);
    }
}