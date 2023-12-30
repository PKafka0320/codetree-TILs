import java.io.*;
import java.util.*;

class Line {
    int start, end;

    public Line(int s, int e) {
        this.start = s;
        this.end = e;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Line[] lines;
    static int[] max;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        lines = new Line[n];
        max = new int[n];

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines[idx] = new Line(start, end);
        }

        int answer = 1;
        Arrays.fill(max, 1);

        for (int idx = 1; idx < n; idx++) {
            for (int exIdx = 0; exIdx < idx; exIdx++) {
                if (lines[idx].start > lines[exIdx].end) {
                    max[idx] = Math.max(max[idx], max[exIdx] + 1);
                    answer = Math.max(answer ,max[idx]);
                }
            }
        }

        System.out.println(answer);
    }
}