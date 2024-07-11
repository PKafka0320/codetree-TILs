import java.io.*;
import java.util.*;

class Edge {
	int startNode, endNode, weight;
	
	public Edge(int startNode, int endNode, int weight) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.weight = weight;
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(tokenizer.nextToken()); // 정점의 개수
		int m = Integer.parseInt(tokenizer.nextToken()); // 간선의 개수
		
		int[][] graph = new int[n + 1][n + 1]; // [i][j]: i번 노드 -> j번 노드의 가중치
		boolean[] visited = new boolean[n + 1]; // [i]: i번 노드의 탐색 여부
		int[] dist = new int[n + 1]; // [i]: i번 노드까지의 최단 거리
		Edge[] edges = new Edge[n + 1]; // [i]: i번째 간선 정보
		
		// 그래프, 간선 정보 생성
		for (int idx = 0; idx < m; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int startNode = Integer.parseInt(tokenizer.nextToken()); // 시작 노드
			int endNode = Integer.parseInt(tokenizer.nextToken()); // 끝 노드
			int weight = Integer.parseInt(tokenizer.nextToken()); // 가중치
			
			edges[idx] = new Edge(startNode, endNode, weight);
			graph[startNode][endNode] = weight;
		}
		
		// 1번 노드에서 각 노드까지의 최단거리 초기화
		for (int node = 1; node <= n; node++) {
			dist[node] = 10 * n;
		}
		dist[1] = 0;
		
		// 최단거리 갱신
		for (int node = 1; node <= n; node++) {
			int minIndex = -1; // 최단거리인 노드 번호
			
			// 아직 방문하지 않은 정점 중 거리값이 가장 작은 노드 탐색
			for (int nextNode = 1; nextNode <= n; nextNode++) {
				if (visited[nextNode]) continue;
				
				if (minIndex == -1 || dist[minIndex] > dist[nextNode]) {
					minIndex = nextNode;
				}
			}
			
			// 최단거리 노드의 탐색 여부 갱신
			visited[minIndex] = true;
			
			// 해당 정점에 연결된 간선들을 보며 시작점으로부터의 최단거리 값을 갱신
			for (int nextNode = 1; nextNode <= n; nextNode++) {
				if (graph[minIndex][nextNode] == 0) continue;
				
				dist[nextNode] = Math.min(dist[nextNode], dist[minIndex] + graph[minIndex][nextNode]);
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (int node = 2; node <= n; node++) {
			if (!visited[node]) {
				answer.append("-1\n");
			}
			else {
				answer.append(dist[node]).append("\n");
			}
		}
		System.out.println(answer);
	}
}