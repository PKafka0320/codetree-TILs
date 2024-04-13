import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int m;
    static int[] num;
    static int[][] dp;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        
        // num[i] : i 번째 숫자
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }

        // dp[i][j] : i 번째 숫자까지 고려하고, 숫자가 변경된 횟수가 j 번 일때의 최대 유사도
        dp = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 1;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i][j] == -1) continue;

                // 숫자 변경이 없는 경우
                if (num[i] == num[i + 1]) {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + 1);
                }
                else {
                    // 다음 숫자와 동일한 숫자가 가지는 최대 유사도 + 1
                    int tmp = 0;
                    for (int k = i; k >= 0; k--) {
                        if (num[k] != num[i + 1] || dp[k][j] == -1) continue;
                        tmp = dp[k][j];
                        break;
                    }
                    dp[i + 1][j] = Math.max(dp[i + 1][j], tmp + 1);

                    // 숫자를 변경할 수 있는 횟수가 남아있는 경우
                    if (j == m) continue;
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + 1);
                }
            }
        }

        // 결과 계산
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // System.out.print(num[i] + " : ");
            for (int j = 0; j <= m; j++) {
                // System.out.print(dp[i][j] + " ");
                ans = Math.max(ans, dp[i][j]);
            }
            // System.out.println();
        }

        System.out.println(ans);
    }
}