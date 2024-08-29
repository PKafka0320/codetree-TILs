import java.io.*;
import java.util.*;

public class Main {
	static int n, r, central;
	static int[] dp;
	static boolean[] visited;
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[n + 1];
		dp = new int[n + 1];
		visited= new boolean[n + 1];
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
		
		central = - 1;
		visited[r] = true;
		findCentral(r);
		
		for (int i = 0; i <= n; i++) {
			visited[i] = false;
		}
		
		visited[central] = true;
		dfs(central);

		if (central == -1) {
			System.out.println(0);
			return;
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int ad : edges[central]) {
			max = Math.max(max, dp[ad]);
			min = Math.min(min, dp[ad]);
		}
		System.out.println(max - min);
	}
	
	public static void findCentral(int node) {
		int child = edges[node].size() - 1;
		
		if (node == r) child++;
		
		if (central == -1 && child >= 2) central = node;
		if (central == -1 && child == 0) central = node;
		
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			
			visited[ad] = true;
			findCentral(ad);
		}
	}
	
	public static void dfs(int node) {
		dp[node] = 1;
		
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			visited[ad] = true;
			dfs(ad);
			dp[node] += dp[ad];
		}
	}
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n, r;
//     public static int midNode;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] treeSize = new int[MAX_N + 1];
//     public static int maxSize, minSize = MAX_N;
    
//     // DFS를 통해 중앙 노드를 판단해줍니다.
//     public static void findMidNode(int x) {
//         // 자식 노드의 개수는 edge의 개수 - 1입니다. (edge중 하나는 반드시 부모 노드와 연결되어 있으므로)
//         int child = edges[x].size() - 1;
    
//         // 단, 루트 노드 한정으로 부모 노드가 없기 때문에 자식 노드가 1개 더 있습니다.
//         if(x == r) 
//             child++;
    
//         if(child >= 2 && midNode == 0) {
//             // 최초로 자식노드가 2개 이상인 노드는 중앙 노드입니다.
//             midNode = x;
//         }
    
//         if(child == 0 && midNode == 0) {
//             // 만약 중앙 노드가 없는 상태로 리프 노드로 끝났다면 해당 노드는 중앙 노드입니다.
//             midNode = x;
//         }
    
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 이미 방문한 노드는 스킵합니다.
//             if(visited[y]) 
//                 continue;
            
//             visited[y] = true;
//             findMidNode(y);
//         }
//     }
    
//     // DFS를 통해 각 노드를 루트로 하는 서브트리의 노드의 개수를 계산합니다.
//     public static void dfs(int x) {
//         treeSize[x] = 1;
    
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             // 이미 방문한 노드는 스킵합니다.
//             if(visited[y]) 
//                 continue;
    
//             visited[y] = true;
//             dfs(y);
    
//             treeSize[x] += treeSize[y];
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();
//         r = sc.nextInt();

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

//         // 루트 노드로부터 시작해 DFS를 해 정보들을 저장합니다.
//         visited[r] = true;
//         findMidNode(r);

//         // visited 배열을 다시 초기화합니다.
//         for(int i = 1; i <= n; i++) 
//             visited[i] = false;

//         // DFS를 통해 각 노드를 루트로 하는 서브트리의 노드의 개수를 계산합니다.
//         visited[midNode] = true;
//         dfs(midNode);

//         for(int i = 0; i < edges[midNode].size(); i++) {
//             int y = edges[midNode].get(i);

//             maxSize = Math.max(maxSize, treeSize[y]);
//             minSize = Math.min(minSize, treeSize[y]);
//         }

//         // 중앙노드에서 연결된 자식 노드 중 해당 노드로부터 시작한
//         // 서브트리의 크기가 가장 큰 값에서 작은 값의 차이를 출력합니다.
//         System.out.print(maxSize - minSize);
//     }
// }