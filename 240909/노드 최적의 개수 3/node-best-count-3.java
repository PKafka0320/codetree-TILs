import java.io.*;
import java.util.*;

public class Main {
	static int parents[], dp[][];
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* input & initialize */
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		parents = new int[N+1];
		dp = new int[N+1][2];
		
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
		
		/* logic */
		dfs(1, new boolean[N+1]);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
		
	}
	
	public static void dfs(int node, boolean[] visited) {
		visited[node] = true;
		
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			parents[ad] = node;
			dfs(ad, visited);
		}
		
		dp[node][1] = 1;
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			dp[node][1] += Math.min(dp[ad][0], dp[ad][1]);
			dp[node][0] += dp[ad][1];
		}
	}

}