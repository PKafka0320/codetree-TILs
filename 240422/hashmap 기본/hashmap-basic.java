import java.util.Scanner;
import java.util.HashMap;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Integer> m = new HashMap<>();
    static int n;

    public static void main(String[] args) {
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String op = sc.next();
            int k = sc.nextInt();

            if (op.equals("add")) {
                int v = sc.nextInt();
                m.put(k, v);
            }
            else if (op.equals("remove")) {
                m.remove(k);
            }
            else if (op.equals("find")) {
                if (m.containsKey(k)) {
                    System.out.println(m.get(k));
                }
                else {
                    System.out.println("None");
                }
            }
        }
    }
}