import java.io.*;
import java.util.*;

public class Main {
	static int N, M, map[][], number[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		number = new int[N][M];
		int size = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 1) continue;
				
				if (r > 0 && map[r-1][c] == 1) {
					number[r][c] = number[r-1][c];
					continue;
				}
				if (c > 0 && map[r][c-1] == 1) {
					number[r][c] = number[r][c-1];
					continue;
				}

				number[r][c] = ++size;
			}
//			System.out.println(Arrays.toString(number[r]));
		}
		
		int cost[][] = new int[size+1][size+1];
		for (int r = 1; r <= size; r++) {
			for (int c = 1; c <= size; c++) {
				cost[r][c] = (int) 1e9;
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1) {
//					System.out.printf("%d %d: %d%n", r, c, number[r][c]);
					int from = number[r][c];
					for (int dir = 0; dir < 4; dir++) {
						int[] pair = find(r, c, dir);
						int to = pair[0];
						int distance = pair[1];
						
						if (to == -1) continue;
						cost[from][to] = Math.min(cost[from][to], distance);
						cost[to][from] = Math.min(cost[to][from], distance);
//						System.out.printf("update %d-%d: %d%n", from, to, cost[from][to]);
					}
				}
			}
		}
		
		for (int i = 1; i <= size; i++) {
			cost[i][i] = 0;
//			for (int j = 1; j <= size; j++) {
//				System.out.printf(cost[i][j] == (int)1e9 ? "INF\t" : "%d\t", cost[i][j]);
//			}
//			System.out.println();
		}
//		System.out.println();
		
		int answer = 0;
		int dist[] = new int[size+1];
		boolean visited[] = new boolean[size+1];
		visited[1] = true;
		int count = 1;
		for (int i = 0; i <= size; i++) {
			dist[i] = cost[1][i];
		}

//		for (int j = 1; j <= size; j++) {
//			System.out.printf(dist[j] == (int)1e9 ? "INF\t" : "%d\t", dist[j]);
//		}
//		System.out.println("\n");
		
		for (int i = 1; i < size; i++) {
			int minIndex = -1;
			
			for (int j = 1; j <= size; j++) {
				if (visited[j]) continue;
				
				if (minIndex == -1 || dist[minIndex] > cost[i][j]) {
					minIndex = j;
				}
			}
			if (dist[minIndex] == (int)1e9) continue;

			visited[minIndex] = true;
			count++;
			answer += dist[minIndex];
//			System.out.printf("select %d: %d%n", minIndex, answer);
			
			for (int j = 1; j <= size; j++) {
				dist[j] = Math.min(dist[j], cost[minIndex][j]);
			}

//			for (int j = 1; j <= size; j++) {
//				System.out.printf(dist[j] == (int)1e9 ? "INF\t" : "%d\t", dist[j]);
//			}
//			System.out.println();
		}
		
		System.out.println(count == size ? answer : -1);
	}
	
	static int dr[] = {-1,1,0,0}, dc[] = {0,0,-1,1};
	public static int[] find(int r, int c, int dir) {
		int distance = 0;
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		while (inMap(nr, nc) && map[nr][nc] == 0) {
			distance++;
			nr += dr[dir];
			nc += dc[dir];
		}
		
		if (!inMap(nr, nc) || number[r][c] == number[nr][nc]) return new int[] {-1,-1};
		return new int[] {number[nr][nc], distance};
	}
	
	public static boolean inMap(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}
}