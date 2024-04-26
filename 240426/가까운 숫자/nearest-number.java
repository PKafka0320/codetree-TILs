import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            ts.add(num);

            if (ts.lower(num) != null) {
                ans = Math.min(ans, num - ts.lower(num));
            }
            if (ts.higher(num) != null) {
                ans = Math.min(ans, ts.higher(num) - num);
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}