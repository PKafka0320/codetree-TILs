import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;

        int[][] grid = new int[n][n];
        grid[r][c] = 1;
        int time = 0;
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        while (time++ < m) {
            int[][] nextGrid = new int[n][n];
            int nextPos = (int)Math.pow(2, time - 1);
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == 1) {
                        nextGrid[row][col] = 1;
                        for (int dir = 0; dir < 4; dir++) {
                            int nr = row;
                            int nc = col;
                            if (dr[dir] != 0) {
                                nr += (dr[dir] * nextPos);
                            }
                            if (dc[dir] != 0) {
                                nc += (dc[dir] * nextPos);
                            }
                            if (outOfRange(n, nr, nc)) continue;
                            nextGrid[nr][nc] = 1;
                        }
                    }
                }
            }
            grid = nextGrid;
        }

        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) count++;
            }
        }
        System.out.println(count);
    }

    public static boolean outOfRange(int n, int r, int c) {
        return (r < 0 || r >= n || c < 0 || c >= n);
    }
}