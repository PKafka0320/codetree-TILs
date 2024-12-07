import java.io.*;
import java.util.*;

public class Main {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Position[] positions = new Position[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			positions[i] = new Position(x, y);
		}
		Arrays.sort(positions, Comparator.comparingInt(p -> p.x));

		long[][] distance = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distance[i][j] = getDistance(positions[i], positions[j]);
			}
		}

		long[][] dp = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = (long) 1e14;
			}
		}
		dp[0][0] = 0;

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				dp[j][i] = dp[j][i - 1] + distance[i - 1][i];

				for (int k = 0; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[k][j] + distance[k][i]);
				}
			}
		}

		long answer = (long) 1e14;
		for (int i = 0; i < N - 1; i++) {
			answer = Math.min(answer, dp[i][N - 1] + distance[i][0]);
		}
		System.out.println(answer);
	}

	public static long getDistance(Position p1, Position p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
}
