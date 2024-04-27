import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(Integer.parseInt(br.readLine()));

        int ans = -1;
        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            Integer floor = ts.floor(num - m);
            Integer ceiling = ts.ceiling(num + m);

            if (floor != null) {
                int diff = num - floor;
                if (ans == -1 || diff < ans) {
                    ans = diff;
                }
            }

            if (ceiling != null) {
                int diff = ceiling - num;
                if (ans == -1 || diff < ans) {
                    ans = diff;
                }
            }
        }

        System.out.println(ans);
    }
}