import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // int result = tabulation(n);

        int[] memo = new int[n + 1];
        int result = memoization(memo, n);

        System.out.println(result % 10007);
    }

    public static int tabulation(int n) {
        if (n == 2 || n == 3) {
            return 1;
        }

        int[] memo = new int[n + 1];
        memo[2] = 1;
        memo[3] = 1;

        for (int i = 4; i <= n; i++) {
            memo[i] = memo[i - 2] + memo[i - 3];
        }

        return memo[n];
    }

    public static int memoization(int[] memo, int n) {
        if (memo[n] != 0) {
            return memo[n];
        }
        if (n == 2 || n == 3) {
            return 1;
        } else if (n < 2) {
            return 0;
        } else {
            memo[n] = memoization(memo, n - 2) + memoization(memo, n - 3);
        }

        return memo[n];
    }
}