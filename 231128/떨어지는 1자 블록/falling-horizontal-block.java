import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt() - 1;

        int[][] grid = new int[n][n];
        int[] depth = new int[n];
        Arrays.fill(depth, n - 1);
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = sc.nextInt();
                if (depth[col] == n - 1 && grid[row][col] == 1) {
                    depth[col] = row - 1;
                }
            }
        }
        // System.out.println("depth: " + Arrays.toString(depth));

        int maxDepth = n - 1;
        for (int col = k; col < k + m; col++) {
            maxDepth = Math.min(maxDepth, depth[col]);
        }
        // System.out.println("max depth: " + maxDepth);

        for (int col = k; col < k + m; col++) {
            grid[maxDepth][col] = 1;
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
}