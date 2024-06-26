import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 카드의 개수
        
        int[] aCards = new int[n]; // [i]: a의 i번째 카드
        int[] bCards = new int[n]; // [i]: b의 i번째 카드
        HashSet<Integer> bSet = new HashSet<>(); // b의 카드 집합
        
        // b카드 입력
        for (int idx = 0; idx < n; idx++) {
            bCards[idx] = Integer.parseInt(reader.readLine());
            bSet.add(bCards[idx]);
        }
        
        // a카드 생성
        for (int aIdx = 0, number = 1; number <= 2 * n; number++) {
            if (bSet.contains(number)) continue;
            aCards[aIdx++] = number; 
        }
        
        // 카드 정렬
        Arrays.sort(aCards);
        Arrays.sort(bCards);
        
        // a의 카드를 작은 숫자부터 보며
        // b카드의 앞에서부터 이길 수 있는 순간에 둘을 매칭하는게 최선임을 이용
        int answer = 0;
        
        for (int aIdx = 0, bIdx = 0; aIdx < n; aIdx++) {
            if (bIdx < n && aCards[aIdx] > bCards[bIdx]) {
                answer++;
                bIdx++;
            }
        }
        
        System.out.println(answer);
    }
}