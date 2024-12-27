import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100;
    public static final int MAX_A = 10000;
    
    public static int n, m;
    public static int[] a = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        
        // 100개의 숫자를 여러 개의 구간으로 나누는 경우의 수는 굉장히 많습니다.
        // 숫자들을 모든 구간으로 나누어 완전탐색하기 위해서는
        // 최악의 경우 99C50 = 약 5 * 10^28개의 경우를 세야 합니다.
        // 이는 아무리 컴퓨터라 해도 너무 많은 경우의 수를 세야 합니다.
        // 따라서 보다 효율적인 방법을 찾아야 합니다.

        // 잘 생각해 보면, 구간의 합의 최댓값을 결정한다면
        // 구간을 몇 개로 나눠야 하는지 손쉽게 찾을 수 있습니다.
        int ans = MAX_A;
        for(int i = 1; i <= MAX_A; i++) {
            // 구간의 합의 최댓값이 i일 때

            // possible : 구간을 나눌 수 있으면 true
            // section : 나뉘어져야 하는 구간의 개수
            boolean possible = true;
            int section = 1;

            int cnt = 0;
            for(int j = 0; j < n; j++) {
                // 숫자 하나가 i보다 크면 구간을 나눌 수 없습니다.
                if(a[j] > i) {
                    possible = false;
                    break;
                }
                
                // j번째 숫자가 들어갔을 때 i보다 커지면
                // j번째 숫자부터 다음 구간으로 만듭니다.
                if(cnt + a[j] > i) {
                    cnt = 0;
                    section++;
                }

                // 이번 구간에 j번째 숫자를 집어넣습니다.
                cnt += a[j];
            }

            if(possible && section <= m) {
                ans = Math.min(ans, i);
            }
        }

        System.out.print(ans);
    }
}