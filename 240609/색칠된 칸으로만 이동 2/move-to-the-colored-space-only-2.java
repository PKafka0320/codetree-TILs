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
    static int[][] grid; // [i][j]: i행 j열의 숫자
    static boolean[][] visited; // [i][j]: i행 j열의 탐색 여부
    static ArrayList<Point> points; // 색칠된 칸의 좌표
    static int M, N; // 격자의 가로, 세로 길이
    static int[] dr = new int[] {0, 0, 1, -1}; // [i]: i번째 방향일때 row 가중치
    static int[] dc = new int[] {1, -1, 0, 0}; // [i]: i번째 방향일때 column 가중치

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        
        // 격자 생성
        grid = new int[M][N];
        visited = new boolean[M][N];
        for (int row = 0; row < M; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        
        points = new ArrayList<>();
        
        for (int row = 0; row < M; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < N; col++) {
                if (Integer.parseInt(tokenizer.nextToken()) == 0) {
                    continue;
                }
                points.add(new Point(row, col));
            }
        }
//        System.out.println("points: " + points);
        
        int low = 0; // D 값의 범위 시작
        int high = 1_000_000_000; // D 값의 범위 끝
        int answer = high; // D의 최솟값
        
        while (low <= high) {
            int mid = (low + high) / 2; // 중앙값
//            System.out.println("mid: " + mid);
            
            // 중앙값을 d로 했을때 가능한지 확인
            if (isPossible(mid)) {
                answer = Math.min(answer, mid);
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        System.out.println(answer);
    }
    
    // 시작 위치와 끝 위치를 설정하면서 bfs 시도
    public static boolean isPossible(int d) {
        for (int fromIdx = 0; fromIdx < points.size() - 1; fromIdx++) {
            for (int toIdx = fromIdx + 1;  toIdx < points.size(); toIdx++) {
//                System.out.print("check: " + points.get(fromIdx) + " -> " + points.get(toIdx));
                resetVisited();
                
                bfs(points.get(fromIdx), d);
                
                Point endPoint = points.get(toIdx);
                if (!visited[endPoint.row][endPoint.col]) {
//                    System.out.println("result: cannot move\n");
                    return false;
                }
            }
        }
        
//        System.out.println("result: can move\n");
        return true;
    }
    
    // bfs 탐색
    public static void bfs(Point startPoint, int d) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int r = point.row;
            int c = point.col;
            visited[r][c] = true;
            
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