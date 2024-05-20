import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 폭탄의 수
        int K = Integer.parseInt(tokenizer.nextToken()); // 폭탄의 유효거리
        
        int[] bombs = new int[N]; // [i]: i번째 폭탄의 번호
        int[] R = new int[N]; // [i]: i + 1번째 부터 N - 1번째까지 있는 폭탄중 가장 가까운 i번째 폭탄과 동일한 번호인 폭탄의 위치
        HashMap<Integer, Integer> latestIndex = new HashMap<>(); // <K, V>: 가장 가까운 K번 폭탄의 위치

        // 폭탄 배열 생성
        for (int idx = 0; idx < N; idx++) {
            bombs[idx] = Integer.parseInt(reader.readLine());
        }

        // R 배열 생성
        for(int idx = N - 1; idx >= 0; idx--) {
            // latestIndex에 bombs[idx]가 존재하지 않는다면 최초로 나온 숫자이므로 -1로 설정
            if(!latestIndex.containsKey(bombs[idx])) {
                R[idx] = -1;
            }

            // 이미 bombs[idx]가 나온적이 있다면 최근에 나온 index를 latestIndex에서 찾아서 추가
            else {
                R[idx] = latestIndex.get(bombs[idx]);
            }

            // bombs[idx]가 가장 최근에 등장한 index의 위치 갱신
            latestIndex.put(bombs[idx], idx);
        }

        int answer = -1;
        for (int idx = 0; idx < N; idx++) {
            if(R[idx] != -1 && R[idx] - idx <= K) {
                answer = Math.max(answer, bombs[idx]);
            }
        }

        System.out.println(answer);
    }
}