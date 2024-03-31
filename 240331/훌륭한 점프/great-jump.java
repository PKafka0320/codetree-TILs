import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100;
    public static final int MAX_NUM = 100;

    public static int n, k;
    public static int[] arr = new int[MAX_N];

    public static boolean isPossible(int limit) {
        // 마지막 index로 부터
        // 숫자 limit을 넘지 않으면서
        // 거리 k이내로 계속 이동이 가능한지를 판단합니다.
        int lastIdx = 0;
        for(int i = 1; i < n; i++) {
            if(arr[i] <= limit) {
                if(i - lastIdx > k)
                    return false;
                lastIdx = i;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();
        k = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // 밟으며 지나간 최댓값을 i라고 가정했을 때
        // 거리 k 이내로 점프하며 끝까지 도달하는 것이
        // 가능한지를 살펴봅니다.
        // 가능하다면, 그때의 i가 최솟값이므로
        // 답을 출력하고 종료합니다.
        for(int i = Math.max(arr[0], arr[n - 1]); i <= MAX_NUM; i++)
            if(isPossible(i)) {
                System.out.println(i);
                break;
            }
    }
}