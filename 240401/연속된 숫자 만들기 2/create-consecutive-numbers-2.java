import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int min = Math.min(Math.min(a, b), c);
        int max = Math.max(Math.max(a, b), c);
        int mid;
        if (min == a || max == a) {
            if (min == b || max == b) {
                mid = c;
            }
            else mid = b;
        }
        else mid = a;

        a = min;
        b = mid;
        c = max;
        
        int cnt = 0;
        while (Math.max(Math.max(a, b), c) - Math.min(Math.min(a, b), c) > 2) {
            int diff1 = b - a;
            int diff2 = c - b;

            if (diff1 > 2 && diff2 > 2) {
                a = b;
                b += 2;
            }
            else if (diff1 == 2) {
                c = b;
                b -= 1;
            }
            else if (diff2 == 2) {
                a = b;
                b += 1;
            }
            else if (diff1 == 1) {
                a = b;
                b += 2;
            }
            else if (diff2 == 1) {
                c = b;
                b -= 2;
            }

            // System.out.println(a + ", " + b + ", " + c);
            cnt++;
        }

        System.out.println(cnt);
    }
}