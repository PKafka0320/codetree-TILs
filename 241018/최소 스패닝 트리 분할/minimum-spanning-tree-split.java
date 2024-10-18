import java.io.*;
import java.util.*;

public class Main {
	static int N, M, root[];
	static class Edge {
		int node1, node2, weight;

		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		
		if (N == 2) {
			System.out.println(0);
			return;
		}
		
		Queue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight - o2.weight;
			}
		});
		
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			queue.add(new Edge(node1, node2, weight));
		}
		
		int count = 0;
		int sum = 0;
		
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			int weight = edge.weight;
			
			if (union(node1, node2)) {
				sum += weight;
				count++;
			}
			
			if (count == N-2) break;
		}
		System.out.println(sum);
	}
	
	public static boolean union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		if (root1 == root2) return false;
		
		root[root1] = root2;
		return true;
	}
	
	public static int find(int node) {
		if (root[node] == node) return node;
		return root[node] = find(root[node]);
	}
}

/*
import java.util.Scanner;
import java.util.Arrays;

class Tuple implements Comparable<Tuple> {
    int x, y, cost;

    public Tuple(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Tuple t) {
        return this.cost - t.cost; // 비용 기준 오름차순 정렬을 진행합니다.
    }
}

public class Main {
    public static final int MAX_M = 100000;
    public static final int MAX_N = 10000;
    
    // 변수 선언
    public static int n, m;
    
    public static Tuple[] edges = new Tuple[MAX_M + 1];
    
    public static int[] uf = new int[MAX_N + 1];
    
    public static int find(int x) {
        if(uf[x] == x)
            return x;
        return uf[x] = find(uf[x]);
    }
    
    public static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        uf[X] = Y;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int cost = sc.nextInt();

            edges[i] = new Tuple(x, y, cost);
        }

        // cost 순으로 오름차순 정렬을 진행합니다.
        Arrays.sort(edges, 1, m + 1);

        // uf 값을 초기값을 적어줍니다.
        for(int i = 1; i <= n; i++)
            uf[i] = i;
        
        // cost가 낮은 간선부터 순서대로 보며
        // 아직 두 노드가 연결이 되어있지 않을 경우에만
        // 해당 간선을 선택하고 두 노드를 합쳐주면서
        // mst를 만들어줍니다.
        int ans = 0;
        int lastCost = 0; // 마지막 간선의 가중치를 저장합니다.
        for(int i = 1; i <= m; i++) {
            int x = edges[i].x;
            int y = edges[i].y;
            int cost = edges[i].cost;

            // x, y가 연결되어 있지 않다면
            if(find(x) != find(y)) {
                // 해당 간선은 MST에 속하는 간선이므로
                // 답을 갱신해주고
                // 두 노드를 연결해줍니다.
                ans += cost;
                lastCost = cost;
                union(x, y);
            }
        }

        // MST에서 가중치가 가장 큰 마지막 간선을 제외한 것이 답이 됩니다.
        System.out.print(ans - lastCost);
    }
}
*/