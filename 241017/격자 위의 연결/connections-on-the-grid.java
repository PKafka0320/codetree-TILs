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
	static int N, M, root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N*M+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				root[M*(i-1)+j] = M*(i-1)+j;
			}
		}
		
		Queue<Edge> queue = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M-1; j++) {
				int weight = Integer.parseInt(st.nextToken());
				queue.add(new Edge(M*(i-1)+j, M*(i-1)+j+1, weight));
			}
		}
		for (int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int weight = Integer.parseInt(st.nextToken());
				queue.add(new Edge(M*(i-1)+j, M*(i-1)+j+M, weight));
			}
		}
		
		int sum = 0;
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			int weight = edge.weight;
			
			if (union(node1, node2)) {
				sum += weight;
			}
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
import java.util.ArrayList;
import java.util.Collections;

class Tuple implements Comparable<Tuple> {
    int cost, x, y;

    public Tuple(int cost, int x, int y) {
        this.cost = cost;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Tuple t) {
        return this.cost - t.cost; // 비용 기준 오름차순 정렬을 진행합니다.
    }
}

public class Main {
    public static final int MAX_N = 90000;
    
    // 변수 선언
    public static int n, m;
    
    public static ArrayList<Tuple> edges = new ArrayList<>();
    
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

        // n개의 줄에 대해 가로 간선을 모두 입력받습니다.
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < m; j++) {
                // 각 칸을 1부터 n * m까지 번호를 매겨줍니다.
                int x = (i - 1) * m + j;
                int y = (i - 1) * m + j + 1;
                int cost = sc.nextInt();
                edges.add(new Tuple(cost, x, y));
            }
        }

        // n - 1개의 줄에 대해 세로 간선을 모두 입력받습니다.
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= m; j++) {
                // 각 칸을 1부터 n * m까지 번호를 매겨줍니다.
                int x = (i - 1) * m + j;
                int y = i * m + j;
                int cost = sc.nextInt();
                edges.add(new Tuple(cost, x, y));
            }
        }

        // cost 순으로 오름차순 정렬을 진행합니다.
        Collections.sort(edges);

        // uf 값을 초기값을 적어줍니다.
        for(int i = 1; i <= n * m; i++)
            uf[i] = i;
        
        // cost가 낮은 간선부터 순서대로 보며
        // 아직 두 노드가 연결이 되어있지 않을 경우에만
        // 해당 간선을 선택하고 두 노드를 합쳐주면서
        // mst를 만들어줍니다.
        int ans = 0;
        for(int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).cost;
            int x = edges.get(i).x;
            int y = edges.get(i).y;

            // x, y가 연결되어 있지 않다면
            if(find(x) != find(y)) {
                // 해당 간선은 MST에 속하는 간선이므로
                // 답을 갱신해주고
                // 두 노드를 연결해줍니다.
                ans += cost;
                union(x, y);
            }
        }
        
        System.out.print(ans);
    }
}
*/