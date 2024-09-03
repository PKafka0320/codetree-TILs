import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] numbers, parents;
	static int[][] dp;
	static boolean[] visited;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 정점의 개수
		numbers = new int[n + 1]; // [i]: i번 정점의 숫자
		parents = new int[n + 1]; // [i]: i번 정점의 부모 정점의 번호
		visited = new boolean[n + 1]; // [i] : i번 정점의 방문 여부
		edges = new ArrayList[n + 1]; // [i]: i번 정점과 연결되는 정점의 번호들
		dp = new int[n + 1][2]; // [i][j]: i번 정점의 선택 여부에 따른 숫자의 합 (j=0: 미선택, j=1: 선택) 
		
		/* 입력 및 초기화 */
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		
		visited[1] = true;
		dfs(1);
		
		System.out.println(dp[1][0] > dp[1][1] ? dp[1][0] : dp[1][1]);
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			parents[ad] = node;
			visited[ad] = true;
			dfs(ad);
		}
		
		// 정점을 선택하는 경우 다른 인접한 정점들은 무조건 선택되지 않아야 한다.
		dp[node][1] = numbers[node]; // 정점을 선택한 경우의 숫자를 초기화
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			dp[node][0] += Math.max(dp[ad][1], dp[ad][0]);
			dp[node][1] += dp[ad][0];
		}
	}
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 10000;
    
//     // 변수 선언:
//     public static int n;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] a = new int[MAX_N + 1];
//     public static int[][] dp = new int[MAX_N + 1][2];
    
//     // 1번 노드를 시작으로 DFS를 진행하며 값을 갱신합니다.
//     // dp[i][j] = i번 노드를 루트로 하는 서브트리에서
//     // (j == 1일 때) i번 노드를 선택했을때의 값의 합의 최댓값
//     // (j == 0일 때) i번 노드를 선택하지 않았을 때의 값의 합의 최댓값
//     public static void dfs(int x) {
//         // x번 노드가 포함된 경우 합은 a[x]가 포함됩니다.
//         dp[x][1] = a[x];
    
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 이미 방문한 정점이라면 스킵해줍니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);
    
//             // x번 노드가 포함된 경우 y번 노드는 포함될 수 없습니다.
//             // x번 노드가 포함되지 않은 경우 y번 노드는 포함될 수도,
//             // 포함되지 않을 수도 있습니다.
//             dp[x][1] += dp[y][0];
//             dp[x][0] += Math.max(dp[y][0], dp[y][1]);
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // n개의 노드에 적힌 값을 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             a[i] = sc.nextInt();

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

//         // 1번 노드를 시작으로 DFS를 진행하며 값을 갱신합니다.
//         // dp[i][j] = i번 노드를 루트로 하는 서브트리에서
//         // (j == 1일 때) i번 노드를 선택했을때의 값의 합의 최댓값
//         // (j == 0일 때) i번 노드를 선택하지 않았을 때의 값의 합의 최댓값
//         visited[1] = true;
//         dfs(1);

//         // 루트 노드에서 얻을 수 있는 최댓값
//         System.out.print(Math.max(dp[1][0], dp[1][1]));
//     }
// }