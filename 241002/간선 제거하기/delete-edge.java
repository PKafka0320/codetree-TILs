import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node1, node2, weight;

		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	static int N, M, roots[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		roots = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			roots[i] = i;
		}
		
		Queue<Edge> edges = new PriorityQueue<>();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(node1, node2, weight));
		}
		
		int edgeCount = 0;
		long answer = 0;
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			int weight = edge.weight;
			
			if (union(node1, node2)) {
				if (++edgeCount == N - 1) {
					break;
				}
			} else {
				answer += weight;
			}
		}
		
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			answer += edge.weight;
		}
		
		System.out.println(answer);
	}
	
	public static boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if (A == B) return false;
		
		roots[B] = A;
		return true;
	}
	
	public static int find(int a) {
		if (roots[a] == a) return a;
		return roots[a] = find(roots[a]);
	}

}

// import java.util.Scanner;
// import java.util.Arrays;

// class Tuple implements Comparable<Tuple> {
//     int x, y, cost;

//     public Tuple(int x, int y, int cost) {
//         this.x = x;
//         this.y = y;
//         this.cost = cost;
//     }

//     @Override
//     public int compareTo(Tuple t) {
//         return this.cost - t.cost; // 비용 기준 오름차순 정렬을 진행합니다.
//     }
// }

// public class Main {
//     public static final int MAX_M = 100000;
//     public static final int MAX_N = 100000;
    
//     // 변수 선언
//     public static int n, m;
    
//     public static Tuple[] edges = new Tuple[MAX_M + 1];
    
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
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력:
//         n = sc.nextInt();
//         m = sc.nextInt();
//         for(int i = 1; i <= m; i++) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();
//             int cost = sc.nextInt();

//             edges[i] = new Tuple(x, y, cost);
//         }

//         // cost 순으로 오름차순 정렬을 진행합니다.
//         Arrays.sort(edges, 1, m + 1);

//         // uf 값을 초기값을 적어줍니다.
//         for(int i = 1; i <= n; i++)
//             uf[i] = i;
        
//         // cost가 낮은 간선부터 순서대로 보며
//         // 아직 두 노드가 연결이 되어있지 않을 경우에만
//         // 해당 간선을 선택하고 두 노드를 합쳐주면서
//         // mst를 만들어줍니다.
//         int total_cost = 0;
//         int mst_cost = 0;
//         for(int i = 1; i <= m; i++) {
//             int x = edges[i].x;
//             int y = edges[i].y;
//             int cost = edges[i].cost;

//             total_cost += cost;

//             // x, y가 연결되어 있지 않다면
//             if(find(x) != find(y)) {
//                 // 해당 간선은 MST에 속하는 간선이므로
//                 // 답을 갱신해주고
//                 // 두 노드를 연결해줍니다.
//                 mst_cost += cost;
//                 union(x, y);
//             }
//         }

//         // 전체 합에서 mst 값을 뺀 것이 답이 됩니다.
//         System.out.print(total_cost - mst_cost);
//     }
// }