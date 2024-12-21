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
