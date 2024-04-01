import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 7;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int a = arr[0];
        int b = arr[1];
        int c = arr[n - 1] - a - b;

        System.out.printf("%d %d %d", a, b, c);
    }
}