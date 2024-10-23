import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return from + " " + to + " " + weight;
		}
	}
	static int N, dist[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Edge> edges = new ArrayList<>(20);
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				boolean isAd = true;
				for (int k = 0; k < N; k++) {
					if (i==k || j==k) continue;
					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						isAd = false;
						break;
					}
				}
				
				if (isAd) {
					edges.add(new Edge(i+1, j+1, dist[i][j]));
				}
			}
		}
		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.from != o2.from) return o1.from - o2.from;
				return o1.to - o2.to;
			}
		});
		
		StringBuilder answer = new StringBuilder();
		for (Edge edge : edges) {
			answer.append(edge.toString()).append("\n");
		}
		System.out.println(answer.toString());
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Tuple implements Comparable<Tuple> {
    int a, b, w;

    public Tuple(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }

    @Override
    public int compareTo(Tuple t) {
        if(this.a != t.a)
            return this.a - t.a; // (1) a 기준 오름차순 정렬을 진행합니다.
        if(this.b != t.b)
            return this.b - t.b; // (2) b 기준 오름차순 정렬을 진행합니다.
        return this.w - t.w;     // (3) w 기준 오름차순 정렬을 진행합니다.
    }
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 500;
    
    // 변수 선언
    public static int n, m;
    public static int[][] graph = new int[MAX_N + 1][MAX_N + 1];
    
    public static int[] dist = new int[MAX_N + 1];
    
    // Prim 진행시 각 노드가 MST에 연결되기 위해
    // 어느 노드에 연결하는 것이 최선인지를 관리합니다.
    public static int[] distFrom = new int[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    public static ArrayList<Tuple> mstEdges = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();

        // 그래프의 인접 행렬을 입력받습니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                graph[i][j] = sc.nextInt();

        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;

        // 시작위치에는 dist값을 0으로 설정
        dist[1] = 0;

        // O(|V|^2) 프림 코드
        for(int i = 1; i <= n; i++) {
            // V개의 정점 중 
            // 아직 방문하지 않은 정점 중
            // dist값이 가장 작은 정점을 찾아줍니다.
            int minIndex = -1;
            for(int j = 1; j <= n; j++) {
                if(visited[j])
                    continue;
                
                if(minIndex == -1 || dist[minIndex] > dist[j])
                    minIndex = j;
            }

            // 최솟값에 해당하는 정점에 방문 표시를 진행합니다.
            visited[minIndex] = true;

            // mst에 해당하는 간선들을 저장합니다.
            if(minIndex != 1)
                mstEdges.add(new Tuple(
                    Math.min(minIndex, distFrom[minIndex]), 
                    Math.max(minIndex, distFrom[minIndex]), 
                    dist[minIndex]
                ));

            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 시작점으로부터의 최솟값을 갱신해줍니다.
            for(int j = 1; j <= n; j++) {
                // 간선이 존재하지 않는 경우에는 넘어갑니다.
                if(graph[minIndex][j] == 0)
                    continue;

                // j번 노드로부터 가장 가까운 간선과
                // 그 간선의 정보들을 저장합니다.
                if(dist[j] > graph[minIndex][j]) {
                    dist[j] = graph[minIndex][j];
                    // j번 정점이 새로 MST에 들어오기 위해서는 
                    // minIndex 정점과 연결되는 것이 최선임을 표시해줍니다.
                    distFrom[j] = minIndex;
                }
            }
        }

        // mst에 해당하는 간선들을 사전 순으로 정렬합니다.
        Collections.sort(mstEdges);

        for(int i = 0; i < mstEdges.size(); i++) {
            int a = mstEdges.get(i).a;
            int b = mstEdges.get(i).b;
            int w = mstEdges.get(i).w;

            System.out.println(a + " " + b + " " + w);
        }
    }
}
*/