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
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ts.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());
            Integer ans = ts.ceiling(num);
            if(ans != null) {
                System.out.println(ans);
            }
            else {
                System.out.println(-1);
            }
        }
    }
}