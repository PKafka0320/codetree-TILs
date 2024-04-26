import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TreeSet<Integer> ts = new TreeSet<>();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();

                if (op.equals("I")) {
                    int num = Integer.parseInt(st.nextToken());
                    ts.add(num);
                }
                else {
                    if (ts.isEmpty()) continue;

                    int type = Integer.parseInt(st.nextToken());
                    if (type == 1) {
                        ts.remove(ts.last());
                    }
                    else {
                        ts.remove(ts.first());
                    }
                }
            }

            if (ts.isEmpty()) {
                System.out.println("EMPTY");
            }
            else {
                System.out.printf("%d %d\n", ts.last(), ts.first());
            }
        }
    }
}