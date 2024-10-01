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
	static int N, M, roots[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		roots = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}
		
		Queue<Edge> edges = new PriorityQueue<>();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(node1, node2, weight));
		}
		
		int edgeCount = 0;
		long answer = 0;
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			int weight = edge.weight;
			
			if (union(node1, node2)) {
				if (++edgeCount == N - 1) {
					break;
				}
			} else {
				answer += weight;
			}
		}
		
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			answer += edge.weight;
		}
		
		System.out.println(answer);
	}
	
	public static boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if (A == B) return false;
		
		roots[B] = A;
		return true;
	}
	
	public static int find(int a) {
		if (roots[a] == a) return a;
		return roots[a] = find(roots[a]);
	}

}