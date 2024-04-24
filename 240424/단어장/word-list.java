import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static int n;
    static TreeMap<String, Integer> tm = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            tm.put(s, tm.getOrDefault(s, 0) + 1);
        }

        Iterator<Entry<String, Integer>> it = tm.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            sb = new StringBuilder();
            sb.append(entry.getKey()).append(" ").append(entry.getValue());
            System.out.println(sb);
        }
    }
}