import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int startNumber = 1; // 범위의 시작
        int endNumber = Integer.parseInt(reader.readLine()); // 컴퓨터가 선택한 수 (범위의 끝)
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int startTarget = Integer.parseInt(tokenizer.nextToken()); // 목표 숫자 범위의 시작
        int endTarget = Integer.parseInt(tokenizer.nextToken()); // 목표 숫자 범위의 끝
        
        int min = Integer.MAX_VALUE; // 게임이 가장 적게 지속될 때
        int max = 0; // 게임이 가장 오래 지속될 때
        
        for (int target = startTarget; target <= endTarget; target++) {
            int left = startNumber; // 구간의 시작 숫자
            int right = endNumber; // 구간의 끝 숫자
            int count = 0;
            
            while (left <= right) {
                count++;
                int mid = (left + right) / 2; // 구간의 중앙값
                
                if (mid == target) {
                    break;
                }
                
                if (mid < target) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            
            min = Math.min(min, count);
            max = Math.max(max, count);
        }
        
        System.out.println(min + " " + max);
    }
}