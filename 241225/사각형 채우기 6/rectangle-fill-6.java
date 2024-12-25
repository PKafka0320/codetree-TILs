import java.util.Scanner;

public class Main {
    public static final int MAX_M = 121;
    public static final int MAX_N = 11;
    
    public static final int MOD = (int)1e4 + 7;
    
    
    // 변수 선언
    public static int n, m;
    public static int[][] board = new int[MAX_N + 1][MAX_N + 1];
    
    // dp[i][j] : 
    // 1번 행 1번 열부터 m번 열까지를 각각 1번부터 m번 칸이라 하고,
    // 2번 행은 m + 1번 ~ 2m번, ... 이렇게 칸 번호를 이름지었을 때
    // 현재 i - 1번 칸까지 채웠을 때 앞 m개의 칸에 각 칸마다
    // 값이 채워졌는지 여부를
    // x1, x2, ..., xk라 헀을 때 
    // 2^x1 + 2^x2 + ... + 2^xk 값이 j라 하면 (bitmask된 정수값이 j)
    // 해당 상태에서 조건에 맞게 선택할 수 있는 경우의 수
    public static int[][] dp = new int[MAX_M + 1][1 << MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        // 초기조건은
        // 1번 칸에서 아무것도 안 고른 상태입니다.
        // 이 때에는 1을 넣어줍니다.
        dp[1][0] = 1;

        // 뿌려주는 방식의 dp를 진행합니다.
        // dp[i][j]가 계산이 되어있다는 가정하에서
        // 그 다음 상태값을 갱신합니다.
        for(int i = 1; i <= n * m; i++) {
            for(int j = 0; j < (1 << m); j++) {
                // dp값이 0이라면
                // 해당 값으로 갱신해줄 것이 없으므로 패스합니다.
                if(dp[i][j] == 0)
                    continue;

                // i번 칸에 사각형을 채워 줄 것입니다.
                // 만약 i번 칸이 이미 채워져있으면
                // 바로 다음 dp로 갱신해주고 넘어갑니다. 
                if((j & 1) == 1) {
                    dp[i + 1][j >> 1] += dp[i][j];
                    dp[i + 1][j >> 1] %= MOD;

                    continue;
                }

                int x = 1 + (i - 1) / m;
                int y = 1 + (i - 1) % m;
                
                // i + 1번 칸에서 1 * 2를 오른쪽으로 두는 방법,
                // 1 * 2를 아랫쪽으로 내리는 두 가지 방법이 있습니다.
                // 각각을 시도해 보고 다음 dp로 갱신해줍니다.

                // 오른쪽으로 두는 방법
                if(y != m) {
                    int nextJ = j >> 1;
                    // i + 1번 칸의 바로 다음 칸도 비어 있어야 합니다.
                    if((nextJ & 1) == 0) {
                        nextJ += 1;
                        dp[i + 1][nextJ] += dp[i][j];
                        dp[i + 1][nextJ] %= MOD;
                    }
                }

                // 아랫쪽으로 두는 방법
                if(x != n) {
                    int nextJ = j >> 1;
                    // i + 1번 칸의 아래 칸(m - 1번 칸)도 비어 있어야 합니다.
                    if(((nextJ >> (m - 1)) & 1) == 0) {
                        nextJ += (1 << (m - 1));
                        dp[i + 1][nextJ] += dp[i][j];
                        dp[i + 1][nextJ] %= MOD;
                    }
                }
            }
        }

        // n * m 크기의 사각형을 채우는 방법의 수를 출력합니다.
        System.out.print(dp[n * m + 1][0]);
    }
}