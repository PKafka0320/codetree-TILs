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
        ts.add(-1);
        ts.add(n + 1);

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int M = 0; M < m; M++) {
            int num = Integer.parseInt(st.nextToken());
            ts.add(num);

            int max = 0;
            for (int tmp : ts) {
                if (tmp == n + 1) break;
                int higher = ts.higher(tmp);
                int diff = higher - tmp - 1;
                max = Math.max(max, diff);
            }
            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }
}