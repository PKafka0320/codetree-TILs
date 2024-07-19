import java.io.*;
import java.util.*;

class Path {
	int node, distance;
	
	public Path (int node, int distance) {
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
    	
    	// 정점의 개수, 간선의 개수 입력
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	// a, b, c 에서 시작해서 각 정점까지의 최단거리 배열 생성 및 초기화
    	st = new StringTokenizer(br.readLine());
    	int startA = Integer.parseInt(st.nextToken()) - 1;
    	int startB = Integer.parseInt(st.nextToken()) - 1;
    	int startC = Integer.parseInt(st.nextToken()) - 1;
    	int distanceA[] = new int[n];
    	int distanceB[] = new int[n];
    	int distanceC[] = new int[n];
    	Arrays.fill(distanceA, -1);
    	Arrays.fill(distanceB, -1);
    	Arrays.fill(distanceC, -1);
    	distanceA[startA] = 0;
    	distanceB[startB] = 0;
    	distanceC[startC] = 0;
    	
    	// 인접 리스트 양방향 그래프 생성 및 데이터 입력
    	ArrayList<Path>[] graph = new ArrayList[n];
    	for (int idx = 0; idx < n; idx++) {
    		graph[idx] = new ArrayList<>();
    	}
    	
    	for (int idx = 0; idx < m; idx++) {
    		st = new StringTokenizer(br.readLine());
    		int node1 = Integer.parseInt(st.nextToken()) - 1;
    		int node2 = Integer.parseInt(st.nextToken()) - 1;
    		int distance = Integer.parseInt(st.nextToken());
    		
    		graph[node1].add(new Path(node2, distance));
    		graph[node2].add(new Path(node1, distance));
    	}

    	// a에서 시작하는 다익스트라
    	PriorityQueue<Route> routesA = new PriorityQueue<>();
    	routesA.add(new Route(startA, 0));
    	while (!routesA.isEmpty()) {
    		Route currentRoute = routesA.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (currentDistance != distanceA[currentNode]) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			
    			if (distanceA[nextNode] == -1 ||
    					distanceA[nextNode] > distanceA[currentNode] + nextDistance) {
    				distanceA[nextNode] = distanceA[currentNode] + nextDistance;
    				routesA.add(new Route(nextNode, distanceA[nextNode]));
    			}
    		}
    	}
    	
    	// b에서 시작하는 다익스트라
    	PriorityQueue<Route> routesB = new PriorityQueue<>();
    	routesB.add(new Route(startB, 0));
    	while (!routesB.isEmpty()) {
    		Route currentRoute = routesB.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (currentDistance != distanceB[currentNode]) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			
    			if (distanceB[nextNode] == -1 ||
    					distanceB[nextNode] > distanceB[currentNode] + nextDistance) {
    				distanceB[nextNode] = distanceB[currentNode] + nextDistance;
    				routesB.add(new Route(nextNode, distanceB[nextNode]));
    			}
    		}
    	}
    	
    	// c에서 시작하는 다익스트라
    	PriorityQueue<Route> routesC = new PriorityQueue<>();
    	routesC.add(new Route(startC, 0));
    	while (!routesC.isEmpty()) {
    		Route currentRoute = routesC.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (currentDistance != distanceC[currentNode]) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.distance;
    			
    			if (distanceC[nextNode] == -1 ||
    					distanceC[nextNode] > distanceC[currentNode] + nextDistance) {
    				distanceC[nextNode] = distanceC[currentNode] + nextDistance;
    				routesC.add(new Route(nextNode, distanceC[nextNode]));
    			}
    		}
    	}
    	
    	// 각 정점까지의 거리중 가장 가까운 거리의 최댓값 계산
    	int maxDistance = 0;
    	for (int idx = 0; idx < n; idx++) {
    		int tmpDistance = Integer.MAX_VALUE; 
    		tmpDistance = Math.min(tmpDistance, distanceA[idx]);
    		tmpDistance = Math.min(tmpDistance, distanceB[idx]);
    		tmpDistance = Math.min(tmpDistance, distanceC[idx]);
    		maxDistance = Math.max(maxDistance, tmpDistance);
    	}
    	
    	System.out.println(maxDistance);
    }
}