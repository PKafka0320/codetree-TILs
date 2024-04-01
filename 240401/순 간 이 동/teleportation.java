import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        int cnt = Math.abs(a - b);

        int tmp = Math.abs(a - x) + Math.abs(b - y);
        cnt = Math.min(cnt, tmp);

        tmp = Math.abs(a - y) + Math.abs(b - x);
        cnt = Math.min(cnt, tmp);

        System.out.println(cnt);
    }
}