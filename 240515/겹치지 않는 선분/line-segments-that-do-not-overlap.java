import java.io.*;
import java.util.*;

class Segment implements Comparable<Segment> {
    int x1, x2;

    public Segment(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int compareTo(Segment s) {
        return this.x1 - s.x1;
    }

    @Override
    public String toString() {
        return "[" + x1 + "," + x2 + "]";
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine());

        Segment[] segments = new Segment[n]; // 선분
        int[] L = new int[n]; // L[i] : 0번부터 i번까지 선분 중 최대 x2값
        int[] R = new int[n]; // R[i] : i번부터 n-1번까지 선분 중 최소 x2값

        // 입력
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(token.nextToken());
            int x2 = Integer.parseInt(token.nextToken());

            segments[idx] = new Segment(x1, x2);
        }

        Arrays.sort(segments);

        // L 배열 생성
        L[0] = segments[0].x2;
        for (int idx = 1; idx < n; idx++) {
            L[idx] = Math.max(L[idx - 1], segments[idx].x2);
        }

        // R 배열 생성
        R[n - 1] = segments[n - 1].x2;
        for (int idx = n - 2; idx >= 0; idx--) {
            R[idx] = Math.min(R[idx + 1], segments[idx].x2);
        }

        // 각 선분 idx에 대해 왼쪽에서 가장 멀리 뻗은 L[idx - 1] 값과
        // 오른쪽에서 가장 멀리 뻗은 R[idx + 1] 값 모두와 겹치지 않는 경우에만 답 갱신
        int ans = 0;
        for(int idx = 0; idx < n; idx++) {
            if(idx > 0 && L[idx - 1] >= segments[idx].x2) continue; // 왼쪽 선분과 겹치는 경우
            if(idx < n - 1 && R[idx + 1] <= segments[idx].x2) continue; // 오른쪽 선분과 겹치는 경우
            
            ans++;
        }
        System.out.println(ans);
    }
}