import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        while (pq.size() > 1) {
            System.out.println(pq);
            int poll1 = pq.poll();
            int poll2 = pq.poll();

            if (poll1 == poll2) continue;
            pq.add(poll1 - poll2);
        }

        if (pq.size() == 0) System.out.println(pq.peek());
        else System.out.println(-1);
    }
}