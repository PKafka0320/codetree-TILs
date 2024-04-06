import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int[] a;
    static int[] b;
    static int[][] point;

    public static void main(String[] args) {
        n = sc.nextInt();
        a = new int[n + 1];
        b = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextInt();
        }

        point = new int[n + 1][n + 1]; // 0: 버리지 않은 경우, 1: 버린 경우
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++) point[i][j] = -1;

        point[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (point[i][j] == -1) continue;

                if (a[i + 1] > b[j + 1]) {
                    point[i][j + 1] = Math.max(point[i][j + 1], point[i][j] + b[j + 1]);
                }
                if (a[i + 1] < b[j + 1]) {
                    point[i + 1][j] = Math.max(point[i + 1][j], point[i][j]);
                }
                
                point[i + 1][j + 1] = Math.max(point[i + 1][j + 1], point[i][j]);
            }
        }

        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = Math.max(Math.max(point[i][n], point[n][i]), ans);
        }

        System.out.println(ans);
    }
}