import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        if (this.x == p.x) return this.y - p.y;
        else return this.x - p.x;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<Point> ts = new TreeSet<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ts.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            int k = Integer.parseInt(br.readLine());
            Point p = ts.ceiling(new Point(k, 0));

            if(p == null) sb.append("-1 -1\n");
            else {
                sb.append(p.x).append(" ").append(p.y).append("\n");
                ts.remove(p);
            }
        }

        System.out.println(sb);
    }
}