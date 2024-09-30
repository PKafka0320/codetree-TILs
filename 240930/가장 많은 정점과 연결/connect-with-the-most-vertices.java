import java.io.*;
import java.util.*;

public class Main {
	static class Pair implements Comparable<Pair> {
		int index, minNumber;

		public Pair(int index, int minNumber) {
			this.index = index;
			this.minNumber = minNumber;
		}
		
		@Override
		public int compareTo(Pair o) {
			return this.minNumber - o.minNumber;
		}
	}
	static int N, M, K, A, B, roots[], minNumbers[];
	static boolean isRoots[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		roots = new int[N+1]; // [i]: i번 노드의 루트 노드
		minNumbers = new int[N+1]; // [i]: i번 노드를 루트로 하는 서브트리의 최소 정점값
		isRoots = new boolean[N+1]; // [i]: i번 노드의 루트 여부
		
		// 초기화
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
			isRoots[i] = true;
		}
		
		// 정점 번호 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			minNumbers[i] = Integer.parseInt(st.nextToken());
		}

		// 초기 간선 정보 입력 및 union
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			union(node1, node2);
		}
		
		// 루트 노드만 찾아서 리스트에 추가
		List<Pair> roots = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (isRoots[i]) {
				roots.add(new Pair(i, minNumbers[i]));
			}
		}
		Collections.sort(roots);
		
		// 가장 낮은 번호의 정점을 가지는 서브트리를 기준으로 나머지 서브트리들의 가장 낮은 번호의 합 계산 
		long result = 0;
		int minNumber = roots.get(0).minNumber;
		for (int i = 1; i < roots.size(); i++) {
			result += minNumber + roots.get(i).minNumber;
		}
		
		System.out.println(result > K ? "NO" : result);
	}
	
	public static boolean union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return false;
		
		isRoots[root1] = false;
		minNumbers[root2] = Math.min(minNumbers[root1], minNumbers[root2]);
		roots[root1] = root2;
		return true;
	}
	
	public static int find(int node) {
		if (roots[node] == node) return node;
		return roots[node] = find(roots[node]);
	}

}

// import java.util.Scanner;
// import java.util.ArrayList;
// import java.util.Collections;

// public class Main {
//     public static final int MAX_M = 100002;
//     public static final int MAX_N = 100002;
    
//     // 변수 선언
//     public static int n, m, k;
    
//     // 각 그룹의 최소비용을 관리합니다.
//     public static int[] minCost = new int[MAX_N+ 1];
    
//     public static boolean[] visited = new boolean[MAX_N + 1];
    
//     public static ArrayList<Integer> costList = new ArrayList<>();
    
//     public static int[] uf = new int[MAX_N + 1];
    
//     public static int find(int x) {
//         if(uf[x] == x)
//             return x;
//         return uf[x] = find(uf[x]);
//     }
    
//     public static void union(int x, int y) {
//         int X = find(x);
//         int Y = find(y);
//         uf[X] = Y;
    
//         // 각 그룹의 최소비용을 관리합니다.
//         minCost[Y] = Math.min(minCost[Y], minCost[X]);
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         // 입력:
//         n = sc.nextInt();
//         m = sc.nextInt();
//         k = sc.nextInt();

//         // minCost의 초기값이 주어집니다.
//         for(int i = 1; i <= n; i++)
//             minCost[i] = sc.nextInt();

//         // uf 값을 초기값을 적어줍니다.
//         for(int i = 1; i <= n; i++)
//             uf[i] = i;

//         for(int i = 1; i <= m; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();

//             // 이미 연결되어 있으므로 union을 진행합니다.
//             union(x, y);
//         }

//         // 각 그룹들에 대해 minCost값을 모아줍니다.
//         for(int i = 1; i <= n; i++) {
//             int I = find(i);

//             // 중복되는 그룹은 패스합니다.
//             if(visited[I])
//                 continue;
            
//             visited[I] = true;
//             costList.add(minCost[I]);
//         }

//         // minCost를 오름차순으로 정렬합니다.
//         Collections.sort(costList);

//         // 가장 작은 minCost 쪽과 간선을 연결하는 것이 최선입니다.
//         int ans = 0;
//         for(int i = 1; i < costList.size(); i++)
//             ans += costList.get(0) + costList.get(i);

//         if(ans <= k)
//             System.out.print(ans);
//         else
//             System.out.print("NO");
//     }
// }