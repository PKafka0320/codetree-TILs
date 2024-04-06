import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int[] num;
    static int[][] length; // 증가-감소가 바뀌는 지점에 따른 부분 수열 길이

    public static void main(String[] args) {
        n = sc.nextInt();
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }

        length = new int[n][2];
        for (int i = 0; i < n; i++) {
            length[i][0] = 1;
            length[i][1] = 1;

            for (int j = 0; j < i; j++) {
                // 증가하는 경우 num[i]보다 작은 값의 최장 증가 부분 수열 길이를 활용
                if (num[j] < num[i]) {
                    length[i][0] = Math.max(length[i][0], length[j][0] + 1);
                }

                // 감소하는 경우 num[i]보다 큰 값의 최장 감소 부분 수열 길이를 활용
                if (num[j] > num[i]) {
                    length[i][1] = Math.max(length[i][1], length[j][1] + 1);
                }
            }

            // num[i]에서 증가-감소가 변하는 지점이라고 할 경우 증가/감소 부분 수열 길이 중
            // 더 큰 값을 선택
            length[i][1] = Math.max(length[i][1], length[i][0]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(Math.max(length[i][0], length[i][1]), ans);
        }

        System.out.println(ans);
    }
}