import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int M = Integer.parseInt(tokenizer.nextToken()); // 그룹 내 숫자들의 최대 차
        
        int[] numbers = new int[N]; // [i]: i번째 숫자
        int[] L = new int[N]; // [i]: 첫 번째부터 i번째까지의 숫자들 중 조건을 만족하는 그룹 중 개수의 최댓값
        int[] R = new int[N]; // [i]: i번째부터 마지막까지의 숫자들 중 조건을 만족하는 그룹 중 개수의 최댓값
        
        // 숫자 배열 생성
        for (int idx = 0; idx < N; idx++) {
            numbers[idx] = Integer.parseInt(reader.readLine());
        }
        
        Arrays.sort(numbers);

        int maxNum = 0;
        int tmpIdx = 0;
        
        // L 배열 생성
        // tmpIdx: 시작 위치
        for (int endIdx = 0; endIdx < N; endIdx++) {
            // 구간 내 차이가 K이하가 될때까지 구간의 시작 지점 이동
            while (tmpIdx + 1 <= endIdx && numbers[endIdx] - numbers[tmpIdx] > M) {
                tmpIdx++;
            }
            
            maxNum = Math.max(maxNum, endIdx - tmpIdx + 1);
            
            L[endIdx] = maxNum;
        }
        
        maxNum = 0;
        tmpIdx = N - 1;
        
        // R 배열 생성
        // tmpIdx: 끝 위치
        for (int startIdx = N - 1; startIdx >= 0; startIdx--) {
            // 구간 내 차이가 K이하가 될때까지 구간의 끝 지점 이동
            while (tmpIdx - 1 >= startIdx && numbers[tmpIdx] - numbers[startIdx] > M) {
                tmpIdx--;
            }
            
            maxNum = Math.max(maxNum, tmpIdx - startIdx + 1);
            
            R[startIdx] = maxNum;
        }
        
        // i번째 위치를 경계로 [1 ~ i], [i + 1 ~ N] 으로 그룹을 만들 영역을 나누었을때
        // 가능한 최대 원소의 개수 계싼
        int answer = L[N - 1];
        for (int idx = 0; idx < N - 1; idx++) {
            answer = Math.max(answer, L[idx] + R[idx + 1]);
        }
        
        System.out.println(answer);
    }
}