import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = 0;
        int b = 0;
        int cur = 3; // 1: a, 2: b, 3: ab

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char obj = sc.next().charAt(0);
            int p = sc.nextInt();

            if (obj == 'A') a += p;
            else b += p;

            if (a > b && cur != 1) {
                cnt++;
                cur = 1;
            }
            else if (a < b && cur != 2) {
                cnt++;
                cur = 2;
            }
            else if (a == b && cur != 3) {
                cnt++;
                cur = 3;
            }
        }

        System.out.println(cnt);
    }
}