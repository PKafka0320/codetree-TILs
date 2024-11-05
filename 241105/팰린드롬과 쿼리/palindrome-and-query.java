import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

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
		for (int i = 0; i < n; i++) {
			if (r < i) {
				maxHalfLen[i] = 0;
			} else {
				int ii = 2 * p - i;
				maxHalfLen[i] = Math.min(r - i, maxHalfLen[ii]);
			}
			
			while (i - maxHalfLen[i] - 1 >= 0 && i + maxHalfLen[i] + 1 < n &&
					inputString[i - maxHalfLen[i] - 1] == inputString[i + maxHalfLen[i] + 1]) {
				maxHalfLen[i]++;
			}
			
			if (i + maxHalfLen[i] > r) {
				r = i + maxHalfLen[i];
				p = i;
			}
		}
		
		StringBuilder answer = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int leftStr = Integer.parseInt(st.nextToken());
			int rightStr = Integer.parseInt(st.nextToken());
			int left = 2*leftStr - 1;
			int right = 2*rightStr - 1;
			int mid = (left + right) / 2;
			
			answer.append((2*maxHalfLen[mid]+1)/2 >= rightStr - leftStr ? "Yes\n" : "No\n");
		}
		System.out.println(answer.toString());
		
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 200001;
    
    // 변수 선언
    public static String temp;
    public static char[] inputStr = new char[MAX_N];
    public static int n, q;
    
    // A : i번지를 중심으로 하는 홀수 길이의 팰린드롬 중 
    //     가장 긴 팰린드롬의 반지름의 길이
    // 즉, [i - A[i], i + A[i]]가 i를 중심으로 하는 최장 팰린드롬이 됩니다.
    public static int[] A = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력:
        n = sc.nextInt();
        q = sc.nextInt();

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
                  inputStr[i - A[i] - 1] == inputStr[i + A[i] + 1])
                A[i]++; 

            // i + A[i] 중 최대가 선택되도록
            // r, p값을 갱신해줍니다.
            if(i + A[i] > r) {
                r = i + A[i];
                p = i;
            }
        }

        // q개의 질의에 대한 답을 처리합니다.
        while(q-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            a--;
            b--;

            // 처음 주어진 문자열에서 a번 인덱스와 b번 인덱스는 각각
            // 새로 만들어진 문자열에서 2 * a - 1와 2 * b - 1입니다.
            a = 2 * a + 1;
            b = 2 * b + 1;

            // (a + b) / 2의 위치에서 최장 팰린드롬의 길이를 확인합니다.
            // 만약 a ~ b까지의 문자열보다 최장 팰린드롬의 길이가 길다면
            // 해당 문자열은 팰린드롬이 맞습니다.
            int mid = (a + b) / 2;
            int maxLen = 2 * A[mid] + 1;
            if(maxLen >= b - a + 1)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
*/