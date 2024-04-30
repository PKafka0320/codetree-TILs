import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int M = Integer.parseInt(br.readLine());

            // 중앙값보다 작은 숫자들, 내림차순
            PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
            // 중앙값과 그보다 큰 숫자들, 오름차순
            PriorityQueue<Integer> higher = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            // 처음 값 추가
            higher.add(Integer.parseInt(st.nextToken()));
            sb.append(higher.peek()).append(" ");

            for (int m = 1; m < M; m++) {
                int num = Integer.parseInt(st.nextToken());

                // 중앙값과 비교하여 알맞는 pq에 추가
                if (num > higher.peek()) higher.add(num);
                else lower.add(num);

                // higher가 중앙값을 포함하기 때문에 higher의 크기는 lower와 같거나 차이가 1개이어야 한다.
                if (higher.size() - lower.size() > 1) lower.add(higher.poll());
                else if (lower.size() > higher.size()) higher.add(lower.poll());

                if (m % 2 == 0) sb.append(higher.peek()).append(" ");
            }

            System.out.println(sb);
        }
    }
}