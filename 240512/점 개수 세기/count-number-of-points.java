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

        token = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < n; idx++) {
            numbers.add(Integer.parseInt(token.nextToken()));
        }

        int cnt = 0;
        for (int num : numbers) {
            sequence.put(num, cnt++);
        }

        StringBuilder answer = new StringBuilder();
        for (int query = 0; query < q; query++) {
            token = new StringTokenizer(reader.readLine());

            int seqFrom = 0;
            int seqTo = cnt - 1;

            Integer numberFrom = numbers.ceiling(Integer.parseInt(token.nextToken()));
            if (numberFrom != null) seqFrom = sequence.get(numberFrom);
            Integer numberTo = numbers.floor(Integer.parseInt(token.nextToken()));
            if (numberTo != null) seqTo = sequence.get(numberTo);

            answer.append(seqTo - seqFrom + 1).append("\n");
        }
        System.out.println(answer);
    }
}