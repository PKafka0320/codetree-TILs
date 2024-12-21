import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][][] dp = new int[N][10][1 << 10];
		int MOD = 10_007;

		for (int i = 1; i <= 9; i++) {
			dp[0][i][1 << i] = 1;
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int mask = 0; mask < (1 << 10); mask++) {
					if (dp[i][j][mask] == 0)
						continue;

					if (j < 9) {
						dp[i + 1][j + 1][mask
								| (1 << (j + 1))] = (dp[i + 1][j + 1][mask | (1 << (j + 1))] + dp[i][j][mask]) % MOD;
					}

					if (j > 0) {
						dp[i + 1][j - 1][mask
								| (1 << (j - 1))] = (dp[i + 1][j - 1][mask | (1 << (j - 1))] + dp[i][j][mask]) % MOD;
					}
				}
			}
		}

		int answer = 0;
		for (int i = 0; i <= 9; i++) {
			answer += dp[N - 1][i][(1 << 10) - 1];
		}
		answer %= MOD;
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_K = 10;
    public static final int MAX_N = 100;
    
    public static final int MOD = (int)1e4 + 7;
    
    // 변수 선언
    public static int n;
    
    // dp[i][j][k] : 
    // 1번을 시작으로 i번 자릿수까지 세었을 때
    // 각 자릿수별로 나온 숫자들을 중복 없이
    // x1, x2, ..., xk라 헀을 때 
    // 2^x1 + 2^x2 + ... + 2^xk 값이 j고 (bitmask된 정수값이 j)
    // 마지막 숫자값이 k인 가짓수
    public static int[][][] dp = new int[MAX_N + 1][1 << MAX_K][MAX_K];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        // 초기조건은
        // 1개를 1 ~ 9 중 하나로 고른 상태입니다.
        // 이 때에는 1을 넣어줍니다.
        for(int i = 1; i <= 9; i++) {
            dp[1][(1 << i)][i] = 1;
        }

        // 뿌려주는 방식의 dp를 진행합니다.
        // dp[i][j]가 계산이 되어있다는 가정하에서
        // 그 다음 상태값을 갱신합니다.
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < (1 << MAX_K); j++) {
                for(int k = 0; k < MAX_K; k++) {
                    // dp값이 0이라면
                    // 해당 값은 갱신할 필요가 없으니 패스합니다.
                    if(dp[i][j][k] == 0)
                        continue;

                    // 그 다음 숫자를 각각 (k - 1, k + 1)번 숫자를
                    // 쓰게 되는 경우를 비교하여 갱신해줍니다.
                    if(0 <= k - 1) {
                        dp[i + 1][j | (1 << (k - 1))][k - 1] += dp[i][j][k];
                        dp[i + 1][j | (1 << (k - 1))][k - 1] %= MOD;
                    }

                    if(k + 1 < MAX_K) {
                        dp[i + 1][j | (1 << (k + 1))][k + 1] += dp[i][j][k];
                        dp[i + 1][j | (1 << (k + 1))][k + 1] %= MOD;
                    }
                }
            }
        }

        // 가능한 모든 경우의 수를 출력합니다.
        int ans = 0;
        for(int i = 0; i < MAX_K; i++) {
            ans += dp[n][(1 << MAX_K) - 1][i];
            ans %= MOD;
        }
        System.out.print(ans);
    }
}
*/
