import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final int MAX_N = 8;
    
    // 변수 선언
    public static int n;
    public static boolean[] visited = new boolean[MAX_N + 1];
    public static ArrayList<Integer> picked = new ArrayList<>();
    
    // 지금까지 선택한 수의 개수를 cnt라 했을 때
    // 계속 탐색을 이어서 진행합니다.
    public static void getPermutation(int cnt) {
        // 모든 원소를 선택했을 때, 해당 순열을 출력합니다.
        if(cnt == n) {
            for(int i = 0; i < (int) picked.size(); i++)
                System.out.print(picked.get(i) + " ");
            System.out.println();
            return;
        }
    
        // 뒤에서부터 하나씩 원소를 선택합니다.
        for(int i = n; i >= 1; i--) {
            if(visited[i]) continue;
            visited[i] = true;
            picked.add(i);
    
            getPermutation(cnt + 1);
    
            visited[i] = false;
            picked.remove(picked.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        getPermutation(0);
    }
}