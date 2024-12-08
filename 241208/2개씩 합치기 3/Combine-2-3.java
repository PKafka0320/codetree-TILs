import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] numbers;
	static int[][] merged, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		numbers = new int[N + 1];
		merged = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			merged[i][i] = numbers[i];
			for (int j = i + 1; j <= N; j++) {
				merged[i][j] = merged[i][j - 1] + numbers[j];
			}
		}

//		tabulation();
		memoization();
	}

	private static void memoization() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = -1;
			}
		}

		System.out.println(findMin(1, N));
	}

	private static int findMin(int i, int j) {
		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (i == j) {
			return dp[i][j] = 0;
		}

		int best = (int) 1e9;
		for (int k = i; k < j; k++) {
			int cost = findMin(i, k) + findMin(k + 1, j) + merged[i][k] + merged[k + 1][j];
			best = Math.min(best, cost);
		}
		
		return dp[i][j] = best;
	}

	private static void tabulation() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = (int) 1e9;
			}
		}

		for (int i = 1; i <= N; i++) {
			dp[i][i] = 0;
		}

		for (int gap = 2; gap <= N; gap++) {
			for (int i = 1; i <= N - gap + 1; i++) {
				int j = i + gap - 1;

				for (int k = i; k < j; k++) {
					int cost = dp[i][k] + dp[k + 1][j] + merged[i][k] + merged[k + 1][j];
					dp[i][j] = Math.min(dp[i][j], cost);
				}
			}
		}

		System.out.println(dp[1][N]);
	}
}
