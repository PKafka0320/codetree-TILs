import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String string = reader.readLine(); // 문자열
        char[] arr = string.toCharArray(); // 문자 배열
        int[] counts = new int[string.length()]; // [i]: i 위치까지 여는 괄호가 2개인 개수
        
        // 각 위치마다 첫 문자부터 여는 괄호가 2개인 개수를 계산
        char prevCh = '-'; // 마지막으로 확인한 문자
        int count = 0; // 여는 괄호가 2개인 개수
        for (int idx = 0; idx < string.length(); idx++) {
            if (arr[idx] == '(') {
                if (prevCh == '(') {
                    count++;
                }
                prevCh = '(';
            }
            else {
                prevCh = ')';
            }
            counts[idx] = count; 
        }

        // 각 위치마다 마지막 문자부터 닫는 괄호가 2개일 때마다 해당 위치의 여는 괄호 개수를 합산
        prevCh = '-';
        int answer = 0;
        for (int idx = string.length() - 1; idx >= 0; idx--) {
            if (arr[idx] == ')') {
                if (prevCh == ')') {
                    answer += counts[idx];
                }
                prevCh = ')';
            }
            else {
                prevCh = '(';
            }
        }
        
        System.out.println(answer);
    }
}