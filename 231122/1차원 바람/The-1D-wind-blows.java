import java.util.*;

public class Main {
    public static final String LEFT = "L";
    public static final String RIGHT = "R";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m ; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < q; i++) {
            int line = sc.nextInt() - 1;
            String dir = sc.next();
            boolean[] visited = new boolean[n];
            process(grid, line, dir, visited);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m ; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void process(int[][] grid, int line, String dir, boolean[] visited) {
        if (visited[line]) {
            return;
        }
        visited[line] = true;

        if (dir.equals(RIGHT)) {
            for (int idx = 0; idx < grid[line].length - 1; idx++) {
                int temp = grid[line][idx];
                grid[line][idx] = grid[line][idx + 1];
                grid[line][idx + 1] = temp;
            }
        } else {
            for (int idx = grid[line].length - 1; idx > 0; idx--) {
                int temp = grid[line][idx];
                grid[line][idx] = grid[line][idx - 1];
                grid[line][idx - 1] = temp;
            }
        }

        boolean effectUp = false;
        boolean effectDown = false;
        for (int idx = 0; idx < grid[line].length; idx++) {
            if (line > 0) {
                if (grid[line - 1][idx] == grid[line][idx]) {
                    effectUp = true;
                }
            }
            if (line < grid.length - 1) {
                if (grid[line + 1][idx] == grid[line][idx]) {
                    effectDown = true;
                }
            }
        }

        if (effectUp) {
            process(grid, line - 1, dir.equals(LEFT) ? RIGHT : LEFT, visited);
        }
        if (effectDown) {
            process(grid, line + 1, dir.equals(LEFT) ? RIGHT : LEFT, visited);
        }
    }
}