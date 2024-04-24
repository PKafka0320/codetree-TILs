import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static TreeMap<Integer, Integer> tm = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();
            if (s.equals("add")) {
                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                tm.put(k, v);
            }
            else if (s.equals("remove")) {
                int k = Integer.parseInt(st.nextToken());

                tm.remove(k);
            }
            else if (s.equals("find")) {
                int k = Integer.parseInt(st.nextToken());
                int v = tm.getOrDefault(k, 0);

                if (v == 0) System.out.println("None");
                else System.out.println(v);
            }
            else if (s.equals("print_list")) {
                if (tm.isEmpty()) {
                    System.out.println("None");
                    continue;
                }

                Iterator<Entry<Integer, Integer>> it = tm.entrySet().iterator();

                while (it.hasNext()) {
                    Entry<Integer, Integer> entry = it.next();
                    System.out.print(entry.getValue() + " ");
                }
                System.out.println();
            }
        }
    }
}