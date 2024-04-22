import java.util.Scanner;
import java.util.HashMap;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Integer> hm = new HashMap<>();
    static int n, m;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < m; i++) {
            int num = sc.nextInt();
            System.out.print(hm.getOrDefault(num, 0) + " ");
        }
    }
}