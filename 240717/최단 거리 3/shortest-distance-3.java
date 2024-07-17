import java.io.*;
import java.util.*;

class Edge {
	int node, distance;
	
	public Edge(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
	
	public String toString() {
		return node+":"+distance;
	}
}

class Position implements Comparable<Position> {
	int node, distance;
	
	public Position(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(Position other) {
		return this.distance - other.distance;
	}
	
	public String toString() {
		return node+":"+distance;
	}
}

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
		/* 데이터 생성 및 입력 */
    	// 점점의 개수, 간선의 개수 입력
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	// 인접 리스트로 그래프 생성, (데이터: 연결된 정점과 해당 정점과의 거리를 가지는 Edge 클래스)
    	ArrayList<Edge>[] graph = new ArrayList[n];
    	for (int idx = 0; idx < n; idx++) {
    		graph[idx] = new ArrayList<>();
    	}
    	
    	// 출발 정점에서 모든 정점으로의 최단거리 배열 생성 및 최댓값으로 초기화
    	int[] minDistance = new int[n];
    	Arrays.fill(minDistance, Integer.MAX_VALUE);
    	
    	// 그래프 데이터 입력 (양방향)
    	for (int count = 0; count < m; count++) {
    		st = new StringTokenizer(br.readLine());
    		int node1 = Integer.parseInt(st.nextToken()) - 1;
    		int node2 = Integer.parseInt(st.nextToken()) - 1;
    		int distance = Integer.parseInt(st.nextToken());
    		
    		graph[node1].add(new Edge(node2, distance));
    		graph[node2].add(new Edge(node1, distance));
    	}
    	
    	// 시작 정점과 도착 정점 입력
    	st = new StringTokenizer(br.readLine());
    	int startNode = Integer.parseInt(st.nextToken()) - 1;
    	int endNode = Integer.parseInt(st.nextToken()) - 1;
    	
		/* 다익스트라 */
    	// 최단 거리 우선으로 확인하기 위해 우선순위 큐 생성 (데이터: 정점과 그 정점까지의 최단거리를 가지는 Position 클래스)
    	PriorityQueue<Position> positions = new PriorityQueue<>();
    	
    	// 큐에 시작 지점 추가 및 시작 지점까지의 최단거리를 0으로 초기화
    	positions.add(new Position(startNode, 0));
    	minDistance[startNode] = 0;
    	
    	// 큐가 빌 때까지,
    	while (!positions.isEmpty()) {
    		// 큐에서 하나 꺼내고,
    		Position currentPosition = positions.poll();
    		int currentNode = currentPosition.node;
    		int currentDistance = currentPosition.distance;
    		
    		// 해당 정보의 최단거리가 실제 최단거리와 동일하지 않다면 무시
    		if (minDistance[currentNode] != currentDistance) continue;
    		
    		// 정점에서 연결된 다른 정점들을 순회하며
    		for (Edge edge : graph[currentNode]) {
    			int nextNode = edge.node;
    			int nextDistance = edge.distance;
    			
    			// 다른 정점으로의 실제 최단거리가 초기값이거나, 정점으로의 실제 최단거리와 다른 정점으로의 거리의 합보다 크다면,
    			if (minDistance[nextNode] == Integer.MAX_VALUE ||
    					minDistance[nextNode] > minDistance[currentNode] + nextDistance) {
    				// 다른 정점으로의 실제 최단거리 갱신, 최단거리 큐에 추가
    				minDistance[nextNode] = minDistance[currentNode] + nextDistance;
    				positions.add(new Position(nextNode, minDistance[nextNode]));
    			}
    			
    		}
		}
    	
    	// 도착 정점으로의 최단거리 출력
    	System.out.println(minDistance[endNode]);
    }
}