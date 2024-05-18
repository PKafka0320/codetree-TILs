import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 사람의 수
        
        int[] startTime = new int[n + 1]; // [i] : i번째 사람이 컴퓨터를 시작하는 시간
        TreeMap<Integer, Integer> times = new TreeMap<>(); // 컴퓨터 시작 시간과 종료 시간
        TreeMap<Integer, Integer> pcNumbers = new TreeMap<>(); // 시작 시간에 사용되는 컴퓨터 번호
        TreeSet<Integer> enablePc = new TreeSet<>(); // 사용 가능한 컴퓨터 번호
        HashMap<Integer, Integer> endStartTime = new HashMap<>(); // 종료 시간과 연관된 시작 시간
        
        for (int idx = 1; idx <= n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            
            startTime[idx] = start;
            times.put(start, 1);
            times.put(end, -1);
            enablePc.add(idx);
            endStartTime.put(end, start);
        }
        
        for (int time : times.keySet()) {
            if (times.get(time) > 0) {
                pcNumbers.put(time, enablePc.pollFirst());
            }
            else {
                enablePc.add(pcNumbers.get(endStartTime.get(time)));
            }
        }
        
        StringBuilder answer = new StringBuilder();
        for (int idx = 1; idx <= n; idx++) {
            answer.append(pcNumbers.get(startTime[idx])).append(" ");
        }
        System.out.println(answer);
    }
}