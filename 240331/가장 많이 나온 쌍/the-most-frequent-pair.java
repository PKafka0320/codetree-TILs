import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (a < b) grid[a][b] += 1;
            else grid[b][a] += 1;
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }

        System.out.println(max);
    }
}