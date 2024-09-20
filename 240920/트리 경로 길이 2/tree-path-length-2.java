import java.io.*;
import java.util.*;

public class Main {
	static class Path {
		int node, distance;

		public Path(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}
	static int N, Q, D, depth[], parents[][], distance[][];
	static List<Path>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		D = (int) (Math.log(N) / Math.log(2)) + 1;
		depth = new int[N+1];
		parents = new int[D+1][N+1];
		distance = new int[D+1][N+1];
		edges = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			edges[node1].add(new Path(node2, distance));
			edges[node2].add(new Path(node1, distance));
		}
		
		depth[1] = 1;
		dfs(1);
		
		for (int d = 1; d <= D; d++) {
			for (int node = 1; node <= N; node++) {
				parents[d][node] = parents[d-1][parents[d-1][node]];
				if (distance[d-1][parents[d-1][node]] != 0) {
					distance[d][node] = distance[d-1][node] + distance[d-1][parents[d-1][node]];
				}
			}
		}
		
		Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			System.out.println(lca(node1, node2));
		}
	}
	
	public static long lca(int node1, int node2) {
		if (depth[node1] < depth[node2]) {
			return lca(node2, node1);
		}
		
		long sum = 0;
		
		for (int d = D; d >= 0; d--) {
			if (depth[node1] - depth[node2] >= (1 << d)) {
				sum += distance[d][node1];
				node1 = parents[d][node1];
			}
		}
		
		if (node1 == node2) {
			return sum;
		}
		
		for (int d = D; d >= 0; d--) {
			if (parents[d][node1] != parents[d][node2]) {
				sum += distance[d][node1];
				sum += distance[d][node2];
				node1 = parents[d][node1];
				node2 = parents[d][node2];
			}
		}
		
		sum += distance[0][node1] + distance[0][node2];
		return sum;
	}
	
	public static void dfs(int node) {
		for (Path ad : edges[node]) {
			if (parents[0][node] == ad.node) continue;
			parents[0][ad.node] = node;
			distance[0][ad.node] = ad.distance;
			depth[ad.node] = depth[node] + 1;
			dfs(ad.node);
		}
	}
	
}