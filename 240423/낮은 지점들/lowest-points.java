import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static HashMap<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (hm.containsKey(x)) {
                hm.put(x, Math.min(y, hm.get(x)));
            }
            else {
                hm.put(x, y);
            }
        }

        long sum = 0;
        for (int x : hm.keySet()) {
            sum += hm.get(x);
        }

        System.out.println(sum);
    }
}