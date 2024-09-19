import java.io.*;
import java.util.*;

public class Main {
	static int N, Q, D, depth[], parents[][];
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		D = (int) (Math.log(N) / Math.log(2)) + 1;
		edges = new ArrayList[N+1];
		depth = new int[N+1];
		parents = new int[D+1][N+1];
		
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
		makeParents();
		
		Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			System.out.println(lca(node1, node2));
		}
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[0][node] == ad) continue;
			parents[0][ad] = node;
			depth[ad] = depth[node] + 1;
			dfs(ad);
		}
	}
	
	public static void makeParents() {
		for (int d = 1; d <= D; d++) {
			for (int node = 1; node <= N; node++) {
				parents[d][node] = parents[d-1][parents[d-1][node]];
			}
		}
	}
	
	public static int lca(int node1, int node2) {
		if (depth[node1] < depth[node2]) {
			return lca(node2, node1);
		}

		int count = 2;
		int start2 = node2;
		
		for (int d = D; d >= 0; d--) {
			if (depth[node1] - depth[node2] >= (1 << d)) {
				node1 = parents[d][node1];
				count += 1 << d;
			}
		}
		if (start2 == node1) count--;
		
		if (node1 == node2) {
			return count;
		}
		
		for (int d = D; d >= 0; d--) {
			if (parents[d][node1] != parents[d][node2]) {
				node1 = parents[d][node1];
				node2 = parents[d][node2];
				count += (1 << (d+1));
			}
		}
		
		return count + 1;
	}
	
}