import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        int[] dir = new int[128];
        dir['L'] = 4;
        dir['R'] = 3;
        dir['U'] = 2;
        dir['D'] = 1;

        int[] dr = {0, 1, -1, 0, 0};
        int[] dc = {0, 0, 0, 1, -1};
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] grid = new int[N][N];
            int[][] gridDir = new int[N][N];

            for (int m = 0; m < M; m++) {
                int row = sc.nextInt() - 1;
                int col = sc.nextInt() - 1;
                char direction = sc.next().charAt(0);
                grid[row][col] = 1;
                gridDir[row][col] = dir[direction];
            }

            for (int time = 0; time < 2*N; time++) {
                int[][] nextGrid = new int[N][N];
                int[][] nextGridDir = new int[N][N];
                for (int row = 0; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        if (grid[row][col] == 0) continue;
                        int nr = row + dr[gridDir[row][col]];
                        int nc = col + dc[gridDir[row][col]];
                        if (outOfRange(N, nr, nc)) {
                            nextGrid[row][col] += 1;
                            nextGridDir[row][col] = (gridDir[row][col] < 3) ? (3 - gridDir[row][col]) : (7 - gridDir[row][col]);
                            continue;
                        }
                        nextGrid[nr][nc] += 1;
                        nextGridDir[nr][nc] = gridDir[row][col];
                    }
                }

                for (int row = 0; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        if (nextGrid[row][col] > 1) {
                            nextGrid[row][col] = 0;
                            nextGridDir[row][col] = 0;
                        }
                    }
                }

                grid = nextGrid;
                gridDir = nextGridDir;
            }

            int count = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (grid[row][col] == 1) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static boolean outOfRange(int n, int r, int c) {
        return (r < 0 || r >= n || c < 0 || c >= n);
    }
}