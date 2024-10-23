import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, cost[][], dist[];
	static boolean visited[];
	static int INF = (int)1e9;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cost = new int[N+1][N+1];
		dist = new int[N+1];
		visited = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) {
			dist[i] = INF;
			for (int j = 0; j <= N; j++) {
				cost[i][j] = INF;
			}
			cost[i][i] = 0;
		}
		
		for (int i = 1; i < N; i++) {
			cost[i][(i+1)%(N+1)] = 0;
			cost[(i+1)%(N+1)][i] = 0;
		}
		cost[1][N] = 0;
		cost[N][1] = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[0][i] = cost[i][0] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			cost[node1][node2] = cost[node2][node1] = INF;
		}
		
		dist[1] = 0;
		int answer = 0;
		
		for (int i = 0; i <= N; i++) {
			int minIndex = -1;
			
			for (int j = 0; j <= N; j++) {
				if (visited[j]) continue;
				
				if (minIndex == -1 || dist[minIndex] > dist[j]) {
					minIndex = j;
				}
			}
			
			visited[minIndex] = true;
			answer += dist[minIndex];
			
			if (allChecked()) break;
			
			for (int j = 0; j <= N; j++) {
				dist[j] = Math.min(dist[j], cost[minIndex][j]);
			}
		}
		
		System.out.println(answer <= K ? 1 : -1);
	}
	
	public static boolean allChecked() {
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) return false;
		}
		return true;
	}
}