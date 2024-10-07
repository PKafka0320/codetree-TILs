import java.io.*;
import java.util.*;

public class Main {
	static class Path implements Comparable<Path> {
		int node, distance;

		public Path(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Path o) {
			return this.distance - o.distance;
		}
	}
	static int INF = (int) 1e9;
	static int N, M, K, distances[];
	static List<Path>[] edges;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N+1];
		distances = new int[N+1];
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			distances[i] = INF;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			edges[node1].add(new Path(node2, distance));
			edges[node2].add(new Path(node1, distance));
		}
		
		distances[1] = 0;
		visited[1] = false;
		int answer = 0;
		int k = 0;
		
		Queue<Path> queue = new PriorityQueue<>();
		queue.add(new Path(1, 0));
		while (!queue.isEmpty()) {
			Path curPath = queue.poll();
			int curNode = curPath.node;
			int curDist = curPath.distance;
			
			if (visited[curNode]) continue;
			visited[curNode] = true;
			answer += curDist + k;
			if (curDist != 0) {
				k += K;
			}
			
			for (Path ad : edges[curNode]) {
				int adNode = ad.node;
				int adDist = ad.distance;
				
				if (visited[adNode]) continue;
				if (distances[adNode] > adDist) {
					distances[adNode] = adDist;
					queue.add(new Path(adNode, adDist));
				}
			}
		}
		
		System.out.println(answer);
	}
}