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

	static final long MAX_VALUE = (long) 1e15;
	static Position[] arr;
	static long[][] dists;
	static long[][][] dp;

	static long getDist(Position a, Position b) {
		long dy = Math.abs(a.y - b.y);
		long dx = Math.abs(a.x - b.x);
		return dy * dy + dx * dx;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		arr = new Position[n + 1];
		dists = new long[n + 1][n + 1];
		dp = new long[n + 1][n + 1][2];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i] = new Position(a, b);
		}
		Arrays.sort(arr, 1, n + 1);
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dists[i][j] = getDist(arr[i], arr[j]);
			}
		}

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				Arrays.fill(dp[i][j], MAX_VALUE);
			}
		}

		dp[1][1][0] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int k = Math.max(i, j) + 1;
				if (k == n + 1) {
					continue;
				}

				dp[k][j][0] = Math.min(dp[k][j][0], dp[i][j][0] + dists[i][k]);
				dp[i][k][0] = Math.min(dp[i][k][0], dp[i][j][0] + dists[j][k]);

				dp[k][j][1] = Math.min(dp[k][j][1], Math.min(dp[i][j][0], dp[i][j][1] + dists[i][k]));
				dp[i][k][1] = Math.min(dp[i][k][1], Math.min(dp[i][j][0], dp[i][j][1] + dists[j][k]));
			}
		}

		long ans = MAX_VALUE;
		for (int i = 1; i < n; i++) {
			ans = Math.min(ans, dp[i][n][1] + dists[i][n]);
			ans = Math.min(ans, dp[i][n][0]);
		}

		System.out.println(ans);
	}
}
