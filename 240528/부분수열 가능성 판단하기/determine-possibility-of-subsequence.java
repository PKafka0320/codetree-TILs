import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 수열 A의 원소의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 수열 B의 원소의 개수
        
        int[] A = new int[n + 1]; // 수열 A
        int[] B = new int[m + 1]; // 수열 B
        int[] L = new int[m + 2]; // [i]: 수열 B의 1번부터 i번까지 1 -> i 순서대로 수열 A의 왼쪽부터 순서대로 매칭헀을 때 마지막으로 매칭되는 수열 A 원소의 위치
        int[] R = new int[m + 2]; // [i]: 수열 B의 m번부터 i번까지 n -> i 순서대로 수열 A의 오른쪽부터 순서대로 매칭헀을 때 마지막으로 매칭되는 수열 A 원소의 위치

        // 수열 A 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 1; idx <= n; idx++) {
            A[idx] = Integer.parseInt(tokenizer.nextToken());
        }

        // 수열 B 입력
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 1; idx <= m; idx++) {
            B[idx] = Integer.parseInt(tokenizer.nextToken());
        }
        
        // L 배열 생성
        for (int idxA = 1, idxB = 1; idxB <= m; idxB++) {
            while (idxA <= n && A[idxA] != B[idxB]) {
                idxA++;
            }
            
            L[idxB] = idxA;
        }
        
        // R 배열 생성
        for (int idxA = n, idxB = m; idxB >= 1; idxB--) {
            while (idxA >= 1 && A[idxA] != B[idxB]) {
                idxA--;
            }
            
            R[idxB] = idxA;
        }
        
        // 경계 부분 처리
        L[0] = 0;
        R[m + 1] = n + 1;
        
        int count = 0; // 부분 수열의 가짓수
        
        for (int idxB = 1; idxB <= m; idxB++) {
            if (L[idxB - 1] < R[idxB + 1]) count++;
        }
        
        System.out.println(count);
    }
}