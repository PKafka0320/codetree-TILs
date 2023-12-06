import java.util.*;

public class Main {
    static int K, N;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        N = sc.nextInt();

        find(0, 0);
    }

    public static void find(int idx, int count) {
        if (idx == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
            return;
        }

        int lastNumber = 0;
        if (idx != 0) {
            lastNumber = list.get(list.size() - 1);
        }

        for (int number = 1; number <= K; number++) {
            list.add(number);
            if (lastNumber == number) {
                if (count == 2) {
                    list.remove(list.size() - 1);
                    continue;
                }
                find(idx + 1, count + 1);
            } else {
                find(idx + 1, 1);
            }
            list.remove(list.size() - 1);
        }
    }
}