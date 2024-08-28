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
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		visited = new boolean[n + 1];
		dp = new int[n + 1];
		edges = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}
		
		int root = -1;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			if (parent[i] == -1) root = i;
			else {
				edges[parent[i]].add(i);
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			
			dp[node] += point;
		}
		
		dfs(root);
		
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			answer.append(dp[i]).append(" ");
		}
		System.out.println(answer);
	}
	
	public static void dfs(int node) {
		for (int next : edges[node]) {
			if (visited[next] || parent[node] == next) continue;
			visited[next] = true;
			parent[next] = node;
			dp[next] += dp[node];
			dfs(next);
		}
	}
}