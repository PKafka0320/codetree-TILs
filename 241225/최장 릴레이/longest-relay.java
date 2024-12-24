import java.util.*;

public class Main {
    static int n;
    static int[][] A;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        n = scanner.nextInt();
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        dp = new int[n][1 << n]; // dp[current][visitedMask]
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int maxRelay = 0;

        // 각 사람을 시작점으로 탐색
        for (int i = 0; i < n; i++) {
            maxRelay = Math.max(maxRelay, dfs(i, 1 << i, -1));
        }

        System.out.println(maxRelay);
    }

    // DFS + Bitmask로 릴레이 탐색
    static int dfs(int current, int visitedMask, int prevValue) {
        if (dp[current][visitedMask] != -1) {
            return dp[current][visitedMask];
        }

        int maxLength = 1; // 최소 릴레이 길이는 1

        for (int next = 0; next < n; next++) {
            if ((visitedMask & (1 << next)) == 0 && A[current][next] > 0) { // 아직 방문하지 않았고 경로 존재
                if (prevValue == -1 || A[prevValue][current] < A[current][next]) { // 조건 확인
                    maxLength = Math.max(maxLength, 1 + dfs(next, visitedMask | (1 << next), current));
                }
            }
        }

        dp[current][visitedMask] = maxLength;
        return maxLength;
    }
}
