import java.io.*;
import java.util.*;

public class Main {
	static int N, M, dp[][], parents[];
	static boolean isSelected[];
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* input & initialize */
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N+1];
		isSelected = new boolean[N+1];
		dp = new int[N+1][2];
		parents = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			isSelected[Integer.parseInt(st.nextToken())] = true;
		}
		
		/* logic */
		dfs(1);
		System.out.println(Integer.min(dp[1][0], dp[1][1]));
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			parents[ad] = node;
			dfs(ad);
		}
		
		dp[node][0] = 0;
		dp[node][1] = isSelected[node] ? 0 : 1;
		
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			dp[node][0] += isSelected[ad] ? dp[ad][0] : dp[ad][1];
			dp[node][1] += dp[ad][0];
		}
	}
}