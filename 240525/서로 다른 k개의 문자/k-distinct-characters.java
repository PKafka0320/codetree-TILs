import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        char[] string = tokenizer.nextToken().toCharArray(); // 문자열
        int n = string.length; // 문자열 길이
        int k = Integer.parseInt(tokenizer.nextToken()); // 서로 다른 문자 수의 최대값
        
        HashMap<Character, Integer> characters = new HashMap<>(); // <K, V>: 구간 내의 K문자의 개수 V
        
        int endIdx = 0; // 구간의 끝 위치
        int max = 0; // 조건을 만족하는 문자열의 최대 길이
        
        for (int startIdx = 0; startIdx < n; startIdx++) {
            int nextCharCount = characters.getOrDefault(string[endIdx], 0); // 구간 내 다음 문자의 개수
            while (endIdx < n && (nextCharCount > 0 || (nextCharCount == 0 && characters.size() < k))) {
                characters.put(string[endIdx], nextCharCount + 1);
                
                endIdx++;
                // 문자열을 끝까지 확인하면 종료
                if (endIdx == n) break;
                
                // 다음 문자의 개수 확인
                nextCharCount = characters.getOrDefault(string[endIdx], 0);
            }
            
            max = Math.max(max, endIdx - startIdx);
            
            // 문자열을 끝까지 확인하면 종료
            if (endIdx == n) break;

            // 제거하는 문자의 개수가 0개가 되면 목록에서 제거
            int startCharCount = characters.getOrDefault(string[startIdx], 0); // 구간 내 처음 문자의 개수
            if (startCharCount == 1) {
                characters.remove(string[startIdx]);
            }
            else {
                characters.put(string[startIdx], startCharCount - 1);
            }
        }
        
        System.out.println(max);
    }
}