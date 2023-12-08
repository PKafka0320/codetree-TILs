import java.util.*;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static ArrayList<Pair> pairs = new ArrayList<>();
    static ArrayList<Pair> selected = new ArrayList<>();
    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            pairs.add(new Pair(x, y));
        }

        select(0, 0);

        System.out.println(minDist);
    }

    public static void select(int index, int count) {
        if (count >= M) {
            findDist();
            return;
        }
        if (index >= N) {
            return;
        }
        selected.add(pairs.get(index));
        select(index + 1, count + 1);
        selected.remove(selected.size() - 1);
        select(index + 1, count);
    }

    public static void findDist() {
        int maxDist = 0;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        for (int fromIndex = 0; fromIndex < selected.size() - 1; fromIndex++) {
            Pair from = selected.get(fromIndex);
            int fx = from.x;
            int fy = from.y;

            for (int toIndex = fromIndex + 1; toIndex < selected.size(); toIndex++) {
                Pair to = selected.get(toIndex);
                int tx = to.x;
                int ty = to.y;

                int dist = (Math.abs(fx - tx) * Math.abs(fx - tx)) + (Math.abs(fy - ty) * Math.abs(fy - ty));
                if (dist > maxDist) {
                    maxDist = dist;
                    x1 = fx;
                    y1 = fy;
                    x2 = tx;
                    y2 = ty;
                }
            }
        }

        int finalDist = (Math.abs(x1 - x2) * Math.abs(x1 - x2)) + (Math.abs(y1 - y2) * Math.abs(y1 - y2));
        if (finalDist < minDist) {
            minDist = finalDist;
        }
    }
}