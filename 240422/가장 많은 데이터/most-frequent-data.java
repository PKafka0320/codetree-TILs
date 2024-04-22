import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static HashMap<String, Integer> hm = new HashMap<>();
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        int max = 0;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            int cnt = hm.getOrDefault(s, 0);
            hm.put(s, cnt + 1);
            max = Math.max(max, cnt + 1);
        }

        System.out.println(max);
    }
}