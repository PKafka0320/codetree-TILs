import java.io.*;
import java.util.*;

public class Main {
	static int n, r, central;
	static int[] dp;
	static boolean[] visited;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
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
		findCentral(r);
		
		for (int i = 0; i <= n; i++) {
			visited[i] = false;
		}
		
		visited[central] = true;
		dfs(central);

		if (central == -1) {
			System.out.println(0);
			return;
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int ad : edges[central]) {
			max = Math.max(max, dp[ad]);
			min = Math.min(min, dp[ad]);
		}
		System.out.println(max - min);
	}
	
	public static void findCentral(int node) {
		int child = edges[node].size() - 1;
		
		if (node == r) child++;
		
		if (central == -1 && child >= 2) central = node;
		if (central == -1 && child == 0) central = node;
		
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			
			visited[ad] = true;
			findCentral(ad);
		}
	}
	
	public static void dfs(int node) {
		dp[node] = 1;
		
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			visited[ad] = true;
			dfs(ad);
			dp[node] += dp[ad];
		}
	}
}