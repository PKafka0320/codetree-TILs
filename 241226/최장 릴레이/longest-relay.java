import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N][N];
		int[][] dp = new int[N][1 << N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][1 << 0] = 0;

		for (int i = 0; i < N; i++) {
			for (int from = 0; from < N; from++) {
				for (int mask = 0; mask < (1 << N); mask++) {
					if ((mask & (1 << from)) == 0 || dp[from][mask] == -1)
						continue;

					for (int to = 0; to < N; to++) {
						if ((mask | (1 << to)) == mask || dp[from][mask] >= grid[from][to])
							continue;

						if (dp[to][mask | (1 << to)] == -1) {
							dp[to][mask | (1 << to)] = grid[from][to];
						} else {
							dp[to][mask | (1 << to)] = Math.min(dp[to][mask | (1 << to)], grid[from][to]);
						}
					}
				}
			}
		}

		int answer = 0;
		for (int end = 0; end < N; end++) {
			for (int mask = 0; mask < (1 << N); mask++) {
				if (dp[end][mask] == -1) continue;
				
				answer = Math.max(answer, countPeople(mask));
			}
		}
		System.out.println(answer);
	}
	
	public static int countPeople(int mask) {
		int result = 0;
		
		while (mask > 0) {
			result += (mask & 1) == 1 ? 1 : 0;
			mask >>= 1;
		}
		
		return result;
	}
}
