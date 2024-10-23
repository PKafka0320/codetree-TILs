import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return from + " " + to + " " + weight;
		}
	}
	static int N, dist[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Edge> edges = new ArrayList<>(20);
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				boolean isAd = true;
				for (int k = 0; k < N; k++) {
					if (i==k || j==k) continue;
					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						isAd = false;
						break;
					}
				}
				
				if (isAd) {
					edges.add(new Edge(i+1, j+1, dist[i][j]));
				}
			}
		}
		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.from != o2.from) return o1.from - o2.from;
				return o1.to - o2.to;
			}
		});
		
		StringBuilder answer = new StringBuilder();
		for (Edge edge : edges) {
			answer.append(edge.toString()).append("\n");
		}
		System.out.println(answer.toString());
	}
}