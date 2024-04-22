import java.util.Scanner;
import java.util.HashMap;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Integer> hm = new HashMap<>();
    static int n;

    public static void main(String[] args) {
        n = sc.nextInt();

        int max = 0;
        for (int i = 0; i < n; i++) {
            String s = sc.next();

            int cnt = hm.getOrDefault(s, 0);
            hm.put(s, cnt + 1);
            max = Math.max(max, cnt + 1);
        }

        System.out.println(max);
    }
}