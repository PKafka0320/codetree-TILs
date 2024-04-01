import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++){ 
            arr[i] = sc.nextInt();
        }

        int ans = 0;
        int start = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (i <= end) continue;

            if (arr[i] == 1) {
                if (start == -1) {
                    start = i;
                    end = -1;
                    continue;
                }
            }

            if (i - start == m) {
                ans++;
                start = -1;
                end = i + m;
            }
        }
        if (start != -1) ans++;

        System.out.println(ans);
    }
}