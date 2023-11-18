import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int answer = tabul(n);

        System.out.println(answer);
    }

    public static int tabul(int n) {
        if (n <= 1) {
            return 2;
        }
        if (n <= 2) {
            return 7;
        }

        int[] memo = new int[n + 1];
        memo[1] = 2;
        memo[2] = 7;

        for (int idx = 3; idx <= n; idx++) {
            memo[idx] = (memo[idx - 1] * 2 + memo[idx - 2] * 4) % 1_000_000_007;
        }
        return memo[n];
    }
}