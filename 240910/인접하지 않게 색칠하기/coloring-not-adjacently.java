import java.io.*;
import java.util.*;

public class Main {
	static int N, K, numbers[], parents[];
	static long dp[][][];
	static List<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* input & initialize */
		N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		parents = new int[N+1];
		graph = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			graph[node1].add(node2);
			graph[node2].add(node1);
		}
		
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		K = Integer.parseInt(br.readLine());
		dp = new long[N+1][K+1][2];
		
		/* logic */
		dfs(1);
		
		long result = 0;
		for (int i = 1; i <= K; i++) {
			result = Math.max(result, dp[1][i][0]);
			result = Math.max(result, dp[1][i][1]);
		}
		System.out.println(result);
	}
	
	public static void dfs(int node) {
		for (int ad : graph[node]) {
			if (parents[node] == ad) continue;
			parents[ad] = node;
			dfs(ad);
		}
		
		dp[node][1][1] = numbers[node];
		dp[node][0][0] = 0;
		
		for (int ad : graph[node]) {
			if (parents[node] == ad) continue;
			
			for (int k = K; k >= 1; k--) {
				for (int prevk = 1; prevk <= k; prevk++) {
					dp[node][k][1] = Math.max(dp[node][k][1], dp[node][k - prevk][1] + dp[ad][prevk][0]);
				}
			}
			
			for (int k = K; k >= 0; k--) {
				for (int prevk = 0; prevk <= k; prevk++) {
					dp[node][k][0] = Math.max(
							dp[node][k][0], 
							dp[node][k - prevk][0] + Math.max(dp[ad][prevk][0], dp[ad][prevk][1]));
				}
			}
		}
		
	}
	
}