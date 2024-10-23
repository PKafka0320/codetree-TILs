import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int answer, N, M, K, weights[], INF = (int) 1e9;
	static boolean visited[];
	static List<Edge> edges[];
	static List<Integer> colored;
	static Queue<Edge> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N + 1];
		weights = new int[N + 1];
		visited = new boolean[N+1];
		queue = new PriorityQueue<>();
		colored = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			weights[i] = INF;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= K; i++) {
			colored.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[node1].add(new Edge(node2, weight));
			edges[node2].add(new Edge(node1, weight));
		}

		for (int node : colored) {
			queue.add(new Edge(node, 0));
			weights[node] = 0;
		}

//		for (int i = 1; i <= N; i++) {
//			System.out.printf(weights[i] == INF ? "INF\t" : "%d\t", weights[i]);
//		}
//		System.out.println();
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if (visited[edge.node]) continue;
			
			visited[edge.node] = true;
			answer += edge.weight;
//			System.out.printf("select %d: +%d = %d%n", edge.node, edge.weight, answer);
			
			for (Edge ad : edges[edge.node]) {
				if (weights[ad.node] > ad.weight) {
					weights[ad.node] = ad.weight;
					queue.add(new Edge(ad.node, ad.weight));
				}
			}
//			for (int i = 1; i <= N; i++) {
//				System.out.printf(weights[i] == INF ? "INF\t" : "%d\t", weights[i]);
//			}
//			System.out.println();
		}
		

		System.out.println(answer);
	}
}