import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = sc.nextInt();
            }
        }

        int answer = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                answer = Math.max(answer, count(grid, row, col, n));
            }
        }

        System.out.println(answer);
    }

    public static int count(int[][] grid, int x, int y, int n) {
        int[][] tempGrid = new int[n][n];
        int bombSize = grid[x][y];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if ((row == x || col == y) && (Math.abs((row + col) - (x + y)) < bombSize)) {
                    continue;
                }
                tempGrid[row][col] = grid[row][col];
            }
        }

        arrange(tempGrid, n);

        return counting(tempGrid, n);
    }

    public static int counting(int[][] tempGrid, int n) {
        int answer = 0;
        int[] dx = {0, 1};
        int[] dy = {1, 0};

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (tempGrid[row][col] == 0) continue;
                for (int dir = 0; dir < 2; dir++) {
                    int nx = row + dx[dir];
                    int ny = col + dy[dir];

                    if (outOfRange(n, nx, ny)) continue;
                    if (tempGrid[row][col] == tempGrid[nx][ny]) answer++;
                }
            }
        }

        return answer;
    }

    public static boolean outOfRange(int n, int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= n);
    }

    public static void arrange(int[][] tempGrid, int n) {
        for (int col = 0; col < n; col++) {
            int currentRow = n - 1;
            for (int row = n - 1; row >= 0; row--) {
                if (tempGrid[row][col] > 0) {
                    if (row != currentRow) {
                        tempGrid[currentRow][col] = tempGrid[row][col];
                        tempGrid[row][col] = 0;
                    }
                    currentRow--;
                }
            }
        }
    }
}