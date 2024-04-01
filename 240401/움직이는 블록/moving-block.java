import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        int avg = sum / n;

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > avg) {
                ans += arr[i] - avg;
            }
        }

        System.out.println(ans);
    }
}