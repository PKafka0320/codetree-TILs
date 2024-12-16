import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			int number = 0;
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				number |= (1 << val);
			}
			numbers[i] = number;
		}
		
		int answer = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if ((numbers[i] & numbers[j]) != 0) continue;
				
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 5000;
    
    // 변수 선언
    public static int n;
    public static int[] arr = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();

        // 각 그룹의 정보를 입력받습니다.
        for(int i = 0; i < n; i++) {
            int k;
            k = sc.nextInt();
            for(int j = 0; j < k; j++) {
                int x;
                x = sc.nextInt();

                // 각 그룹의 정보를 비트로 관리합니다.
                // x번 사람이 있을 경우
                // (1 + x)에 해당하는 비트가 1이 됩니다.
                arr[i] |= (1 << x);
            }
        }

        // 모든 그룹에 대해 겹치지 않는 모든 쌍의 개수를 찾습니다.
        int ans = 0;
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++) {
                // 두 그룹에 속한 사람 번호가 겹치지 않는다는 뜻은
                // 두 그룹을 and연산했을 때 0이 됨을 의미합니다.
                if((arr[i] & arr[j]) == 0)
                    ans++;
            }

        // 조건을 만족하는 그룹의 순서쌍의 개수를 출력합니다.
        System.out.print(ans);
    }
}
*/
