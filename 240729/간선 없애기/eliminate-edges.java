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
	static int n, m;
	static ArrayList<Path>[] graph;
	static int[] distance, beforeNode;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	distance = new int[n];
    	beforeNode = new int[n];
    	
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

    	// 원래 그래프에서 최단거리 탐색
    	dijkstra();
    	
    	// 원래 최단거리 기록
    	int originDistance = distance[n - 1];
    	
    	// 최단거리 경로의 노드 저장
    	List<Integer> list = new ArrayList<>();
    	int currentNode = n - 1;
    	while (currentNode != 0) {
    		list.add(currentNode);
    		currentNode = beforeNode[currentNode];
    	}
    	list.add(0);

    	// 경로를 되돌아가면서 하나씩 경로를 제거해서 최단거리 탐색
    	int count = 0;
    	for (int i = list.size() - 1; i >= 1; i--) {
    		int x = list.get(i);
    		int y = list.get(i - 1);

    		// 기존의 경로 기억
    		Path originPath = null;
    		for (Path path : graph[x]) {
    			if (path.node == y) {
    				originPath = path;
    				break;
    			}
    		}
    		
    		// 경로 제거
    		graph[x].remove(originPath);
    		
    		// 최단거리 탐색 및 비교
    		dijkstra();
    		if (originDistance != distance[n - 1]) {
    			count++;
    		}
    		
    		// 경로 복원
    		graph[x].add(originPath);
		}
    	
    	System.out.println(count);
    }
    
    public static void dijkstra() {
    	Arrays.fill(distance, -1);
    	Arrays.fill(beforeNode, -1);
    	
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(0, 0));
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
    				pq.add(new Route(nextNode, sumDistance));
    			}
    		}
    	}
    }
}