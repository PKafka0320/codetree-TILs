import java.io.*;
import java.util.*;

class Pair {
    int row, col;
    
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int n, m, max_high;
    static int[][] grid;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken()); // 격자의 행 개수
        m = Integer.parseInt(tokenizer.nextToken()); // 격자의 열 개수
        
        grid = new int[n][m]; // [i][j]: i행 j열의 숫자
        visited = new boolean[n][m]; // [i][j]: i행 j열의 숫자 탐색 여부
        max_high = 0;
        
        for (int row = 0; row < n; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < m; col++) {
                grid[row][col] = Integer.parseInt(tokenizer.nextToken());
                max_high = Math.max(max_high, grid[row][col]);
            }
        }
        
        int low = 0; // 높이 차의 범위 시작
        int high = max_high; // 높이 차의 범위 끝
        int answer = max_high; // 높이 차의 최솟값
        
        while (low <= high) {
            int mid = (low + high) / 2; // 중앙값
            
            if (reachable(mid)) {
                answer = Math.min(answer, mid);
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        System.out.println(answer);
    }
    
    // d 이하로 최대 높이와 최소 높이의 차이가 나는 칸만 갈 수 있을 때,
    // 마지막 칸으로 이동할 수 있는지 확인
    public static boolean reachable(int d) {
        // 모든 높이 제한에 대해서, 도달 가능한지 확인
        for(int lo = 1; lo <= max_high; lo++) {
            // 탐색 배열 초기화
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    visited[row][col] = false;
                }
            }
    
            int hi = lo + d;
            
            // 만약 시작하는 위치의 높이가 lo이상 hi이하라면 dfs로 탐색
            if(grid[0][0] >= lo && grid[0][0] <= hi) {
                bfs(lo, hi);
            }
            
            // 마지막에 도달할 수 있으면 도달 가능
            if(visited[n - 1][m - 1]) {
                return true;
            }
        }
    
        return false;
    }
    
    public static void bfs(int low, int high) {
        Queue<Pair> queue = new LinkedList<>();
        int[] dr = new int[] {0, 0, 1, -1};
        int[] dc = new int[] {1, -1, 0, 0};
        
        queue.add(new Pair(0, 0));
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int row = current.row;
            int col = current.col;
            
            for (int dir = 0; dir < 4; dir++) {
                int nr = row + dr[dir];
                int nc = col + dc[dir];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                        grid[nr][nc] >= low && grid[nr][nc] <= high && !visited[nr][nc]) { 
                    queue.add(new Pair(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }
}