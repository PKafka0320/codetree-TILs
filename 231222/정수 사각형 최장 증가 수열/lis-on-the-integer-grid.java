import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] grid;
    static int[][] max;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        for (int row = 0; row < n; row ++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        max = new int[n][n];
        for (int row = 0; row < n; row++) {
            Arrays.fill(max[row], 1);
        }

        int answer = 1;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                for (int dir = 0; dir < 4; dir++) {
                    int nr = row + dr[dir];
                    int nc = col + dc[dir];
                    if (outOfRange(nr, nc)) continue;
                    if (grid[nr][nc] < grid[row][col]) {
                        max[row][col] = Math.max(max[row][col], max[nr][nc] + 1);
                    }
                }
                answer = Math.max(answer, max[row][col]);
            }
        }

        System.out.println(answer);
    }

    public static boolean outOfRange(int r, int c) {
        return (r < 0 || r >= n || c < 0 || c >= n);
    }
}