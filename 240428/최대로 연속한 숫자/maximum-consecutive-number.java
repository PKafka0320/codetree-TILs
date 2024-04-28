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
        for (int i = 0; i <= n; i++) {
            ts.add(i);
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int M = 0; M < m; M++) {
            int num = Integer.parseInt(st.nextToken());
            ts.remove(num);

            int len = 0;
            int tmp = 0;
            for (int i = 0; i <= n; i++) {
                if (ts.contains(i)) tmp++;
                else {
                    len = Math.max(tmp, len);
                    tmp = 0;
                }
            }
            len = Math.max(tmp, len);

            sb.append(len).append("\n");
        }

        System.out.println(sb);
    }
}