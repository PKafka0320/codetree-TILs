import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node1, node2, weight;

		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	static int N, M, root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N*M+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				root[M*(i-1)+j] = M*(i-1)+j;
			}
		}
		
		Queue<Edge> queue = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M-1; j++) {
				int weight = Integer.parseInt(st.nextToken());
				queue.add(new Edge(M*(i-1)+j, M*(i-1)+j+1, weight));
			}
		}
		for (int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int weight = Integer.parseInt(st.nextToken());
				queue.add(new Edge(M*(i-1)+j, M*(i-1)+j+M, weight));
			}
		}
		
		int sum = 0;
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			int weight = edge.weight;
			
			if (union(node1, node2)) {
				sum += weight;
			}
		}
		System.out.println(sum);
		
	}
	
	public static boolean union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return false;
		
		root[root1] = root2;
		return true;
	}
	
	public static int find(int node) {
		if (root[node] == node) return node;
		return root[node] = find(root[node]);
	}
}