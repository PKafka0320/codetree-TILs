import java.io.*;
import java.util.*;

public class Main {
	static int N, depth[], parents[];
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* input & initialize */
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];
		depth = new int[N+1];
		parents = new int[N+1];
		int[] indegree = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			
			edges[parent].add(child);
			indegree[child]++;
		}
		
		st = new StringTokenizer(br.readLine());
		int fnode1 = Integer.parseInt(st.nextToken());
		int fnode2 = Integer.parseInt(st.nextToken());
		int root = -1;
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				root = i;
				break;
			}
		}
		
		/* logic */
		depth[root] = 1;
		dfs(root);
		
		while (depth[fnode1] != depth[fnode2]) {
			if (depth[fnode1] > depth[fnode2]) {
				fnode1 = parents[fnode1];
			} else {
				fnode2 = parents[fnode2];
			}
		}
		
		while (fnode1 != fnode2) {
			fnode1 = parents[fnode1];
			fnode2 = parents[fnode2];
		}
		
		System.out.println(fnode1);
	}
	
	public static void dfs(int node) {
		for (int ad : edges[node]) {
			if (parents[node] == ad) continue;
			depth[ad] = depth[node] + 1;
			parents[ad] = node;
			dfs(ad);
		}
	}
}

// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언:
//     public static int n;
//     public static ArrayList<Integer>[] edges = new ArrayList[MAX_N + 1];
//     public static int[] parent = new int[MAX_N + 1];
//     public static int[] depth = new int[MAX_N + 1];
    
//     // 트리 순회를 진행합니다.
//     // 동시에 depth를 기록해줍니다.
//     public static void dfs(int x) {
//         // 노드 x의 자식들을 살펴봅니다.
//         for(int i = 0; i < edges[x].size(); i++) {
//             int y = edges[x].get(i);
//             // depth값을 갱신해주며
//             // 재귀적으로 탐색합니다.
//             depth[y] = depth[x] + 1;
//             dfs(y);
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();
        
//         // n - 1개의 부모-자식 연결 관계 정보를 입력받습니다.
//         for(int i = 1; i <= n; i++)
//             edges[i] = new ArrayList<>();

//         for(int i = 1; i <= n - 1; i++) {
//             int p = sc.nextInt();
//             int x = sc.nextInt();

//             parent[x] = p;
//             edges[p].add(x);
//         }
//         // 가장 가까운 공통 조상을 구할 두 노드의 번호가 주어집니다.
//         int a = sc.nextInt();
//         int b = sc.nextInt();

//         // 부모가 여전히 없는 노드가 루트 노드가 됩니다.
//         int rootVertex = 0;
//         for(int i = 1; i <= n; i++)
//             if(parent[i] == 0)
//                 rootVertex = i;
        
//         // dfs 탐색을 진행하며
//         // 각 노드의 depth를 계산합니다.
//         depth[rootVertex] = 1;
//         dfs(rootVertex);

//         // Step 1.
//         // 두 노드 a, b의 depth를 비교하며
//         // depth가 더 큰 쪽을 위로 올리는 것을 반복하며 두 노드의 depth를 맞춰줍니다.
//         while(depth[a] != depth[b]) {
//             if(depth[a] > depth[b])
//                 a = parent[a];
//             else
//                 b = parent[b];
//         }

//         // Step 2.
//         // 두 노드 a, b가 일치해질떄까지
//         // 한 칸씩 위로 올라갑니다.
//         while(a != b) {
//             a = parent[a];
//             b = parent[b];
//         }

//         // LCA 값을 출력합니다.
//         System.out.print(a);
//     }
// }