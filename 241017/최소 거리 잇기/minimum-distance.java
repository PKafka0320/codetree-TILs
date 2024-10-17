import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node1, node2;
		double weight;

		public Edge(int node1, int node2, double weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			if (this.weight < o.weight) return -1;
			else if (this.weight == o.weight) return 0;
			else return 1;
		}
	}
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, root[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];

		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		List<Position> positions = new ArrayList<>();
		Queue<Edge> queue = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j < i; j++) {
				Position position = positions.get(j-1);
				double distance = Math.sqrt(Math.pow(position.x - x, 2) + Math.pow(position.y - y, 2));
				queue.add(new Edge(i, j, distance));
			}
			
			positions.add(new Position(x, y));
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			union(node1, node2);
		}
		
		double sum = 0;
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			int node1 = edge.node1;
			int node2 = edge.node2;
			double weight = edge.weight;
			
			if (union(node1, node2)) {
				sum += weight;
			}
		}
		System.out.printf("%.2f", sum);
		
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

class Pair {
    int x, y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Tuple implements Comparable<Tuple> {
    double dist;
    int x, y;

    public Tuple(double dist, int x, int y) {
        this.dist = dist;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Tuple t) {
        return new Double(this.dist).compareTo(t.dist); // 거리 기준 오름차순 정렬을 진행합니다.
    }
}

public class Main {
    public static final int MAX_N = 200;
    
    // 변수 선언
    public static int n, m;
    public static Pair[] points = new Pair[MAX_N + 1];
    
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

        // 입력
        n = sc.nextInt();
        m = sc.nextInt();

        // 그래프를 인접행렬로 표현
        for(int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            points[i] = new Pair(x, y);
        }

        for(int i = 1; i <= n; i++)
            for(int j = i + 1; j <= n; j++) {
                int x1 = points[i].x;
                int y1 = points[i].y;
                int x2 = points[j].x;
                int y2 = points[j].y;
                double dist = Math.sqrt(
                    (long)(x1 - x2) * (x1 - x2) + (long)(y1 - y2) * (y1 - y2)
                );
                
                edges.add(new Tuple(dist, i, j));
            }

        // cost 순으로 오름차순 정렬을 진행합니다.
        Collections.sort(edges);

        // uf 값을 초기값을 적어줍니다.
        for(int i = 1; i <= n; i++)
            uf[i] = i;

        // m개의 간선 정보를 입력받습니다. 간선을 서로 연결해줍니다.
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            union(x, y);
        }
        
        // dist가 낮은 간선부터 순서대로 보며
        // 아직 두 노드가 연결이 되어있지 않을 경우에만
        // 해당 간선을 선택하고 두 노드를 합쳐주면서
        // mst를 만들어줍니다.
        double ans = 0;
        for(int i = 0; i < edges.size(); i++) {
            double dist = edges.get(i).dist;
            int x = edges.get(i).x;
            int y = edges.get(i).y;

            // x, y가 연결되어 있지 않다면
            if(find(x) != find(y)) {
                // 해당 간선은 MST에 속하는 간선이므로
                // 답을 갱신해주고
                // 두 노드를 연결해줍니다.

                ans += dist;
                union(x, y);
            }
        }

        // 모든 간선의 길이의 총합을 출력합니다.
        System.out.printf("%.2f", ans);
    }
}
*/