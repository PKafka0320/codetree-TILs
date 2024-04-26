import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < n; i ++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if (op.equals("add")) {
                int x = Integer.parseInt(st.nextToken());
                ts.add(x);
            }
            else if (op.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());
                ts.remove(x);
            }
            else if (op.equals("find")) {
                int x = Integer.parseInt(st.nextToken());
                System.out.println(ts.contains(x));
            }
            else if (op.equals("lower_bound")) {
                int x = Integer.parseInt(st.nextToken());
                if (ts.ceiling(x) != null) {
                    System.out.println(ts.ceiling(x));
                }
                else {
                    System.out.println("None");
                }
            }
            else if (op.equals("upper_bound")) {
                int x = Integer.parseInt(st.nextToken());
                if (ts.higher(x) != null) {
                    System.out.println(ts.higher(x));
                }
                else {
                    System.out.println("None");
                }
            }
            else if (op.equals("largest")) {
                if (ts.isEmpty()) {
                    System.out.println("None");
                }
                else {
                    System.out.println(ts.last());
                }
            }
            else if (op.equals("smallest")) {
                if (ts.isEmpty()) {
                    System.out.println("None");
                }
                else {
                    System.out.println(ts.first());
                }
            }
        }
    }
}