import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 정수의 개수
        
        int[] numbers = new int[n]; // 정수 배열
        int[] prefixSum = new int[n]; // prefixSum[i] : 0번 부터 i번까지의 숫자의 합
        
        // 입력 및 누적합 계산
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numbers[0] = Integer.parseInt(tokenizer.nextToken());
        prefixSum[0] = numbers[0];
        for (int idx = 1; idx < n; idx++) {
            numbers[idx] =  Integer.parseInt(tokenizer.nextToken());
            prefixSum[idx] = prefixSum[idx - 1] + numbers[idx];
        }
        
        int answer = 0;
        for (int idx1 = 0; idx1 < n - 3; idx1++) {
            for (int idx2 = idx1 + 1; idx2 < n - 2; idx2++) {
                for (int idx3 = idx2 + 1; idx3 < n - 1; idx3++) {
                    if (prefixSum[idx1] == prefixSum[idx2] - prefixSum[idx1] && 
                            prefixSum[idx1] == prefixSum[idx3] - prefixSum[idx2] && 
                            prefixSum[idx1] == prefixSum[n - 1] - prefixSum[idx3]) {
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}