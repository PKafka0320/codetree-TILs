import java.io.*;
import java.util.*;

class Node {
    int n;
    Node prev, next;

    public Node(int n) {
        this.n = n;
        this.prev = this.next = null;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int Q = Integer.parseInt(br.readLine());

        HashMap<Integer, Node> hm = new HashMap<>();
        for (int i = 1; i <= N; i++) hm.put(i, new Node(i));

        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int i = Integer.parseInt(st.nextToken());
                Node cur = hm.get(i);

                if (cur.next != null) cur.next.prev = cur.prev;
                if (cur.prev != null) cur.prev.next = cur.next;
                cur.next = cur.prev = null;
            }
            else if (op == 2) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

                Node ni = hm.get(i);
                Node nj = hm.get(j);

                nj.next = ni;
                nj.prev = ni.prev;
                if (nj.next != null) nj.next.prev = nj;
                if (nj.prev != null) nj.prev.next = nj;
            }
            else if (op == 3) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

                Node ni = hm.get(i);
                Node nj = hm.get(j);

                nj.prev = ni;
                nj.next = ni.next;
                if (nj.next != null) nj.next.prev = nj;
                if (nj.prev != null) nj.prev.next = nj;}
            else if (op == 4) {
                int i = Integer.parseInt(st.nextToken());
                Node cur = hm.get(i);

                sb.append(cur.prev == null ? 0 : cur.prev.n).append(" ")
                    .append(cur.next == null ? 0 : cur.next.n).append("\n");
            }
        }

        for (int i = 1; i <= N; i++) {
            Node cur = hm.get(i);
            sb.append(cur.next == null ? 0 : cur.next.n).append(" ");
        }
        
        System.out.println(sb);
    }
}