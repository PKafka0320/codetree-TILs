import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Pair other) {
        if (this.a != other.a) return this.a - other.a;
        return this.b - other.b;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] red = new int[c]; // [i]: i번째 빨간돌의 T의 값
        Pair[] black = new Pair[n];  // [i]: i번째 검은돌의 A, B의 값

        for (int idx = 0; idx < c; idx++) {
            red[idx] = Integer.parseInt(br.readLine());
        }

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            black[idx] = new Pair(a, b);
        }

        Arrays.sort(red);
        Arrays.sort(black);

        int redIdx = 0; // 현재 확인중인 빨간돌의 포인터
        int blackIdx = 0; // 현재 확인중인 검은돌의 포인터
        int count = 0; // 짝지어지는 빨간돌과 검정돌의 쌍 개수

        // A, T, B의 값을 비교하면서 포인터 이동
        while (redIdx < c && blackIdx < n) {
            int T = red[redIdx];
            int A = black[blackIdx].a;
            int B = black[blackIdx].b;

            if (A <= T && T <= B) {
                count++;
                redIdx++;
                blackIdx++;
            }
            else if (T < A) {
                redIdx++;
            }
            else if (B < T) {
                blackIdx++;
            }
        }

        System.out.println(count);
    }
}