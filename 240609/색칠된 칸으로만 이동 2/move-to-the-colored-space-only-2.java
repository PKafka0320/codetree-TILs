import java.io.*;
import java.util.*;

class Point {
    int row, col;
    
    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString() {
        return "[" + row + "," + col + "]";
    }
}

public class Main {
    static int[][] grid; // [i][j]: i행 j열 칸의 숫자
    static int[][] colored; // [i][j]: i행 j열 칸의 색칠 여부
    static boolean[][] visited; // [i][j]: i행 j열 칸의 탐색 여부
    static int M, N; // 격자의 가로, 세로 길이
    static int[] dr = new int[] {0, 0, 1, -1}; // [i]: i번째 방향일때 row 가중치
    static int[] dc = new int[] {1, -1, 0, 0}; // [i]: i번째 방향일때 column 가중치

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        grid = new int[M][N];
        colored = new int[M][N];
        visited = new boolean[M][N];
        
        int low = 0; // D 값의 범위 시작
        int high = 0; // D 값의 범위 끝
        int answer = 1_000_000_000; // D의 최솟값
        int startRow = -1; // 탐색 시작 row
        int startCol = -1; // 탐색 시작 column
        
        // 격자 생성
        for (int row = 0; row < M; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(tokenizer.nextToken());
                high = Math.max(high, grid[row][col]);
            }
        }
        
        for (int row = 0; row < M; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < N; col++) {
                colored[row][col] = Integer.parseInt(tokenizer.nextToken());
                
                if (colored[row][col] == 1) {
                    startRow = row;
                    startCol = col;
                }
            }
        }
        
        while (low <= high) {
            int mid = (low + high) / 2; // 중앙값
            
            // 중앙값을 d로 했을때 가능한지 확인
            if (isPossible(startRow, startCol, mid)) {
                answer = Math.min(answer, mid);
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        System.out.println(answer);
    }
    
    // 탐색 시작 위치에서 bfs 시도
    public static boolean isPossible(int startRow, int startCol, int d) {
        resetVisited();
        
        bfs(startRow, startCol, d);
        
        // 모든 색칠된 칸으로 이동할 수 있는지 확인
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(colored[i][j] > 0 && !visited[i][j]) return false;
            }
        }
        
        return true;
    }
    
    // bfs 탐색
    public static void bfs(int startRow, int startCol, int d) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startRow, startCol));
        visited[startRow][startCol] = true;
        
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int r = point.row;
            int c = point.col;
            
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                
                if (cannotMove(nr, nc, r, c, d)) {
                    continue;
                }
                visited[nr][nc] = true;
                queue.add(new Point(nr, nc));
            }
        }
    }
    
    // 탐색 배열 초기화
    public static void resetVisited() {
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                visited[row][col] = false;
            }
        }
    }
    
    // 격자 범위, 탐색 여부, 이동 조건 확인
    public static boolean cannotMove(int row, int col, int brow, int bcol, int d) {
        if (row < 0 || row >= M || col < 0 || col >= N) {
            return true;
        }
        if (visited[row][col] || Math.abs(grid[brow][bcol] - grid[row][col]) > d) {
            return true;
        }
        return false;
    }
}