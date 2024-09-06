import java.io.*;
import java.util.*;

public class Main {
	static int N, K, numbers[], dp[][][];
	static boolean  visited[];
	static List<Integer>[] edges;
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        /* input & initialize */
        
        N = Integer.parseInt(br.readLine());
        edges = new ArrayList[N+1];
        numbers = new int[N+1];
        visited = new boolean[N+1];
        
        for (int i = 1; i <= N; i++) {
        	edges[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N - 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	
        	edges[node1].add(node2);
        	edges[node2].add(node1);
        }
        
        for (int i = 1; i <= N; i++) {
        	numbers[i] = Integer.parseInt(br.readLine());
        }
        
        K = Integer.parseInt(br.readLine());
        dp = new int[N+1][K+1][2];
        
        /* logic */
        
        visited[1] = true;
        dfs(1);
        
        int ans = 0;
        for(int i = 1; i <= K; i++) {
            ans = Math.max(ans, dp[1][i][0]);
            ans = Math.max(ans, dp[1][i][1]);
        }
        System.out.println(ans);
    }
    
    public static void dfs(int node) {
    	int leftNum = 0;
    	int rightNum = 0;
    	
    	for (int ad : edges[node]) {
    		if (visited[ad]) continue;
    		visited[ad] = true;
    		dfs(ad);
    		
    		if (leftNum == 0) {
    			leftNum = ad;
    		} else {
    			rightNum = ad;
    		}
    	}
    	
    	dp[node][1][1] = numbers[node];
    	dp[node][0][0] = 0;
    	
    	if (leftNum != 0 && rightNum != 0) {
    		for (int i = 1; i <= K; i++) {
    			for (int j = 0; j < i; j++) {
    				dp[node][i][1] = Math.max(dp[node][i][1], 
    						dp[leftNum][j][0] + dp[rightNum][i - j - 1][0] + numbers[node]);
    			}
    		}
    		
    		for (int i = 0; i <= K; i++) {
    			for (int j = 0; j <= i; j++) {
    				dp[node][i][0] = Math.max(dp[node][i][0], 
    						Math.max(dp[leftNum][j][0], dp[leftNum][j][1]) +
    						Math.max(dp[rightNum][i - j][0], dp[rightNum][i - j][1]));
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
//         int leftNum = 0;
//         int rightNum = 0;
    
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 이미 방문한 정점이라면 스킵해줍니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);
    
//             if(leftNum == 0) 
//                 leftNum = y;
//             else 
//                 rightNum = y;
//         }
    
//         // 현재 노드를 최초로 색칠하는 경우에 대한 초기조건입니다.
//         dp[x][1][1] = a[x];
//         // 현재 노드를 칠하지 않는 경우에 대한 초기조건입니다.
//         dp[x][0][0] = 0;
    
//         // 만약 자식 노드가 있다면, dp값들을 점화식에 따라 채워줍니다.
//         if(leftNum != 0 && rightNum != 0) {
//             // x번 노드를 색칠하는 경우이며 동시에 총 i개의 노드가 색칠되었기를 바라는 순간이라면
//             // 왼쪽 서브트리에서 j개의 노드를 칠했으며 동시에 왼쪽 노드 자체는 색칠이 되지 않아야 하고
//             // 오른쪽 서브트리에서는 i - j - 1개의 노드가 칠해져야하며 동시에 왼쪽 노드 자체는 색칠이 되지 않아야 합니다.
//             for(int i = 1; i <= k; i++)
//                 for(int j = 0; j < i; j++)
//                     dp[x][i][1] = Math.max(dp[x][i][1], dp[leftNum][j][0] + dp[rightNum][i - j - 1][0] + a[x]);
    
//             // x번 노드를 색칠하지 않는 경우이며 동시에 총 i개의 노드가 색칠되었기를 바라는 순간이라면
//             // 왼쪽 서브트리에서 j개의 노드를 칠했으며 동시에 왼쪽 노드 자체는 색칠이 되던 말던 상관없고
//             // 오른쪽 서브트리에서는 i - j개의 노드를 칠했으며 동시에 왼쪽 노드 자체는 색칠이 되던 말던 상관이 없습니다.
//             for(int i = 0; i <= k; i++)
//                 for(int j = 0; j <= i; j++)
//                     dp[x][i][0] = Math.max(dp[x][i][0], 
//                                       Math.max(dp[leftNum][j][0], dp[leftNum][j][1]) + 
//                                       Math.max(dp[rightNum][i - j][0], dp[rightNum][i - j][1])
//                                   );
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