import java.io.*;
import java.util.*;

class Student {
    int num;
    Student prev, next;

    public Student(int n) {
        this.num = n;
        this.prev = this.next = null;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        HashMap<Integer, Student> hm = new HashMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Student start = null;
            Student end = null;

            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                hm.put(num, new Student(num));

                if (j == 0) {
                    end = start = hm.get(num);
                    start.prev = start.next = start;
                }
                else {
                    Student tmp = hm.get(num);
                    tmp.prev = end;
                    tmp.next = start;
                    start.prev = tmp;
                    end.next = tmp;
                    end = tmp;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                Student a = hm.get(Integer.parseInt(st.nextToken()));
                Student b = hm.get(Integer.parseInt(st.nextToken()));

                a.next.prev = b.prev;
                b.prev.next = a.next;
                a.next = b;
                b.prev = a;
            }
            else if (op == 2) {
                Student a = hm.get(Integer.parseInt(st.nextToken()));
                Student b = hm.get(Integer.parseInt(st.nextToken()));

                a.prev.next = b;
                b.prev.next = a;
                Student tmp = a.prev;
                a.prev = b.prev;
                b.prev = tmp;
            }
            else if (op == 3) {
                Student a = hm.get(Integer.parseInt(st.nextToken()));
                
                Student min = a, cur = a;
                cur = cur.next;
                while (cur != a) {
                    if (cur.num < min.num) min = cur;
                    cur = cur.next;
                }

                cur = min;
                answer.append(cur.num).append(" ");
                cur = cur.prev;
                while (cur != min) {
                    answer.append(cur.num).append(" ");
                    cur = cur.prev;
                }
            }
        }

        System.out.println(answer);
    }
}