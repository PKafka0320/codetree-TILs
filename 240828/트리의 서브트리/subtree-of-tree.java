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
		int r = Integer.parseInt(st.nextToken()) - 1;
		int q = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		visited = new boolean[n];
		dp = new int[n];
		edges = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		
		visited[r] = true;
		dfs(r);
		
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int root = Integer.parseInt(br.readLine()) - 1;
			answer.append(dp[root]).append("\n");
		}
		System.out.println(answer);
	}
	
	public static void dfs(int node) {
		for (int next : edges[node]) {
			if (visited[next] || parent[node] == next) continue;
			visited[next] = true;
			parent[next] = node;
			dfs(next);
		}
		
		dp[node] = 1;
		for (int next : edges[node]) {
			if (parent[node] == next) continue;
			dp[node] += dp[next];
		}
	}
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n, r, q;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] size = new int[MAX_N + 1];
    
//     // DFS를 통해 연결된 모든 정점을 순회합니다.
//     // 동시에 dp값을 계산해줍니다.
//     public static void dfs(int x) {
//         size[x] = 1;
    
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 만약 y번 정점을 이미 방문했다면 스킵합니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);
    
//             // 자신의 자식 노드의 크기를 전부 더해 저장합니다.
//             size[x] += size[y];
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();
//         r = sc.nextInt();
//         q = sc.nextInt();

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

//         // 루트를 시작으로 DFS를 진행하며 값을 갱신합니다.
//         visited[r] = true;
//         dfs(r);

//         // q개의 질의에 대해 올바른 답을 출력합니다.
//         while(q-- > 0) {
//             int x = sc.nextInt();

//             // x번 노드에 대해, x번 노드를 루트로 하는
//             // 서브트리의 크기를 출력합니다.
//             System.out.println(size[x]);
//         }
//     }
// }