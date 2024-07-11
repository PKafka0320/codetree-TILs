import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	int index, dist;
	
	public Node(int index, int dist) {
		this.index = index;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Node other) {
		return this.dist - other.dist;
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(tokenizer.nextToken()); // 정점의 개수
		int m = Integer.parseInt(tokenizer.nextToken()); // 간선의 개수
		int k = Integer.parseInt(reader.readLine()); // 시작 정점
		
		ArrayList<Node>[] graph = new ArrayList[n + 1]; // [i]: i번 노드와 연결된 노드 리스트
		PriorityQueue<Node> pQueue = new PriorityQueue<>(); // 거리순 노드
		int[] dists = new int[n + 1]; // [i]: i번 노드까지의 최단 거리
		
		for (int idx = 1; idx <= n; idx++) {
			graph[idx] = new ArrayList<>();
		}
		
		for (int idx = 1; idx <= m; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int startNode = Integer.parseInt(tokenizer.nextToken());
			int endNode = Integer.parseInt(tokenizer.nextToken());
			int dist = Integer.parseInt(tokenizer.nextToken());
			
			graph[startNode].add(new Node(endNode, dist));
			graph[endNode].add(new Node(startNode, dist));
		}
		
		// 최단거리값 초기화
		for (int idx = 1; idx <= n; idx++) {
			dists[idx] = 10 * (n + 1);
		}
		
		// 시작위치 초기화
		dists[k] = 0;
		pQueue.add(new Node(k, 0));
		
		while(!pQueue.isEmpty()) {
			int minDist = pQueue.peek().dist;
			int minIndex = pQueue.peek().index;
			pQueue.poll();
			
			// 같은 정점의 원소가 여러 번 들어가는 문제가 발생할 수 있어
            // minDist가 최신 dists[minIndex]값과 다르다면 무시
			if (minDist != dists[minIndex]) continue;
			
			// 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최단거리 값을 갱신
			for (int idx = 0; idx < graph[minIndex].size(); idx++) {
				int targetIndex = graph[minIndex].get(idx).index;
				int targetDist = graph[minIndex].get(idx).dist;
				
				// 현재 위치에서 연결된 간선으로 가는 것이 더 작다면
				// 값을 갱신해주고, 우선순위 큐에 해당 정보 추가
				int newDist = dists[minIndex] + targetDist;
				if (dists[targetIndex] > newDist) {
					dists[targetIndex] = newDist;
					pQueue.add(new Node(targetIndex, newDist));
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (int idx = 1; idx <= n; idx++) {
			if (dists[idx] == 10 * (n + 1)) {
				answer.append("-1\n");
			}
			else {
				answer.append(dists[idx]).append("\n");
			}
		}
		System.out.println(answer);
	}
}