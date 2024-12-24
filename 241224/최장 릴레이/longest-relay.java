import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N + 1][N + 1];
		int[][][][] dp = new int[N + 2][N + 1][N + 1][1 << N];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int first = 1; first <= N; first++) {
			for (int second = 1; second <= N; second++) {
				if (first == second)
					continue;

				if (grid[first][second] > 0) {
					dp[2][first][second][(1 << (first - 1)) | (1 << (second - 1))] = 2;
				}
			}
		}

		for (int count = 2; count <= N; count++) {
			for (int current = 1; current <= N; current++) {
				for (int mask = 0; mask < (1 << N); mask++) {
					for (int before = 1; before <= N; before++) {
						for (int next = 1; next <= N; next++) {
							if (current == next || before == next || current == before)
								continue;

							if ((mask | (1 << (current - 1))) != mask || (mask | (1 << (before - 1))) != mask)
								continue;

							if ((mask | (1 << (next - 1))) == mask)
								continue;

							if (grid[before][current] < grid[current][next]) {
								dp[count + 1][current][next][mask | (1 << (next - 1))] = Math.max(
										dp[count + 1][current][next][mask | (1 << (next - 1))],
										dp[current][before][current][mask] + 1);
							}
						}
					}
				}
			}
		}

		int answer = 0;
		for (int before = 1; before <= N; before++) {
			for (int current = 1; current <= N; current++) {
				if (before == current) continue;
				
				for (int mask = 0; mask < (1 << N); mask++) {
					answer = Math.max(answer, dp[N][before][current][mask]);
				}
			}
		}
		System.out.println(answer);
	}
}
