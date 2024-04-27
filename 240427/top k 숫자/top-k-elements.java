import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        TreeSet<Integer> ts = new TreeSet<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            ts.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        while(k > 0) {
            sb.append(ts.first()).append(" ");
            ts.remove(ts.first());
            k--;
        }
        System.out.println(sb);
    }
}