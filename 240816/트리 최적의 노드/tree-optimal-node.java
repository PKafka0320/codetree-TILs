import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] map;
	static boolean visited[];
	static int answer, tmpans;
	
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
        
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
        	tmpans = 0;
        	visited = new boolean[n];
        	visited[i] = true;
        	dfs(i, 0);
        	answer = Math.min(answer, tmpans);
        }
        
        System.out.println(answer);
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
			if (tmpans < dist) {
				tmpans = dist;
			}
		}
	}
	
}