import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] grid;
    static int[][] max;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        max = new int[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        compute();
        System.out.println(max[N - 1][N - 1]);
    }

    public static void compute() {
        max[0][0] = grid[0][0];

        for (int row = 1; row < N; row++) {
            max[row][0] = Math.max(max[row - 1][0], grid[row][0]);
        }
        for (int col = 1; col < N; col++) {
            max[0][col] = Math.max(max[0][col - 1], grid[0][col]);
        }

        for (int row = 1; row < N; row++) {
            for (int col = 1; col < N; col++) {
                int exMin = Math.min(max[row - 1][col], max[row][col - 1]);
                max[row][col] = Math.max(exMin, grid[row][col]);
            }
        }
    }
}