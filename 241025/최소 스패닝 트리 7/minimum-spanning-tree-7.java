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

	static int totalSum, N, M, weights[], totalWeights[];
	static int INF = (int) 1e9;
	static List<Edge> edges[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N + 1];
		weights = new int[N + 1];
		totalWeights = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			weights[i] = INF;
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[node1].add(new Edge(node2, weight));
			edges[node2].add(new Edge(node1, weight));
		}

		int start = 1;
		mst(start);

		start = -1;
		int nextMax = -1;
		for (int i = 1; i <= N; i++) {
			if (nextMax < totalWeights[i]) {
				nextMax = totalWeights[i];
				start = i;
			}
		}
		
		mst(start);
		
		int longest = 0;
		for (int i = 1; i <= N; i++) {
			if (longest < totalWeights[i]) {
				longest = totalWeights[i];
			}
		}

		System.out.println(totalSum);
		System.out.println(longest);
	}

	public static void mst(int start) {
		Queue<Edge> queue = new PriorityQueue<>();
		boolean visited[] = new boolean[N + 1];
		totalSum = 0;

		for (int i = 1; i <= N; i++) {
			weights[i] = INF;
			totalWeights[i] = 0;
		}
		weights[start] = 0;
		queue.add(new Edge(start, 0));

		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if (visited[edge.node])
				continue;

			visited[edge.node] = true;
			totalSum += edge.weight;

			for (Edge ad : edges[edge.node]) {
				if (visited[ad.node]) continue;
				if (weights[ad.node] > ad.weight) {
					weights[ad.node] = ad.weight;
					totalWeights[ad.node] = totalWeights[edge.node] + ad.weight;
					queue.add(new Edge(ad.node, ad.weight));
				}
			}
		}
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

class Tuple {
    int x, y, dist;

    public Tuple(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 40000;
    
    // 변수 선언
    public static int n, m;
    
    // mst를 위한 변수들
    public static ArrayList<Node>[] graph = new ArrayList[MAX_N + 1];
    public static PriorityQueue<Element> pq = new PriorityQueue<>();
    public static ArrayList<Tuple> mstEdges = new ArrayList<>();
    
    // Prim 진행시 각 노드가 MST에 연결되기 위해
    // 어느 노드에 연결하는 것이 최선인지를 관리합니다.
    public static int[] distFrom = new int[MAX_N + 1];
    
    // tree를 위한 변수들
    public static ArrayList<Node>[] edge = new ArrayList[MAX_N + 1];
    public static int maxDist;
    public static int lastNode;
    
    public static int[] dist = new int[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];
    
    public static int mst() {
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 1; i <= n; i++)
            dist[i] = (int)1e9;
    
        // 시작위치에는 dist값을 0으로 설정
        dist[1] = 0;

        // 우선순위 큐에 시작점을 넣어줍니다.
        // 거리가 가까운 곳이 먼저 나와야 하며
        // 해당 지점이 어디인지에 대한 정보도 필요하므로
        // (거리, 정점 번호) 형태로 넣어줘야 합니다.
        pq.add(new Element(0, 1));

        // O(|E|log|V|) 프림 코드
        // 우선순위 큐에
        // 원소가 남아있다면 계속 진행해줍니다.
        int totalDist = 0;
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

            // mst에 해당하는 간선들을 만들어줍니다.
            if(minIndex != 1)
                mstEdges.add(new Tuple(
                    Math.min(minIndex, distFrom[minIndex]), 
                    Math.max(minIndex, distFrom[minIndex]), 
                    dist[minIndex]
                ));

            totalDist += minDist;

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

                    // targetIndex번 정점이 새로 MST에 들어오기 위해서는 
                    // minIndex 정점과 연결되는 것이 최선임을 표시해줍니다.
                    distFrom[targetIndex] = minIndex;
                }
            }
        }
    
        return totalDist;
    }
    
    // 모든 노드의 정점을 탐색하는 DFS를 진행합니다.
    public static void dfs(int x) {
        for(int i = 0; i < edge[x].size(); i++) {
            int y = edge[x].get(i).index;
            int d = edge[x].get(i).dist;
    
            // 이미 방문한 정점이면 스킵합니다.
            if(visited[y]) continue;
    
            visited[y] = true;
            dist[y] = dist[x] + d;
    
            // 현재 정점을 기준으로 가장 먼 노드를 찾습니다.
            if(dist[y] > maxDist) {
                maxDist = dist[y];
                lastNode = y;
            }
    
            dfs(y);
        }
    }
    
    public static int treeDiameter() {
        // n - 1개의 간선 정보를 인접리스트에 넣어줍니다.
        for(int i = 0; i < mstEdges.size(); i++) {
            int x = mstEdges.get(i).x;
            int y = mstEdges.get(i).y;
            int d = mstEdges.get(i).dist;
    
            edge[x].add(new Node(y, d));
            edge[y].add(new Node(x, d));
        }
        
        // DFS를 통해 가장 먼 노드를 찾습니다.
        for(int i = 1; i <= n; i++) {
            visited[i] = false;
            dist[i] = 0;
        }
    
        visited[1] = true;
        dfs(1);
    
        // 가장 먼 노드에서 시작해 다시 한번 DFS를 돌려 트리의 가장 긴 거리를 찾습니다.
        for(int i = 1; i <= n; i++) {
            visited[i] = false;
            dist[i] = 0;
        }
    
        visited[lastNode] = true;
        dfs(lastNode);
    
        // 트리의 가장 긴 거리를 출력합니다.
        return maxDist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            edge[i] = new ArrayList<>();
        }

        // 그래프를 인접리스트로 표현합니다.
        while(m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            graph[x].add(new Node(y, z));
            graph[y].add(new Node(x, z));
        }
        
        System.out.println(mst());
        System.out.print(treeDiameter());
    }
}
*/