import java.io.*;
import java.util.*;

public class Main {
	static int parents[];
	static long dp[][][];
	static boolean visited[];
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* input & initialize */
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		parents = new int[N+1];
		dp = new long[N+1][2][2];
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		
		/* logic */
		visited[1] = true;
		dfs(1);
		
		System.out.print(Math.min(dp[1][1][0], dp[1][1][1]));
	}
	
	public static void dfs(int node) {
		
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			visited[ad] = true;
			parents[ad] = node;
			dfs(ad);
		}
		
		dp[node][0][0] = 0;
        dp[node][1][0] = 0;
        dp[node][1][1] = 1;
		
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			dp[node][0][0] += dp[ad][1][0];
			dp[node][1][0] += Math.min(dp[ad][1][1], dp[ad][1][0]);
			dp[node][1][1] += Math.min(Math.min(dp[ad][0][0], dp[ad][1][0]), dp[ad][1][1]);
		}
		
		if(dp[node][1][0] == 0) {
			dp[node][1][0] = (int)1e9;
		}
        else {
            long best = (int)1e9;
            
            for(int ad : edges[node]) {
                if(parents[ad] != node) continue;
                
                best = Math.min(best, dp[node][1][0] - Math.min(dp[ad][1][1], dp[ad][1][0]) + dp[ad][1][1]);
            }
    
            dp[node][1][0] = best;
        }
	}

}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] parent = new int[MAX_N + 1];
    
//     // dp[i][j][k] : i번 노드의 서브트리까지 고려했을 때
//     //               j = 0이면 아직 i번째 노드는 해결이 안된 상황
//     //               j = 1이면 i번째 노드까지 완벽히 해결된 상황
//     //               k = 0이면 i번째 노드에 물건을 놓지 않은 상황
//     //               k = 1이면 i번째 노드에 물건을 놓은 상황
//     //               이라 했을 때 놓아야 하는 최소 물건 개수
//     public static long[][][] dp = new long[MAX_N + 1][2][2];
    
//     // DFS를 통해 연결된 모든 정점을 순회합니다.
//     // 동시에 dp값을 계산해줍니다.
//     public static void dfs(int x) {
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
//             if(!visited[y]) {
//                 visited[y] = true;
//                 parent[y] = x;
//                 dfs(y);
//             }
//         }
    
//         // 이제 퇴각하기 전에
//         // 각각의 자식들을 다시 순회하며 
//         // dp[x]값을 갱신해줍니다.
//         dp[x][0][0] = 0;
//         dp[x][1][0] = 0;
//         dp[x][1][1] = 1;
    
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // y가 x의 자식이 아니라면 패스합니다.
//             if(parent[y] != x)
//                 continue;
    
//             // Case 1. 
//             // (x, 0, 0) 상황이 되려면
//             // 자식들은 해결이 끝났고, 각 위치에 물건을 놓은 적이 없는 상황이어야 합니다.
//             dp[x][0][0] += dp[y][1][0];
    
//             // Case 2.
//             // (x, 1, 0) 상황이 되려면
//             // 자식들은 전부 해결이 되었으면서 (j = 1)
//             // 최소 (y, 1, 1)에 해당하는 자식이 하나 이상 있어야 합니다.
//             // 우선 최솟값을 전부 더해줍니다.
//             dp[x][1][0] += Math.min(dp[y][1][1], dp[y][1][0]);
    
//             // Case 3. 
//             // (x, 1, 1) 상황이 되려면
//             // 자식들의 j, k가 전혀 상관이 없습니다.
//             dp[x][1][1] += Math.min(Math.min(dp[y][0][0], dp[y][1][0]), dp[y][1][1]);
//         }
    
//         // 불가능한 경우에는 
//         // 불가능의 표시로 큰 값을 넣어줍니다.
//         if(dp[x][1][0] == 0)
//             dp[x][1][0] = (int)1e9;
//         else {
//             // 그렇지 않은 경우에는 
//             // (x, 1, 0) case에 대해 
//             // 최소 (y, 1, 1)에 해당하는 자식이 하나 이상 나오도록 하기 위해
//             // 각각의 케이스 중 최적의 답을 계산해봅니다.
//             long best = (int)1e9;
//             for(int i = 0; i < edges[x].size(); i++) {
//                 int y = edges[x].get(i);
    
//                 // y가 x의 자식이 아니라면 패스합니다.
//                 if(parent[y] != x)
//                     continue;
    
//                 // y 노드에 대해 (y, 1, 1)를 선택했을 때의 경우를 계산해
//                 // 가능한 최솟값을 계산해줍니다. 
//                 best = Math.min(best, dp[x][1][0] - Math.min(dp[y][1][1], dp[y][1][0]) + dp[y][1][1]);
//             }
    
//             // (x, 1, 0) 값을 갱신해줍니다.
//             dp[x][1][0] = best;
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

//         // 1번 정점을 루트로 하는 트리를 만들어 문제를 해결합니다.
//         // 1번 정점을 시작으로 DFS를 진행하며 dp값을 갱신합니다.
//         visited[1] = true;
//         dfs(1);

//         // dp[1][1][0], dp[1][1][1] 중 최솟값을 출력합니다.
//         System.out.print(Math.min(dp[1][1][0], dp[1][1][1]));
//     }
// }