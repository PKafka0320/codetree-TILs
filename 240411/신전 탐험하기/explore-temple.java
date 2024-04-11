import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static int n;
    static int[][] treasuer;
    static int[][] dp;

    public static void main(String[] args) {
        n = sc.nextInt();
        treasuer = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                treasuer[i][j] = sc.nextInt();
            }
        }

        // dp[i][j] : i번째 층까지 올라왔을 때,
        // j번 방 (j = 1일 때 : 왼쪽, j = 2일 때 : 가운데, j = 3일 때 : 오른쪽)
        // 을 골랐을 때 가져갈 수 있는 보물의 최대 개수
        dp = new int[n + 1][3];
        
        for (int i = 0; i < n; i++) { // i 번째 층
            for (int j = 0; j < 3; j++) { // j 번째 방을 선택한 경우
                for (int k = 0; k < 3; k++) { // 이전 방이 k 번째인 경우
                    if (j == k) continue;
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][k] + treasuer[i + 1][j]);
                }
            }
        }

        // 결과 계산
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans = Math.max(ans, dp[n][i]);
        }

        System.out.println(ans);
    }
}