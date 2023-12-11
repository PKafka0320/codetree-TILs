import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] grid;
    static int[][] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        sum = new int[N][N];

        for (int row = 0; row < N; row++) {
            input = br.readLine().split(" ");
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(input[col]);
            }
        }

        compute();

        System.out.println(sum[N -1][N - 1]);
    }

    public static void compute() {
        sum[0][0] = grid[0][0];

        for (int row = 1; row < N; row++) {
            sum[row][0] = sum[row - 1][0] + grid[row][0];
        }
        for (int col = 1; col < N; col++) {
            sum[0][col] = sum[0][col - 1] + grid[0][col];
        }

        for (int row = 1; row < N; row++) {
            for (int col = 1; col < N; col++) {
                sum[row][col] = Math.max(sum[row - 1][col], sum[row][col - 1]) + grid[row][col];
            }
        }
    }
}