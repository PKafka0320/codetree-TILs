import java.util.Scanner;

public class Main {
    public static int n;
    public static int m;
    public static int[][] grid;
    public static int[][] downMax;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        downMax = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        preProcessing();

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int bestHeight = Integer.MAX_VALUE;

                for (int k = j; k < m; k++) {
                    bestHeight = Math.min(bestHeight, downMax[i][k]);

                    int h = i + bestHeight - 1;
                    ans = Math.max(ans, (h - i + 1) * (k - j + 1));
                }
            }
        }

        if (ans <= 0) {
            ans = -1;
        }

        System.out.println(ans);
    }

    public static void preProcessing() {
        for (int j = 0; j < m; j++) {
            if (grid[n - 1][j] > 0) {
                downMax[n - 1][j] = 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 0) {
                    downMax[i][j] = downMax[i + 1][j] + 1;
                }
            }
        }
    }
}