import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] map;
	static boolean visited[];
	static int startNode, answer;
	
	public static void main(String[] args) throws Exception {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        map = new ArrayList[n];
        for (int i = 0; i < n; i++) {
        	map[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken()) - 1;
        	int node2 = Integer.parseInt(st.nextToken()) - 1;
        	
        	map[node1].add(node2);
        	map[node2].add(node1);
        }
        
        // DFS를 통해 가장 먼 노드 탐색
        startNode = 0;
        answer = 0;
        visited = new boolean[n];
        visited[startNode] = true;
        dfs(startNode, 0);
        
        // 가장 먼 노드에서 시작해 다시 한번 DFS를 돌려 트리의 가장 긴 거리 계산
        answer = 0;
        visited = new boolean[n];
        visited[startNode] = true;
        dfs(startNode, 0);
        
        // 거리의 중간값 출력
        System.out.println((answer + 1) / 2);
	}
	
	public static void dfs(int node, int dist) {
		boolean isLeaf = true;
		
		for (int nextNode : map[node]) {
			if (visited[nextNode]) continue;
			visited[nextNode] = true;
			isLeaf = false;
			dfs(nextNode, dist + 1);
		}
		
		if (isLeaf) {
			if (answer < dist) {
				answer = dist;
				startNode = node;
			}
		}
	}
	
}