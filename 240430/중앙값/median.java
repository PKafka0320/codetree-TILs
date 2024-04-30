import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int M = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> higher = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            higher.add(Integer.parseInt(st.nextToken()));
            sb.append(higher.peek()).append(" ");

            for (int m = 1; m < M; m++) {
                int num = Integer.parseInt(st.nextToken());

                if (num > higher.peek()) higher.add(num);
                else lower.add(num);

                if (higher.size() - lower.size() > 1) lower.add(higher.poll());
                else if (lower.size() > higher.size()) higher.add(lower.poll());

                if (m % 2 == 0) sb.append(higher.peek()).append(" ");
            }

            System.out.println(sb);
        }
    }
}