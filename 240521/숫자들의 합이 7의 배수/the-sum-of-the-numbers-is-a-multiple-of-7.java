import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 숫자의 개수
        
        int[] numbers = new int[N + 1]; // 숫자
        int[] maxIdx = new int[7]; // [i]: 누적합의 모듈러 연산 결과가 i인 값의 위치의 최댓값
        int[] minIdx = new int[7]; // [i]: 누적합의 모듈러 연산 결과가 i인 값의 위치의 최솟값
        
        // 숫자 입력 및 누적합 계산
        for (int idx = 1; idx <= N; idx++) {
            numbers[idx] = Integer.parseInt(reader.readLine());
        }

        // 초기화
        for(int i = 0; i < 7; i++) {
            maxIdx[i] = 0;
            minIdx[i] = N;
        }
        minIdx[0] = maxIdx[0] = 0;

        // 누적합의 모듈러 연산을 계산하면서 해당 값이 등장한 위치의 최솟값과 최댓값 갱신
        int sumMod = 0;
        for (int idx = 1; idx <= N; idx++) {
            sumMod = (sumMod + numbers[idx]) % 7;

            maxIdx[sumMod] = Math.max(maxIdx[sumMod], idx);
            minIdx[sumMod] = Math.min(minIdx[sumMod], idx);
        }
        
        // 최대 길이를 설정하고 누적합을 사용해 7의 배수인지 확인
        int answer = 0;
        for (int mod = 0; mod < 7; mod++) {
            answer = Math.max(answer, maxIdx[mod] - minIdx[mod]);
        }
        System.out.println(answer);
    }
}