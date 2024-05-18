import java.io.*;
import java.util.*;

class Tuple implements Comparable<Tuple> { 
    int time, value, index;
    public Tuple(int time, int value, int index) { 
        this.time = time; 
        this.value = value;
        this.index = index; 
    }
    @Override
    public int compareTo(Tuple t) {
        if(time != t.time) return time - t.time;
        if(value != t.value) return value - t.value;
        return index - t.index;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 사람의 수
        
        int[] assignedNums = new int[n + 1]; // [i] : i번째 사람에게 할당된 컴퓨터 번호
        PriorityQueue<Integer> computers = new PriorityQueue<>(); // 할당 가능한 컴퓨터 번호
        ArrayList<Tuple> points = new ArrayList<>(); // 사람이 컴퓨터를 사용하는 시작 시간과 끝 시간
        
        for (int idx = 1; idx <= n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            
            computers.add(idx);
            points.add(new Tuple(start, 1, idx)); // 시작 시간
            points.add(new Tuple(end, -1, idx)); // 끝 시간
        }
        
        Collections.sort(points);
        
        for (Tuple point : points) {
            int time = point.time;
            int value = point.value;
            int idx = point.index;

            if (value == 1) {
                assignedNums[idx] = computers.poll();
            }
            else {
                computers.add(assignedNums[idx]);
            }
        }
        
        StringBuilder answer = new StringBuilder();
        for (int idx = 1; idx <= n; idx++) {
            answer.append(assignedNums[idx]).append(" ");
        }
        System.out.println(answer);
    }
}