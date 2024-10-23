import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node, weight;

		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int answer, N, M, K, weights[], INF = (int) 1e9;
	static boolean visited[];
	static List<Edge> edges[];
	static List<Integer> colored;
	static Queue<Edge> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N + 1];
		weights = new int[N + 1];
		visited = new boolean[N+1];
		queue = new PriorityQueue<>();
		colored = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			weights[i] = INF;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= K; i++) {
			colored.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[node1].add(new Edge(node2, weight));
			edges[node2].add(new Edge(node1, weight));
		}

		for (int node : colored) {
			queue.add(new Edge(node, 0));
			weights[node] = 0;
		}
		
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if (visited[edge.node]) continue;
			
			visited[edge.node] = true;
			answer += edge.weight;
			
			for (Edge ad : edges[edge.node]) {
				if (weights[ad.node] > ad.weight) {
					weights[ad.node] = ad.weight;
					queue.add(new Edge(ad.node, ad.weight));
				}
			}
		}

		System.out.println(answer);
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Node {
    int index, dist;
    
    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
};

class Element implements Comparable<Element> {
    int dist, index;
    
    public Element(int dist, int index) {
        this.dist = dist;
        this.index = index;
    }

    @Override
    public int compareTo(Element e) {
        return this.dist - e.dist;   // dist 기준 오름차순 정렬
    }
};

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m, k;
    public static ArrayList<Node>[] graph = new ArrayList[MAX_N + 1];
    public static PriorityQueue<Element> pq = new PriorityQueue<>();
    
    public static int[] dist = new int[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;

        // 시작위치(색칠된 위치)들은 dist값을 0으로 설정
        for(int i = 1; i <= k; i++) {
            int x = sc.nextInt();
            dist[x] = 0;

            // 우선순위 큐에 시작점을 넣어줍니다.
            // 거리가 가까운 곳이 먼저 나와야 하며
            // 해당 지점이 어디인지에 대한 정보도 필요하므로
            // (거리, 정점 번호) 형태로 넣어줘야 합니다.
            pq.add(new Element(0, x));
        }

        // 그래프를 인접리스트로 표현합니다.
        for(int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        while(m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            graph[x].add(new Node(y, z));
            graph[y].add(new Node(x, z));
        }

        // O(|E|log|V|) 프림 코드
        // 우선순위 큐에
        // 원소가 남아있다면 계속 진행해줍니다.
        int ans = 0;
        while(!pq.isEmpty()) {
            // 가장 거리가 가까운 정보를 받아온 뒤, 원소를 제거해줍니다.
            int minDist = pq.peek().dist;
            int minIndex = pq.peek().index;
            pq.poll();

            // 우선순위 큐를 이용하면
            // 같은 정점의 원소가 
            // 여러 번 들어가는 문제가 발생할 수 있어
            // 이미 계산해본 적이 있는 경우라면
            // 바로 패스해줍니다.
            if(visited[minIndex])
                continue;

            // visited 값을 true로 바꿔주고
            // 답을 갱신해줍니다. 
            visited[minIndex] = true;
            ans += minDist;

            // 최솟값에 해당하는 정점에 연결된 간선들을 보며
            // 최솟값을 갱신해줍니다.
            for(int j = 0; j < graph[minIndex].size(); j++) {
                int targetIndex = graph[minIndex].get(j).index;
                int targetDist = graph[minIndex].get(j).dist;
                
                // 현재 위치에서 연결된 간선으로 가는 것이 더 작다면
                int newDist = targetDist;
                if(dist[targetIndex] > newDist) {
                    // 값을 갱신해주고, 우선순위 큐에 해당 정보를 넣어줍니다.
                    dist[targetIndex] = newDist;
                    pq.add(new Element(newDist, targetIndex));
                }
            }
        }

        System.out.print(ans);
    }
}
*/