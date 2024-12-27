import java.util.Scanner;

public class Main {
    public static final int MAX_N = 1000;
    
    public static int n;
    public static int[] a = new int[MAX_N];
    public static int[] arr = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        
        for(int i = 0; i < n - 1; i++)
            a[i] = sc.nextInt();
        
        // 모든 1 ~ n이 등장하는 수열의 개수는 n!개입니다.
        // 이를 완전탐색하기 위해서는 1000!개의 수열을 알아보아야 하며,
        // 이는 아무리 컴퓨터라 해도 너무 많은 경우의 수를 세야 합니다.
        // 따라서 보다 효율적인 방법을 찾아야 합니다.

        // 잘 생각해 보면, 수열의 첫 번째 수만 결정한다면
        // 그 뒤의 숫자들은 자동으로 값이 하나로 결정된다는 것을 알 수 있습니다.
        // 따라서 수열의 첫 번째 수를 기준으로 모든 수열을 탐색해 볼 수 있습니다.
        for(int i = 1; i <= n; i++) {
            // 수열의 첫 번째 수가 i일 때
            arr[0] = i;
            
            for(int j = 1; j < n; j++) {
                // a[j - 1]는 arr[j] + arr[j - 1]의 값이기 때문에, 이 식을 잘 이용하면
                // arr[0]에서 arr[1]을, arr[1]에서 arr[2]를 차근차근 알 수 있습니다.
                arr[j] = a[j - 1] - arr[j - 1];
            }

            // arr수열에 1부터 n까지의 값이 한 번씩 이용되는지 확인합니다.
            // satisfied : arr수열에 1부터 n까지의 값이 한 번씩 이용될때 true
            // exist : 한 번이라도 해당 숫자가 arr수열에 존재했다면 true
            // 같은 숫자가 앞에 있었다면 exist배열을 확인했을 때 true가 나오게 됩니다.
            boolean satisfied = true;
            boolean[] exist = new boolean[MAX_N + 1];
            for(int j = 0; j < n; j++) {
                if(arr[j] <= 0 || arr[j] > n)
                    satisfied = false;
                else {
                    if(exist[arr[j]])
                        satisfied = false;
                    exist[arr[j]] = true;
                }
            }

            if(satisfied) {
                for(int j = 0; j < n; j++)
                    System.out.print(arr[j] + " ");
            }
        }
    }
}