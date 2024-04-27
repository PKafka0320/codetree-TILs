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
        for (int i = 1; i <= m; i++) {
            ts.add(i);
        }

        st = new StringTokenizer(br.readLine());
        int ans = 0;
        for (int i = 0 ; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            
            Integer floor = ts.floor(num);
            if (floor == null) break;
            else {
                ans++;
                ts.remove(floor);
            }
        }

        System.out.println(ans);
    }
}