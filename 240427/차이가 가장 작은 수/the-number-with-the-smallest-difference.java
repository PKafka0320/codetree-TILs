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

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            Integer floor = ts.floor(num - m);
            Integer ceiling = ts.ceiling(num + m);

            if (floor != null) {
                int diff = num - floor;
                if (diff < ans) {
                    ans = diff;
                }
            }

            if (ceiling != null) {
                int diff = ceiling - num;
                if (diff < ans) {
                    ans = diff;
                }
            }
        }

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}