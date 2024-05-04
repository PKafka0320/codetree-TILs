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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());
        HashMap<Integer, Student> hm = new HashMap<>();

        // 초기화
        Student initS = new Student(1);
        initS.prev = initS.next = initS;
        hm.put(1, initS);
        int curNum = 1;

        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                Student a = hm.get(Integer.parseInt(st.nextToken()));
                int num = Integer.parseInt(st.nextToken());

                // a의 다음 위치에 마지막으로 추가되는 학생부터 추가
                for (int i = num; i > 0; i--) {
                    Student newS = new Student(curNum + i);
                    newS.prev = a;
                    newS.next = a.next;
                    a.next.prev = newS;
                    a.next = newS;
                    hm.put(curNum + i, newS);
                }
            }
            else if (op == 2) {
                Student a = hm.get(Integer.parseInt(st.nextToken()));
                int num = Integer.parseInt(st.nextToken());

                // a의 이전 위치에 처음으로 추가되는 학생부터 추가
                for (int i = 1; i <= num; i++) {
                    Student newS = new Student(curNum + i);
                    newS.next = a;
                    newS.prev = a.prev;
                    a.prev.next = newS;
                    a.prev = newS;
                    hm.put(curNum + i, newS);
                }
            }
            else if (op == 3) {
                Student a = hm.get(Integer.parseInt(st.nextToken()));

                Student prev = a.prev;
                Student next = a.next;

                if (prev == next) sb.append("-1\n");
                else sb.append(prev.num).append(" ").append(next.num).append("\n");
            }
        }

        System.out.println(sb);
    }
}