import java.io.*;
import java.util.*;

public class Main {
	static int N, K, Q, D, depths[], parents[][], counts[][];
	static boolean colored[];
	static List<Integer> edges[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		D = (int) (Math.log(N) / Math.log(2)) + 1;
		depths = new int[N+1];
		parents = new int[D+1][N+1];
		edges = new ArrayList[N+1];
		colored = new boolean[N+1];
		counts = new int[D+1][N+1];
		
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
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			int node = Integer.parseInt(br.readLine());
			colored[node] = true;
		}
		
		depths[1] = 1;
		dfs(1);
		
		for (int d = 1; d <= D; d++) {
			for (int n = 1; n <= N; n++) {
				parents[d][n] = parents[d-1][parents[d-1][n]];
				if (parents[d][n] != 0) {
					counts[d][n] = counts[d-1][n] + counts[d-1][parents[d-1][n]];
				}
			}
		}
		
		Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			System.out.println(lca(node1, node2));
		}
	}
	
	public static int lca(int node1, int node2) {
		if (depths[node1] < depths[node2]) {
			return lca (node2, node1);
		}
		
		int count = 0;
		if (colored[node1]) count++;
		
		for (int d = D; d >= 0; d--) {
			if (depths[node1] - depths[node2] >= (1 << d)) {
				count += counts[d][node1];
				node1 = parents[d][node1];
			}
		}
		
		if (node1 == node2) {
			return count;
		}
		
		if (colored[node2]) count++;
		
		for (int d = D; d >= 0; d--) {
			if (parents[d][node1] != parents[d][node2]) {
				count += counts[d][node1] + counts[d][node2];
				node1 = parents[d][node1];
				node2 = parents[d][node2];
			}
		}
		
		if (colored[parents[0][node1]]) count++;
		return count;
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[0][node] == ad) continue;
			parents[0][ad] = node;
			depths[ad] = depths[node] + 1;
			if (colored[node]) {
				counts[0][ad]++;
			}
			dfs(ad);
		}
	}

}