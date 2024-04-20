import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        int n = a.length(), m = b.length();
        int[][] dp = new int[n + 1][m + 1];
        int[][][] path = new int[n + 1][m + 1][2]; // 경로 추적을 위한 3차원 배열

        // dp 배열을 계산하고 각 단계에서의 경로를 추적합니다.
        // dp[i][j] : 문자열 a를 i번째까지 확인하고, 문자열 b를 j번째까지 확인했을 때
        // 최장 공통 부분 수열의 길이
        // path[i][j] : 그러한 최장 공통 부분 수열이 어느 이전 정보에서 왔는지의 정보
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] < dp[i - 1][j]) {
                    dp[i][j] = dp[i - 1][j];
                    path[i][j][0] = i - 1;
                    path[i][j][1] = j;
                }

                if (dp[i][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    path[i][j][0] = i;
                    path[i][j][1] = j - 1;
                }

                if (a.charAt(i - 1) == b.charAt(j - 1) && dp[i][j] < dp[i - 1][j - 1] + 1) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    path[i][j][0] = i - 1;
                    path[i][j][1] = j - 1;
                }
            }
        }

        // 최장 공통 부분 수열을 추적하여 결과를 구성합니다.
        List<Character> lcs = new ArrayList<>();
        for (int i = n, j = m; i > 0 && j > 0;) {
            if (path[i][j][0] == i - 1 && path[i][j][1] == j - 1 && a.charAt(i - 1) == b.charAt(j - 1)) {
                lcs.add(a.charAt(i - 1));
                i--; j--;

            } else {
                int next_i = path[i][j][0];
                int next_j = path[i][j][1];

                i = next_i;
                j = next_j;
            }
        }

        // 최장 공통 부분 수열을 역순으로 출력합니다.
        for (int i = lcs.size() - 1; i >= 0; i--) {
            System.out.print(lcs.get(i));
        }
    }
}