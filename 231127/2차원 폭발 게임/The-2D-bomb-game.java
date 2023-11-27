import java.util.*;

public class Main {
    static int[][] grid;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        grid = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = sc.nextInt();
            }
        }

        for (int count = 0; count < k; count++) {
            explode(n, m);
            rotate(n);
            explode(n, m);
        }

        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] > 0) count++;
            }
        }

        System.out.println(count);
    }

    private static void explode(int n, int m) {
        boolean exploded = true;
        while (exploded) {
            exploded = false;
            for (int col = 0; col < n; col++) {
                int keepNumber = 0;
                int count = 0;
                for (int row = 0; row < n; row++) {
                    int bombNumber = grid[row][col];
                    if (bombNumber > 0) {
                        if (bombNumber == keepNumber) {
                            count++;
                        } else {
                            if (count >= m) {
                                for (int offset = 0; offset < count; offset++) {
                                    grid[row - 1 - offset][col] = 0;
                                    exploded = true;
                                }
                            }
                            keepNumber = bombNumber;
                            count = 1;
                        }
                    }
                }
                if (count >= m) {
                    for (int offset = 0; offset < count; offset++) {
                        grid[n - 1 - offset][col] = 0;
                    }
                }
            }
        }

        int[][] nextGrid = new int[n][n];
        for (int col = 0; col < n; col++) {
            int rowNum = n - 1;
            for (int row = n - 1; row >= 0; row--) {
                if (grid[row][col] > 0) {
                    nextGrid[rowNum--][col] = grid[row][col];
                }
            }
        }

        grid = nextGrid;
    }

    private static void rotate(int n) {
        int[][] nextGrid = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                nextGrid[row][col] = grid[n - 1 - col][row];
            }
        }

        grid = nextGrid;

        nextGrid = new int[n][n];
        for (int col = 0; col < n; col++) {
            int rowNum = n - 1;
            for (int row = n - 1; row >= 0; row--) {
                if (grid[row][col] > 0) {
                    nextGrid[rowNum--][col] = grid[row][col];
                }
            }
        }

        grid = nextGrid;
    }
}