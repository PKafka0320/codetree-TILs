import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node1, node2;
		double weight;

		public Edge(int node1, int node2, double weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			if (this.weight < o.weight) return -1;
			else if (this.weight == o.weight) return 0;
			else return 1;
		}
	}
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];

		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		List<Position> positions = new ArrayList<>();
		Queue<Edge> queue = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j < i; j++) {
				Position position = positions.get(j-1);
				double distance = Math.sqrt(Math.pow(position.x - x, 2) + Math.pow(position.y - y, 2));
				queue.add(new Edge(i, j, distance));
			}
			
			positions.add(new Position(x, y));
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			union(node1, node2);
		}
		
		double sum = 0;
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			double weight = edge.weight;
			
			if (union(node1, node2)) {
				sum += weight;
			}
		}
		System.out.printf("%.2f", sum);
		
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