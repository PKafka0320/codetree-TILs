import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Pair other) {
        if (this.b == other.b) return this.a - other.a;
        return this.b - other.b;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        TreeSet<Integer> redS = new TreeSet<>(); // 빨간돌
        ArrayList<Pair> blackStones = new ArrayList<>(); // 검은돌

        for (int idx = 0; idx < c; idx++) {
            redS.add(Integer.parseInt(br.readLine()));
        }

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            blackStones.add(new Pair(a, b));
        }

        Collections.sort(blackStones);

        int count = 0; // 짝지어지는 빨간돌과 검정돌의 쌍 개수
        for (int idx = 0; idx < n; idx++) {
            int a = blackStones.get(idx).a;
            int b = blackStones.get(idx).b;

            if (redS.ceiling(a) == null) continue;

            int t = redS.ceiling(a);
            if (t <= b) {
                count++;
                redS.remove(t);
            }
        }

        System.out.println(count);
    }
}