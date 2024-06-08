import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;
    static boolean[][] visited;
    static final int[] dr = new int[]{1, -1, 0, 0};
    static final int[] dc = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine()); // 격자의 크기
        
        grid = new int[n][n]; // [i][j]: 격자의 i행 j열의 숫자
        visited = new boolean[n][n]; // [i][j]: 격자의 i행 j열 탐색 여부
        
        for (int row = 0; row < n; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < n; col++) {
                grid[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        
        int min = 0; // 색칠된 칸이 전체 칸의 반 이상이 되기 위한 D 값의 범위 시작
        int max = 1_000_000; // 색칠된 칸이 전체 칸의 반 이상이 되기 위한 D 값의 범위 끝
        int answer = max; //색칠된 칸이 전체 칸의 반 이상이 되기 위한 D 값의 최솟값
        
        while (min <= max) {
            int mid = (min + max) / 2; // 중앙값
            
            if (isPossible(mid)) {
                max = mid - 1;
                answer= mid;
            }
            else {
                min = mid + 1;
            }
        }
        
        System.out.println(answer);
    }
    
    public static boolean isPossible(int d) {
        // visited 초기화
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                visited[row][col] = false;
            }
        }
        
        // 모든 구역에 대해 절반 이상이 칠해지는 구역이 있는지 확인
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (!visited[row][col]) {
                    if (dfs(row, col, d) * 2 >= n * n) {
                        return true;
                    }
                }
            }
        }
        
        // 절반 이상이 칠해지는 구역이 없다면 false를 반환
        return false;
    }
    
    // 색칠되는 칸의 개수 계산
    public static int dfs(int row, int col, int d) {
        if (visited[row][col]) {
            return 0;
        }
        
        visited[row][col] = true;
        int count = 1;
        
        for (int dir = 0; dir < 4; dir++) {
            int nr = row + dr[dir];
            int nc = col + dc[dir];
            
            if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
                    Math.abs(grid[nr][nc] - grid[row][col]) <= d) {
                count += dfs(nr, nc, d);
            }
        }
        
        return count;
    }
}