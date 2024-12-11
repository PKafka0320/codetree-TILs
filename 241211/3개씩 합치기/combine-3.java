import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[][] dp = new int[N][N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = (int) 1e9;
			}
		}

		for (int i = 0; i < N; i++) {
			dp[i][i] = 0;

			if (i == N - 1)
				continue;
			dp[i][i + 1] = 0;
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int i = 0; i < N - gap + 1; i++) {
				int j = i + gap - 1;

				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + numbers[i] * numbers[j] * numbers[j - 1]);
				dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + numbers[i] * numbers[j] * numbers[i + 1]);
			}
		}

		System.out.println(dp[0][N - 1]);
	}
}
