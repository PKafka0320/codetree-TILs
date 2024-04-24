import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n;
    static TreeMap<Integer, Integer> tm = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (tm.containsKey(num)) continue;
            tm.put(num, i);
        }

        Iterator<Entry<Integer, Integer>> it = tm.entrySet().iterator();
        while(it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append(" ");
            sb.append(entry.getValue());
            sb.append("\n");
            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
    }
}