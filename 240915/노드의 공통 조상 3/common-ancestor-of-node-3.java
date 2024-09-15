import java.io.*;
import java.util.*;

public class Main {
	static int N, Q, D, parent[][], depth[];
	static boolean visited[];
	static List<Integer>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		D = (int) (Math.log(N) / Math.log(2)) + 1;
		parent = new int[N+1][D+1];
		depth = new int[N+1];
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
		
		depth[1] = 1;
		visited[1] = true;
		dfs(1);
		
		for (int d = 1; d <= D; d++) {
			for (int node = 1; node <= N; node++) {
				parent[node][d] = parent[parent[node][d-1]][d-1];
			}
		}
		
		Q = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int node3 = Integer.parseInt(st.nextToken());
			
			answer.append(lca(lca(node1, node2), node3)).append("\n");
		}
		System.out.println(answer);
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (visited[ad]) continue;
			visited[ad] = true;
			parent[ad][0] = node;
			depth[ad] = depth[node] + 1;
			dfs(ad);
		}
	}
	
	public static int lca(int node1, int node2) {
		if (depth[node1] < depth[node2]) {
			return lca(node2, node1);
		}
		
		for (int d = D; d >= 0; d--) {
			if (depth[node1] - depth[node2] >= (1 << d)) {
				node1 = parent[node1][d];
			}
		}
		
		if (node1 == node2) {
			return node1;
		}
		
		for (int d = D; d >= 0; d--) {
			if (parent[node1][d] != parent[node2][d]) {
				node1 = parent[node1][d];
				node2 = parent[node2][d];
			}
		}
		
		return parent[node1][0];
	}

}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_H = 16;
//     public static final int MAX_N = 50000;
    
//     // 변수 선언:
//     public static int n, q;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] depth = new int[MAX_N + 1];
    
//     // parent[h][i] : i번 노드에서 2^h번 부모를 따라 위로 올라갔을 때의 노드 번호를 관리합니다.
//     public static int[][] parent = new int[MAX_H + 1][MAX_N + 1]; 
    
//     // 트리 순회를 진행합니다.
//     // 동시에 depth를 기록해줍니다.
//     public static void dfs(int x) {
//         // 노드 x의 자식들을 살펴봅니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             if(!visited[y]) {
//                 visited[y] = true;
    
//                 // depth값을 갱신해주며
//                 // 재귀적으로 탐색합니다.
//                 depth[y] = depth[x] + 1;
    
//                 // 이때 y번 노드에서 1번(=2^0)번 부모를 따라 위로 올라갔을 때의 
//                 // 노드 번호는 x가 됩니다.
//                 parent[0][y] = x;
    
//                 dfs(y);
//             }
//         }
//     }
    
//     // a와 b의 LCA를 구해줍니다.
//     public static int lca(int a, int b) {
//         // Step 0.
//         // 노드 a의 높이가 더 같거나 커지도록 만들어줍니다.
//         if(depth[a] < depth[b])
//             return lca(b, a);
    
//         // Step 1.
//         // 노드 a의 높이를 노드 b의 높이까지 끌어올려줍니다.
//         // 이는 십진수를 이진수로 빠르게 바꾸는 방법 중 하나인
//         // 2의 거듭제곱 중 가장 큰 값을 계속 빼주는 원리를 이용합니다.
//         for(int h = MAX_H; h >= 0; h--) {
//             // a의 높이를 b에 맞추기 위해
//             // 아직 2^h 만큼 더 끌어 올려줘도 된다면
//             // 그만큼 올라갔을 때의 조상 값으로 변경해줍니다.
//             if(depth[a] - depth[b] >= (1 << h))
//                 a = parent[h][a];
//         }
    
//         // 이미 a와 b가 일치한다면
//         // 바로 그 값을 반환합니다.
//         if(a == b)
//             return a;
    
//         // Step 2.
//         // 두 노드 a, b가 일치해지기 직전까지
//         // 위로 올라갑니다.
//         // 이때 역시 해당 높이에 도달하기 위해 점프해야 하는 값을 x라 한다면
//         // x라는 십진수를 이진수로 빠르게 바꾸는 방법 중 하나인
//         // 2의 거듭제곱 중 가장 큰 값을 계속 빼주는 원리를 이용합니다.
//         // 단, 그 시점을 찾기 위해]
//         // 두 노드가 일치해지기 바로 직전까지 최대한 올라가는 방법으로 진행합니다.
//         for(int h = MAX_H; h >= 0; h--) {
//             if(parent[h][a] != parent[h][b]) {
//                 a = parent[h][a];
//                 b = parent[h][b];
//             }
//         }
    
//         // 이제 a, b는
//         // 같아지기 바로 직전의 위치까지 올라온 것이므로
//         // 최종 답은 a의 부모가 됩니다.
//         return parent[0][a];
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         // n - 1개의 에지 정보를 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             edges[i] = new ArrayList<>();

//         for(int i = 1; i <= n - 1; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();

//             edges[x].add(y);
//             edges[y].add(x);
//         }
        
//         // dfs 탐색을 진행하며
//         // 각 노드의 depth와 parent값을 계산합니다.
//         depth[1] = 1;
//         visited[1] = true;
//         dfs(1);

//         // parent값을 갱신해줍니다.
//         for(int h = 1; h <= MAX_H; h++) {
//             // 0 ~ h - 1까지는 이미 계산이 되어 있다는 전제 하에
//             // 정점 i로부터 2^h번 위로 올라갔을 때의 위치는
//             // 정점 i로부터 2^(h-1)번 위로 올라간 뒤, 
//             // 다시 그 노드로부터 2^(h-1)번 위로 다시 올라가면 
//             // O(1)에 바로 구해집니다.
//             for(int i = 1; i <= n; i++)
//                 parent[h][i] = parent[h - 1][parent[h - 1][i]];
//         }

//         // q개의 질의에 대해 답을 구해줍니다.
//         q = sc.nextInt();
//         while(q-- > 0) {
//             int a = sc.nextInt();
//             int b = sc.nextInt();
//             int c = sc.nextInt();
            
//             // 세 노드의 LCA를 구하는 것도 두 노드를 구할 때와 같은 원리로 짤 수 있지만,
//             // 세 노드의 LCA = (두 노드의 LCA)와 (나머지 한 노드)의 LCA이기도 합니다.
//             // 여기서는 이렇게 구현하겠습니다.
//             System.out.println(lca(lca(a, b), c));
//         }
//     }
// }