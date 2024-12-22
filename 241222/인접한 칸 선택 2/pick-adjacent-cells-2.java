import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] origin = new int[M][N];
		int[][] grid = new int[N + 1][M];
		int[][] dp = new int[N + 2][1 << M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				grid[i][j] = origin[j][i - 1];
			}
		}

		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 0;

		for (int i = 0; i <= N; i++) {
			for (int mask = 0; mask < (1 << M); mask++) {
				if (dp[i][mask] == -1)
					continue;

				nextMaskLoop: for (int nextMask = 0; nextMask < (1 << M); nextMask++) {
					if ((mask & nextMask) > 0)
						continue;

					for (int j = 0; j < M - 1; j++) {
						if (((nextMask >> j) & 1) == 1 && ((nextMask >> (j + 1)) & 1) == 1) {
							continue nextMaskLoop;
						}
					}

					int sum = 0;
					for (int j = 0; j < M; j++) {
						if (((nextMask >> j) & 1) == 1) {
							sum += grid[i][j];
						}
					}

					dp[i + 1][nextMask] = Math.max(dp[i + 1][nextMask], dp[i][mask] + sum);
				}
			}
		}

		int answer = 0;
		for (int mask = 0; mask < (1 << M); mask++) {
			answer = Math.max(answer, dp[N + 1][mask]);
		}
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_M = 100;
    public static final int MAX_N = 10;
    
    // 변수 선언
    public static int n, m;
    public static int[][] board = new int[MAX_N + 1][MAX_M + 1];
    
    // dp[i][j] : 
    // 1번 행을 시작으로 i번 열까지 칸을 채웠을 때
    // 각 i번 열에서 각 행마다 칸을 선택했는지 여부를
    // x1, x2, ..., xk라 헀을 때 
    // 2^x1 + 2^x2 + ... + 2^xk 값이 j라 하면 (bitmask된 정수값이 j)
    // 해당 상태에서 조건에 맞게 선택하면서 얻을 수 있는 수들의 최대 합
    public static int[][] dp = new int[MAX_M + 1][1 << MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++)
                board[i][j] = sc.nextInt();

        // 초기값으로 -1을 넣어 해당 상태가 불가능함을 표기해줍니다.
        for(int i = 0; i < m; i++)
            for(int j = 0; j < (1 << n); j++)
                dp[i][j] = -1;

        // 초기조건은
        // 0번째 행에서 아무것도 안 고른 상태입니다.
        // 이 때에는 0을 넣어줍니다.
        dp[0][0] = 0;

        // 뿌려주는 방식의 dp를 진행합니다.
        // dp[i][j]가 계산이 되어있다는 가정하에서
        // 그 다음 상태값을 갱신합니다.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < (1 << n); j++) {
                // dp값이 -1이라면
                // 해당 값은 불가능한 상황이므로 패스합니다.
                if(dp[i][j] == -1)
                    continue;
                
                for(int k = 0; k < (1 << n); k++) {
                    // 그다음 줄의 state에 대해 해당 값을 만들 수 있는지 판단하고 답을 갱신해줍니다.
                    
                    // 두 값의 비트가 겹친다면
                    // 상하로 인접한 칸이 있음을 의미하므로 패스합니다.
                    if((k & j) > 0) continue;

                    // k에서 연속된 두 비트의 값이 1이라면
                    // 좌우로 인접한 칸이 있음을 의미하므로 패스합니다.
                    boolean isOverlap = false;
                    for(int x = 0; x < n - 1; x++) {
                        if((((k >> x) & 1) == 1) && (((k >> (x + 1)) & 1) == 1))
                            isOverlap = true;
                    }
                    if(isOverlap) continue;

                    // i + 1번째 줄에 추가로 선택되는 숫자의 총합 num을 계산해 준 뒤,
                    // dp값을 갱신해줍니다.
                    int num = 0;
                    for(int x = 0; x < n; x++) {
                        if(((k >> x) & 1) == 1) num += board[x + 1][i + 1];
                    }

                    dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][j] + num);
                }
            }
        }

        // 선택할 수 있는 칸의 개수의 최댓값을 출력합니다.
        int ans = 0;
        for(int i = 0; i < (1 << n); i++) {
            ans = Math.max(ans, dp[m][i]);
        }
        System.out.print(ans);
    }
}
*/
