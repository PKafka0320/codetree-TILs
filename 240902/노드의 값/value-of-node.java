import java.io.*;
import java.util.*;

public class Main {
	static int n, result;
	static int[] numbers, dp;
	static boolean[] visited;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		numbers = new int[n + 1];
		visited = new boolean[n + 1];
		dp = new int[n + 1];
		edges = new ArrayList[n + 1];
		
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
		
		System.out.println(result);
	}
	
	public static void dfs(int node) {
		dp[node] = numbers[node];
		
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			
			visited[ad] = true;
			dfs(ad);
			
			dp[node] += dp[ad] - 1;
		}
		
		result += Math.abs(dp[node] - 1);
	}
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 10000;
    
//     // 변수 선언:
//     public static int n;
//     public static int[] a = new int[MAX_N + 1];
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
    
//     public static int[] dp = new int[MAX_N + 1]; // dp[i] : i를 루트로 하는 서브트리를 전부 처리했을 때 최종적으로 i가 가지게 되는 값
    
//     public static int ans;
    
//     // DFS를 통해 연결된 모든 정점을 순회합니다.
//     // 자식부터 그리디하게 1로 만들어주는게 최선입니다.
//     public static void dfs(int x) {
//         // dp값은 주어진 초기값이 됩니다.
//         dp[x] = a[x];
    
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 만약 y번 정점을 이미 방문했다면 스킵합니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);
    
//             // 자식들의 값들 전부 더해줍니다.
//             // 이때 자식들은 1만 남기고 가져와줍니다.
//             dp[x] += dp[y] - 1;
//         }
    
//         // dp[x]를 1로 만들기 위해 필요한 비용을 계산합니다.
//         ans += Math.abs(dp[x] - 1);
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // 초기값들이 주어집니다.
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

//         // 루트를 시작으로 DFS를 진행하며 값을 갱신합니다.
//         visited[1] = true;
//         dfs(1);

//         System.out.print(ans);
//     }
// }