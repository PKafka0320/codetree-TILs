import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int k;
    static int m;
    static int[] num;
    static int[][] max;
    static int ans;

    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();
        m = sc.nextInt();

        // num = new int[n + 1];
        // for (int i = 1; i <= n; i++) {
        //     num[i] = sc.nextInt();
        // }

        // max = new int[n + 1][2];
        // max[0][0] = k;
        // max[0][1] = k;
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 0; j < i; j++) {
        //         if (max[j] + num[i] >= m) {
        //             max[i] = Math.max(max[i], max[j]);
        //         }
        //         else {
        //             max[i] = Math.max(max[i], max[j] + num[i]);
        //         }
        //     }
        // }

        // int ans = 0;
        // for (int i = 0; i <= n; i++) {
        //     ans = Math.max(ans, max[i]);
        // }

        // if (ans >= m) {
        //     System.out.println(-1);
        // }
        // else {
        //     System.out.println(ans);
        // }
        int[] nums = new int[n]; // 연산을 할 정수 값들
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 동적 프로그래밍을 위한 배열 초기화
        int[][] dp = new int[n + 1][m + 1];
        dp[0][k] = k; // 초기값 설정

        // 동적 프로그래밍을 사용하여 최대값 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i - 1][j] != 0) {
                    int added = j + nums[i - 1];
                    int subtracted = j - nums[i - 1];
                    if (added <= m) {
                        dp[i][added] = Math.max(dp[i][added], added);
                    }
                    if (subtracted >= 0) {
                        dp[i][subtracted] = Math.max(dp[i][subtracted], subtracted);
                    }
                }
            }
        }

        // 결과 출력
        int maxResult = -1;
        for (int j = 0; j <= m; j++) {
            maxResult = Math.max(maxResult, dp[n][j]);
        }
        System.out.println(maxResult);
    }
}