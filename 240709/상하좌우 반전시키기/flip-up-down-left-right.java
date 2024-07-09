import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 격자의 크기
        grid = new int[n][n]; // [i][j]: i행 j열의 숫자
        
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 1; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row - 1][col] == 0) reverse(row, col);
            }
        }

        for (int row = 0; row < n; row++) {
            System.out.println(Arrays.toString(grid[row]));
        }
    }

    public static void reverse(int row, int col) {
        int[] dr = {0, 0, 0, 1, -1};
        int[] dc = {0, 1, -1, 0, 0,};

        for (int dir = 0; dir < 5; dir++) {
            int nr = row + dr[dir];
            int nc = col + dc[dir];

            if (outOfRange(nr, nc)) continue;

            grid[nr][nc] = 1 - grid[nr][nc];
        }
    }

    public static boolean outOfRange(int row, int col) {
        return (row < 0 || row >= n || col < 0 || col >= n);
    }
}