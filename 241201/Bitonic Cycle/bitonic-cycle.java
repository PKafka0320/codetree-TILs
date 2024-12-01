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
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Position[] positions = new Position[N];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			positions[n] = new Position(x, y);
		}

		Arrays.sort(positions);

		long[][] dist = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = getDist(positions[i], positions[j]);
			}
		}

		long[][] dp = new long[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = (int) 1e9;
			}
		}
		dp[0][0] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int next = Math.max(i, j) + 1;

				if (next == N)
					continue;

				dp[next][j] = Math.min(dp[next][j], dp[i][j] + dist[i][next]);
				dp[i][next] = Math.min(dp[i][next], dp[i][j] + dist[j][next]);
			}
		}
		
		long answer = (int) 1e9;
		for (int i = 0; i < N; i++) {
			answer = Math.min(answer, dp[i][N-1] + dist[i][N-1]);
		}
		System.out.println(answer);
	}

	public static long getDist(Position p1, Position p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
}
