import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final int MAX_N = 15;
    
    public static int n = 15;
    public static int[] arr = new int[MAX_N];
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        
        // 오름차순으로 정렬을 진행합니다.
        Arrays.sort(arr, 0, n);

        // 오름차순으로 정렬했을 때,
        // 가장 작은 숫자는 A,
        // 두 번째로 작은 숫자 B,
        // 그리고 세 번째로 작은 숫자는 C가 됩니다.
        int a = arr[0];
        int b = arr[1];
        int c = arr[2]; 

        // 또한, 가장 큰 숫자는 항상 A + B + C + D가 되므로
        // D 는 끝 숫자 - A - B - C가 됩니다
        int d = arr[n - 1] - a - b - c;

        System.out.print(a + " " + b + " " + c + " " + d);
    }
}