import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int k;
    static String s;
    static int[][] dp;

    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();
        s = sc.next();

        // dp[i][j] : i번째 수정, j번 이동한 경우 얻을 수 있는 최대 수정의 개수
        dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) { // i번째 수정
            for (int j = 0; j <= k; j++) { // j번째 이동
                if (dp[i][j] == -1) continue;
                
                // 이동하지 않는 경우
                if (s.charAt(i) == 'L') {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + (j % 2 == 0 ? 1 : 0));
                }
                else {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + (j % 2 == 1 ? 1 : 0));
                }

                // 이동하는 경우
                if (j == k) continue; // 마지막 이동 예외처리
                if (s.charAt(i) == 'L') {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + ((j + 1) % 2 == 0 ? 1 : 0));
                }
                else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + ((j + 1) % 2 == 1 ? 1 : 0));
                }
            }
        }

        // 결과 계산
        int ans = 0;
        for (int i = 0; i <= k; i++) {
            ans = Math.max(ans, dp[n][i]);
        }

        System.out.println(ans);
    }
}