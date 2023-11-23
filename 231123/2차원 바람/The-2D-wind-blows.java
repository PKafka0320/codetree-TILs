import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        int[][] grid = new int[n][m];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < q; i++) {
            int r1 = sc.nextInt() - 1;
            int c1 = sc.nextInt() - 1;
            int r2 = sc.nextInt() - 1;
            int c2 = sc.nextInt() - 1;

            rotate(grid, r1, c1, r2, c2);
            changeVal(grid, r1, c1, r2, c2, new int[n][m]);
        }

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rotate(int[][] grid, int r1, int c1, int r2, int c2) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int dir = 3;
        int stash = grid[r1][c1];
        int x = r1;
        int y = c1;
        do {
            if ((x == r1 && y == c1) || (x == r1 && y == c2) ||(x == r2 && y == c1) ||(x == r2 && y == c2)) {
                dir = (dir + 1) % 4;
            }
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            int temp = grid[nx][ny];
            grid[nx][ny] = stash;
            stash = temp;
            x = nx;
            y = ny;
        } while (!(x == r1 && y == c1));
    }

    public static void changeVal(int[][] grid, int r1, int c1, int r2, int c2, int[][] temp) {
        for (int x = r1; x <= r2; x++) {
            for (int y = c1; y <= c2; y++) {
                temp[x][y] = computeAvg(grid, x, y);
            }
        }

        for (int x = r1; x <= r2; x++) {
            for (int y = c1; y <= c2; y++) {
                grid[x][y] = temp[x][y];
            }
        }
    }

    public static int computeAvg(int[][] grid, int x, int y) {
        int[] dx = {0, 0, 1, 0, -1};
        int[] dy = {0, 1, 0, -1, 0};
        int sum = 0;
        int count = 0;

        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (outOfRange(grid, nx, ny)) {
                continue;
            }
            sum += grid[nx][ny];
            count++;
        }

        return sum / count;
    }

    public static boolean outOfRange(int[][] grid, int x, int y) {
        int n = grid.length;
        int m = grid[0].length;
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return true;
        }
        return false;
    }
}