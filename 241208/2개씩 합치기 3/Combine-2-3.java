import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N + 1];
		int[][] merged = new int[N + 1][N + 1];
		int[][] dp = new int[N + 1][N + 1];

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
