import java.io.*;
import java.util.*;

class Section implements Comparable<Section> {
    int x1, x2;

    public Section(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int compareTo(Section s) {
        return this.x1 - s.x1;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine()); // 구간의 개수
        int min = 200_000; // 최대 좌표값
        int max = 1; // 최소 좌표값

        Section[] sections = new Section[n]; // 구간

        // 구간 입력
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(token.nextToken());
            int x2 = Integer.parseInt(token.nextToken());

            sections[idx] = new Section(x1, x2);
            min = Math.min(min, x1);
            max = Math.max(max, x2);
        }

        Arrays.sort(sections);

        int[] sum = new int[max + 2]; // sum[i] : i번째 위치의 구간의 개수
        for (Section section : sections) {
            sum[section.x1]++;
            sum[section.x2]--;
        }

        // 구간의 개수를 누적해서 계산하며 최댓값 갱신
        int answer = 0;
        for (int idx = 1; idx <= max; idx++) {
            sum[idx] = sum[idx - 1] + sum[idx];

            answer = Math.max(answer, sum[idx]);
        }
        System.out.println(answer);
    }
}