import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(bf.readLine());
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(bf.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        bomb(grid, x, y);
        arrange(grid);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bomb(int[][] grid, int x, int y) {
        int size = grid[x][y];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int dir = 0; dir < 4; dir++) {
            for (int len = 0; len < size; len++) {
                int nx = x + (dx[dir] * len);
                int ny = y + (dy[dir] * len);

                if (outOfRange(grid, nx, ny)) {
                    continue;
                }
                grid[nx][ny] = 0;
            }
        }
    }

    public static void arrange(int[][] grid) {
        for (int col = 0; col < grid.length; col++) {
            int curr = grid.length - 1;
            for (int row = grid.length - 1; row >= 0; row--) {
                if (grid[row][col] == 0) {
                    continue;
                }
                grid[curr][col] = grid[row][col];
                if(curr != row) {
                    grid[row][col] = 0;
                }
                curr--;
            }
        }
    }

    public static boolean outOfRange(int[][] grid, int x, int y) {
        return (x < 0 || x >= grid.length || y < 0 || y >= grid.length);
    }
}