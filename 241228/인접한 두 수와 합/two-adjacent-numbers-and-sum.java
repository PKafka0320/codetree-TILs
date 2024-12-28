import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[][][] dp = new int[N][N][2]; // 점수, 숫자

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j][0] = dp[i][j][1] = -1;
			}
		}

		for (int i = 0; i < N; i++) {
			dp[i][i][0] = 0;
			dp[i][i][1] = numbers[i];

			if (i + 1 != N) {
				dp[i][i + 1][0] = Math.abs(numbers[i] - numbers[i + 1]);
				dp[i][i + 1][1] = numbers[i] + numbers[i + 1];
			}
		}

		for (int gap = 3; gap <= N; gap++) {
			for (int from = 0; from <= N - gap; from++) {
				int to = from + gap - 1;

//				System.out.printf("--------------dp[%d][%d]%n", from, to);

				for (int fromEnd = from; fromEnd < to; fromEnd++) {
//					System.out.printf("dp[%d][%d] + dp[%d][%d] + abs(%d - %d) = %d + %d + %d = %d%n", from, fromEnd,
//							fromEnd + 1, to, dp[from][fromEnd][1], dp[fromEnd + 1][to][1], dp[from][fromEnd][0],
//							dp[fromEnd + 1][to][0], Math.abs(dp[from][fromEnd][1] - dp[fromEnd + 1][to][1]),
//							dp[from][fromEnd][0] + dp[fromEnd + 1][to][0]
//									+ Math.abs(dp[from][fromEnd][1] - dp[fromEnd + 1][to][1]));

					if (dp[from][to][0] <= dp[from][fromEnd][0] + dp[fromEnd + 1][to][0]
							+ Math.abs(dp[from][fromEnd][1] - dp[fromEnd + 1][to][1])) {
						dp[from][to][0] = dp[from][fromEnd][0] + dp[fromEnd + 1][to][0]
								+ Math.abs(dp[from][fromEnd][1] - dp[fromEnd + 1][to][1]);
						dp[from][to][1] = Math.max(dp[from][to][1], dp[from][fromEnd][1] + dp[fromEnd + 1][to][1]);
					}
				}

			}
		}

		System.out.println(dp[0][N - 1][0]);
	}
}
