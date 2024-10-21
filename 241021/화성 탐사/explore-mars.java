import java.io.*;
import java.util.*;

public class Main {
	static class Position {
		int row, col, distance;

		public Position(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
	}
	static class Route implements Comparable<Route> {
		int node1, node2;
		int distance;
		
		public Route(int node1, int node2, int distance) {
			this.node1 = node1;
			this.node2 = node2;
			this.distance = distance;
		}

		@Override
		public int compareTo(Route o) {
			return this.distance - o.distance;
		}
	}
	static int N, map[][], root[], number[][];
	static int dr[] = {-1,1,0,0}, dc[] = {0,0,-1,1};
	static Queue<Route> route;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		number = new int[N][N];
		route = new PriorityQueue<>();
		int totalCount = 0;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1 || map[r][c] == 2) {
					number[r][c] = ++totalCount;
				}
			}
		}
		
		root = new int[totalCount+1];
		for (int i = 1; i <= totalCount; i++) {
			root[i] = i;
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1 || map[r][c] == 2) {
					bfs(r, c);
				}
			}
		}
		
		System.out.println(mst(totalCount));
	}
	
	public static int mst(int totalCount) {
		int answer = 0;
		int count = 0;
		
		while (!route.isEmpty()) {
			Route current = route.poll();
			int dist = current.distance;
			int node1 = current.node1;
			int node2 = current.node2;
			
			if (union(node1, node2)) {
				count++;
				answer += dist;
			}
			
			if (count == totalCount-1) break;
		}
		
		return count == totalCount-1 ? answer : -1;
	}
	
	public static void bfs(int row, int col) {
		Queue<Position> queue = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		
		visited[row][col] = true;
		queue.add(new Position(row, col, 0));
		
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			int r = current.row;
			int c = current.col;
			int dist = current.distance;
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if (outOfMap(nr, nc) || cannotMove(nr, nc, visited)) continue;
				visited[nr][nc] = true;
				queue.add(new Position(nr, nc, dist+1));
				
				if (map[nr][nc] == 1 || map[nr][nc] == 2) {
					route.add(new Route(number[row][col], number[nr][nc], dist+1));
				}
			}
		}
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
	
	public static boolean cannotMove(int r, int c, boolean[][] visited) {
		return (visited[r][c] || map[r][c] == -1);
	}
	
	public static boolean outOfMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}