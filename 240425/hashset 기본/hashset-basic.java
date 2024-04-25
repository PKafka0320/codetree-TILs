import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> hs = new HashSet<>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int x = Integer.parseInt(st.nextToken());
                hs.add(x);
            }
            else if (cmd.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());
                hs.remove(x);
            }
            else if (cmd.equals("find")) {
                int x = Integer.parseInt(st.nextToken());
                System.out.println(hs.contains(x));
            }
        }
    }
}