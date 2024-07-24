import java.io.*;
import java.util.*;

class Path {
	int node, ditance;
	
	public Path(int node, int distance) {
		this.node = node;
		this.ditance = distance;
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
    	int x = Integer.parseInt(st.nextToken()) - 1;
    	
    	ArrayList<Path>[] graph = new ArrayList[n];
    	for (int i = 0; i < n; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	ArrayList<Path>[] rgraph = new ArrayList[n];
    	for (int i = 0; i < n; i++) {
    		rgraph[i] = new ArrayList<>();
    	}
    	
    	for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			
			graph[node1].add(new Path(node2, distance));
			rgraph[node2].add(new Path(node1, distance));
		}

    	int[] distance = new int[n];
    	Arrays.fill(distance, -1);
    	distance[x] = 0;
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(x, 0));
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (distance[currentNode] != currentDistance) continue;
    		
    		for (Path path : rgraph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.ditance;
    			int sumDistance = currentDistance + nextDistance;
    			
    			if (distance[nextNode] == -1 || distance[nextNode] > sumDistance) {
    				distance[nextNode] = sumDistance;
    				pq.add(new Route(nextNode, sumDistance));
    			}
    		}
    	}
    	
    	int[] backDistance = new int[n];
    	Arrays.fill(backDistance, -1);
    	backDistance[x] = 0;
    	PriorityQueue<Route> backpq = new PriorityQueue<>();
    	backpq.add(new Route(x, 0));
    	
    	while (!backpq.isEmpty()) {
    		Route currentRoute = backpq.poll();
    		int currentNode = currentRoute.node;
    		int currentDistance = currentRoute.distance;
    		
    		if (backDistance[currentNode] != currentDistance) continue;
    		
    		for (Path path : graph[currentNode]) {
    			int nextNode = path.node;
    			int nextDistance = path.ditance;
    			int sumDistance = currentDistance + nextDistance;
    			
    			if (backDistance[nextNode] == -1 || backDistance[nextNode] > sumDistance) {
    				backDistance[nextNode] = sumDistance;
    				backpq.add(new Route(nextNode, sumDistance));
    			}
    		}
    	}
    	
    	int max = 0;
    	for (int i = 0; i < n; i++) {
    		max = Math.max(max, distance[i] + backDistance[i]);
    	}
    	System.out.println(max);
    }
}