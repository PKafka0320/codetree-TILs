import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N + 1];
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int[][] diff = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				diff[i][j] = Math.abs(numbers[i] - numbers[j]);
			}
		}

		long[][][] dp = new long[N + 1][N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				for (int m = 0; m <= M; m++) {
					dp[i][j][m] = (long) 1e12;
				}
			}
		}
		dp[0][0][0] = 0;

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				int next = Math.max(i, j) + 1;

				if (next == N + 1)
					continue;

				for (int m = 0; m <= M; m++) {
					dp[next][j][m] = Math.min(dp[next][j][m], dp[i][j][m] + diff[i][next]);
					dp[i][next][m] = Math.min(dp[i][next][m], dp[i][j][m] + diff[j][next]);

					if (m == M)
						continue;
					dp[next][next][m + 1] = Math.min(dp[next][next][m + 1], dp[j][i][m]);
				}
			}
		}

		long answer = (long) 1e12;
		for (int i = 1; i <= N; i++) {
			answer = Math.min(answer, dp[i][N][M]);
			answer = Math.min(answer, dp[N][i][M]);
		}
		System.out.println(answer);
	}
}
