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

	static int totalSum, N, M, weights[], totalWeights[];
	static int INF = (int) 1e9;
	static List<Edge> edges[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N + 1];
		weights = new int[N + 1];
		totalWeights = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			weights[i] = INF;
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[node1].add(new Edge(node2, weight));
			edges[node2].add(new Edge(node1, weight));
		}

		int start = 1;
		mst(start);
//		System.out.println(Arrays.toString(totalWeights));

		start = -1;
		int nextMax = -1;
		for (int i = 1; i <= N; i++) {
			if (nextMax < totalWeights[i]) {
				nextMax = totalWeights[i];
				start = i;
			}
		}
		
		mst(start);
//		System.out.println(Arrays.toString(totalWeights));
		
		int longest = 0;
		for (int i = 1; i <= N; i++) {
			if (longest < totalWeights[i]) {
				longest = totalWeights[i];
			}
		}

		System.out.println(totalSum);
		System.out.println(longest);
	}

	public static void mst(int start) {
		Queue<Edge> queue = new PriorityQueue<>();
		boolean visited[] = new boolean[N + 1];
		totalSum = 0;

		for (int i = 1; i <= N; i++) {
			weights[i] = INF;
			totalWeights[i] = 0;
		}
		weights[start] = 0;
		queue.add(new Edge(start, 0));

		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if (visited[edge.node])
				continue;

			visited[edge.node] = true;
//			System.out.printf("select %d%n", edge.node);
			totalSum += edge.weight;

			for (Edge ad : edges[edge.node]) {
				if (visited[ad.node]) continue;
				if (weights[ad.node] > ad.weight) {
					weights[ad.node] = ad.weight;
					totalWeights[ad.node] = totalWeights[edge.node] + ad.weight;
					queue.add(new Edge(ad.node, ad.weight));
				}
			}
		}
	}
}