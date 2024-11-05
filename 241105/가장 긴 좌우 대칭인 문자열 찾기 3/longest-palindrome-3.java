import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		char c = st.nextToken().charAt(0);

		String input = br.readLine();
		int n = 2 * N + 1;
		char[] inputString = new char[n];
		int[] maxHalfLen = new int[n];

		for (int i = 0; i < N; i++) {
			inputString[2 * i] = '#';
			inputString[2 * i + 1] = input.charAt(i);
		}
		inputString[n - 1] = '#';

		int r = -1, p = -1;
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (r < i) {
				maxHalfLen[i] = 0;
			} else {
				int ii = 2 * p - i;
				maxHalfLen[i] = Math.min(r - i, maxHalfLen[ii]);
			}

			if (inputString[i] == c)
				continue;

			while (i - maxHalfLen[i] - 1 >= 0 && i + maxHalfLen[i] + 1 < n
					&& inputString[i - maxHalfLen[i] - 1] == inputString[i + maxHalfLen[i] + 1]
					&& inputString[i - maxHalfLen[i] - 1] != c) {
				maxHalfLen[i]++;
			}
			answer = Math.max(answer, maxHalfLen[i]);

			if (i + maxHalfLen[i] > r) {
				r = i + maxHalfLen[i];
				p = i;
			}
		}

		System.out.println((2*answer + 1)/2);

	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 600001;
    
    // 변수 선언
    public static String temp;
    public static char[] inputStr = new char[MAX_N];
    public static int n;
    public static char c;
    
    // A : i번지를 중심으로 하는 홀수 길이의 팰린드롬 중 
    //     가장 긴 팰린드롬의 반지름의 길이
    // 즉, [i - A[i], i + A[i]]가 i를 중심으로 하는 최장 팰린드롬이 됩니다.
    public static int[] A = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        c = sc.next().charAt(0);
        String temp = sc.next();
        int len = temp.length();

        // Manacher's algorithm을 적용하기 위해
        // 주어진 문자열 내 문자 사이사이에 #을 넣어줍니다.
        for(int i = 0; i < len; i++) {
            inputStr[i * 2] = '#';
            inputStr[i * 2 + 1] = temp.charAt(i);
        }

        n = len * 2 + 1;
        inputStr[n - 1] = '#';

        // Manacher's algorithm을 진행해봅니다.
        int r = -1, p = -1;
        // r : j < i를 만족하는 j들 중 Math.max(j + A[j]) 값을 기록합니다.
        // p : Math.max(j + A[j]) 가 되는 j의 값을 기록합니다.
        for(int i = 0; i < n; i++) {
            // 만약 r값이 i보다 작다면
            // 줄일 수 있는 부분이 없으므로
            // A[i] = 0으로 시작합니다.
            if(r < i)
                A[i] = 0;
            // r값이 i보다 같거나 크다면
            // i를 p로부터 대칭시켰을 때의 위치인 ii에 대해
            // 이미 계산된 A[ii]값을 이용하여
            // i를 중심으로 뻗어나갈 수 있는 적절한 초기값을 
            // O(1)에 정해줄 수 있습니다.
            else {
                int ii = 2 * p - i;
                A[i] = Math.min(r - i, A[ii]);
            }

            // i를 중심으로 최대로 뻗어나갑니다.
            while(i - A[i] - 1 >= 0 && i + A[i] + 1 < n && 
                  inputStr[i - A[i] - 1] == inputStr[i + A[i] + 1] && inputStr[i + A[i] + 1] != c)
                A[i]++; 

            // 만약 현재 위치에 있는 수가 c라면
            // A[i]는 0이 됩니다.
            if(inputStr[i] == c)
                A[i] = 0;

            // i + A[i] 중 최대가 선택되도록
            // r, p값을 갱신해줍니다.
            if(i + A[i] > r) {
                r = i + A[i];
                p = i;
            }
        }

        // 최장 팰린드롬의 길이를 계산합니다.
        int ans = 0;
        for(int i = 0; i < n; i++)
            ans = Math.max(ans, 2 * A[i] + 1);
        
        // 처음 주어진 문자열에서 
        // #을 제외한 부분의 길이가 실제 답이 되기에
        // 2로 나눴을 때의 몫이 답이 됩니다.
        System.out.print(ans / 2);
    }
}
*/