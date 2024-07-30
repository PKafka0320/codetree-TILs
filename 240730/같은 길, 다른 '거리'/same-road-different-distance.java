import java.io.*;
import java.util.*;

import javax.xml.stream.events.StartDocument;

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
	static int routesA[], routesB[];
	static int minCount;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;

    	st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	// 그래프 생성
    	List<Path>[] graphA = new ArrayList[n];
    	List<Path>[] graphB = new ArrayList[n];
    	for (int i = 0; i < n; i++) {
    		graphA[i] = new ArrayList<>();
    		graphB[i] = new ArrayList<>();
    	}
    	
    	// 그래프 데이터 추가
    	// 도착 지점에서 각 지점으로의 최단거리를 알아야 하기 때문에 역방향으로 생성
    	for (int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int to = Integer.parseInt(st.nextToken()) - 1; 
    		int from = Integer.parseInt(st.nextToken()) - 1; 
    		int distA = Integer.parseInt(st.nextToken()); 
    		int distB = Integer.parseInt(st.nextToken());
    		
    		graphA[from].add(new Path(to, distA));
    		graphB[from].add(new Path(to, distB));
    	}
    	
    	// 각 그래프에서 다익스트라 진행
    	routesA = new int[n];
    	routesB = new int[n];
    	dijkstra(graphA, routesA, n - 1, n);
    	dijkstra(graphB, routesB, n - 1, n);
    	
        // A, B 경로를 dfs로 확인
    	minCount = n;
    	dfs(0, n - 1, 0);
    	System.out.println(minCount);
    }
    
    public static void dfs(int currentNode, int endNode, int count) {
    	if (currentNode == endNode) {
    		minCount = Math.min(minCount, count);
    		return;
    	}
    	
    	if (routesA[currentNode] != routesB[currentNode]) {
    		dfs(routesA[currentNode], endNode, count + 1);
    		dfs(routesB[currentNode], endNode, count + 1);
    	} else {
    		dfs(routesA[currentNode], endNode, count);
    	}
    }
    
    public static void dijkstra(List<Path>[] graph, int[] routes, int startNode, int nodeCount) {
    	int[] minDistance = new int[nodeCount];
    	Arrays.fill(minDistance, -1);
    	Arrays.fill(routes, -1);
    	routes[startNode] = 0;
    	minDistance[startNode] = 0;
    	
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(startNode, 0));
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (minDistance[currentNode] != currentDistance) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int distance = path.distance;
    			int sumDistance = currentDistance + distance;
    			
    			if (minDistance[nextNode] == -1 || minDistance[nextNode] > sumDistance) {
    				minDistance[nextNode] = sumDistance;
    				routes[nextNode] = currentNode;
    				pq.add(new Route(nextNode, sumDistance));
    			}
    		}
    	}
    }
}