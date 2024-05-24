import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        char[] string = reader.readLine().toCharArray(); // 문자열
        
        HashSet<Character> characters = new HashSet<>(); // 부분 문자열에 있는 문자들
        
        int endIdx = -1; // 문자열의 끝
        int max = 0; // 중복되는 문자가 없는 가장 긴 문자열의 길이
        for (int startIdx = 0; startIdx < string.length; startIdx++) {
            while (endIdx + 1 < string.length && !characters.contains(string[endIdx + 1])) {
                characters.add(string[endIdx + 1]);
                endIdx++;
            }
            
            max = Math.max(max, characters.size());
            
            characters.remove(string[startIdx]);
        }
        
        System.out.println(max);
    }
}