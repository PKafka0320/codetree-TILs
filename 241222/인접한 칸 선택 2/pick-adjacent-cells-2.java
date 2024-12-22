import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] grid = new int[N + 1][M];
		int[][] dp = new int[N + 2][1 << M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
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
			answer = Math.max(answer, dp[N+1][mask]);
		}
		System.out.println(answer);
	}
}
