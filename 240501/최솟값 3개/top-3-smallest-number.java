import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        long res = 1;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (pq.size() < 2) {
                pq.add(num);
                res *= num;
                sb.append("-1\n");
                continue;
            }
            else if (pq.size() == 2) {
                pq.add(num);
                res *= num;
                sb.append(res).append("\n");
                continue;
            }

            int max = pq.peek();
            if (max > num) {
                res /= pq.poll();
                pq.add(num);
                res *= num;
            }
            sb.append(res).append("\n");
        }

        System.out.println(sb);
    }
}