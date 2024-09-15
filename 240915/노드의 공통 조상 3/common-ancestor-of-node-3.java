import java.io.*;
import java.util.*;

public class Main {
	static int N, Q, parent[], depth[];
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		depth = new int[N+1];
		parent = new int[N+1];
		
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
		
		Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int node3 = Integer.parseInt(st.nextToken());
			System.out.println(lca(node1, node2, node3));
		}
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parent[node] == ad) continue;
			parent[ad] = node;
			depth[ad] = depth[node] + 1;
			dfs(ad);
		}
	}
	
	public static int lca(int node1, int node2, int node3) {
		int minDepth = Math.min(depth[node1], Math.min(depth[node2], depth[node3]));
		while (depth[node1] != minDepth) {
			node1 = parent[node1];
		}
		while (depth[node2] != minDepth) {
			node2 = parent[node2];
		}
		while (depth[node3] != minDepth) {
			node3 = parent[node3];
		}
		
		if (node1 == node2 && node1 == node3) {
			return node1;
		}
		
		while (node1 != node2 || node1 != node3) {
			node1 = parent[node1];
			node2 = parent[node2];
			node3 = parent[node3];
		}
		
		return node1;
	}

}