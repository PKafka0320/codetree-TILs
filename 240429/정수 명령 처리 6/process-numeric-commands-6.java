import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String op = st.nextToken();
            if (op.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                pq.add(num);
            }
            else if (op.equals("pop")) {
                sb.append(pq.poll()).append("\n");
            }
            else if (op.equals("size")) {
                sb.append(pq.size()).append("\n");
            }
            else if (op.equals("empty")) {
                if (pq.isEmpty()) sb.append("1\n");
                else sb.append("0\n");
            }
            else if (op.equals("top")) {
                sb.append(pq.peek()).append("\n");
            }
        }
        System.out.println(sb);
    }
}