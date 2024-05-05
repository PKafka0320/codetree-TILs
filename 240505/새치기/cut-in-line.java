import java.io.*;
import java.util.*;

class Person {
    int num;
    Person prev, next;

    public Person(int n) {
        this.num = n;
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
        int Q = Integer.parseInt(st.nextToken());

        Person[] people = new Person[N + 1];
        for (int i = 1; i <= N; i++) people[i] = new Person(i);

        // 각 줄의 맨 앞 사람을 기록하면서 앞 뒤 사람 연결
        Person[] headline = new Person[M + 1];
        for (int m = 1; m <= M; m++) {
            Person cur = headline[m];

            st = new StringTokenizer(br.readLine());
            int lineCount = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= lineCount; j++) {
                int num = Integer.parseInt(st.nextToken());
                Person p = people[num];

                if (j != 1) {
                    cur.next = p;
                    p.prev = cur;
                }
                else headline[m] = p;
                cur = p;
            }
        }

        // 연산
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                Person pfrom = people[from];
                Person pto = people[to];

                // 새치기 하는 사람이 맨 앞일 경우 다음 사람을 기록
                for (int m = 1; m <= M; m++) {
                    if (headline[m] != pfrom) continue;
                    headline[m] = pfrom.next;
                    break;
                }
                // 새치기 당하는 사람이 맨 앞일 경우 새치기 하는 사람을 기록
                for (int m = 1; m <= M; m++) {
                    if (headline[m] != pto) continue;
                    headline[m] = pfrom;
                    break;
                }

                // 연결 수정
                if (pfrom.next != null) pfrom.next.prev = pfrom.prev;
                if (pfrom.prev != null) pfrom.prev.next = pfrom.next;
                if (pto.prev != null) pto.prev.next = pfrom;
                pfrom.prev = pto.prev;
                pfrom.next = pto;
                pto.prev = pfrom;
            }
            else if (op == 2) {
                int num = Integer.parseInt(st.nextToken());

                Person p = people[num];

                // 집으로 가는 사람이 맨 앞일 경우 다음 사람을 기록
                for (int m = 1; m <= M; m++) {
                    if (headline[m] != p) continue;
                    headline[m] = p.next;
                    break;
                }

                // 연결 수정
                if (p.prev != null) p.prev.next = p.next;
                if (p.next != null) p.next.prev = p.prev;
                p.prev = p.next = null;
            }
            else if (op == 3) {
                int froma = Integer.parseInt(st.nextToken());
                int fromb = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                Person pfroma = people[froma];
                Person pfromb = people[fromb];
                Person pto = people[to];

                // 새치기 하는 사람이 맨 앞일 경우 마지막 사람의 다음 사람을 기록
                for (int m = 1; m <= M; m++) {
                    if (headline[m] != pfroma) continue;
                    headline[m] = pfromb.next;
                    break;
                }
                // 새치기 당하는 사람이 맨 앞일 경우 처음 사람을 기록
                for (int m = 1; m <= M; m++) {
                    if (headline[m] != pto) continue;
                    headline[m] = pfroma;
                    break;
                }

                // 연결 수정
                if (pfroma.prev != null) pfroma.prev.next = pfromb.next;
                if (pfromb.next != null) pfromb.next.prev = pfroma.prev;
                if (pto.prev != null) pto.prev.next = pfroma;
                pfroma.prev = pto.prev;
                pto.prev = pfromb;
                pfromb.next = pto;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            Person p = headline[i];
            if (p == null) {
                sb.append("-1\n");
                continue;
            }
            while (p != null) {
                sb.append(p.num).append(" ");
                p = p.next;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}