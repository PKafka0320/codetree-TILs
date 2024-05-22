import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 숫자의 개수
        
        int[] numbers = new int[n]; // [i]: i번째 숫자
        TreeSet<Integer> elements = new TreeSet<>(); // 구간에 포함되는 숫자
        
        // 숫자 입력
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        int endIdx = 0; // 구간의 끝 위치
        int answer = 0; // 구간 내에 중복되는 숫자가 전혀 없는 최대 구간의 크기
        for (int startIdx = 0; startIdx < n; startIdx++) {
            // 구간 내에 중복되는 숫자가 있을 때까지 구간의 끝 이동
            while (endIdx < n && !elements.contains(numbers[endIdx])) {
                elements.add(numbers[endIdx]);
                endIdx++;
            }
            
            answer = Math.max(answer, endIdx - startIdx);
            
            // 구간의 시작 위치를 이동하기 전 구간 내에서 해당 숫자 제거
            elements.remove(numbers[startIdx]);
        }
        
        System.out.println(answer);
    }
}