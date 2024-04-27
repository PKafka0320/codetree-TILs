import java.util.Scanner;
import java.util.TreeSet;

class Pair implements Comparable<Pair> {
    int l, p;

    public Pair(int l, int p) {
        this.l = l;
        this.p = p;
    }

    @Override
    public int compareTo(Pair p2) {
        if(this.l != p2.l)
            return this.l - p2.l;  // l 기준 오름차순 정렬
        else
            return this.p - p2.p;  // l이 같다면, p 기준 오름차순 정렬
    }
}

public class Main {
    // 변수 선언
    public static int n, m;
    
    public static TreeSet<Pair> problems = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int p = sc.nextInt();
            int l = sc.nextInt();

            // 주어진 문제를 treeset에 넣어줍니다.
            problems.add(new Pair(l, p));
        }

        m = sc.nextInt();
        // m개의 명령을 수행합니다.
        for(int i = 0; i < m; i++) {
            String command = sc.next();

            if(command.equals("ad")) {
                int p = sc.nextInt();
                int l = sc.nextInt();

                // 문제를 추가합니다.
                problems.add(new Pair(l, p));
            }
            else if(command.equals("sv")) {
                int p = sc.nextInt();
                int l = sc.nextInt();

                // 문제를 제거힙니다.
                problems.remove(new Pair(l, p));
            }
            else {
                int x = sc.nextInt();
                // x가 1이면 난이도가 가장 높은 문제의 번호를 출력합니다.
                if(x == 1)
                    System.out.println(problems.last().p);
                // x가 -1이면 난이도가 가장 낮은 문제의 번호를 출력합니다.
                else
                    System.out.println(problems.first().p);
            }
        }
    }
}