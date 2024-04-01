import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];

        x[0] = sc.nextInt();
        y[0] = sc.nextInt();

        int minIdx = 0;
        int min = x[0];
        int maxIdx = 0;
        int max = y[0];
        for (int i = 1; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();

            if (min > x[i]) {
                minIdx = i;
                min = x[i];
            }
            if (max < y[i]) {
                maxIdx = i;
                max = y[i];
            }
        }
        
        int ans = Integer.MAX_VALUE;
        int a = -1;
        int b = -1;
        for (int i = 0; i < n; i++) {
            if (i == minIdx) continue;

            if (a == -1 && b == -1) {
                a = x[i];
                b = y[i];
                continue;
            }

            a = Math.min(a, x[i]);
            b = Math.max(b, y[i]);
        }
        ans = Math.min(b - a, ans);

        a = -1;
        b = -1;
        for (int i = 0; i < n; i++) {
            if (i == maxIdx) continue;

            if (a == -1 && b == -1) {
                a = x[i];
                b = y[i];
                continue;
            }

            a = Math.min(a, x[i]);
            b = Math.max(b, y[i]);
        }
        ans = Math.min(b - a, ans);

        System.out.println(ans);
    }
}