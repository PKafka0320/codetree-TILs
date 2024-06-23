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
        if (this.end == other.end) {
            return this.start - other.start;
        }
        
        return this.end - other.end;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 회의의 수
        
        Meeting[] meetings = new Meeting[n]; // [i]: i번째 회의
        
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            
            meetings[idx] = new Meeting(start, end);
        }
        
        Arrays.sort(meetings);
        
        int answer = 1; // 최대 회의 수
        int currentTime = meetings[0].end; // 가장 일찍 끝난 회의 시간
        
        for (int idx = 1; idx < n; idx++) {
            if (meetings[idx].start < currentTime) continue;
            
            answer++;
            currentTime = meetings[idx].end;
        }
        
        System.out.println(answer);
    }
}