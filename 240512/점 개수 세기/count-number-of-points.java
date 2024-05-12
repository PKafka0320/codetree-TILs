import java.io.*;
import java.util.*;

public class Main {
    static int n, q;
    static TreeSet<Integer> numbers;
    static HashMap<Integer, Integer> sequence;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(token.nextToken());
        q = Integer.parseInt(token.nextToken());

        numbers = new TreeSet<>(); // 숫자
        sequence = new HashMap<>(); // 순서

        // 숫자 입력
        token = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers.add(Integer.parseInt(token.nextToken()));
        }

        // 작은 수부터 순서대로 번호 부여
        int cnt = 0;
        for (int num : numbers) {
            sequence.put(num, cnt++);
        }

        // 구간 내 점의 개수 계산
        StringBuilder answer = new StringBuilder();
        for (int query = 0; query < q; query++) {
            token = new StringTokenizer(reader.readLine());

            // 범위 내의 점이 없는 경우 방지
            int seqFrom = cnt - 1;
            int seqTo = 0;

            Integer numberFrom = numbers.ceiling(Integer.parseInt(token.nextToken()));
            if (numberFrom != null) seqFrom = sequence.get(numberFrom);
            Integer numberTo = numbers.floor(Integer.parseInt(token.nextToken()));
            if (numberTo != null) seqTo = sequence.get(numberTo);

            answer.append(seqTo - seqFrom + 1).append("\n");
        }
        System.out.println(answer);
    }
}