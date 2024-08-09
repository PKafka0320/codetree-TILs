import java.io.*;
import java.util.*;

class Edge {
	int node, distance;
	
	public Edge(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}

public class Main {
	static int n;
	static List<Edge>[] graph;
	static boolean[] visited;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n];
		visited = new boolean[n];
		dist = new int[n];
		
		for (int idx = 0; idx < n; idx++) {
			graph[idx] = new ArrayList<>();
		}
		
		for (int idx = 0; idx < n - 1; idx++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			int distancde = Integer.parseInt(st.nextToken());
			
			graph[node1].add(new Edge(node2, distancde));
			graph[node2].add(new Edge(node1, distancde));
		}

		// 1번 정점으로부터 가장 멀리 있는 정점 정보를 찾습니다.
        int fVertex = FindLargestVertex(1).node;

        // farthest vertex로부터 가장 멀리 있는 정점 정보를 찾습니다.
        // 이때의 거리가 지름이 됩니다.
        int diameter = FindLargestVertex(fVertex).distance;

        System.out.print(diameter);
		
	}
	
	// 정점 x로부터 가장 멀리 있는 정점 정보를 찾아줍니다.
    public static Edge FindLargestVertex(int x) {
        // visited, dist 값을 초기화해줍니다.
        for(int i = 0; i < n; i++) {
            visited[i] = false;
            dist[i] = 0;
        }
        
        // 정점 x를 시작으로 하는 DFS를 진행합니다.
        visited[x] = true;
        dist[x] = 0;
        dfs(x, 0);
        
        // 정점 x로부터 가장 멀리 떨어진 정점 정보를 찾습니다.
        int farthestDist = -1;
        int farthestVertex = -1;
        for(int i = 0; i < n; i++) {
            if(dist[i] > farthestDist) {
                farthestDist = dist[i];
                farthestVertex = i;
            }
        }
    
        // 가장 멀리 떨어진 정점 번호와 그때의 거리를 반환합니다.
        return new Edge(farthestVertex, farthestDist);
    }
	
	public static void dfs(int node, int totalDist) {
		for (Edge edge : graph[node]) {
			int object = edge.node;
			int distance = edge.distance;
			
			if (!visited[object]) {
				visited[object] = true;
				dist[object] = totalDist + distance;
				dfs(object, distance + totalDist);
				
			}
		}
	}

}