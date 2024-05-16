import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int position, value, index;

    public Point(int p, int v, int i) {
        this.position = p;
        this.value = v;
        this.index = i;
    }

    @Override
    public int compareTo(Point p) {
        return this.position - p.position;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 구간의 개수
        TreeSet<Point> points = new TreeSet<>(); // 각 점의 위치 집합

        // 구간의 시작과 끝 점 입력
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(token.nextToken());
            int x2 = Integer.parseInt(token.nextToken());
            points.add(new Point(x1, 1, idx)); // 시작점
            points.add(new Point(x2, -1, idx)); // 끝점
        }

        int answer = 0;
        int count = 0;
        for (Point point : points) {
            count += point.value; // 현재 위치의 구간의 개수
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
}