import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static HashMap<String, Integer> sti = new HashMap<>();
    static HashMap<Integer, String> its = new HashMap<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i ++) {
            String s = br.readLine();
            sti.put(s, i);
            its.put(i, s);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            
            if (sti.containsKey(s)) {
                sb.append(sti.get(s) + "\n");
            }
            else {
                sb.append(its.get(Integer.parseInt(s)) + "\n");
            }
        }
        System.out.println(sb);
    }
}