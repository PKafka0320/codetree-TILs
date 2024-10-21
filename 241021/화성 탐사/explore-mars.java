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

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

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

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_K = 100;
    public static final int MAX_N = 50;
    
    // 변수 선언
    public static int n;
    public static int N; // 새로 만들어진 그래프에서 노드의 수 입니다.
    
    public static int[][] board = new int[MAX_N + 1][MAX_N + 1];
    public static int[][] renum = new int[MAX_N + 1][MAX_N + 1];
    
    public static boolean[][] visited = new boolean[MAX_N + 1][MAX_N + 1];
    public static int[][] step = new int[MAX_N + 1][MAX_N + 1];
    
    public static ArrayList<Tuple> edges = new ArrayList<>();
    
    public static int[] uf = new int[MAX_K + 1];
    
    public static boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }
    
    public static boolean canGo(int x, int y) {
        return inRange(x, y) && !visited[x][y] && board[x][y] != -1;
    }
    
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
    
    public static void bfs(int sx, int sy) {
        Queue<Pair> q = new LinkedList<>();
        
        // visited 배열과
        // 최단거리 값을 저장할 step 배열 값을 초기화합니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++) {
                visited[i][j] = false;
                step[i][j] = -1;
            }
    
        // 시작 위치는 최단거리 값이 0이 됩니다.
        visited[sx][sy] = true;
        step[sx][sy] = 0;
        q.add(new Pair(sx, sy));
    
        while(!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();
    
            int[] dx = new int[]{-1, 0, 1, 0};
            int[] dy = new int[]{0, -1, 0, 1};
    
            for(int dir = 0; dir < DIR_NUM; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
    
                // 이동이 불가능한 경우에는 패스합니다.
                if(!canGo(nx, ny))
                    continue;
    
                visited[nx][ny] = true;
                step[nx][ny] = step[x][y] + 1;
                q.add(new Pair(nx, ny));
    
                // bfs를 진행하며 탐사기지를 찾은 경우, 
                // 각 기지로부터의 거리를
                // 그래프의 형식으로 간선을 추가해줍니다.
                if(board[nx][ny] == 1 || board[nx][ny] == 2)
                    edges.add(new Tuple(step[nx][ny], renum[sx][sy], renum[nx][ny]));
            }
        }
    }
    
    public static int mst() {
        // cost 순으로 오름차순 정렬을 진행합니다.
        Collections.sort(edges);
    
        // uf 값을 초기값을 적어줍니다.
        for(int i = 1; i <= N; i++)
            uf[i] = i;
        
        // cost가 낮은 간선부터 순서대로 보며
        // 아직 두 노드가 연결이 되어있지 않을 경우에만
        // 해당 간선을 선택하고 두 노드를 합쳐주면서
        // mst를 만들어줍니다.
        int totalCost = 0;
        for(int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).cost;
            int x = edges.get(i).x;
            int y = edges.get(i).y;
    
            // x, y가 연결되어 있지 않다면
            if(find(x) != find(y)) {
                // 해당 간선은 MST에 속하는 간선이므로
                // 답을 갱신해주고
                // 두 노드를 연결해줍니다.
    
                totalCost += cost;
                union(x, y);
            }
        }
    
        // 모두 연결되어있는지를 판단하기 위해
        // isAllConnected라는 변수를 관리합니다.
        boolean isAllConnected = true;
        for(int i = 2; i <= N; i++) {
            int x = find(1);
            int y = find(i);
            if(x != y) 
                isAllConnected = false;
        }
    
        // 모두 연결되어 있다면 총 비용을 출력합니다.
        // 그렇지 않다면 불가능하다는 의미로 -1을 반환합니다.
        if(isAllConnected)
            return totalCost;
        else
            return -1;  
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력:
        n = sc.nextInt();

        // n행 n열의 격자 정보를 모두 입력받습니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                board[i][j] = sc.nextInt();

        // 기지마다 새로운 그래프에서의 정점 번호를 매겨줍니다.
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                if(board[i][j] == 1 || board[i][j] == 2)
                    renum[i][j] = ++N;
        
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++) {
                if(board[i][j] == 1 || board[i][j] == 2) {
                    // 탐사 기지가 있는 경우 해당 위치에서부터 bfs를 진행합니다.
                    // bfs를 통해 각 기지까지의 최단거리를 구해줄 수 있습니다.
                    bfs(i, j);
                }
            }

        // MST를 계산합니다.
        System.out.print(mst());
    }
}
*/