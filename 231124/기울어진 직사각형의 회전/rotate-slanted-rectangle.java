import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int x = 0; x < n ; x++) {
            for (int y = 0; y < n; y++) {
                grid[x][y] = sc.nextInt();
            }
        }
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();
        int m3 = sc.nextInt();
        int m4 = sc.nextInt();
        int dir = sc.nextInt();

        rotate(grid, r, c, m1, m2, m3, m4, dir);

        for (int x = 0; x < n ; x++) {
            for (int y = 0; y < n; y++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static void rotate(int[][] grid, int r, int c, int m1, int m2, int m3, int m4, int dir) {
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {1, -1, -1, 1};
        int temp = grid[r][c];

        if (dir == 0) {
            for (int i = 0; i < m4; i++) {
                grid[r][c] = grid[r - dx[3]][c - dy[3]];
                r -= dx[3];
                c -= dy[3];
            }
            for (int i = 0; i < m3; i++) {
                grid[r][c] = grid[r - dx[2]][c - dy[2]];
                r -= dx[2];
                c -= dy[2];
            }
            for (int i = 0; i < m2; i++) {
                grid[r][c] = grid[r - dx[1]][c - dy[1]];
                r -= dx[1];
                c -= dy[1];
            }
            for (int i = 0; i < m1 - 1; i++) {
                grid[r][c] = grid[r - dx[0]][c - dy[0]];
                r -= dx[0];
                c -= dy[0];
            }
        }

        if (dir == 1) {
            for (int i = 0; i < m1; i++) {
                grid[r][c] = grid[r + dx[0]][c + dy[0]];
                r += dx[0];
                c += dy[0];
            }
            for (int i = 0; i < m2; i++) {
                grid[r][c] = grid[r + dx[1]][c + dy[1]];
                r += dx[1];
                c += dy[1];
            }
            for (int i = 0; i < m3; i++) {
                grid[r][c] = grid[r + dx[2]][c + dy[2]];
                r += dx[2];
                c += dy[2];
            }
            for (int i = 0; i < m4 - 1; i++) {
                grid[r][c] = grid[r + dx[3]][c + dy[3]];
                r += dx[3];
                c += dy[3];
            }
        }

        grid[r][c] = temp;
    }
}