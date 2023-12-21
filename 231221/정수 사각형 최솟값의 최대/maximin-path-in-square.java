import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] grid;
    static int[][] min;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        min = new int[n][n];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        min[0][0] = grid[0][0];
        for (int idx = 1; idx < n; idx++) {
            min[0][idx] = Math.min(min[0][idx - 1], grid[0][idx]);
            min[idx][0] = Math.min(min[idx - 1][0], grid[idx][0]);
        }

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < n; col++) {
                int minVal = Math.max(min[row - 1][col], min[row][col - 1]);
                min[row][col] = Math.min(minVal, grid[row][col]);
            }
        }

        System.out.println(min[n - 1][n - 1]);
    }
}