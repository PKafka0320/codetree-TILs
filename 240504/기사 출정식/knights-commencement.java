import java.io.*;
import java.util.*;

class Knight {
    int num;
    Knight prev, next;

    public Knight(int num) {
        this.num = num;
        this.prev = this.next = null;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // Doubly-LinkedList 생성
        Knight[] knights = new Knight[N + 1];
        st = new StringTokenizer(br.readLine());
        knights[1] = new Knight(Integer.parseInt(st.nextToken())); // 처음 기사
        for (int i = 2; i <= N; i++) {
            knights[i] = new Knight(Integer.parseInt(st.nextToken()));

            // 앞 뒤의 기사 연결
            knights[i].prev = knights[i - 1];
            knights[i - 1].next = knights[i];
        }
        // 첫 기사와 마지막 기사 연결
        knights[N].next = knights[1];
        knights[1].prev = knights[N];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            Knight call = knights[Integer.parseInt(br.readLine())];

            // 양 옆의 기사 번호 확인
            Knight next = call.next;
            Knight prev = call.prev;
            sb.append(next.num).append(" ").append(prev.num).append("\n");

            // 호명된 기사를 list에서 제거
            prev.next = call.next;
            next.prev = call.prev;
            call.prev = call.next = null;
        }

        System.out.println(sb);
    }
}