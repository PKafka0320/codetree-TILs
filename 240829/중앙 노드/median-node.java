import java.io.*;
import java.util.*;

public class Main {
	static int n, r, central;
	static int[] parents, dp;
	static boolean[] visited;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		parents = new int[n + 1];
		edges = new ArrayList[n + 1];
		dp = new int[n + 1];
		visited= new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		
		central = - 1;
		visited[r] = true;
		dfs(r);

		if (central == -1) {
			System.out.println(0);
			return;
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int ad : edges[central]) {
			if (ad == parents[central]) continue;
			max = Math.max(max, dp[ad]);
			min = Math.min(min, dp[ad]);
		}
		System.out.println(max - min);
	}
	
	public static void dfs(int node) {
		if (central == -1 &&
				((node == r && edges[node].size() > 1) ||
					(node != r && edges[node].size() > 2))) {
			central = node;
		}
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			visited[ad] = true;
			parents[ad] = node;
			dfs(ad);
		}
		
		dp[node] = 1;
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			dp[node] += dp[ad];
		}
	}
}