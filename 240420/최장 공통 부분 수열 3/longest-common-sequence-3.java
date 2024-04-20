import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m;
    static int[] a, b;
    static int[][] dp;
    static int[][][] path;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n + 1];
        b = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextInt();
        }
        
        // dp[i][j] : a 수열의 i 번째, b 수열의 j 번째까지 확인했을때 최장 공통 부분 수열의 길이
        // path[i][j] : 최장 공통 부분 수열에서 이전 수의 위치
        // path[i][j][0] : 이전 i 정보
        // path[i][j][1] : 이전 j 정보
        dp = new int[n + 1][m + 1];
        path = new int[n + 1][m + 1][2];
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
                else if (dp[i][j] == dp[i][j - 1]) {
                    int[] tmpa = new int[dp[i][j] + 1];
                    int cur = dp[i][j];
                    for (int tmpi = i, tmpj = j; tmpi > 0 && tmpj > 0;) {
                        if (a[tmpi] == b[tmpj]) {
                            tmpa[cur--] = a[tmpi];
                        }
                        int ntmpi = path[tmpi][tmpj][0];
                        int ntmpj = path[tmpi][tmpj][1];
                        tmpi = ntmpi;
                        tmpj = ntmpj;
                    }
                    int[] tmpb = new int[dp[i][j] + 1];
                    cur = dp[i][j];
                    for (int tmpi = i, tmpj = j - 1; tmpi > 0 && tmpj > 0;) {
                        if (a[tmpi] == b[tmpj]) {
                            tmpa[cur--] = a[tmpi];
                        }
                        int ntmpi = path[tmpi][tmpj][0];
                        int ntmpj = path[tmpi][tmpj][1];
                        tmpi = ntmpi;
                        tmpj = ntmpj;
                    }

                    boolean isA = false;
                    for (int tmpi = 1; tmpi <= dp[i][j]; tmpi++) {
                        if (tmpa[tmpi] == tmpb[tmpi]) continue;
                        if (tmpa[tmpi] > tmpb[tmpi]) break;
                        isA = true;
                        break;
                    }

                    if (!isA) {
                        path[i][j][0] = i;
                        path[i][j][1] = j - 1;
                    }
                }

                if (a[i] == b[j] && dp[i][j] < dp[i - 1][j - 1] + 1) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    path[i][j][0] = i - 1;
                    path[i][j][1] = j - 1;
                }
            }
        }

        // for (int i = 0; i <= n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // for (int i = 0; i <= n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         System.out.printf("(%d,%d) ", path[i][j][0], path[i][j][1]);
        //     }
        //     System.out.println();
        // }

        int cnt = dp[n][m];
        int[] ans = new int[cnt + 1];
        for (int i = n, j = m; i > 0 && j > 0;) {
            if (a[i] == b[j]) {
                ans[cnt--] = a[i];
            }
            int ni = path[i][j][0];
            int nj = path[i][j][1];
            i = ni;
            j = nj;
        }

        for (int i = 1; i <= dp[n][m]; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}