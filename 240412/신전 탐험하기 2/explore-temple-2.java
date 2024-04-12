import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] treasuer;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        // treasuer[i][j] : i번째 층 j번 방에 있는 보물의 개수
        treasuer = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                treasuer[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j][k] : 처음 i번 방에 들어가고, j번째 층에서
        // k번 방 (j = 0일 때 : 왼쪽, j = 1일 때 : 가운데, j = 2일 때 : 오른쪽)
        // 을 골랐을 때 가져갈 수 있는 보물의 최대 개수
        dp = new int[3][n + 1][3];
        dp[0][1][0] = treasuer[1][0];
        dp[1][1][1] = treasuer[1][1];
        dp[2][1][2] = treasuer[1][2];

        for (int i = 0; i < 3; i++) { // 처음 i번째 방
            for (int j = 1; j < n; j++) { // j번째 층
                for (int k = 0; k < 3; k++) { // 이전 방이 k번인 경우
                    for (int l = 0; l < 3; l++) { // 다음 방이 ㅣ번인 경우
                        if (k == l) continue;
                        dp[i][j + 1][l] = Math.max(dp[i][j + 1][l], dp[i][j][k] + treasuer[j + 1][l]);
                    }
                }
            }
        }

        // 결과 계산
        int ans = 0;
        for (int i = 0; i < 3; i++) { // 처음 i번째 방
            for (int j = 0; j < 3; j++) { // 마지막 j번째 방
                if (i == j) continue;
                ans = Math.max(ans, dp[i][n][j]);
            }
        }

        System.out.println(ans);
    }
}