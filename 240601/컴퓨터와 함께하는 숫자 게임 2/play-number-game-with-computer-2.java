import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long startNumber = 1; // 범위의 시작
        long endNumber = Integer.parseInt(reader.readLine()); // 컴퓨터가 선택한 수 (범위의 끝)
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        long startTarget = Long.parseLong(tokenizer.nextToken()); // 목표 숫자 범위의 시작
        long endTarget = Long.parseLong(tokenizer.nextToken()); // 목표 숫자 범위의 끝
        
        long min = Integer.MAX_VALUE; // 게임이 가장 적게 지속될 때
        long max = 0; // 게임이 가장 오래 지속될 때
        
        for (long target = startTarget; target <= endTarget; target++) {
            long left = startNumber; // 구간의 시작 숫자
            long right = endNumber; // 구간의 끝 숫자
            int count = 0;
            
            while (left <= right) {
                count++;
                long mid = (left + right) / 2; // 구간의 중앙값
                
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