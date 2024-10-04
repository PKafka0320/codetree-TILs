import java.io.*;
import java.util.*;

public class Main {
	static class Path implements Comparable<Path> {
		int vertex, dist;

		public Path(int vertex, int dist) {
			this.vertex = vertex;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Path o) {
			return this.dist - o.dist;
		}
	}
	static int INF = (int) 1e9;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int dists[] = new int[N+1];
		Queue<Path> queue = new PriorityQueue<>();
		List<Path> edges[] = new ArrayList[N+1];
		boolean visited[] = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			dists[i] = INF;
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			edges[node1].add(new Path(node2, dist));
			edges[node2].add(new Path(node1, dist));
		}
		
		queue.add(new Path(1, 0));
		dists[1] = 0;
		visited[0] = true;
		
		int answer = 0;
		int cnt = 0;
		while (!queue.isEmpty()) {
			Path path = queue.poll();
			int curVertex = path.vertex;
			int curDist = path.dist;
			
			if (visited[curVertex]) continue;
			answer += curDist;
			visited[curVertex] = true;
			if (++cnt == N) break;
			
			for (Path next : edges[curVertex]) {
				int nextVertex = next.vertex;
				int nextDist = next.dist;
				
				if (!visited[nextVertex] && dists[nextVertex] > nextDist) {
					dists[nextVertex] = nextDist;
					queue.add(new Path(nextVertex, dists[nextVertex]));
				}
			}
		}
		
		System.out.println(answer);
	}
}

// import java.util.Scanner;

// public class Main {
//     public static final int INT_MAX = Integer.MAX_VALUE;
//     public static final int MAX_N = 500;
    
//     // 변수 선언
//     public static int n, m;
//     public static int[][] graph = new int[MAX_N + 1][MAX_N + 1];
//     public static boolean[] visited = new boolean[MAX_N + 1];
    
//     public static int[] dist = new int[MAX_N + 1];

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         // 입력
//         n = sc.nextInt();
//         m = sc.nextInt();

//         // 그래프를 인접행렬로 표현
//         while(m-- > 0) {
//             int x = sc.nextInt();
//             int y = sc.nextInt();
//             int z = sc.nextInt();
//             graph[x][y] = (graph[x][y] == 0) ? z : Math.min(graph[x][y], z);
//             graph[y][x] = (graph[y][x] == 0) ? z : Math.min(graph[y][x], z);
//         }
        
//         // 그래프에 있는 모든 노드들에 대해
//         // 초기값을 전부 아주 큰 값으로 설정
//         // INT_MAX 그 자체로 설정하면
//         // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
//         // 적당히 큰 숫자로 적어줘야함에 유의!
//         for(int i = 1; i <= n; i++)
//             dist[i] = (int)1e9;

//         // 시작위치에는 dist값을 0으로 설정
//         dist[1] = 0;

//         // O(|V|^2) 프림 코드
//         int ans = 0;
//         for(int i = 1; i <= n; i++) {
//             // V개의 정점 중 
//             // 아직 방문하지 않은 정점 중
//             // dist값이 가장 작은 정점을 찾아줍니다.
//             int minIndex = -1;
//             for(int j = 1; j <= n; j++) {
//                 if(visited[j])
//                     continue;
                
//                 if(minIndex == -1 || dist[minIndex] > dist[j])
//                     minIndex = j;
//             }

//             // 최솟값에 해당하는 정점에 방문 표시를 진행합니다.
//             visited[minIndex] = true;

//             // mst 값을 갱신해줍니다.
//             ans += dist[minIndex];

//             // 최솟값에 해당하는 정점에 연결된 간선들을 보며
//             // 시작점으로부터의 최솟값을 갱신해줍니다.
//             for(int j = 1; j <= n; j++) {
//                 // 간선이 존재하지 않는 경우에는 넘어갑니다.
//                 if(graph[minIndex][j] == 0)
//                     continue;

//                 dist[j] = Math.min(dist[j], graph[minIndex][j]);
//             }
//         }

//         System.out.print(ans);
//     }
// }