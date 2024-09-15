import java.io.*;
import java.util.*;

public class Main {
	static int N, Q, D, parent[][], depth[];
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		D = (int) (Math.log(N) / Math.log(2)) + 1;
		parent = new int[N+1][D+1];
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
		dfs(1);
		
		for (int node = 1; node <= N; node++) {
			for (int d = 1; d <= D; d++) {
				parent[node][d] = parent[parent[node][d-1]][d-1];
			}
		}
		
		Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int node3 = Integer.parseInt(st.nextToken());
			
			System.out.println(lca(lca(node1, node2), node3));
		}
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parent[node][0] == ad) continue;
			parent[ad][0] = node;
			depth[ad] = depth[node] + 1;
			dfs(ad);
		}
	}
	
	public static int lca(int node1, int node2) {
		if (depth[node1] < depth[node2]) {
			return lca(node2, node1);
		}
		
		for (int d = D; d >= 0; d--) {
			if (depth[node1] - depth[node2] >= (1 << d)) {
				node1 = parent[node1][d];
			}
		}
		
		if (node1 == node2) {
			return node1;
		}
		
		for (int d = D; d >= 0; d--) {
			if (parent[node1][d] != parent[node2][d]) {
				node1 = parent[node1][d];
				node2 = parent[node2][d];
			}
		}
		
		return parent[node1][0];
	}

}