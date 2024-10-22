import java.io.*;
import java.util.*;

public class Main {
	static int N, cost[], conCost[][], dist[];
	static boolean visited[];
	static int INF = (int) 1e9;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N];
		conCost = new int [N][N];
		dist = new int[N];
		visited = new boolean[N];
		int answer = INF;
		
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(br.readLine());
			dist[i] = INF;
			answer = Math.min(answer, cost[i]);
		}
		
		for (int j = 0; j < N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				conCost[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		dist[0] = 0;
		
		for (int i = 0; i < N; i++) {
			int minIndx = -1;
			
			for (int j = 0; j < N; j++) {
				if (visited[j]) continue;
				
				if (minIndx == -1 || dist[minIndx] > dist[j]) {
					minIndx = j;
				}
			}
			
			visited[minIndx] = true;
			
			answer += dist[minIndx];
			
			for (int j = 0; j < N; j++) {
				if (conCost[minIndx][j] == 0) continue;
				
				dist[j] = Math.min(dist[j], conCost[minIndx][j]);
			}
		}
		
		System.out.println(answer);
	}
}