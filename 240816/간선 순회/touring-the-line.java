import java.io.*;
import java.util.*;

class Path {
    int node, distance;
    
    public Path(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class Main {
    static List<Path>[] graph;
    static boolean[] visited;
    static int minDist, maxCnt, startNode;
	
	public static void main(String[] args) throws Exception {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());
            
            graph[node1].add(new Path(node2, distance));
            graph[node2].add(new Path(node1, distance));
        }
        
        startNode = 0;
        minDist = Integer.MAX_VALUE;
        maxCnt = 0;
        visited = new boolean[n];
        visited[startNode] = true;
        dfs(startNode, 0, 0);
//        System.out.printf("to node %d, path count: %d, distance: %d%n", startNode, maxCnt, minDist);
        
        minDist = Integer.MAX_VALUE;
        maxCnt = 0;
        visited = new boolean[n];
        visited[startNode] = true;
        dfs(startNode, 0, 0);
//        System.out.printf("to node %d, path count: %d, distance: %d%n", startNode, maxCnt, minDist);
        
        if (minDist % d == 0) {
            System.out.println(minDist / d);
        } else {
            System.out.println((minDist / d) + 1);
        }
        
	}
	
	public static void dfs(int node, int dist, int cnt) {
	    boolean isLeaf = true;
	    
	    for (Path path : graph[node]) {
	        int nextNode = path.node;
	        int distance = path.distance;
	        if (visited[nextNode]) continue;
	        
	        visited[nextNode] = true;
	        isLeaf = false;
	        dfs(nextNode, dist + distance, cnt + 1);
	    }
	    
	    if (isLeaf) {
	        if (maxCnt < cnt) {
	            startNode = node;
	            maxCnt = cnt;
	            minDist = dist;
	        }
	        else if (maxCnt == cnt && minDist > dist) {
	            startNode = node;
	            minDist = dist;
	        }
	    }
	}
	
}