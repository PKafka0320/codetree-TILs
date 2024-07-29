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
	int node, distance, prev;
	
	public Route(int node, int distance, int prev) {
		this.node = node; 
		this.distance = distance;
		this.prev = prev;
	}
	
	@Override
	public int compareTo(Route o) {
		if (this.distance == o.distance) return this.prev - o.prev;
		return this.distance - o.distance;
	}
}

public class Main {
	static int n, m;
	static ArrayList<Path>[] graph;
	static int[] distance;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	distance = new int[n];
    	int[] beforeNodeA = new int[n];
    	
    	// 그래프 생성
    	graph = new ArrayList[n];
    	for (int i = 0; i < n; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			
			graph[node1].add(new Path(node2, distance));
			graph[node2].add(new Path(node1, distance));
		}

    	dijkstra(beforeNodeA);
    	dijkstraB(beforeNodeA);
    	
    	System.out.println(distance[n - 1]);
    }
    
    public static void dijkstra(int[] beforeNode) {
    	Arrays.fill(distance, -1);
    	Arrays.fill(beforeNode, -1);
    	
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(0, 0, -1));
    	distance[0] = 0;
    	beforeNode[0] = 0;
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (distance[currentNode] != currentDistance) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			int sumDistance = currentDistance + nextDistance;
    			
    			if (distance[nextNode] == -1 || distance[nextNode] > sumDistance) {
    				distance[nextNode] = sumDistance;
    				beforeNode[nextNode] = currentNode;
    				pq.add(new Route(nextNode, sumDistance, currentNode));
    			}
    		}
    	}
    }
    
    public static void dijkstraB(int[] beforeNode) {
    	Arrays.fill(distance, -1);
    	
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(0, 0, -1));
    	distance[0] = 0;
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (distance[currentNode] != currentDistance) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			int sumDistance = currentDistance + nextDistance;
    			
    			if (beforeNode[nextNode] == currentNode) continue;
    			
    			if (distance[nextNode] == -1 || distance[nextNode] > sumDistance) {
    				distance[nextNode] = sumDistance;
    				pq.add(new Route(nextNode, sumDistance, currentNode));
    			}
    		}
    	}
    }
}