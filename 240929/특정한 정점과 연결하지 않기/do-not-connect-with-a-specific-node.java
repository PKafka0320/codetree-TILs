import java.io.*;
import java.util.*;

public class Main {
	static int N, M, A, B, K, root[], size[];
	static boolean isRoot[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		size = new int[N+1];
		isRoot = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
			size[i] = 1;
			isRoot[i] = true;
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			union(node1, node2);
		}
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int rootA = find(A);
		int rootB = find(B);
		int count = size[rootA];
		
		List<Integer> sizes = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (i == rootA || i == rootB) continue;
			if (!isRoot[i]) continue;
			
			sizes.add(size[i]);
		}
		
		Collections.sort(sizes, Collections.reverseOrder());
		
		for (int i = 0; i < sizes.size(); i++) {
			if (i >= K) break;
			count += sizes.get(i);
		}
		
		System.out.println(count);
	}
	
	public static void union(int superNode, int subNode) {
		int superRoot = find(superNode);
		int subRoot = find(subNode);
		
		if (superRoot == subRoot) return;
		
		isRoot[subRoot] = false;
		size[superRoot] += size[subRoot]; 
		root[subRoot] = superRoot;
	}
	
	public static int find(int node) {
		if (root[node] == node) return node;
		return root[node] = find(root[node]);
	}

}

// import java.util.Scanner;
// import java.util.ArrayList;
// import java.util.Collections;

// public class Main {
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n, m;
//     public static int a, b, k;
    
//     // 번호별 그룹을 관리합니다.
//     public static int[] uf = new int[MAX_N + 1];
    
//     // 그룹별 노드 개수를 관리합니다.
//     public static int[] sz = new int[MAX_N + 1];
    
//     // 각 그룹을 방문했는지 여부를 기록합니다.
//     public static boolean[] visited = new boolean[MAX_N + 1];
    
//     public static ArrayList<Integer> groupList = new ArrayList<>();
    
//     // x의 대표 번호를 찾아줍니다.
//     public static int find(int x) {
//         // x가 루트 노드라면 x값을 반환합니다.
//         if(uf[x] == x)
//             return x;
//         // x가 루트 노드가 아니라면
//         // x의 부모인 uf[x]에서 탐색을 더 진행한 뒤
//         // 찾아낸 루트 노드를 uf[x]에 넣어줌과 동시에
//         // 해당 노드값을 반환합니다.
//         return uf[x] = find(uf[x]);
//     }
    
//     // x, y가 같은 집합이 되도록 합니다.
//     public static void union(int x, int y) {
//         // x, y의 대표 번호를 찾은 뒤
//         // 연결해줍니다.
//         int X = find(x);
//         int Y = find(y);
//         if(X != Y) {
//             uf[X] = Y;
//             sz[Y] += sz[X];
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
        
//         // 입력:
//         n = sc.nextInt();
//         m = sc.nextInt();

//         // 초기 uf, sz 값을 설정합니다.
//         for(int i = 1; i <= n; i++) {
//             uf[i] = i;
//             sz[i] = 1;
//         }

//         // m개의 간선 정보를 입력받습니다.
//         for(int i = 1; i <= m; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();

//             // 주어진 모든 정점을 연결시킵니다.
//             union(x, y);
//         }

//         a = sc.nextInt();
//         b = sc.nextInt();
//         k = sc.nextInt();

//         int A = find(a);
//         int B = find(b);
//         // 모든 노드의 그룹을 정리합니다.
//         // B번 노드와도, A번 노드와도 연결되지 않은 그룹들을 ans에 저장합니다.
//         for(int i = 1; i <= n; i++) {
//             int I = find(i);

//             // A, B와 같은 그룹이라면 패스합니다.
//             if(I == A || I == B) continue;

//             // 이미 선택된 그룹이라면 패스합니다.
//             if(visited[I]) 
//                 continue;

//             // 아직 연결되지 않은 그룹들의 크기를 넣어줍니다.
//             visited[I] = true;
//             groupList.add(sz[I]);
//         }

//         // a와 연결될 수 있는 최대 노드의 개수를 구합니다.
//         int szA = sz[A];

//         // 내림차순으로 나열합니다.
//         Collections.sort(groupList, Collections.reverseOrder());

//         // a와 가능한 많은 그룹을 연결시킵니다. 최대 k개까지의 그룹을 연결시킬 수 있습니다.
//         for(int i = 0; i < Math.min(k, groupList.size()); i++)
//             szA += groupList.get(i);

//         // 최대 k개까지의 그룹을 연결시킨 후 크기의 총합을 출력합니다.
//         System.out.print(szA);
//     }
// }