import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        int[][] grid = new int[n][n];
        int[][] dice = {{0, 5, 0}, {4, 1, 3}, {0, 2, 0}};

        grid[r][c] = 6;
        while (m-- > 0) {
            char dir = sc.next().charAt(0);
            int nr = -1;
            int nc = -1;
            if (dir == 'L') {
                nr = r;
                nc = c - 1;
            } else if (dir == 'R') {
                nr = r;
                nc = c + 1;
            } else if (dir == 'U') {
                nr = r - 1;
                nc = c;
            } else if (dir == 'D') {
                nr = r + 1;
                nc = c;
            }
            if (outOfRange(n, nr, nc)) continue;

            moveDice(grid, dice, nr, nc, dir);
            r = nr;
            c = nc;
        }

        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                sum += grid[row][col];
            }
        }
        System.out.println(sum);
    }

    public static void moveDice(int[][] grid, int[][] dice, int r, int c, char dir) {
        int bottom = 7 - dice[1][1];
        if (dir == 'L') {
            grid[r][c] = dice[1][0];
            for (int idx = 0; idx < 2; idx++) {
                dice[1][idx] = dice[1][idx + 1];
            }
            dice[1][2] = bottom;
        } else if (dir == 'R') {
            grid[r][c] = dice[1][2];
            for (int idx = 2; idx > 0; idx--) {
                dice[1][idx] = dice[1][idx - 1];
            }
            dice[1][0] = bottom;
        } else if (dir == 'U') {
            grid[r][c] = dice[0][1];
            for (int idx = 0; idx < 2; idx++) {
                dice[idx][1] = dice[idx + 1][1];
            }
            dice[2][1] = bottom;
        } else if (dir == 'D') {
            grid[r][c] = dice[2][1];
            for (int idx = 2; idx > 0; idx--) {
                dice[idx][1] = dice[idx - 1][1];
            }
            dice[0][1] = bottom;
        }
    }

    public static boolean outOfRange(int n, int r, int c) {
        return (r < 0 || r >= n || c < 0 || c >= n);
    }
}