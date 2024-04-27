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
        int ans = 0;
        for (int i = 0 ; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            
            boolean isOver = true;
            for (int j = num; j > 0; j--) {
                if (ts.contains(j)) continue;
                ts.add(j);
                isOver = false;
                ans++;
                break;
            }
            
            if (isOver) break;
        }

        System.out.println(ans);
    }
}