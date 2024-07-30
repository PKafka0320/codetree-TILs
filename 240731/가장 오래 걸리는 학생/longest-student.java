import java.io.*;
import java.util.*;

class Path {
	int node, distance;
	
	public Path(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}

class Route implements Comparable<Route> {
	int node, distance;
	
	public Route(int node, int distance) {
		this.node = node; 
		this.distance = distance;
	}
	
	@Override
	public int compareTo(Route o) {
		return this.distance - o.distance;
	}
}

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;

    	st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	// 그래프 생성
    	List<Path>[] graph = new ArrayList[n];
    	for (int i = 0; i < n; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	// 그래프 데이터 추가
    	// 도착 지점에서 각 지점으로의 최단거리를 알아야 하기 때문에 역방향으로 생성
    	for (int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int to = Integer.parseInt(st.nextToken()) - 1;
    		int from = Integer.parseInt(st.nextToken()) - 1;
    		int distance = Integer.parseInt(st.nextToken());
    		
    		graph[to].add(new Path(from, distance));
    		graph[from].add(new Path(to, distance));
    	}
    	
    	// 각 그래프에서 다익스트라 진행
    	int[] dist = new int[n];
    	dijkstra(graph, dist, n - 1);
    	
    	int max = 0;
    	for (int distance : dist) {
    	    max = Math.max(max, distance);
    	}
    	System.out.println(max);
    }
    
    public static void dijkstra(List<Path>[] graph, int[] dists, int startNode) {
    	Arrays.fill(dists, -1);
    	dists[startNode] = 0;
    	
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(startNode, 0));
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (dists[currentNode] != currentDistance) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int distance = path.distance;
    			int sumDistance = currentDistance + distance;
    			
    			if (dists[nextNode] == -1 || dists[nextNode] > sumDistance) {
    				dists[nextNode] = sumDistance;
    				pq.add(new Route(nextNode, sumDistance));
    			}
    		}
    	}
    }
}