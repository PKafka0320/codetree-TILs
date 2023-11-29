import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
        int[][] grid = new int [n][n];
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String string = sc.next();
            for (int j = 0; j < n; j++) {
                if (string.charAt(j) == '.') {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = 1;
                }
            }
        }

        int time = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0;
        while (true) {
            if (visited[x][y]) {
                System.out.println(-1);
                return;
            }
            visited[x][y] = true;
            time++;
            // System.out.println("time " + time);

            dir = setDirection(dx, dy, dir, grid, x, y, n);
            // System.out.println("dir " + dir);
            if (dir == -1) {
                System.out.println(-1);
                return;
            }

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (outOfRange(n, nx, ny)) {
                System.out.println(time);
                return;
            }
            x = nx;
            y = ny;
        }
    }

    public static int setDirection(int[] dx, int[] dy, int dir, int[][] grid, int x, int y, int n) {
        int checkCount = 0;
        int nDir = dir;
        int wDir;
        while (checkCount++ < 4) {
            wDir = (nDir + 1) % 4;
            int wx = x + dx[wDir];
            int wy = y + dy[wDir];
            if (grid[wx][wy] == 1) {
                // System.out.println("find wall [" + wx + "," + wy + "]");
                int nx = x + dx[nDir];
                int ny = y + dy[nDir];
                if (outOfRange(n, nx, ny) || grid[nx][ny] == 0) {
                    return nDir;
                }
                nDir = (nDir + 3) % 4;
            } else {
                nDir = (nDir + 1) % 4;
                return nDir;
            }
        }
        return -1;
    }

    public static boolean outOfRange(int n, int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= n);
    }
}