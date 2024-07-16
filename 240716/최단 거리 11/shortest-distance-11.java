import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int destination, distance;
	
	public Edge(int destination, int distance) {
		this.destination = destination;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(Edge other) {
		if(this.distance != other.distance) return this.distance - other.distance;
		return this.destination - other.destination;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 정점의 개수
		int m = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		ArrayList<Edge>[] graph = new ArrayList[n]; // [i]: i번 정점에서 이동할 수 있는 경로
		
		// 그래프 초기화
		for (int idx = 0; idx < n; idx++) {
			graph[idx] = new ArrayList<>();
		}
		
		// 그래프 생성
		for (int idx = 0; idx < m; idx++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			
			graph[node1].add(new Edge(node2, distance));
			graph[node2].add(new Edge(node1, distance));
		}
		
		// 그래프 정렬
		for (int idx = 0; idx < n; idx++) {
			Collections.sort(graph[idx]);
		}
		
		int[] minDistance = new int[n]; // [i]: 시작 위치에서 i번 정점까지의 최단거리
		int[] pathBefore = new int[n]; // [i]: i번 정점을 최단거리로 갈 때 i번 노드의 이전 정점
		
		// minDistance, pathBefore 초기화
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		Arrays.fill(pathBefore, -1);
		
		st = new StringTokenizer(br.readLine());
		int startNode = Integer.parseInt(st.nextToken()) - 1; // 시작 정점
		int endNode = Integer.parseInt(st.nextToken()) - 1; // 끝 정점
		
		PriorityQueue<Edge> pQueue = new PriorityQueue<>(); // 다음으로 이동할 최단 경로
		
		// 시작 정점 설정
		pQueue.add(new Edge(startNode, 0));
		minDistance[startNode] = 0;
		
		// 갈 수 있는 경로 중 최단 경로 순으로 선택하면서 갱신
		while(!pQueue.isEmpty()) {
			Edge currentEdge = pQueue.poll();
			int currentDestination = currentEdge.destination;
			int currentDistance = currentEdge.distance;
			
			// 최신 갱신 정보가 아닌 경우 무시
			if (currentDistance != minDistance[currentDestination]) continue;
			
			// 정점에서 이동할 수 있는 경로들 탐색
			for (Edge dest: graph[currentDestination]) {
				int nextDestination = dest.destination;
				int nextDistance = dest.distance;
				
				// 최단 경로 갱신이 가능한 경우에만 갱신
				if (minDistance[nextDestination] == Integer.MAX_VALUE ||
						minDistance[nextDestination] > minDistance[currentDestination] + nextDistance) {
					minDistance[nextDestination] = minDistance[currentDestination] + nextDistance;
					pathBefore[nextDestination] = currentDestination;
					pQueue.add(new Edge(nextDestination, minDistance[nextDestination]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(minDistance[endNode]).append("\n");
		
		Stack<Integer> stack = new Stack<>();
		int node = endNode;
		while (node != startNode) {
			for (Edge dest : graph[node]) {
				if (minDistance[dest.destination] + dest.distance == minDistance[node]) {
					stack.push(node);
					node = dest.destination;
					break;
				}
			}
		}
		stack.push(startNode);
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + 1).append(" ");
		}

        System.out.println(sb);
	}
}