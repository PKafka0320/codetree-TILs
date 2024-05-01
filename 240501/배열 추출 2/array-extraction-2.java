import java.io.*;
import java.util.*;

class Number implements Comparable<Number> {
    int number;

    public Number(int n) {
        this.number = n;
    }

    @Override
    public int compareTo(Number n) {
        if (Math.abs(this.number) != Math.abs(n.number)) return Math.abs(this.number) - Math.abs(n.number);
        return this.number - n.number;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Number> pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num != 0) pq.add(new Number(num));
            else if (pq.isEmpty()) sb.append("0\n");
            else {
                Number m = pq.poll();
                sb.append(m.number).append("\n");
            }
        }

        System.out.println(sb);
    }
}