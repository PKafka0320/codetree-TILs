import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node, distance;

		public Edge(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}
	}
	static int N, M, K, dist[];
	static boolean visited[];
	static int INF = (int)1e9;
	static List<Edge> edges[];
	static List<Integer> remove[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		edges = new ArrayList[N+1];
		remove = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			dist[i] = INF;
			edges[i] = new ArrayList<>();
			remove[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int distance = Integer.parseInt(st.nextToken());
			edges[0].add(new Edge(i, distance));
			edges[i].add(new Edge(0, distance));
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			remove[node1].add(node2);
			remove[node2].add(node1);
		}
		
		for (int i = 1; i < N; i++) {
			if (!remove[i].contains((i+1)%(N+1))) {
				edges[i].add(new Edge((i+1)%(N+1), 0));
			}
			if (!remove[(i+1)%(N+1)].contains(i)) {
				edges[(i+1)%(N+1)].add(new Edge(i, 0));
			}
		}
		if (!remove[1].contains(N)) {
			edges[1].add(new Edge(N, 0));
		}
		if (!remove[N].contains(1)) {
			edges[N].add(new Edge(1, 0));
		}
		
		dist[1] = 0;
		int answer = 0;
		
		Queue<Edge> queue = new PriorityQueue<>();
		queue.add(new Edge(1, 0));
		
		while (!queue.isEmpty()) {
			Edge current = queue.poll();
			
			if (visited[current.node]) continue;
			if (dist[current.node] != current.distance) continue;

			visited[current.node] = true;
			answer += dist[current.node];
			
			if (allChecked()) break;
			
			for (Edge edge : edges[current.node]) {
				if (visited[edge.node]) continue;
				dist[edge.node] = Math.min(dist[edge.node], edge.distance);
				
				queue.add(new Edge(edge.node, dist[edge.node]));
			}
		}
		
		System.out.println(answer <= K ? 1 : 0);
	}
	
	public static boolean allChecked() {
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) return false;
		}
		return true;
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
    public static final int MAX_N = 200000;
    
    // 변수 선언
    public static int n, m, k;
    public static ArrayList<Node>[] graph = new ArrayList[MAX_N + 1];
    public static PriorityQueue<Element> pq = new PriorityQueue<>();
    
    public static int[] dist = new int[MAX_N + 1];
    public static boolean[] visited = new boolean[MAX_N + 1];

    // 간선이 연결되어있는지 아닌지를 배열을 통해 관리합니다.
    public static boolean[] isBlocked = new boolean[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        // 그래프를 인접리스트로 표현합니다.
        for(int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        // 그래프를 인접리스트로 표현합니다.
        // 더미 노드(0번 노드)를 만들어서 1번부터 n번까지 노드를 통행증의 비용으로 연결합니다.
        for(int i = 1; i <= n; i++) {
            int cost = sc.nextInt();
            graph[0].add(new Node(i, cost));
            graph[i].add(new Node(0, cost));
        }

        // m개의 간선이 연결되어있는지 없는지 정보를 저장합니다.
        while(m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            if(x > y) {
                int temp = x;
                x = y;
                y = temp;
            }

            // x번 노드와 x+1번 노드 사이의 간선이 막혔음을 표시해줍니다.
            if(x == 1 && y == n)
                isBlocked[n] = true;
            else
                isBlocked[x] = true;
        }

        // 인접한 막히지 않은 간선들을 연결해둡니다.
        for(int i = 1; i <= n; i++) {
            if(isBlocked[i]) 
                continue;

            int x = i;
            int y = i + 1;
            if (x == n) 
                y = 1;

            graph[x].add(new Node(y, 0));
            graph[y].add(new Node(x, 0));
        }
        
        // 그래프에 있는 모든 노드들에 대해
        // 초기값을 전부 아주 큰 값으로 설정
        // INT_MAX 그 자체로 설정하면
        // 후에 덧셈 진행시 overflow가 발생할 수도 있으므로
        // 적당히 큰 숫자로 적어줘야함에 유의!
        for(int i = 0; i <= n; i++)
            dist[i] = ((int)1e9) + 5;

        // 시작위치에는 dist값을 0으로 설정
        dist[0] = 0;

        // 우선순위 큐에 시작점을 넣어줍니다.
        // 거리가 가까운 곳이 먼저 나와야 하며
        // 해당 지점이 어디인지에 대한 정보도 필요하므로
        // (거리, 정점 번호) 형태로 넣어줘야 합니다.
        pq.add(new Element(0, 0));

        // O(|E|log|V|) 프림 코드
        // 우선순위 큐에
        // 원소가 남아있다면 계속 진행해줍니다.
        long ans = 0;
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

        // k원을 넘기지 않고 모든 지점을 이동할 수 있다면 1을, 없다면 0을 출력합니다.
        if(ans <= k) 
            System.out.print(1);
        else 
            System.out.print(0);
    }
}
*/