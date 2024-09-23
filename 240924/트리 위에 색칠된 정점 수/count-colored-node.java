import java.io.*;
import java.util.*;

public class Main {
	static int N, K, Q, D, depths[], parents[][], counts[][];
	static boolean colored[];
	static List<Integer> edges[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		D = (int) (Math.log(N) / Math.log(2)) + 1;
		depths = new int[N+1];
		parents = new int[D+1][N+1];
		edges = new ArrayList[N+1];
		colored = new boolean[N+1];
		counts = new int[D+1][N+1];
		
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
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			int node = Integer.parseInt(br.readLine());
			colored[node] = true;
		}
		
		depths[1] = 1;
		dfs(1);
		
		for (int d = 1; d <= D; d++) {
			for (int n = 1; n <= N; n++) {
				parents[d][n] = parents[d-1][parents[d-1][n]];
				if (parents[d][n] != 0) {
					counts[d][n] = counts[d-1][n] + counts[d-1][parents[d-1][n]];
				}
			}
		}
		
		Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			System.out.println(lca(node1, node2));
		}
	}
	
	public static int lca(int node1, int node2) {
		if (depths[node1] < depths[node2]) {
			return lca (node2, node1);
		}
		
		int count = 0;
		if (colored[node1]) count++;
		
		for (int d = D; d >= 0; d--) {
			if (depths[node1] - depths[node2] >= (1 << d)) {
				count += counts[d][node1];
				node1 = parents[d][node1];
			}
		}
		
		if (node1 == node2) {
			return count;
		}
		
		if (colored[node2]) count++;
		
		for (int d = D; d >= 0; d--) {
			if (parents[d][node1] != parents[d][node2]) {
				count += counts[d][node1] + counts[d][node2];
				node1 = parents[d][node1];
				node2 = parents[d][node2];
			}
		}
		
		if (colored[parents[0][node1]]) count++;
		return count;
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[0][node] == ad) continue;
			parents[0][ad] = node;
			depths[ad] = depths[node] + 1;
			if (colored[node]) {
				counts[0][ad]++;
			}
			dfs(ad);
		}
	}

}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_H = 17;
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n, k, q;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static boolean[] isColored = new boolean[MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
//     public static int[] depth = new int[MAX_N + 1];
    
//     // 루트 노드에서 i번 노드까지의 경로 상에서 색칠된 노드의 개수를 의미합니다.
//     public static int[] coloredNumFromRoot = new int[MAX_N + 1];
    
//     // parent[h][i] : i번 노드에서 2^h번 부모를 따라 위로 올라갔을 때의 노드 번호를 관리합니다.
//     public static int[][] parent = new int[MAX_H + 1][MAX_N + 1]; 
    
//     // 트리 순회를 진행합니다.
//     // 동시에 depth, coloredNumFromRoot를 기록해줍니다.
//     public static void dfs(int x) {
//         // 노드 x의 자식들을 살펴봅니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
    
//             if(!visited[y]) {
//                 visited[y] = true;
    
//                 // depth값을 갱신해주며
//                 // 재귀적으로 탐색합니다.
//                 depth[y] = depth[x] + 1;
    
//                 if(isColored[y]) 
//                     coloredNumFromRoot[y] = coloredNumFromRoot[x] + 1;
//                 else
//                     coloredNumFromRoot[y] = coloredNumFromRoot[x];
    
//                 // 이때 y번 노드에서 1번(=2^0)번 부모를 따라 위로 올라갔을 때의 
//                 // 노드 번호는 x가 됩니다.
//                 parent[0][y] = x;
    
//                 dfs(y);
//             }
//         }
//     }
    
//     // a와 b의 LCA를 구한 뒤, LCA와 a, b의
//     // coloredNumFromRoot를 바탕으로 a와 b사이의 색칠된 노드의 개수를 구합니다.
//     public static int getNum(int a, int b) {
//         // Step 0.
//         // 노드 a의 높이가 더 같거나 커지도록 만들어줍니다.
//         if(depth[a] < depth[b]) {
//             int temp = a;
//             a = b;
//             b = temp;
//         }
        
//         // 노드 a와 노드 b의 번호를 저장합니다.
//         int aSave = a;
//         int bSave = b;
    
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
//         // 그 값이 lca입니다.
//         // a, b, lca를 이용해 a노드와 b노드 사이의
//         // 색칠된 노드의 개수를 찾습니다.
//         if(a == b) {
//             // (a, b) 사이에 색칠된 노드의 개수는
//             // 루트로부터 a, b까지 각각 색칠된 노드의 합을 구한 뒤
//             // 겹치는 값인 루트->lca까지 색칠된 노드의 수를 뺀 값이 됩니다.
//             // 이때 lca가 색칠되었는지 여부에 따라 값을 잘 조정해줘야 함에 유의합니다.
//             int lca = a;
//             if(isColored[lca])
//                 return coloredNumFromRoot[aSave] + coloredNumFromRoot[bSave] - 2 * coloredNumFromRoot[lca] + 1;
//             else
//                 return coloredNumFromRoot[aSave] + coloredNumFromRoot[bSave] - 2 * coloredNumFromRoot[lca];
//         }
    
//         // Step 2.
//         // 두 노드 a, b가 일치해지기 직전까지
//         // 위로 올라갑니다.
//         // 이때 역시 해당 높이에 도달하기 위해 점프해야 하는 값을 x라 한다면
//         // x라는 십진수를 이진수로 빠르게 바꾸는 방법 중 하나인
//         // 2의 거듭제곱 중 가장 큰 값을 계속 빼주는 원리를 이용합니다.
//         // 단, 그 시점을 찾기 위해
//         // 두 노드가 일치해지기 바로 직전까지 최대한 올라가는 방법으로 진행합니다.
//         for(int h = MAX_H; h >= 0; h--) {
//             if(parent[h][a] != parent[h][b]) {
//                 a = parent[h][a];
//                 b = parent[h][b];
//             }
//         }
    
//         // 이제 a, b는
//         // 같아지기 바로 직전의 위치까지 올라온 것이므로
//         // 바로 위 부모노드 값이 lca입니다.
//         // a, b, lca를 이용해 a노드와 b노드 사이의
//         // 색칠된 노드의 개수를 찾습니다.
//         // (a, b) 사이에 색칠된 노드의 개수는
//         // 루트로부터 a, b까지 각각 색칠된 노드의 합을 구한 뒤
//         // 겹치는 값인 루트->lca까지 색칠된 노드의 수를 뺀 값이 됩니다.
//         // 이때 lca가 색칠되었는지 여부에 따라 값을 잘 조정해줘야 함에 유의합니다.
//         int lca = parent[0][a];
//         if(isColored[lca])
//             return coloredNumFromRoot[aSave] + coloredNumFromRoot[bSave] - 2 * coloredNumFromRoot[lca] + 1;
//         else
//             return coloredNumFromRoot[aSave] + coloredNumFromRoot[bSave] - 2 * coloredNumFromRoot[lca];
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();

//         for(int i = 1; i <= n; i++)
//             edges[i] = new ArrayList<>();

//         // n - 1개의 에지 정보를 입력받습니다.
//         for(int i = 1; i <= n - 1; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();

//             edges[x].add(y);
//             edges[y].add(x);
//         }

//         k = sc.nextInt();
//         // k개의 색칠된 노드 번호를 입력받습니다.
//         for(int i = 1; i <= k; i++) {
//             int x = sc.nextInt();
//             isColored[x] = true;
//         }
        
//         // dfs 탐색을 진행하며
//         // 각 노드의 depth, coloredNumFromRoot와 parent값을 계산합니다.
//         if(isColored[1]) coloredNumFromRoot[1] = 1;
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

//             // a부터 b까지의 색칠된 노드의 수를 계산합니다.
//             System.out.println(getNum(a, b));
//         }
//     }
// }