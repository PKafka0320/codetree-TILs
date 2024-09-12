import java.io.*;
import java.util.*;

public class Main {
	static int maxDepth, parent[][], depth[];
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		parent = new int[N+1][N+1];
		depth = new int[N+1];
		
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
		
		depth[1] = 1;
		maxDepth = 1;
		dfs(1);
		
		for (int depth = 1; depth <= maxDepth; depth++) {
			for (int node = 1; node <= N; node++) {
				parent[depth][node] = parent[depth - 1][parent[depth -1][node]];
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			if (node1 == node2) {
				System.out.println(node1);
			} else {
				System.out.println(lca(node1, node2));
			}
		}
	}
	
	public static int lca(int node1, int node2) {
		for (int d = maxDepth; d >= 0; d--) {
			if (depth[node1] - depth[node2] >= (1 << d)) {
				node1 = parent[d][node1];
			} else if (depth[node2] - depth[node1] >= (1 << d)) {
				node2 = parent[d][node2];
			}
		}
		
		for (int d = maxDepth; d >= 0; d--) {
			if (parent[d][node1] != parent[d][node2]) {
				node1 = parent[d][node1];
				node2 = parent[d][node2];
			}
		}
		
		if (node1 == node2) {
			return node1;
		}
		return parent[0][node1];
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parent[0][node] == ad) continue;
			parent[0][ad] = node;
			depth[ad] = depth[node] + 1;
			maxDepth = Math.max(maxDepth, depth[ad]);
			dfs(ad);
		}
	}
	
}