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
        if ((Math.abs(this.x) + Math.abs(this.y)) != (Math.abs(p.x) + Math.abs(p.y))) 
            return (Math.abs(this.x) + Math.abs(this.y)) - (Math.abs(p.x) + Math.abs(p.y));
        if (this.x != p.x) return this.x - p.x;
        return this.y - p.y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Point> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {   
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Point(x, y));
        }

        for (int i = 0; i < m; i++) {
            Point p = pq.poll();
            pq.add(new Point(p.x + 2, p.y + 2));
        }

        StringBuilder sb = new StringBuilder();
        Point ans = pq.peek();
        sb.append(ans.x).append(" ").append(ans.y);
        System.out.println(sb);
    }
}