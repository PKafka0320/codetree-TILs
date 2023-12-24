import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_R = 100;
    public static final int MAX_N = 100;
    
    // 변수 선언
    public static int n;
    public static int[][] num = new int[MAX_N][MAX_N];
    public static int[][][] dp = new int[MAX_N][MAX_N][MAX_R + 1];
    
    public static int ans = INT_MAX;
    
    public static void initialize() {
        // 전부 INT_MAX로 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                for(int k = 1; k <= MAX_R; k++)
                    dp[i][j][k] = INT_MAX;
    
        // 시작점의 경우 dp[0][0][num[0][0]] = num[0][0]으로 초기값을 설정해줍니다
        dp[0][0][num[0][0]] = num[0][0];
    
        // 최좌측 열의 초기값을 설정해줍니다.
        for(int i = 1; i < n; i++)
            for(int k = 1; k <= MAX_R; k++)
                dp[i][0][Math.min(k, num[i][0])] = Math.min(
                    dp[i][0][Math.min(k, num[i][0])],
                    Math.max(dp[i - 1][0][k], num[i][0])
                );
    
        // 최상단 행의 초기값을 설정해줍니다.
        for(int j = 1; j < n; j++)
            for(int k = 1; k <= MAX_R; k++)
                dp[0][j][Math.min(k, num[0][j])] = Math.min(
                    dp[0][j][Math.min(k, num[0][j])],
                    Math.max(dp[0][j - 1][k], num[0][j])
                );
    }
    
    public static void solve() {
        // DP 초기값 설정
        initialize();
    
        // 탐색하는 위치의 위에 값과 좌측 값 중에 작은 값과
        // 해당 위치의 숫자 중에 최댓값을 구해줍니다.
        for(int i = 1; i < n; i++)
            for(int j = 1; j < n; j++)
                for(int k = 1; k <= MAX_R; k++) {
                    dp[i][j][Math.min(k, num[i][j])] = Math.min(
                        dp[i][j][Math.min(k, num[i][j])],
                        Math.max(Math.min(dp[i - 1][j][k], dp[i][j - 1][k]), num[i][j])
                    );
                }
    
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                num[i][j] = sc.nextInt();


        // DP로 문제를 해결합니다.
        solve();

        // 가능한 답 중 최적의 답을 계산합니다.
        int ans = INT_MAX;
        for(int k = 1; k <= MAX_R; k++)
            if(dp[n - 1][n - 1][k] != INT_MAX)
                ans = Math.min(ans, dp[n - 1][n - 1][k] - k);

        System.out.print(ans);
    }
}