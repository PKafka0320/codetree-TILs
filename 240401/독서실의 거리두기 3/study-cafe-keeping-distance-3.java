import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        char[] list = sc.next().toCharArray();

        int max = 0;
        int min = 1001;

        int last = 0;
        for (int i = 1; i < n; i++) {
            if (list[i] == '0') continue;

            max = Math.max(max, i - last);
            min = Math.min(min, i - last);
            last = i;
        }

        System.out.print(Math.min(min, max / 2));
    }
}