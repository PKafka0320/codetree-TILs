import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n  = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int m  = Integer.parseInt(tokenizer.nextToken()); // 구간 안, 밖에 있어야 하는 1이상 m이하의 숫자
        
        HashMap<Integer, Integer> inCounts = new HashMap<>(); // <K, V>: 수열 안에 있는 숫자 K의 개수 V
        HashMap<Integer, Integer> outCounts = new HashMap<>(); // <K, V>: 수열 밖에 있는 숫자 K의 개수 V
        int[] numbers = new int[n]; // [i]: i번째 숫자
        
        // 숫자 배열 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
            
            if (numbers[idx] <= m) {
                outCounts.put(numbers[idx], outCounts.getOrDefault(numbers[idx], 0) + 1);
            }
        }
        
        int endIdx = 0; // 구간의 끝 위치
        int min = Integer.MAX_VALUE; // 구간의 최소 길이
        for (int startIdx = 0; startIdx < n; startIdx++) {
            // m 이하의 숫자가 아니라면 무시
            if (numbers[startIdx] > m) continue;
            
            // 선택한 구간 내에 1부터 m까지의 숫자가 모두 있을때까지 구간의 끝 이동
            while (endIdx < n && inCounts.size() < m) {
                // m 이하의 숫자가 아니라면 무시
                if (numbers[endIdx] <= m) {
                    // 구간 안에 해당 숫자 추가, 구간 밖에 해당 숫자 제거
                    inCounts.put(numbers[endIdx], inCounts.getOrDefault(numbers[endIdx], 0) + 1);
                    outCounts.put(numbers[endIdx], outCounts.get(numbers[endIdx]) - 1);
                    
                    // 숫자의 개수가 0개일 경우 목록에서 제거
                    if (outCounts.getOrDefault(numbers[endIdx], 0) == 0) {
                        outCounts.remove(numbers[endIdx]);
                    }
                }
                endIdx++;
            }
            
            // 구간의 안, 밖에 1이상 m이하의 숫자가 있는 경우에만 최소 길이 갱신
            if (inCounts.size() == m && outCounts.size() == m) {
                min = Math.min(min, endIdx - startIdx);
            }

            // 구간의 시작 위치를 이동하기 전,
            // 구간 안에 해당 숫자 제거, 구간 밖에 해당 숫자 추가
            outCounts.put(numbers[startIdx], outCounts.getOrDefault(numbers[startIdx], 0) + 1);
            inCounts.put(numbers[startIdx], inCounts.get(numbers[startIdx]) - 1);
            
            // 숫자의 개수가 0개일 경우 목록에서 제거
            if (inCounts.getOrDefault(numbers[startIdx], 0) == 0) {
                inCounts.remove(numbers[startIdx]);
            }
        }
        
        // 만족하는 경우가 없는 경우 정답 수정
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        
        System.out.println(min);
    }
}