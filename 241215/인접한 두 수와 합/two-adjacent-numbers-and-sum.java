import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		Map<Integer, Integer>[][] dp = new HashMap[N][N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = new HashMap<>();
			}
		}

		for (int i = 0; i < N; i++) {
			dp[i][i].put(numbers[i], 0);
			if (i + 1 != N) {
				dp[i][i + 1].put(numbers[i] + numbers[i + 1], Math.abs(numbers[i] - numbers[i + 1]));
			}
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int i = 0; i <= N - gap; i++) {
				int j = i + gap - 1;

				for (int k = i; k < j; k++) {
					if (dp[i][k].isEmpty() || dp[k + 1][j].isEmpty()) {
						continue;
					}

					for (int left : dp[i][k].keySet()) {
						for (int right : dp[k + 1][j].keySet()) {
							int score = dp[i][k].get(left) + dp[k + 1][j].get(right) + Math.abs(left - right);

							if (!dp[i][j].containsKey(left + right) || dp[i][j].get(left + right) < score) {
								dp[i][j].put(left + right, score);
							}
						}
					}
				}
			}
		}

		int ans = 0;
		for (int key : dp[0][N - 1].keySet()) {
			ans = Math.max(ans, dp[0][N - 1].get(key));
		}
		System.out.print(ans);
	}
}
