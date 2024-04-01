import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[3];

        for (int i = 0; i < 3; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        if(arr[0] + 1 == arr[1] && arr[1] + 1 == arr[2])
            System.out.print(0);
        else if(arr[0] + 2 == arr[1] || arr[1] + 2 == arr[2])
            System.out.print(1);
        else
            System.out.print(2);
    }
}