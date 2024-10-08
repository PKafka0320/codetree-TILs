import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] edges;
	static int[] parent, dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		visited = new boolean[n + 1];
		dp = new int[n + 1];
		edges = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}
		
		int root = -1;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			if (parent[i] == -1) root = i;
			else {
				edges[parent[i]].add(i);
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			
			dp[node] += point;
		}
		
		dfs(root);
		
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			answer.append(dp[i]).append(" ");
		}
		System.out.println(answer);
	}
	
	public static void dfs(int node) {
		for (int next : edges[node]) {
			if (visited[next] || parent[node] == next) continue;
			visited[next] = true;
			parent[next] = node;
			dp[next] += dp[node];
			dfs(next);
		}
	}
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n, m;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static int[] dp = new int[MAX_N + 1];
    
//     // DFS를 통해 연결된 모든 정점을 순회합니다.
//     // 동시에 dp값을 계산해줍니다.
//     public static void dfs(int x) {
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 자신이 받은 정보를 자식노드에게도 전달합니다.
//             dp[y] += dp[x];
    
//             dfs(y);
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
        
//         // 입력:
//         n = sc.nextInt();
//         m = sc.nextInt();

//         for(int i = 1; i <= n; i++)
//             edges[i] = new ArrayList<>();
        
//         // n개의 간선 정보를 입력받습니다. (루트 노드인 1번 노드는 스킵합니다.)
//         for(int i = 1; i <= n; i++) {
//             int x = sc.nextInt();
//             int y = i;

//             if(x == -1) 
//                 continue;
            
//             // 간선 정보를 인접리스트에 넣어줍니다.
//             // x번 노드가 반드시 부모노드이므로,
//             // 여기서는 단방향 간선으로 연결했습니다.
//             edges[x].add(y);
//         }

//         // m번에 대해 전달받아야 할 정보를 입력받습니다.
//         for(int i = 1; i <= m; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();

//             // x번 노드가 전달받는 정보의 양에 y만큼 갱신합니다.
//             dp[x] += y;
//         }

//         // 루트를 시작으로 DFS를 진행하며 값을 갱신합니다.
//         dfs(1);

//         // 1번부터 n번 정점에 대해 받은 정보의 양을 출력합니다.
//         for(int i = 1; i <= n; i++)
//             System.out.print(dp[i] + " ");
//     }
// }