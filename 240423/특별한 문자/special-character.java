import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Character, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String s = br.readLine();

        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            hm.put(c[i], hm.getOrDefault(c[i], 0) + 1);
        }

        char ans = '.';
        for (char ch : hm.keySet()) {
            if (hm.get(ch) != 1) continue;
            ans = ch;
            break;
        }
        
        if (ans != '.') {
            System.out.println(ans);
        }
        else {
            System.out.println("None");
        }
    }
}