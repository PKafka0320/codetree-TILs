import java.io.*;
import java.util.*;

public class Main {
	static int D, depths[], parents[][];
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		D = (int)(Math.log(N) / Math.log(2)) + 1;
		parents = new int[N+1][D+1];
		depths = new int[N+1];
		
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
		
		depths[1] = 1;
		dfs(1);
		
		for (int d = 1; d <= D; d++) {
			for (int node = 1; node <= N; node++) {
				parents[node][d] = parents[parents[node][d-1]][d-1];
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			System.out.println(lca(node1, node2));
		}
	}
	
	public static int lca(int node1, int node2) {
		for (int d = D; d >= 0; d--) {
			if (depths[node1] - depths[node2] >= (1 << d)) {
				node1 = parents[node1][d];
			} else if (depths[node2] - depths[node1] >= (1 << d)) {
				node2 = parents[node2][d];
			}
		}
		
		for (int d = D; d >= 0; d--) {
			if (parents[node1][d] != parents[node2][d]) {
				node1 = parents[node1][d];
				node2 = parents[node2][d];
			}
		}
		
		if (node1 == node2) return node1;
		return parents[node1][0];
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[node][0] == ad) continue;
			depths[ad] = depths[node] + 1;
			parents[ad][0] = node;
			dfs(ad);
		}
	}
	
}