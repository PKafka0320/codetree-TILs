import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 1000;
    public static final int MAX_C = 3;
    
    // 변수 선언
    public static int[][] arr = new int[MAX_NUM + 1][MAX_NUM + 1];
    public static int[][][] prefixSum = new int[MAX_C + 1][MAX_NUM + 1][MAX_NUM + 1];
    
    public static int n, m, k;
    
    // 특정 숫자 c에 대해 
    // (x1, y1), (x2, y2) 직사각형 구간 내의 원소의 합을 반환합니다.
    public static int getSum(int c, int x1, int y1, int x2, int y2) {
        return prefixSum[c][x2][y2]     - prefixSum[c][x1 - 1][y2] -
               prefixSum[c][x2][y1 - 1] + prefixSum[c][x1 - 1][y1 - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for(int i = 1; i <= n; i++) {
            String input = sc.next();
            for(int j = 1; j <= m; j++) {
                // 편의를 위해 
                // 입력받은 문자 a, b, c를 각각 
                // 1, 2, 3으로 바꿔서 저장해줍니다.
                char c = input.charAt(j - 1);

                if(c == 'a')
                    arr[i][j] = 1;
                else if(c == 'b')
                    arr[i][j] = 2;
                else
                    arr[i][j] = 3;
            }
        }
        
        // 누적합 배열을 만들어줍니다.
        // prefixSum[c][i][j] : 숫자가 c인 경우에 대한 누적합을 저장합니다.
        for(int c = 1; c <= 3; c++) {
            for(int i = 1; i<= n; i++)
                for(int j = 1; j <= m; j++) {
                    int additionalValue = 0;

                    // (i, j) 위치에 적혀있는 숫자가 c인 경우에만
                    // 값을 1 증가시켜줍니다.
                    if(arr[i][j] == c)
                        additionalValue = 1;

                    prefixSum[c][i][j] = prefixSum[c][i - 1][j] + 
                                    prefixSum[c][i][j - 1] -
                                    prefixSum[c][i - 1][j - 1] +
                                    additionalValue;
                }
        }
        
        // k개의 질의에 대한
        // 답을 출력합니다.
        while(k-- > 0) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            System.out.print(getSum(1, x1, y1, x2, y2) + " ");
            System.out.print(getSum(2, x1, y1, x2, y2) + " ");
            System.out.println(getSum(3, x1, y1, x2, y2));
        }
    }
}