import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	static class Line implements Comparable<Line> {
		int point1, point2;
		long cost;

		public Line(int point1, int point2, long cost) {
			this.point1 = point1;
			this.point2 = point2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Line o) {
			if (this.cost > o.cost) return 1;
			else if (this.cost == o.cost) return 0;
			else return -1;
		}
	}
	static int N, root[];
	static Point points[];
	static Queue<Line> lines;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		points = new Point[N];
		root = new int[N];
		lines = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			root[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			points[i] = new Point(x, y, z);
			
			for (int j = 0; j < i; j++) {
				lines.add(new Line(i, j, getCost(i, j)));
			}
		}
		
		
		System.out.println(mst());
	}
	
	public static long mst() {
		long answer = (long) 1e10;
		
		int count = 0;
		while (!lines.isEmpty()) {
			Line line = lines.poll();
			int point1 = line.point1;
			int point2 = line.point2;
			long cost = line.cost;
			
			if (union(point1, point2)) {
				answer = Math.min(answer, cost);
				if (++count == N-1) break;
			}
		}
		
		return answer;
	}
	
	public static long getCost(int i, int j) {
		Point point1 = points[i];
		Point point2 = points[j];
		
		long answer = (long) 1e10;
		answer = Math.min(answer, Math.abs(point1.x - point2.x));
		answer = Math.min(answer, Math.abs(point1.y - point2.y));
		answer = Math.min(answer, Math.abs(point1.z - point2.z));
		
		return answer;
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