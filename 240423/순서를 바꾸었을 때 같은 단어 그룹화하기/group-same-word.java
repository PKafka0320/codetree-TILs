import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static HashMap<String, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String ns = String.valueOf(c);
            hm.put(ns, hm.getOrDefault(ns, 0) + 1);
        }

        int ans = 0;
        for (String s : hm.keySet()) {
            ans = Math.max(ans, hm.get(s));
        }

        System.out.println(ans);
    }
}