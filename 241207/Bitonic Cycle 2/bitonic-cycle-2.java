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

		long[][][][] dp = new long[N + 1][N + 1][N + 1][2];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				for (int k = 0; k <= N; k++) {
					for (int m = 0; m <= 1; m++) {
						dp[i][j][k][m] = (long) 1e14;
					}
				}
			}
		}
		dp[0][0][0][0] = 0;

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				for (int k = 0; k <= N; k++) {
					for (int m = 0; m <= 1; m++) {
						int next = i + 1;

						if (next == N + 1)
							continue;

						dp[next][next][k][m] = Math.min(dp[next][next][k][m], dp[i][j][k][m] + distance[j][next]);
						dp[next][j][next][m] = Math.min(dp[next][j][next][m], dp[i][j][k][m] + distance[k][next]);

						if (m == 1)
							continue;

						dp[next][j][k][m + 1] = Math.min(dp[next][j][k][m + 1], dp[i][j][k][m]);
					}
				}
			}
		}

		long answer = (long) 1e14;
		for (int j = 0; j <= N; j++) {
			for (int k = 0; k <= N; k++) {
				long temp = dp[N][j][k][1];
				
				if (j != N) {
					temp += distance[j][N];
				} else {
					temp += distance[1][N];
				}
				if (k != N) {
					temp += distance[k][N];
				} else {
					temp += distance[1][N];
				}
				
				answer = Math.min(answer, temp);
			}
		}
		System.out.println(answer);
	}
	
	public static long getDistance(Position p1, Position p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
}
