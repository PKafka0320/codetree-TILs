import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Pair p) {
        return (this.a + this.b) - (p.a + p.b);
    }

    @Override
    public String toString() {
        return "[" + this.a + "," + this.b + "]";
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pq.add(new Pair(A[i], B[j]));
            }
        }

        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        System.out.println(pq.peek().a + pq.peek().b);
    }
}