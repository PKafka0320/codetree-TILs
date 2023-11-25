import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < 2; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            arr = remove(arr, from, to);
        }

        System.out.println(arr.length);
        for (int val : arr) {
            System.out.println(val);
        }
    }

    public static int[] remove(int[] arr, int from, int to) {
        int nextLen = arr.length - (to - from + 1);
        int[] temp = new int[nextLen];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i >= from && i <= to) {
                continue;
            }
            temp[count++] = arr[i];
        }

        return temp;
    }
}