import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100;
    
    public static int n;
    public static int[] a = new int[MAX_N];
    public static int[] b = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        int maxWin = 0;

        // Case 1. 1이 2를 이기고, 2가 3을 이기고 3이 1을 이기는 경우
        int win = 0;
        for(int i = 0; i < n; i++) {
            if(a[i] == 1 && b[i] == 2)
                win++;
            else if(a[i] == 2 && b[i] == 3)
                win++;
            else if(a[i] == 3 && b[i] == 1)
                win++;
        }

        maxWin = Math.max(maxWin, win);

        // Case 2. 1이 3을 이기고, 3이 2를 이기고 2가 1을 이기는 경우
        win = 0;
        for(int i = 0; i < n; i++) {
            if(a[i] == 1 && b[i] == 3)
                win++;
            else if(a[i] == 3 && b[i] == 2)
                win++;
            else if(a[i] == 2 && b[i] == 1)
                win++;
        }
        
        maxWin = Math.max(maxWin, win);

        System.out.print(maxWin);
    }
}