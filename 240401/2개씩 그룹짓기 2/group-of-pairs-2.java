import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // System.out.printf("|%d - %d| = %d\n", arr[n + i], arr[i], Math.abs(arr[n + i] - arr[i]));
            ans = Math.min(ans, Math.abs(arr[n + i] - arr[i]));
        }

        System.out.println(ans);
    }
}