import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[][][] dp = new int[N][N][10001];
		boolean[][][] canMake = new boolean[N][N][10001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			canMake[i][i][numbers[i]] = true;

			if (i + 1 != N) {
				dp[i][i + 1][numbers[i] + numbers[i + 1]] = Math.abs(numbers[i] - numbers[i + 1]);
				canMake[i][i + 1][numbers[i] + numbers[i + 1]] = true;
			}
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int i = 0; i <= N - gap; i++) {
				int j = i + gap - 1;

				for (int k = i; k < j; k++) {
					for (int x = 0; x <= 10000; x++) {
						if (!canMake[i][k][x])
							continue;

						for (int y = 0; y <= 10000; y++) {
							if (!canMake[k + 1][j][y])
								continue;

							int score = dp[i][k][x] + dp[k + 1][j][y] + Math.abs(x - y);
							canMake[i][j][x + y] = true;
							dp[i][j][x + y] = Math.max(dp[i][j][x + y], score);
						}
					}
				}
			}
		}

		int ans = 0;
		for (int x = 0; x <= 10000; x++)
			ans = Math.max(ans, dp[0][N - 1][x]);
		System.out.print(ans);
	}
}
