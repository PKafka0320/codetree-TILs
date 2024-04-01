import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m1 = Integer.MAX_VALUE;
        int m2 = Integer.MIN_VALUE;
        int n1 = Integer.MAX_VALUE;
        int n2 = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            m1 = Math.min(m1, x);
            m2 = Math.max(m2, x);
            n1 = Math.min(n1, y);
            n2 = Math.max(n2, y);
        }

        System.out.println((m2 - m1) * (n2 - n1));
    }
}