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
	static int N, M, type[], root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		type = new int[N+1];
		root = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			char t = st.nextToken().charAt(0);
			type[i] = t - 'a';
			root[i] = i;
		}
		
		Queue<Edge> queue = new PriorityQueue<>();
		while (M-- > 0) {
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
			
			if (type[node1] == type[node2]) continue;
			
			if (union(node1, node2)) {
				sum += weight;
				count++;
			}
			
			if (count == N-1) break;
		}
		System.out.println(count == N-1 ? sum : -1);
		
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
    public static char[] abType = new char[MAX_N + 1];
    
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

        // n개의 정점에 대해 각각 a, b종류 중 어느 쪽인지 저장합니다.
        for(int i = 1; i <= n; i++)
            abType[i] = sc.next().charAt(0);

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
        for(int i = 1; i <= m; i++) {
            int x = edges[i].x;
            int y = edges[i].y;
            int cost = edges[i].cost;

            // 두 노드의 타입이 같다면 해당 간선은 사용할 수 없습니다.
            if(abType[x] == abType[y]) 
                continue;

            // x, y가 연결되어 있지 않다면
            if(find(x) != find(y)) {
                // 해당 간선은 MST에 속하는 간선이므로
                // 답을 갱신해주고
                // 두 노드를 연결해줍니다.
                ans += cost;
                union(x, y);
            }
        }

        // 모든 정점이 연결되어있는지를 판단하기 위해
        // isAllConnected라는 변수에 관리합니다.
        boolean isAllConnected = true;
        for(int i = 2; i <= n; i++) {
            int x = find(1);
            int y = find(i);
            if(x != y) 
                isAllConnected = false;
        }

        // 만약 전부 연결되어있다면 사용한 비용의 합을 출력합니다.
        // 그렇지 않다면 -1을 출력합니다.
        if(isAllConnected)
            System.out.print(ans);
        else
            System.out.print(-1);
    }
}
*/