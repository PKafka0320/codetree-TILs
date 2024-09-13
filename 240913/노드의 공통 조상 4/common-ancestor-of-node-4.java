import java.io.*;
import java.util.*;

public class Main {
	static int N, Q, depths[], parents[];
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		depths = new int[N+1];
		parents = new int[N+1];
		
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
		
		Q = Integer.parseInt(br.readLine());
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			System.out.println(lca(node1, node2));
		}
	}
	
	public static int lca(int node1, int node2) {
		while (depths[node1] != depths[node2]) {
			if (depths[node1] > depths[node2]) {
				node1 = parents[node1];
			} else {
				node2 = parents[node2];
			}
		}
		
		if (node1 == node2) return node1;
		
		while (parents[node1] != parents[node2]) {
			node1 = parents[node1];
			node2 = parents[node2];
		}
		
		return parents[node1];
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			parents[ad] = node;
			depths[ad] = depths[node] + 1;
			dfs(ad);
		}
	}
	
}