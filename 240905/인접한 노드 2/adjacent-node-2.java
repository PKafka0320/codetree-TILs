import java.io.*;
import java.util.*;

public class Main{
	static int N, numbers[], parents[], dp[][];
	static List<Integer>[] edges;
	static List<Integer> select;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        edges = new ArrayList[N + 1];
        parents = new int[N + 1];
        dp = new int[N + 1][2];
        select = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
        	edges[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	
        	edges[node1].add(node2);
        	edges[node2].add(node1);
        }
        
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
        
        if (dp[1][0] > dp[1][1]) {
        	dfsSelect(1, 0);
        } else {
        	dfsSelect(1, 1);
        }
        
        Collections.sort(select);
        for (int i : select) {
        	System.out.printf("%d ", i);
        }
    }
    
    public static void dfsSelect(int node, int tp) {
    	if (tp == 1) {
    		select.add(node);
    	}
    	
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		
    		if (tp == 1) {
    			dfsSelect(ad, 0);
    		} else {
    			if (dp[ad][0] > dp[ad][1]) {
    				dfsSelect(ad, 0);
    			} else {
    				dfsSelect(ad, 1);
    			}
    		}
    	}
    }
    
    public static void dfs(int node) {
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		parents[ad] = node;
    		dfs(ad);
    	}
    	
    	dp[node][1] = numbers[node];
    	
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		dp[node][1] += dp[ad][0];
    		dp[node][0] += Math.max(dp[ad][0], dp[ad][1]);
    	}
    }
}

// import java.util.Scanner;
// import java.util.ArrayList;
// import java.util.Collections;

// public class Main {
//     public static final int MAX_N = 10000;
    
//     // 변수 선언:
//     public static int n;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] a = new int[MAX_N + 1];
//     public static int[][] dp = new int[MAX_N + 1][2];
//     public static ArrayList<Integer> ansPath = new ArrayList<>();
    
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

//     // 실제 DP의 값을 DFS2를 진행하여 역추적합니다.
//     public static void dfs2(int x, int tp) {
//         // tp가 1인 경우 x가 정답에 포함되어 있습니다. 정답 경로에 추가해줍니다.
//         if(tp == 1) 
//             ansPath.add(x);
    
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 이미 방문한 정점이라면 스킵해줍니다.
//             if(visited[y]) continue;
            
//             visited[y] = true;
    
//             // tp가 1인 경우 tp가 0인 서브트리가 최선이므로 그 방향으로 이동합니다.
//             if(tp == 1)
//                 dfs2(y, 0);
//             // tp가 0인 경우 자식노드들은 0과 1중 더 큰 값이 넘어가므로 해당 방향으로 이동합니다.
//             else {
//                 if(dp[y][0] > dp[y][1])
//                     dfs2(y, 0);
//                 else
//                     dfs2(y, 1);
//             }
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

//         // DFS를 다시 진행하며 DP 최적의 값이 나온 Path를 추적합니다.
//         for(int i = 1; i <= n; i++)
//             visited[i] = false;
        
//         // 최적의 답이 나오는 경로를 선택합니다
//         visited[1] = true;
//         if(dp[1][0] > dp[1][1])
//             dfs2(1, 0);
//         else
//             dfs2(1, 1);

//         // 경로를 오름차순으로 정렬해줍니다.
//         Collections.sort(ansPath);

//         // 루트 노드에서 얻을 수 있는 최댓값
//         System.out.println(Math.max(dp[1][0], dp[1][1]));
//         for(int i = 0; i < ansPath.size(); i++)
//             System.out.print(ansPath.get(i) + " ");
//     }
// }