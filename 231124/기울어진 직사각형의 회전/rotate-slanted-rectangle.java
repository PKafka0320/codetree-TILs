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

    public static void rotate(int[][] grid, int r, int c, int m1, int m2, int m3, int m4, int moveDir) {
        int[] dx;
        int[] dy;
        int[] moveNums;
        if(moveDir == 1) {
            dx = new int[]{-1, -1, 1, 1};
            dy = new int[]{1, -1, -1, 1};
            moveNums = new int[]{m1, m2, m3, m4}; 
        }
        else {
            dx = new int[]{-1, -1, 1, 1};
            dy = new int[]{-1, 1, 1, -1};
            moveNums = new int[]{m4, m3, m2, m1};
        }

        int temp = grid[r][c];
        for (int dir = 0; dir < 4; dir++) {
            for (int count = 0; count < moveNums[dir]; count++) {
                int nr = r + dx[dir];
                int nc = c + dy[dir];
                grid[r][c] = grid[nr][nc];
                r = nr;
                c = nc;
            }
        }
        grid[r - dx[3]][c - dy[3]] = temp;
    }
}