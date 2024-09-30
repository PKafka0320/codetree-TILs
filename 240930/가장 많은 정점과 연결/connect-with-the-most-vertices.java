import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node1, node2, distance;

		public Edge(int node1, int node2, int distance) {
			this.node1 = node1;
			this.node2 = node2;
			this.distance = distance;
		}
		
		public int compareTo(Edge e) {
			return this.distance - e.distance;
		}
	}
	static int N, M, K, A, B, roots[], numbers[];
	static List<Edge> edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1];
		numbers = new int[N+1];
		edges = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			union(node1, node2);
		}
		
		for (int i = 1; i <= N-1; i++) {
			for (int j = i+1; j <= N; j++) {
				if (find(i) == find(j)) continue;
				edges.add(new Edge(i, j, numbers[i] + numbers[j]));
			}
		}
		
		Collections.sort(edges);
		
		long result = 0;
		for (Edge edge : edges) {
			int node1 = edge.node1;
			int node2 = edge.node2;
			int distance = edge.distance;
			
			if (union(node1, node2)) {
				result += distance;
			}
		}
		
		System.out.println(result > K ? "NO" : result);
	}
	
	public static boolean union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return false;
		
		roots[root1] = root2;
		return true;
	}
	
	public static int find(int node) {
		if (roots[node] == node) return node;
		return roots[node] = find(roots[node]);
	}

}