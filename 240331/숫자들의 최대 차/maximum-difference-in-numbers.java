import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        int min = 1000;
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }

        if (max - min <= k) {
            System.out.println(n);
            return;
        }

        int ans = 0;
        for (int i = min; i <= max - k; i++) {
            int cnt = 0;
            for (int idx = 0; idx < n; idx++) {
                if (arr[idx] >= i && arr[idx] <= i + k) cnt++;
            }
            // System.out.println("min: " + i + ", cnt: " + cnt);

            ans = Math.max(cnt, ans);
        }

        System.out.println(ans);
    }
}