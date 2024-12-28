import java.io.*;
import java.util.*;

public class Main {
	static class Position implements Comparable<Position> {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Position o) {
			return this.x - o.x;
		}
	}
	static long MAX_VALUE = (long) 1e15;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Position[] positions = new Position[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			positions[i] = new Position(x, y);
		}
		Arrays.sort(positions, 1, N + 1);

		long[][] distance = new long[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				distance[i][j] = getDistance(positions[i], positions[j]);
			}
		}

		long[][][] dp = new long[N + 1][N + 1][2];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				dp[i][j][0] = MAX_VALUE;
				dp[i][j][1] = MAX_VALUE;
			}
		}
		dp[1][1][0] = 0; // 전체 거리

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int next = Math.max(i, j) + 1;

				if (next == N + 1) {
					continue;
				}

				dp[next][j][0] = Math.min(dp[next][j][0], dp[i][j][0] + distance[i][next]);
				dp[next][j][1] = Math.min(dp[next][j][1], dp[i][j][0]);
				dp[next][j][1] = Math.min(dp[next][j][1], dp[i][j][1] + distance[i][next]);

				dp[i][next][0] = Math.min(dp[i][next][0], dp[i][j][0] + distance[j][next]);
				dp[i][next][1] = Math.min(dp[i][next][1], dp[i][j][0]);
				dp[i][next][1] = Math.min(dp[i][next][1], dp[i][j][1] + distance[j][next]);
			}
		}

		long answer = MAX_VALUE;
		for (int i = 1; i < N; i++) {
			answer = Math.min(answer, dp[i][N][0]);
			answer = Math.min(answer, dp[i][N][1] + distance[1][N]);
		}
		System.out.println(answer);
	}

	public static long getDistance(Position p1, Position p2) {
		long dx = p1.x - p2.x;
		long dy = p1.y - p2.y;
		return dx*dx + dy*dy;
	}
}
