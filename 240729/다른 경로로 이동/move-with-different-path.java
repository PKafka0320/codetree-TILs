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
    	
    	int[] minDistance = new int[n];
    	
    	// 그래프 생성
    	List<Path>[] graph = new ArrayList[n];
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

    	dijkstra(graph, minDistance, n - 1);
    	
    	int[] beforeNode = new int[n];
    	Arrays.fill(beforeNode, -1);
    	
    	int currentNode = n - 1;
    	while (currentNode != 0) {
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			
    			if (minDistance[currentNode] + nextDistance == minDistance[nextNode]) {
    				beforeNode[currentNode] = nextNode;
    				currentNode = nextNode;
    				break;
    			}
    		}
    	}
    	
    	int[] minDistanceB = new int[n];

    	dijkstraCustom(graph, minDistanceB, 0, beforeNode);
    	
    	System.out.println(minDistanceB[n - 1]);
    }
    
    public static void dijkstra(List<Path>[] graph, int[] minDistance, int startNode) {
    	Arrays.fill(minDistance, -1);
    	
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(startNode, 0));
    	minDistance[startNode] = 0;
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (minDistance[currentNode] != currentDistance) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			int sumDistance = currentDistance + nextDistance;
    			
    			if (minDistance[nextNode] == -1 || minDistance[nextNode] > sumDistance) {
    				minDistance[nextNode] = sumDistance;
    				pq.add(new Route(nextNode, sumDistance));
    			}
    		}
    	}
    }
    
    public static void dijkstraCustom(List<Path>[] graph, int[] minDistance, int startNode, int[] check) {
    	Arrays.fill(minDistance, -1);
    	
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(startNode, 0));
    	minDistance[startNode] = 0;
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (minDistance[currentNode] != currentDistance) continue;
    		
    		for (Path path : graph[currentNode]) {    			
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			int sumDistance = currentDistance + nextDistance;
    			
    			if (check[nextNode] == currentNode) continue;
    			if (minDistance[nextNode] == -1 || minDistance[nextNode] > sumDistance) {
    				minDistance[nextNode] = sumDistance;
    				pq.add(new Route(nextNode, sumDistance));
    			}
    		}
    	}
    }
}