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
		D = (int) (Math.log(N) / Math.log(2));
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
			
			int topNode = -1;
			int lowerNode1 = -1;
			int lowerNode2 = -1;
			if (depth[node1] <= depth[node2] && depth[node1] <= depth[node3]) {
				topNode = node1;
				lowerNode1 = node2;
				lowerNode2 = node3;
			} else if (depth[node2] <= depth[node1] && depth[node2] <= depth[node3]) {
				topNode = node2;
				lowerNode1 = node1;
				lowerNode2 = node3;
			} else if (depth[node3] <= depth[node1] && depth[node3] <= depth[node2]) {
				topNode = node3;
				lowerNode1 = node1;
				lowerNode2 = node2;
			}
			System.out.println(lca(topNode, lowerNode1, lowerNode2));
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
	
	public static int lca(int node1, int node2, int node3) {
		for (int d = D; d >= 0; d--) {
			if (depth[node2] - depth[node1] >= (1 << d)) {
				node2 = parent[node2][d];
			}
			
			if (depth[node3] - depth[node1] >= (1 << d)) {
				node3 = parent[node3][d];
			}
		}
		
		for (int d = D; d >= 0; d--) {
			if (parent[node1][d] != parent[node2][d]) {
				node1 = parent[node1][0];
				node2 = parent[node2][0];
				node3 = parent[node3][0];
			}
			
			if (parent[node1][d] != parent[node3][d]) {
				node1 = parent[node1][0];
				node2 = parent[node2][0];
				node3 = parent[node3][0];
			}
		}
		
		if (node1 == node2 && node1 == node3) {
			return node1;
		}
		
		return parent[node1][0];
	}

}