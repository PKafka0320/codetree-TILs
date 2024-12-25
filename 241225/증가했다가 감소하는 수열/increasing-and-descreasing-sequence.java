import java.util.Scanner;

public class Main {
    public static final int MAX_N = 200;
    
    public static final int MOD = 10007;
    
    // 변수 선언
    public static int n;
    public static int[] points = new int[MAX_N];
    
    // dp[i][j] : 둘 다 시작점에서 출발하여 서로 겹치지 않게 점을 순서대로 선택하면서
    //            하나는 i번 점에 있고, 나머지 하나는 j번 점에 있는 상황이 되도록
    //            만들 수 있는 경우의 수의 총합
    public static int[][] dp = new int[MAX_N][MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            points[i] = sc.nextInt();

        // 초기조건을 설정합니다.
        // Bitonic Cycle 유형에서
        // 둘 다 시작점인 0번 점에 서있는 순간입니다.
        dp[0][0] = 1;

        // 뿌려주는 dp를 진행합니다.
        // 이는 이미 값이 구해져 있다는 가정 하에서
        // 그 다음 값을 갱신하는 형태입니다.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // 하나는 i번 점에 있고, 나머지 하나는 j번 점에 있는 상황에서
                // 그 다음 점으로의 이동을 고민해야 합니다.

                // dp[i][j] 값이 구해져있다는 가정 하에서
                // 그 다음 상황에 해당하는 값을 갱신해야합니다.

                // Bitonic Cycle 유형의 특성상
                // Math.max(i, j)까지는 이미 전부 해결했기에
                // Math.max(i, j) + 1번 점을 고려해야 하는 순간입니다.
                int next = Math.max(i, j) + 1;

                // 이미 next가 n이면 더 이상 진행하지 않습니다.
                if(next == n)
                    continue;

                // next 이상의 모든 값 k에 대해,
                // k번으로 한번에 이동할 수 있는 경우의 수를 전부 갱신해줍니다.
                for(int k = next; k < n; k++) {
                    // k가 마지막일 경우에만 예외적으로 처리합니다.
                    // 마지막 값으로 도달하기 위해서는
                    // 반드시 points[i], points[j]가 다 points[k]보다 작아야 합니다.
                    if(k == n - 1) {
                        if(points[i] < points[k] && points[j] < points[k]) {
                            dp[k][j] += dp[i][j];
                            dp[k][j] %= MOD;

                            dp[i][k] += dp[i][j];
                            dp[i][k] %= MOD;
                        }
                        break;
                    }

                    // 첫번째 경로에서 k로 이동하는 경우입니다.
                    // 이 경우에는 k번 숫자가 i번 숫자보다 커야 합니다.
                    if(points[i] < points[k]) {
                        dp[k][j] += dp[i][j];
                        dp[k][j] %= MOD;
                    }

                    // 두번째 경로에서 k로 이동하는 경우입니다.
                    // 이 경우에는 k번 숫자가 j번 숫자보다 커야 합니다.
                    if(points[j] < points[k]) {
                        dp[i][k] += dp[i][j];
                        dp[i][k] %= MOD;
                    }
                }
            }
        }

        // 가능한 모든 경로의 수를 구해줍니다.
        // 한쪽이 n - 1인 모든 dp값의 합을 구해줍니다.

        // 문제 정의상 한쪽 경로의 마지막이 i, 두번째 경로의 마지막이 j
        // 인 상황에서 n - 1로 이동한 경우 dp[n - 1][j], dp[i][n - 1]
        // 양쪽으로 중복해서 기록됩니다.
        // 따라서 dp[n - 1][x] 꼴,
        // 혹은 dp[x][n - 1]꼴의 총합만을 구하면 중복 없이
        // 모든 경우의 수를 구해줄 수 있습니다.
        int ans = 0;
        for(int i = 0; i < n; i++) {
            ans += dp[i][n - 1];
            ans %= MOD;
        }

        System.out.print(ans);
    }
}