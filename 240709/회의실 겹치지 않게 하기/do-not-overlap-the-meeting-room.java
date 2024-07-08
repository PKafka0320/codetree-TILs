import java.io.*;
import java.util.*;

class Meeting implements Comparable<Meeting> {
    int start, end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting other) {
        if (this.end != other.end) return this.end - other.end;
        return this.start - other.start;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 회의의 수

        Meeting[] meetings = new Meeting[n]; // [i]: i번재 회의

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings[idx] = new Meeting(start, end);
        }

        Arrays.sort(meetings);

        int count = 0;
        int currentTime = 0;

        for (int idx = 0; idx < n; idx++) {
            if (currentTime > meetings[idx].start) {
                count++;
                continue;
            }

            currentTime = meetings[idx].end;
        }

        System.out.println(count);
    }
}