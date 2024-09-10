import java.io.*;
import java.util.*;

public class Main {
	static int N, K, numbers[], parents[];
	static long dp[][][];
	static List<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* input & initialize */
		N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		parents = new int[N+1];
		graph = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			graph[node1].add(node2);
			graph[node2].add(node1);
		}
		
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		K = Integer.parseInt(br.readLine());
		dp = new long[N+1][K+1][2];
		
		/* logic */
		dfs(1);
		
		long result = 0;
		for (int i = 1; i <= K; i++) {
			result = Math.max(result, dp[1][i][0]);
			result = Math.max(result, dp[1][i][1]);
		}
		System.out.println(result);
	}
	
	public static void dfs(int node) {
		for (int ad : graph[node]) {
			if (parents[node] == ad) continue;
			parents[ad] = node;
			dfs(ad);
		}
		
		dp[node][1][1] = numbers[node];
		dp[node][0][0] = 0;
		
		for (int ad : graph[node]) {
			if (parents[node] == ad) continue;
			
			for (int k = K; k >= 1; k--) {
				for (int prevk = 1; prevk <= k; prevk++) {
					dp[node][k][1] = Math.max(dp[node][k][1], dp[node][k - prevk][1] + dp[ad][prevk][0]);
				}
			}
			
			for (int k = K; k >= 0; k--) {
				for (int prevk = 0; prevk <= k; prevk++) {
					dp[node][k][0] = Math.max(
							dp[node][k][0], 
							dp[node][k - prevk][0] + Math.max(dp[ad][prevk][0], dp[ad][prevk][1]));
				}
			}
		}
	}
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_K = 10;
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n, k;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] a = new int[MAX_N + 1];
//     public static int[][][] dp = new int[MAX_N + 1][MAX_K + 1][2];
//     public static int ans;
    
//     // 1번 노드를 시작으로 DFS를 진행하며 값을 갱신합니다.
//     // dp[x][i][j] = x번 노드를 루트로 하는 서브트리에서 i개를 색칠했을 때의 최댓값
//     // (j == 1일 경우 x번 노드를 색칠, j == 0일 경우 x번 노드를 색칠하지 않음)
//     public static void dfs(int x) {
//         // 현재 노드를 최초로 색칠하는 경우에 대한 초기조건입니다.
//         dp[x][1][1] = a[x];
//         // 현재 노드를 칠하지 않는 경우에 대한 초기조건입니다.
//         dp[x][0][0] = 0;
    
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int l = 0; l < edges[x].size(); l++) {
//             int y = edges[x].get(l);
    
//             // 이미 방문한 정점이라면 스킵해줍니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);

//             // dp값들을 점화식에 따라 채워줍니다.

//             // x번 노드를 색칠하는 경우이며 동시에 총 i개의 노드가 색칠되었기를 바라는 순간이라면
//             // 지금까지의 자식들 가지고는 i - j개의 노드를 칠했으며 x번 노드 자체는 색칠이 되어야 하고
//             // 자식 노드 y에서 정확히 j개의 노드를 칠하고 동시에 y번 노드 자체는 색칠이 되지 않아야 합니다.
//             for(int i = k; i >= 1; i--)
//                 for(int j = 1; j <= i; j++)
//                     dp[x][i][1] = Math.max(dp[x][i][1], dp[x][i - j][1] + dp[y][j][0]);

//             // x번 노드를 색칠하지 않은 경우이며 동시에 총 i개의 노드가 색칠되었기를 바라는 순간이라면
//             // 지금까지의 자식들 가지고는 i - j개의 노드를 칠했으며 x번 노드 자체는 색칠이 되지 않아야 하고
//             // 자식 노드 y에서 정확히 j개의 노드를 칠하고 동시에 y번 노드 자체는 색칠이 되던 말던 상관이 없습니다.
//             for(int i = k; i >= 0; i--)
//                 for(int j = 0; j <= i; j++)
//                     dp[x][i][0] = Math.max(dp[x][i][0], 
//                                            dp[x][i - j][0] + 
//                                            Math.max(dp[y][j][0], dp[y][j][1]));
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         // 입력:
//         n = sc.nextInt();

//         for(int i = 1; i <= n; i++)
//             edges[i] = new ArrayList<>();

//         // n - 1개의 간선 정보를 입력받습니다.
//         for(int i = 1; i <= n - 1; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();
            
//             // 간선 정보를 인접리스트에 넣어줍니다.
//             edges[x].add(y);
//             edges[y].add(x);
//         }

//         // n개의 노드에 적힌 값을 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             a[i] = sc.nextInt();

//         k = sc.nextInt();

//         // 1번 노드를 시작으로 DFS를 진행하며 값을 갱신합니다.
//         visited[1] = true;
//         dfs(1);

//         // 모든 dp의 값 중 최댓값을 출력합니다.
//         // 최대 i개의 노드를 색칠하는 경우를 전부 탐색합니다.
//         for(int i = 1; i <= k; i++) {
//             ans = Math.max(ans, dp[1][i][0]);
//             ans = Math.max(ans, dp[1][i][1]);
//         }

//         System.out.print(ans);
//     }
// }