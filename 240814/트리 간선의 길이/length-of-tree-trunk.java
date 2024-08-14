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
	static List<Edge>[] tree;
	static int max = 0;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
        	tree[i] = new ArrayList<>();
        }
        
        // 트리 생성
        for (int i = 0; i < n - 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int node1 = Integer.parseInt(st.nextToken()) - 1;
        	int node2 = Integer.parseInt(st.nextToken()) - 1;
        	int distance = Integer.parseInt(st.nextToken());
        	
        	tree[node1].add(new Edge(node2, distance));
        	tree[node2].add(new Edge(node1, distance));
        }
        
        for (int i = 0; i < n; i++) {
        	dfs(i, new boolean[n], 0);
        }
        
        System.out.println(max);
    }
    
    public static void dfs(int node, boolean[] visited, int dist) {
    	visited[node] = true;
    	
    	boolean isEnd = true;
    	for (Edge edge : tree[node]) {
    		int nextNode = edge.node;
    		int nextDist = edge.distance;
    		if (visited[nextNode]) continue;
    		
    		isEnd = false;
    		dfs(nextNode, visited, dist + nextDist);
    	}
    	
    	if (isEnd) {
    		max = Math.max(max, dist);
    	}
    	
    }
    
}