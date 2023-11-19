import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int answer = bst(n);

        System.out.println(answer);
    }

    public static int bst(int n) {
        if (n <= 2) {
            return n;
        }
        int[] memo = new int[n + 1];

        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 2;

        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                memo[i] += memo[j] * memo[i - j -1];
            }
        }

        return memo[n];
    }
}