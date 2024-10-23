import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node, distance;

		public Edge(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}
	}
	static int N, M, K, dist[];
	static boolean visited[];
	static int INF = (int)1e9;
	static List<Edge> edges[];
	static List<Integer> remove[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		edges = new ArrayList[N+1];
		remove = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			dist[i] = INF;
			edges[i] = new ArrayList<>();
			remove[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int distance = Integer.parseInt(st.nextToken());
			edges[0].add(new Edge(i, distance));
			edges[i].add(new Edge(0, distance));
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			remove[node1].add(node2);
			remove[node2].add(node1);
		}
		
		for (int i = 1; i < N; i++) {
			if (!remove[i].contains((i+1)%(N+1))) {
				edges[i].add(new Edge((i+1)%(N+1), 0));
			}
			if (!remove[(i+1)%(N+1)].contains(i)) {
				edges[(i+1)%(N+1)].add(new Edge(i, 0));
			}
		}
		if (!remove[1].contains(N)) {
			edges[1].add(new Edge(N, 0));
		}
		if (!remove[N].contains(1)) {
			edges[N].add(new Edge(1, 0));
		}
		
		dist[1] = 0;
		int answer = 0;
		
		Queue<Edge> queue = new PriorityQueue<>();
		queue.add(new Edge(1, 0));
		
		while (!queue.isEmpty()) {
			Edge current = queue.poll();
			
			if (visited[current.node]) continue;
			if (dist[current.node] != current.distance) continue;

			visited[current.node] = true;
			answer += dist[current.node];
			
			if (allChecked()) break;
			
			for (Edge edge : edges[current.node]) {
				if (visited[edge.node]) continue;
				dist[edge.node] = Math.min(dist[edge.node], edge.distance);
				
				queue.add(new Edge(edge.node, dist[edge.node]));
			}
		}
		
		System.out.println(answer <= K ? 1 : 0);
	}
	
	public static boolean allChecked() {
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) return false;
		}
		return true;
	}
}