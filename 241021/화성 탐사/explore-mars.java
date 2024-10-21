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
		Position position1, position2;
		int distance;
		
		public Route(Position position1, Position position2, int distance) {
			this.position1 = position1;
			this.position2 = position2;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Route o) {
			return this.distance - o.distance;
		}
	}
	static int N, totalCount, count, map[][];
	static int dr[] = {-1,1,0,0}, dc[] = {0,0,-1,1};
	static boolean isActivate[][];
	static Queue<Position> base;
	static Queue<Route> route;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isActivate = new boolean[N][N];
		base = new LinkedList<>();
		route = new PriorityQueue<>();
		count = 1;
		totalCount = 0;
		int answer = 0;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					base.add(new Position(r, c, 0));
					isActivate[r][c] = true;
					totalCount++;
				} else if (map[r][c] == 2) {
					totalCount++;
				}
			}
		}
		
		while (!base.isEmpty()) {
			dfs(base.poll());
			
			if (route.isEmpty()) break;
			
			Route selectRoute = route.poll();
			Position from = selectRoute.position1;
			Position to = selectRoute.position2;
			int dist = selectRoute.distance;
			
			if (!isActivate[from.row][from.col]) {
				isActivate[from.row][from.col] = true;
				count++;
				answer += dist;
				base.add(from);
			} else if (!isActivate[to.row][to.col]) {
				isActivate[to.row][to.col] = true;
				count++;
				answer += dist;
				base.add(to);
			}
			
			if (count == totalCount) break;
		}
		
		if (count < totalCount) {
			while (!route.isEmpty()) {
				Route selectRoute = route.poll();
				Position from = selectRoute.position1;
				Position to = selectRoute.position2;
				int dist = selectRoute.distance;
				
				if (!isActivate[from.row][from.col]) {
					isActivate[from.row][from.col] = true;
					count++;
					answer += dist;
				} else if (!isActivate[to.row][to.col]) {
					isActivate[to.row][to.col] = true;
					count++;
					answer += dist;
				}
				
				if (count == totalCount) break;
			}
		}
		
		System.out.println(count == totalCount ? answer : -1);
	}
	
	public static void dfs(Position position) {
		Queue<Position> queue = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		
		visited[position.row][position.col] = true;
		queue.add(position);
		
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
				if (map[nr][nc] == 2 && !isActivate[nr][nc]) {
					route.add(new Route(position, new Position(nr, nc, 0), dist+1));
				} else {
					queue.add(new Position(nr, nc, dist+1));
				}
			}
		}
	}
	
	public static boolean cannotMove(int r, int c, boolean[][] visited) {
		return (visited[r][c] || map[r][c] == -1 || map[r][c] == 1);
	}
	
	public static boolean outOfMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}