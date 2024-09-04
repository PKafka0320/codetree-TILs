import java.io.*;
import java.util.*;

public class Main{
	static List<Integer>[] edges;
	static int n, numbers[], parents[], dp[], max[];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        
        edges = new ArrayList[n + 1]; // [i]: i번 정점과 연결된 정점 번호
        numbers = new int[n + 1]; // [i]: i번 정점의 숫자
        parents = new int[n + 1]; // [i]: i번 정점의 부모 정점 번호
        dp = new int[n + 1]; // [i]: i번 정점을 루트로 하는 서브트리에서 i번 정점을 끝으로 하는 경로 중 최대 합에 해당하는 경로
        max = new int[n + 1]; // [i]: i번 정점을 지나는 경로 중 가장 긴 경로의 최대 합
        
        /* 입력  */
        for (int i = 1; i <= n; i++) {
        	edges[i] = new ArrayList<>();
        }
        
        for (int i = 0 ; i < n - 1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	
        	edges[node1].add(node2);
        	edges[node2].add(node1);
        }
        
        for (int i = 1; i <= n; i++) {
        	numbers[i] = Integer.parseInt(br.readLine());
        }
        
        dfs(1);
        
        int result = -1000;
        for (int i = 1; i <= n; i++) {
        	result = Math.max(result, max[i]);
        }
        System.out.println(result);
    }
    
    public static void dfs(int node) {
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		parents[ad] = node;
    		dfs(ad);
    	}

    	dp[node] = numbers[node];
    	max[node] = numbers[node];
    	int maxdp = 0;
    	for (int ad : edges[node]) {
    		if (parents[node] == ad) continue;
    		maxdp = Math.max(maxdp, dp[ad]);
    		max[node] += Math.max(0, dp[ad]);
    	}
    	dp[node] += maxdp;
    }
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 30000;
    
//     // 변수 선언:
//     public static int n;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] a = new int[MAX_N + 1];
//     public static int[] dp = new int[MAX_N + 1];
//     public static int[] maxLength = new int[MAX_N + 1];
//     public static int ans;
    
//     // 1번 노드를 시작으로 DFS를 진행하며 값을 갱신합니다.
//     // dp[i] = i번 노드를 루트로 하는 서브트리에서 
//     // i번 노드를 끝으로 하는 경로 중 최대 합에 해당하는 경로
//     // maxLength[i] = i번 노드를 루트로 하는 서브트리에서
//     // 두 자식노드로 이어지는 경로의 최댓갑
//     public static void dfs(int x) {
//         // 이진트리므로 최대 자식 개수는 2개입니다.
//         // 자식의 번호를 관리합니다.
//         ArrayList<Integer> children = new ArrayList<>();
    
//         // 노드 x에 연결된 간선을 살펴보며 전부 방문해줍니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 이미 방문한 정점이라면 스킵해줍니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);
    
//             // y번 노드의 경로의 길이를 path에 추가해줍니다.
//             children.add(y);
//         }
    
//         // 왼쪽, 오른쪽 자식 번호를 기입해줍니다.
//         // 만약 없다면 index가 0이 되도록 하여 후보 계산이 dp[0] = 0값을 이용하여
//         // 답에 영향을 주지 않도록 합니다.
//         int left = children.size() > 0 ? children.get(0) : 0;
//         int right = children.size() > 1 ? children.get(1) : 0;
    
//         // dp와 maxLength를 갱신합니다.
//         // dp의 경우 왼쪽 오른쪽 자식 중 값이 더 큰 경우에 현재 노드에 적혀있는 값을 추가합니다.
//         // 단, 그 값이 음수라면 연결하지 않는 상태를 의미하는 0이 더 좋습니다.
//         dp[x] = a[x] + Math.max(0, Math.max(dp[left], dp[right]));
        
//         // maxLength의 경우 현재 노드 + 왼쪽에서 얻을 수 있는 최대 + 오른쪽에서 얻을 수 있는 최대가 됩니다.
//         maxLength[x] = a[x] + Math.max(0, dp[left]) + Math.max(0, dp[right]);
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

//         // 1번 노드를 시작으로 DFS를 진행하며 값을 갱신합니다.
//         visited[1] = true;
//         dfs(1);

//         // 모든 maxLength의 값 중 최댓값을 출력합니다.
//         for(int i = 1; i <= n; i++)
//             ans = Math.max(ans, maxLength[i]);

//         System.out.print(ans);
//     }
// }