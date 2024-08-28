import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] edges;
	static int[] parent, dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int q = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		visited = new boolean[n];
		dp = new int[n];
		edges = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		
		visited[r] = true;
		dfs(r);
		
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int root = Integer.parseInt(br.readLine()) - 1;
			answer.append(dp[root]).append("\n");
		}
		System.out.println(answer);
	}
	
	public static void dfs(int node) {
		for (int next : edges[node]) {
			if (visited[next] || parent[node] == next) continue;
			visited[next] = true;
			parent[next] = node;
			dfs(next);
		}
		
		dp[node] = 1;
		for (int next : edges[node]) {
			if (parent[node] == next) continue;
			dp[node] += dp[next];
		}
	}
}