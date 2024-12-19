import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] numbers = new int[N + 1][N + 1];
		int[][][] dp = new int[N + 1][N + 1][1 << N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				numbers[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		for (int j = 1; j <= N; j++) {
//			System.out.printf("set %d %d %d%n", 1, j, 1 << j);
			dp[1][j][1 << j] = numbers[1][j];
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					for (int flag = 0; flag < (1 << N + 1); flag++) {

						if (dp[i][j][flag] == -1) {
							continue;
						}
//						System.out.printf("dp[%d][%d][%s] -> dp[%d][%d][%s]: ", i, j, Integer.toBinaryString(flag),
//								i + 1, k, Integer.toBinaryString(flag | (1 << k)));

						if ((flag | (1 << k)) == flag) {
//							System.out.printf("flagged%n");
							continue;
						}

						dp[i + 1][k][flag | (1 << k)] = Math.max(dp[i + 1][k][flag | (1 << k)], dp[i][j][flag] + numbers[i + 1][k]);
//						System.out.printf("%d%n", dp[i + 1][k][flag | (1 << k)]);
					}
				}
			}
		}

		int answer = 0;
//		System.out.println(Integer.toBinaryString((1 << N + 1) - 2));
		for (int i = 0; i <= N; i++) {
			answer = Math.max(answer, dp[N][i][(1 << N + 1) - 2]);
		}
		System.out.println(answer);
	}
}
