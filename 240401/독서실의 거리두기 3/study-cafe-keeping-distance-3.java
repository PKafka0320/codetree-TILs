import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();

        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        for (int i = 1; i < n; i++) {
            count++;
            if (str.charAt(i) == '1') {
                list.add(count);
                count = 0;
            }
        }

        Collections.sort(list);

        int ans = Math.min(list.get(0), list.get(list.size() - 1) / 2);
        System.out.println(ans);
    }
}