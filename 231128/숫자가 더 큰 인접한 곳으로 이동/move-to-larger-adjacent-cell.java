import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;

        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean canMove = true;
        int current = grid[r][c];
        while (canMove) {
            System.out.print(grid[r][c] + " ");
            canMove = false;
            for (int dir = 0; dir < 4; dir++) {
                int nx = r + dx[dir];
                int ny = c + dy[dir];
                if (outOfRange(n, nx, ny)) continue;
                if (grid[nx][ny] > grid[r][c]) {
                    canMove = true;
                    r = nx;
                    c = ny;
                    break;
                }
            }
        }
    }

    public static boolean outOfRange(int n, int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= n);
    }
}