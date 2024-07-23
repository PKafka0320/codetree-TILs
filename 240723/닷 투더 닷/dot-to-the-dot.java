import java.io.*;
import java.util.*;

class Path {
	int node, l, c;
	
	public Path(int node, int l, int c) {
		this.node = node;
		this.l = l;
		this.c = c;
	}
}

class Route implements Comparable<Route> {
	int node, b, a, x;
	
	public Route(int node, int b, int a, int x) {
		this.node = node;
		this.b = b;
		this.a = a;
		this.x = x;
	}
	
	@Override
	public int compareTo(Route o) {
		double subject = b + (double) x / a;
		double object = o.b + (double) o.x / o.a;
		
		if (subject > object) return 1;
		else if (subject == object) return 0;
		else return -1;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		ArrayList<Path>[] graph = new ArrayList[n];
		for (int idx = 0; idx < n; idx++) {
			graph[idx] = new ArrayList<>();
		}
		
		for (int idx = 0; idx < m; idx++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[node1].add(new Path(node2, l, c));
			graph[node2].add(new Path(node1, l, c));
		}
		
		double[] minDistance = new double[n];
		Arrays.fill(minDistance, -1);
		
		PriorityQueue<Route> pq = new PriorityQueue<>();
		pq.add(new Route(0, 0, 500, x));
		minDistance[0] = 0;
		
		while (!pq.isEmpty()) {
			Route currentRoute = pq.poll();
			int currentNode = currentRoute.node;
			int currentB = currentRoute.b;
			int currentA = currentRoute.a;
			
			for (Path path : graph[currentNode]) {
				int nextNode = path.node;
				int nextL = path.l;
				int nextC = path.c;
				
				int nextB = currentB + nextL;
				int nextA = Math.min(currentA, nextC);
				double nextDistance = nextB + (double) x / nextA;
				if (minDistance[nextNode] == -1 || minDistance[nextNode] >= nextDistance) {
					minDistance[nextNode] = nextDistance;
					pq.add(new Route(nextNode, nextB, nextA, x));
				}
			}
		}
		
		System.out.println((int) minDistance[n - 1]);
	}
	
}