import java.io.*;
import java.util.*;

class City {
    String name;
    City prev, next;

    public City(String s) {
        this.name = s;
    }
}
         
public class Main { 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken()); 

        City[] cities = new City[N + 1];
        st = new StringTokenizer(br.readLine());
        cities[1] = new City(st.nextToken());
        for (int i = 2; i <= N; i++) {
            cities[i] = new City(st.nextToken());
            cities[i].prev = cities[i - 1];
            cities[i - 1].next = cities[i];
        }
        cities[N].next = cities[1];
        cities[1].prev = cities[N];

        StringBuilder sb = new StringBuilder();
        City flag = cities[1];
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                City next = flag.next;
                if (next != flag) flag = next;
            }
            else if (op == 2) {
                City prev = flag.prev; 
                if (prev != flag) flag = prev;
            }
            else if (op == 3) {
                City remove = flag.next;
                if (remove != flag) {
                    City next = remove.next;

                    flag.next = next;
                    next.prev = flag;
                    remove.next = remove.prev = null;
                }
            }
            else if (op == 4) {
                String name = st.nextToken();
                City add = new City(name);
                City next = flag.next;

                add.prev = flag;
                add.next = next;
                flag.next = add;
                next.prev = add;
            }

            City prev = flag.prev;
            City next = flag.next;

            if (prev == next) sb.append("-1\n");
            else sb.append(prev.name).append(" ").append(next.name).append("\n");
        }
        System.out.println(sb);
    }
}