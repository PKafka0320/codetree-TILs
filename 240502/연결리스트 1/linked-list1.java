import java.io.*;
import java.util.*;

class Node {
    String s;
    Node prev, next;

    public Node(String s) {
        this.s = s;
        this.prev = this.next = null;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Node cur = new Node(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            if (n == 1) {
                Node tmp = new Node(st.nextToken());
                
                tmp.next = cur;
                tmp.prev = cur.prev;
                if (tmp.prev != null) tmp.prev.next = tmp;
                if (tmp.next != null) tmp.next.prev = tmp;
            }
            else if (n == 2) {
                Node tmp = new Node(st.nextToken());

                tmp.prev = cur;
                tmp.next = cur.next;
                if (tmp.prev != null) tmp.prev.next = tmp;
                if (tmp.next != null) tmp.next.prev = tmp;
            }
            else if (n == 3 && cur.prev != null) cur = cur.prev;
            else if (n == 4 && cur.next != null) cur = cur.next;
                
            sb.append(cur.prev == null ? "(Null)" : cur.prev.s).append(" ")
                .append(cur.s).append(" ")
                .append(cur.next == null ? "(Null)" : cur.next.s).append("\n");
        }

        System.out.println(sb);
    }
}