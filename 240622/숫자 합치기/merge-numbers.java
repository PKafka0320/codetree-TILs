import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 숫자의 개수

        PriorityQueue<Long> pq = new PriorityQueue<>(); // 숫자 목록

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            pq.add(Long.parseLong(tokenizer.nextToken()));
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            long a = pq.poll();

            if (pq.isEmpty()) {
                break;
            }

            long b = pq.poll();
            
            sum += a + b;
            pq.add(a + b);
        }

        System.out.println(sum);
    }
}