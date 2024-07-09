import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 문자의 개수
        char[] before = br.readLine().toCharArray(); // 시작 문자열
        char[] after = br.readLine().toCharArray(); // 목표 문자열

        int count = 0;

        // 뒤에서부터 확인하면서 변경
        for (int idx = n - 1; idx >= 0; idx--) {
            if (before[idx] != after[idx]) {
                count++;
                for (int flipIdx = 0; flipIdx <= idx; flipIdx++) {
                    if (before[flipIdx] == 'G') before[flipIdx] = 'H';
                    else before[flipIdx] = 'G';
                }
            }
        }

        System.out.println(count);
    }
}