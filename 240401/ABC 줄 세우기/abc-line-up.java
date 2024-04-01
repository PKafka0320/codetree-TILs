import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next().charAt(0) - 'A';
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == i) continue;

            int from = i;
            int to = arr[i];
            for (int j = from; j < to; j++) {
                int tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
                cnt++;
            }
            i = 0;

        }

        System.out.println(cnt);
    }
}