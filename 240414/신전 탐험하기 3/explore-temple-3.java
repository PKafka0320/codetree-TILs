import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static int n, m;
    static int[][] treasuer;
    static int[][] dp;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        treasuer = new int[n + 1][m];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                treasuer[i][j] = sc.nextInt();
            }
        }

        // dp[i][j] : i번째 층까지 올라왔을 때, j번 방을 선택한 경우 가져갈 수 있는 보물의 최대 개수
        dp = new int[n + 1][m];
        
        for (int i = 0; i < n; i++) { // i 번째 층
            for (int j = 0; j < m; j++) { // j 번째 방을 선택한 경우
                for (int k = 0; k < m; k++) { // 이전 방이 k 번째인 경우
                    if (j == k) continue;
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][k] + treasuer[i + 1][j]);
                }
            }
        }

        // 결과 계산
        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, dp[n][i]);
        }

        System.out.println(ans);
    }
}