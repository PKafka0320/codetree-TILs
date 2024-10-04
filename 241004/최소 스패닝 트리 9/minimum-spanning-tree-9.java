import java.io.*;
import java.util.*;

public class Main {
	static class Path implements Comparable<Path> {
		int vertex, dist;

		public Path(int vertex, int dist) {
			this.vertex = vertex;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Path o) {
			return this.dist - o.dist;
		}
	}
	static int INF = (int) 1e9;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int dists[] = new int[N+1];
		Queue<Path> queue = new PriorityQueue<>();
		List<Path> edges[] = new ArrayList[N+1];
		boolean visited[] = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			dists[i] = INF;
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			edges[node1].add(new Path(node2, dist));
			edges[node2].add(new Path(node1, dist));
		}
		
		queue.add(new Path(1, 0));
		dists[1] = 0;
		visited[0] = true;
		
		int answer = 0;
		int cnt = 0;
		while (!queue.isEmpty()) {
			Path path = queue.poll();
			int curVertex = path.vertex;
			int curDist = path.dist;
			
			if (visited[curVertex]) continue;
			answer += curDist;
			visited[curVertex] = true;
			if (++cnt == N) break;
			
			for (Path next : edges[curVertex]) {
				int nextVertex = next.vertex;
				int nextDist = next.dist;
				
				if (!visited[nextVertex] && dists[nextVertex] > nextDist) {
					dists[nextVertex] = nextDist;
					queue.add(new Path(nextVertex, dists[nextVertex]));
				}
			}
		}
		
		System.out.println(answer);
	}
}