import java.io.*;
import java.util.*;

public class Main {
    static int[] numbers;
    static int[] countArrayIn;
    static int[] countArrayOut;
    static int distinctNumIn;  // 구간 내 서로 다른 숫자 개수
    static int distinctNumOut; // 구간 외 서로 다른 숫자 개수

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n  = Integer.parseInt(tokenizer.nextToken()); // 숫자의 개수
        int m  = Integer.parseInt(tokenizer.nextToken()); // 구간 안, 밖에 있어야 하는 1이상 m이하의 숫자
        
        numbers = new int[n]; // [i]: i번째 숫자
        countArrayIn = new int[m + 1];  // 구간 내 숫자 정보
        countArrayOut = new int[m + 1]; // 구간 외 숫자 정보
        
        // 숫자 배열 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken());
            
            // 해당 숫자가 최초로 등장했다면 구간 외 숫자의 종류 증가
            if(countArrayOut[numbers[idx]] == 0)
                distinctNumOut++;
            
            countArrayOut[numbers[idx]]++;
        }
        
        int endIdx = 0; // 구간의 끝 위치
        int min = Integer.MAX_VALUE; // 구간의 최소 길이
        
        // 구간 결정 및 계산
        for (int startIdx = 0; startIdx < n; startIdx++) {
            // m 이하의 숫자가 아니라면 무시
            if (numbers[startIdx] > m) continue;
            
            // 선택한 구간 내에 1부터 m까지의 숫자가 모두 있을때까지 구간의 끝 이동
            while (endIdx < n && distinctNumIn < m) {
                push(endIdx);
                endIdx++;
            }
            
            // 가능한 경우가 없는 경우 종료
            if (distinctNumIn < m) break;
            
            // 구간의 안에도 1이상 m이하의 숫자가 있는 경우에만 최소 길이 갱신
            if (distinctNumOut == m) {
                min = Math.min(min, endIdx - startIdx);
            }

            // 구간의 시작 위치를 이동하기 전,
            // 해당 숫자를 구간에서 제거
            pop(startIdx);
        }
        
        // 만족하는 경우가 없는 경우 정답 수정
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        
        System.out.println(min);
    }
    
    // idx번째 숫자를 구간에 집어넣게 되었을 때의 countArray와 distinctNum 값을 갱신
    public static void push(int idx) {
        int num = numbers[idx];
    
        // 구간 내로 새로 들어온 숫자라면 구간 내 숫자의 종류 증가
        if(countArrayIn[num] == 0)
            distinctNumIn++;
        countArrayIn[num]++;
    
        // num과 동일한 구간 외 숫자가 없다면 구간 외 숫자의 종류 감소
        countArrayOut[num]--;
        if(countArrayOut[num] == 0)
            distinctNumOut--;
    }
    
    // idx번째 숫자를 구간에서 빼게 되었을 때의 countArray와 distinctNum 값을 갱신
    public static void pop(int idx) {
        int num = numbers[idx];
    
        // num과 동일한 구간 내 숫자가 없다면 구간 내 숫자의 종류 감소
        countArrayIn[num]--;
        if(countArrayIn[num] == 0)
            distinctNumIn--;
    
        // 구간 외로 새로 들어온 숫자라면 구간 외 숫자의 종류 증가
        if(countArrayOut[num] == 0)
            distinctNumOut++; 
        countArrayOut[num]++;
    }
}