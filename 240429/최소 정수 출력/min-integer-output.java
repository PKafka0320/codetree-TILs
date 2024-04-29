import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 0) pq.add(num);
            else if (pq.isEmpty()) sb.append("0\n");
            else {
                int peek = pq.peek();
                sb.append(peek).append("\n");
                pq.remove(peek);
            }
        }
        System.out.println(sb);
    }
}