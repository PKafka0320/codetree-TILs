import java.io.*;
import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static Point[] points;
    static int[] L, R;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine()); // 체크포인트 개수
        points = new Point[N + 1]; // 체크포인트
        L = new int[N + 1]; // 1번째 부터 idx번째 체크포인트까지의 거리
        R = new int[N + 1]; // idx번째 부터 N번째 체크포인트까지의 거리

        // 체크포인트 입력
        for (int idx = 1; idx <= N; idx++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            points[idx] = new Point(x, y);
        }

        // L 배열 생성
        for (int idx = 2; idx <= N; idx++) {
            Point pfrom = points[idx - 1];
            Point pto = points[idx];

            L[idx] = Math.abs(pfrom.x - pto.x) + Math.abs(pfrom.y - pto.y) + L[idx - 1];
        }

        // R 배열 생성
        for (int idx = N - 1; idx >= 1; idx--) {
            Point pfrom = points[idx + 1];
            Point pto = points[idx];

            R[idx] = Math.abs(pfrom.x - pto.x) + Math.abs(pfrom.y - pto.y) + R[idx + 1];
        }

        // 건너뛰는 체크포인트마다 거리값 계산 및 최소 거리 갱신
        int ans = Integer.MAX_VALUE;
        for (int idx = 2; idx <= N - 1; idx++) {
            Point pfrom = points[idx - 1];
            Point pto = points[idx + 1];

            int dist = Math.abs(pfrom.x - pto.x) + Math.abs(pfrom.y - pto.y) + L[idx - 1] + R[idx + 1];
            ans = Math.min(ans, dist);
        }
        System.out.println(ans);
    }
}