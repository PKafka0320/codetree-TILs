import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] up = new int[n];
        int[] down = new int[n];

        for (int idx = 0; idx < n; idx++) {
            up[idx] = sc.nextInt();
        }
        for (int idx = 0; idx < n; idx++) {
            down[idx] = sc.nextInt();
        }

        for (int time = 0; time < t; time++) {
            int tempUp = up[n - 1];
            int tempDown = down[n - 1];

            for (int idx = n - 1; idx >= 1; idx--) {
                up[idx] = up[idx - 1];
            }
            for (int idx = n - 1; idx >= 1; idx--) {
                down[idx] = down[idx - 1];
            }

            up[0] = tempDown;
            down[0] = tempUp;
        }

        for (int idx = 0; idx < n; idx++) {
            System.out.print(up[idx] + " ");
        }
        System.out.println();
        for (int idx = 0; idx < n; idx++) {
            System.out.print(down[idx] + " ");
        }
    }
}