import java.util.Scanner;

public class Main {
    public static final int MAX_A = 1000000;
    public static final int MAX_N = 100;
    
    public static int n, k;
    public static int[] num = new int[MAX_N];
    public static int[] bomb = new int[MAX_A + 1];
    public static boolean[] explode = new boolean[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();
        k = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            num[i] = sc.nextInt();

        int maxval = 1;
        int maxidx = 0;

        // 모든 쌍에 대해서 터질 수 있는 폭탄을 찾고
        // 터진 폭탄의 개수를 저장합니다.
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++) {
                // 거리가 k를 초과하는 경우 넘어갑니다.
                if(j - i > k)
                    break;

                // 두 폭탄의 번호가 다를 경우 터지지 않습니다.
                if(num[i] != num[j])
                    continue;

                // 두 폭탄의 번호가 같을 경우 폭탄은 터집니다.
                // 해당 폭탄이 이미 터진 폭탄인지 확인하고,
                // 아직 터지지 않은 폭탄이라면 터진 폭탄의 개수를 갱신합니다.
                if(explode[i] == false) {
                    bomb[num[i]]++;
                    explode[i] = true;
                }

                if(explode[j] == false) {
                    bomb[num[j]]++;
                    explode[j] = true;
                }
            }
        
        for(int i = 0; i <= MAX_A; i++)
            if(maxval <= bomb[i]) {
                maxval = bomb[i];
                maxidx = i;
            }
        
        System.out.print(maxidx);
    }
}