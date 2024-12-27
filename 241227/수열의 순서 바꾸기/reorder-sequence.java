import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100;
    
    public static int n;
    public static int[] blocks = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            blocks[i] = sc.nextInt();
        
        // 맨 뒤에 있는 숫자들이 
        // 정렬된 상태로 가장 길게 놓여져 있는 것이 좋습니다.
        // 예를 들어 1 3 6 5 2 4 7 라는 수열이 있다면
        // 2 4 7은 이미 정렬되어 있으므로
        // 앞에 있는 1 3 6 5만 각 위치에 잘 옮겨주면 됩니다.
        // 따라서 4가 됩니다.

        // 즉, 뒤에서부터 보며
        // 정렬되어 있지 않은 순간을 잡아
        // 그 앞에 있는 원소는 전부 옮겨주면 됩니다.

        int idx = n - 2;
        while(idx >= 0 && blocks[idx] < blocks[idx + 1])
            idx--;

        System.out.print(idx + 1);
    }
}