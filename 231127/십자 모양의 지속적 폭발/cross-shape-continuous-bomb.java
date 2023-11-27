import java.util.*;

public class Main {
    static int[][] grid;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            int bombCol = sc.nextInt() - 1;
            explode(n, bombCol);
        }

        for (int[] line : grid) {
            for (int num : line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void explode(int n, int bombCol) {
        int bombRow = -1;
        for (int row = 0; row < n; row++) {
            if (grid[row][bombCol] > 0) {
                bombRow = row;
                break;
            }
        }
        if (bombRow == -1) return;

        int bombSize = grid[bombRow][bombCol];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if ((row == bombRow || col == bombCol) && Math.abs((row + col) - (bombRow + bombCol)) < bombSize) {
                    grid[row][col] = 0;
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
}