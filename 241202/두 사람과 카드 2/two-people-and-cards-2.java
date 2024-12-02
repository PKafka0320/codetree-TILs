import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N + 1];
		int M = Integer.parseInt(st.nextToken());
		Set<Integer> excepts = new HashSet<>(M);

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				excepts.add(Integer.parseInt(st.nextToken()));
			}			
		}

		int[][] diff = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				diff[i][j] = Math.abs(numbers[i] - numbers[j]);
			}
		}

		long[][] dp = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				dp[i][j] = (long) 1e12;
			}
		}
		dp[0][0] = 0;

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				int next = Math.max(i, j) + 1;

				if (next == N + 1)
					continue;

				dp[next][j] = Math.min(dp[next][j], dp[i][j] + diff[i][next]);
				if (excepts.contains(next)) {
					continue;
				}
				dp[i][next] = Math.min(dp[i][next], dp[i][j] + diff[j][next]);
			}
		}

		long answer = (long) 1e12;
		for (int i = 1; i <= N; i++) {
			answer = Math.min(answer, dp[i][N]);
			answer = Math.min(answer, dp[N][i]);
		}
		System.out.println(answer);
	}
}
