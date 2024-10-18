import java.io.*;
import java.util.*;

public class Main {
	static int N, M, root[];
	static class Edge {
		int node1, node2, weight;

		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		
		Queue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight - o2.weight;
			}
		});
		
		Queue<Edge> queue2 = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o2.weight - o1.weight;
			}
		});
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = 1 - Integer.parseInt(st.nextToken());
			
			queue.add(new Edge(node1, node2, weight));
			queue2.add(new Edge(node1, node2, weight));
		}
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		int count = 0;
		int minSum = 0;
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			int weight = edge.weight;
			
			if (union(node1, node2)) {
				minSum += weight;
				count++;
			}
			
			if (count == N-1) break;
		}
		minSum *= minSum;
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		int maxSum = 0;
		while (!queue2.isEmpty()) {
			Edge edge = queue2.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			int weight = edge.weight;
			
			if (union(node1, node2)) {
				maxSum += weight;
				count++;
			}
			
			if (count == N-1) break;
		}
		maxSum *= maxSum;
		
		System.out.println(maxSum - minSum);
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