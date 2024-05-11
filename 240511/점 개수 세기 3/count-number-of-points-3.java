import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    static TreeSet<Integer> nums = new TreeSet<>(); // 숫자
    static HashMap<Integer, Integer> sequence = new HashMap<>(); // 숫자 번호

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer token = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(token.nextToken()); // 숫자 개수
        q = Integer.parseInt(token.nextToken()); // 질의 개수

        token = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            nums.add(Integer.parseInt(token.nextToken()));
        }

        // 순서대로 번호 매핑
        int cnt = 1;
        for (int num : nums) {
            sequence.put(num, cnt);
            cnt++;
        }

        StringBuilder answer = new StringBuilder();
        for (int query = 0; query < q; query++) {
            token = new StringTokenizer(reader.readLine());
            int rangeFrom = Integer.parseInt(token.nextToken()); // 범위 시작
            int rangeTo = Integer.parseInt(token.nextToken()); // 범위 끝

            int numFrom = nums.ceiling(rangeFrom); // 범위 내의 처음 숫자
            int numTo = nums.ceiling(rangeTo); // 범위 내의 마지막 숫자
            answer.append(sequence.get(numTo) - sequence.get(numFrom) + 1).append("\n");
        }
        System.out.println(answer);
    }
}