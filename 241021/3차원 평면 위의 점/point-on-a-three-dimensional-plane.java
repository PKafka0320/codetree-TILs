import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Tuple implements Comparable<Tuple> {
    int cost, x, y;

    public Tuple(int cost, int x, int y) {
        this.cost = cost;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Tuple t) {
        return this.cost - t.cost; // 비용 기준 오름차순 정렬을 진행합니다.
    }
}

class Point {
    int x, y, z, idx;

    Point(int x, int y, int z, int idx) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
};

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n;
    
    public static ArrayList<Tuple> edges = new ArrayList<>();
    
    public static int[] uf = new int[MAX_N + 1];
    
    public static ArrayList<Point> points = new ArrayList<>();
    
    public static boolean cmpx(Point a, Point b) {
        return a.x < b.x;
    }

    public static class CmpX implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            // x순으로 오름차순 정렬을 진행합니다.
            return p1.x - p2.x;
        }
    };
    
    public static class CmpY implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            // y순으로 오름차순 정렬을 진행합니다.
            return p1.y - p2.y;
        }
    };
    
    public static class CmpZ implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            // z순으로 오름차순 정렬을 진행합니다.
            return p1.z - p2.z;
        }
    };
    
    public static int find(int x) {
        if(uf[x] == x)
            return x;
        return uf[x] = find(uf[x]);
    }
    
    public static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        uf[X] = Y;
    }
    
    public static void push(Point p1, Point p2) {
        int cost = Math.min(Math.min(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y)), Math.abs(p1.z - p2.z));
        edges.add(new Tuple(cost, p1.idx, p2.idx));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();

        // n개의 점의 정보를 전부 입력받습니다.
        for(int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            points.add(new Point(x, y, z, i));
        }

        // 모든 점을 x 순으로 정렬합니다.
        Collections.sort(points, new CmpX());
        // 각 정렬된 점에서 연속한 순서대로 간선을 이어줍니다.
        for(int i = 0; i < n - 1; i++)
            push(points.get(i), points.get(i + 1));

        // 모든 점을 y 순으로 정렬합니다.
        Collections.sort(points, new CmpY());
        // 각 정렬된 점에서 연속한 순서대로 간선을 이어줍니다.
        for(int i = 0; i < n - 1; i++)
            push(points.get(i), points.get(i + 1));

        // 모든 점을 z 순으로 정렬합니다.
        Collections.sort(points, new CmpZ());
        // 각 정렬된 점에서 연속한 순서대로 간선을 이어줍니다.
        for(int i = 0; i < n - 1; i++)
            push(points.get(i), points.get(i + 1));

        // cost 순으로 오름차순 정렬을 진행합니다.
        Collections.sort(edges);

        // uf 값을 초기값을 적어줍니다.
        for(int i = 1; i <= n; i++)
            uf[i] = i;
        
        // cost가 낮은 간선부터 순서대로 보며
        // 아직 두 노드가 연결이 되어있지 않을 경우에만
        // 해당 간선을 선택하고 두 노드를 합쳐주면서
        // mst를 만들어줍니다.
        int ans = 0;
        for(int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).cost;
            int x = edges.get(i).x;
            int y = edges.get(i).y;

            // x, y가 연결되어 있지 않다면
            if(find(x) != find(y)) {
                // 해당 간선은 MST에 속하는 간선이므로
                // 답을 갱신해주고
                // 두 노드를 연결해줍니다.

                ans += cost;
                union(x, y);
            }
        }

        System.out.print(ans);
    }
}