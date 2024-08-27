import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] tree;
	static int[][] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<>();
		}
		dp = new int[n][2];
		visited = new boolean[n];
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;

			tree[node1].add(node2);
			tree[node2].add(node1);
		}
		
		visited[0] = true;
		dfs(0);
		
		System.out.println(Math.min(dp[0][0], dp[0][1]));
	}
	
	public static void dfs(int index) {
		for (int nextIndex : tree[index]) {
			if (visited[nextIndex]) continue;
			visited[nextIndex] = true;
			dfs(nextIndex);
		}

		dp[index][0] = 0;
		dp[index][1] = 1;
		for (int nextIndex : tree[index]) {
			dp[index][0] += dp[nextIndex][1];
			dp[index][1] += Math.min(dp[nextIndex][0], dp[nextIndex][1]);
		}
	}
	
}