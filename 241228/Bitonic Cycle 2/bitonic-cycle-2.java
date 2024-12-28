import java.util.*;

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final long MAX_VALUE = (long) 1e15;
    static List<Node> arr;
    static long[][][] dp;

    static long dist(int a, int b) {
        long dy = Math.abs(arr.get(a).y - arr.get(b).y);
        long dx = Math.abs(arr.get(a).x - arr.get(b).x);
        return dy * dy + dx * dx;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        arr = new ArrayList<>();
        arr.add(new Node(0, 0));
        for (int i = 1; i <= n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            arr.add(new Node(a, b));
        }

        arr.sort(Comparator.comparingInt(o -> o.x));

        dp = new long[n + 1][n + 1][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], MAX_VALUE);
            }
        }

        dp[1][1][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int k = Math.max(i, j) + 1;
                if (k == n + 1) {
                    continue;
                }

                dp[k][j][0] = Math.min(dp[k][j][0], dp[i][j][0] + dist(i, k));
                dp[i][k][0] = Math.min(dp[i][k][0], dp[i][j][0] + dist(j, k));

                dp[k][j][1] = Math.min(dp[k][j][1], Math.min(dp[i][j][0], dp[i][j][1] + dist(i, k)));
                dp[i][k][1] = Math.min(dp[i][k][1], Math.min(dp[i][j][0], dp[i][j][1] + dist(j, k)));
            }
        }

        long ans = MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[i][n][1] + dist(i, n));
            ans = Math.min(ans, dp[i][n][0]);
            ans = Math.min(ans, dp[n][i][1] + dist(i, n));
            ans = Math.min(ans, dp[n][i][0]);
        }

        System.out.println(ans);
    }
}
