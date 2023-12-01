import java.util.*;

public class Main {
    static int RIGHT = 0;
    static int DOWN = 1;
    static int LEFT = 2;
    static int UP = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int N = n + 2;
        int[][] grid = new int[N][N];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                grid[row][col] = sc.nextInt();
            }
        }

        for (int startR = 0; startR < N; startR++) {
            for (int startC = 0; startC < N; startC++) {
                if (notStartPosition(N, startR, startC) || grid[startR][startC] > 0) continue;
                simulate(grid, startR, startC, N);
            }
        }

        int max = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                max = Math.max(max, grid[row][col]);
            }
        }
        System.out.println(max);
    }

    public static void simulate(int[][] grid, int startR, int startC, int n) {
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int dir = -1;
        if (startR == 0) {
            dir = DOWN;
        }
        if (startR == n - 1) {
            dir = UP;
        }
        if (startC == 0) {
            dir = RIGHT;
        }
        if (startC == n - 1) {
            dir = LEFT;
        }

        int currentR = startR;
        int currentC = startC;
        int time = 0;
        while (true) {
            time++;
            currentR += dr[dir];
            currentC += dc[dir];
            if (outOfBoard(n, currentR, currentC)) break;
            if (grid[currentR][currentC] == 1) {
                if (dir == LEFT) dir = DOWN;
                else if (dir == DOWN) dir = LEFT;
                else if (dir == RIGHT) dir = UP;
                else if (dir == UP) dir = RIGHT;
            }
            else if (grid[currentR][currentC] == 2) {
                if (dir == LEFT) dir = UP;
                else if (dir == DOWN) dir = RIGHT;
                else if (dir == RIGHT) dir = DOWN;
                else if (dir == UP) dir = LEFT;
            }
        }
        grid[startR][startC] = time;
        grid[currentR][currentC] = time;
    }

    public static boolean outOfBoard(int n, int r, int c) {
        return (r < 1 || r >= n - 1 || c < 1 || c >= n - 1);
    }

    public static boolean notStartPosition(int n, int r, int c) {
        if (0 < r && r < n - 1 && 0 < c && c < n - 1) return true;
        if (r == 0 && c == 0) return true;
        if (r == 0 && c == n - 1) return true;
        if (r == n - 1 && c == 0) return true;
        if (r == n - 1 && c == n - 1) return true;
        return false;
    }
}