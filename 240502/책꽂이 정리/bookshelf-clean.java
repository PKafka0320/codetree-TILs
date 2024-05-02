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

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N + 1];
        nodes[1] = new Node(1);
        for (int i = 2; i <= N; i++) {
            nodes[i] = new Node(i);
            connect(nodes[i - 1], nodes[i]);
        }

        // 각 책꽂이의 맨 앞 책과 맨 뒷 책
        Node[][] shelf = new Node[K + 1][2];
        shelf[1][0] = nodes[1];
        shelf[1][1] = nodes[N];
        for (int i = 2; i <= K; i++) {
            shelf[i][0] = shelf[i][1] = null;
        }

        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (op == 1) {
                // i 번 책꽂이의 맨 앞 책 선택
                Node fnode = shelf[from][0];
                if (fnode == null) continue; // i 번 책꽂이가 비어있는 경우
                if (shelf[from][0] == shelf[to][1]) continue; // 책꽂이에 책이 1개만 있는 경우
                
                // i 번 책꽂이에서 맨 앞 책 제거
                shelf[from][0] = fnode.next;
                if (shelf[from][0] == null) shelf[from][1] = null; // i 번 책꽂이가 비게 되는 경우
                connect(fnode.prev, fnode.next);
                fnode.prev = fnode.next = null;

                // j 번 책꽂이의 맨 뒷 책 선택
                Node tnode = shelf[to][1];

                // j 번 책꽂이에의 맨 뒤에 추가
                shelf[to][1] = fnode;
                if (shelf[to][0] == null) shelf[to][0] = fnode; // j 번 책꽂이가 비어있던 경우
                connect(tnode, fnode);
            }
            else if (op == 2) {
                // i 번 책꽂이의 맨 앞 책 선택
                Node fnode = shelf[from][1];
                if (fnode == null) continue; // i 번 책꽂이가 비어있는 경우
                if (shelf[from][0] == shelf[to][1]) continue; // 책꽂이에 책이 1개만 있는 경우

                // j 번 책꽂이의 맨 뒷 책 선택
                Node tnode = shelf[to][0];

                // i 번 책꽂이에서 맨 앞 책 제거
                shelf[from][1] = fnode.prev;
                if (shelf[from][1] == null) shelf[from][0] = null; // i 번 책꽂이가 비게 되는 경우
                connect(fnode.prev, fnode.next);
                fnode.prev = fnode.next = null;

                // j 번 책꽂이에의 맨 앞에 추가
                shelf[to][0] = fnode;
                if (shelf[to][1] == null) shelf[to][1] = fnode; // j 번 책꽂이가 비어있던 경우
                connect(fnode, tnode);
            }
            else if (op == 3) {
                if (from == to) continue; // 같은 책꽂이인 경우

                // i 번 책꽂이의 맨 앞 책과 맨 뒷 책 선택
                Node sfnode = shelf[from][0];
                Node efnode = shelf[from][1];
                if (sfnode == null || efnode == null) continue; // i 번 책꽂이가 비어있는 경우

                // j 번 책꽂이의 맨 앞 책 선택
                Node tnode = shelf[to][0];
                if (tnode == null) { // j 번 책꽂이가 비어있는 경우
                    shelf[to][0] = sfnode;
                    shelf[to][1] = efnode;
                    shelf[from][0] = shelf[from][1] = null;
                    continue;
                }

                // j 번 책꽂이에의 맨 앞에 추가
                connect(efnode, tnode);
                shelf[to][0] = sfnode;
                shelf[from][0] = shelf[from][1] = null;
            }
            else if (op == 4) {
                if (from == to) continue; // 같은 책꽂이인 경우

                // i 번 책꽂이의 맨 앞 책과 맨 뒷 책 선택
                Node sfnode = shelf[from][0];
                Node efnode = shelf[from][1];
                if (sfnode == null || efnode == null) continue; // i 번 책꽂이가 비어있는 경우

                // j 번 책꽂이의 맨 뒷 책 선택
                Node tnode = shelf[to][1];
                if (tnode == null) { // j 번 책꽂이가 비어있는 경우
                    shelf[to][0] = sfnode;
                    shelf[to][1] = efnode;
                    shelf[from][0] = shelf[from][1] = null;
                    continue;
                }

                // j 번 책꽂이에의 맨 뒤에 추가
                connect(tnode, sfnode);
                shelf[to][1] = efnode;
                shelf[from][0] = shelf[from][1] = null;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 1; k <= K; k++) {
            Node node = shelf[k][0];
            int cnt = 0;
            StringBuilder tmpsb = new StringBuilder();
            while (node != null) {
                cnt++;
                tmpsb.append(node.n).append(" ");
                node = node.next;
            }
            sb.append(cnt).append(" ").append(tmpsb.toString()).append("\n");
        }
        System.out.println(sb);
    }

    static void connect(Node prev, Node next) {
        if (prev != null) prev.next = next;
        if (next != null) next.prev = prev;
    }
}