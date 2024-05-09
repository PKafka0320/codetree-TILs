import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(reader.readLine());
        int totalPos = Integer.parseInt(token.nextToken());
        int totalQuery = Integer.parseInt(token.nextToken());

        HashSet<Integer> positions = new HashSet<>();
        token = new StringTokenizer(reader.readLine());
        for (int posCount = 0; posCount < totalPos; posCount++) positions.add(Integer.parseInt(token.nextToken()));

        StringBuilder answer = new StringBuilder();
        for (int queryCount = 0; queryCount < totalQuery; queryCount++) {
            token = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());

            int count = 0;
            for (int number = start; number <= end; number++) count += positions.contains(number) ? 1 : 0;

            answer.append(count).append("\n");
        }

        System.out.println(answer);
    }
}