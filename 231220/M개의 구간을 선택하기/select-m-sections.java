// 상태
// - 구간 총합
// - 구간 개수
// - 원소 위치 (몇번째 원소 보는 지)
// - 구간이 끝나는 위치 ( 마지막 원소가 구간에 포함되면 다음엔 새로운 구간을 만들 수 없고)
//   그렇지 않다면 새로운 구간을 추가할 수 있음 )

// 1) dp[i][j][0] = i번째 원소를 비교할 때, 구간이 j개이고 마지막 원소가 k 상태인 최대 구간 총합
// 2) - k = 0인 경우, dp[i][j][0] = dp[i-1][j][1], dp[i-1][j][0]
//      마지막 원소를 포함하지 않는다는 거니까 이전 원소까지를 봤을때 마지막 원소 포함된 경우, 안된 경우 중 최대
//    - k = 1인 경우, dp[i][j][1] = dp[i-1][j][1] + i번째 원소 값, dp[i-1][j-1][0] + i번째 원소 값
//      마지막 원소를 포함하니까 마지막 원소가 구간에 포함되는 경우와 새로운 구간을 만드는 경우 중 최대.
// 3) 모든 원소에서 구간이 처음 시작가능 -> dp[i][0][0] = 0



import java.io.*;

public class Main {
    static final int MIN_VAL = Integer.MIN_VALUE;
    static final int MAX_N = 500, MAX_M = 500;
    static int n, m;
    static int[] grid = new int[MAX_N + 1];
    static int[][][] dp = new int[MAX_N + 1][MAX_M + 1][2];

    static void initialize() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k < 2; k++) {
                    if (j == 0 && k == 0) { // 구간의 시작은 어떤 원소든 가능하기에 0으로 설정.
                        dp[i][j][k] = 0;
                        continue;
                    }

                    dp[i][j][k] = MIN_VAL; // 최대값 비교를 위해 나머지 작은 값으로 설정.
                }
            }
        }
    }

    static void findMaxSum() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < 2; k++) {
                    if (k == 0) { // i번째 원소를 선택하지 않는 상태
                        // i-1번째 원소까지 고려했을 때, 그 원소를 포함하거나 포함하지 않는 상태 중 최대 값.
                        dp[i][j][k] = Math.max(dp[i-1][j][0], dp[i-1][j][1]);
                    } else { // i번째 원소를 선택하는 상태
                        // i번째 원소가 새로운 구간이 되는 경우, i번째 원소가 같은 구간이 되는 경우
                        if (dp[i-1][j-1][0] == MIN_VAL && dp[i-1][j][1] == MIN_VAL) continue;
                        dp[i][j][k] = Math.max(dp[i-1][j-1][0], dp[i-1][j][1]) + grid[i];
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            grid[i+1] = Integer.parseInt(str[i]);
        }

        initialize();
        findMaxSum();

        System.out.println(Math.max(dp[n][m][0], dp[n][m][1]));
    }
}