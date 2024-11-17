import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String text = br.readLine();

		int[] failure = makeFailure(text);

		int result = 1;
		for (int i = 1; i < text.length(); i++) {
			if (failure[i] == 0) {
				result = i + 1;
			}
		}
		
		System.out.println(result);
	}

	public static int[] makeFailure(String text) {
		int[] result = new int[text.length()];

		for (int i = 1, j = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != text.charAt(j)) {
				j = result[j - 1];
			}

			if (text.charAt(i) == text.charAt(j)) {
				result[i] = ++j;
			}
		}

		return result;
	}
}

/*
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n;
    public static String text;
    
    // failure function입니다.
    // f[i] : pattern에서 
    //        [1, i]로 이루어진 문자열 중
    //        접두사와 접미사가 일치하는 최장 길이 (단, 자기자신은 제외)
    public static int[] f = new int[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        text = sc.next();

        n = text.length();

        // 구현의 편의를 위해 맨 앞에 #을 붙여
        // 문자열을 1번지부터 사용합니다.
        text = "#" + text;

        // failure function값을 먼저 계산합니다.
        f[0] = -1; // f[0]은 구현의 편의를 위해 -1로 설정합니다.
        for(int i = 1; i <= n; i++) {
            // 시작은 최적의 답에 해당하는 f[i - 1]에서 합니다.
            // 그 전 위치까지 최적의 (접두사, 접미사) 매칭 결과 바로 뒤에
            // 추가되는 것이 가능하다면 최적의 답이 되기 때문입니다.
            int j = f[i - 1];
            // [1, i - 1]까지는
            // 정확히 길이 j만큼 접두사와 접미사가 일치한다고 했을 때
            // 그 다음 문자인 pattern[j + 1]과 pattern[i]가 일치하는지를 확인합니다.
            // 일치하지 않는다면 그 다음 후보로 j값을 옮겨줍니다.
            while(j >= 0 && text.charAt(j + 1) != text.charAt(i))
                j = f[j];

            // [1, i - 1]까지 일치하며 동시에 그 다음 문자까지 일치하는 최대 j가 구해져있으므로
            // 이제 그 값에 1을 더한 결과가 f[i]가 됩니다.
            // 매칭에 실패했더라도 f[0]에는 -1이 들어있기에
            // f[i] = 0이 됩니다.
            f[i] = j + 1;
        }
        
        // 관찰 1 : 답이 k라고 할 때, 문자열 T의
        // 가장 앞 k번째 문자열을 계속해서 길이가 n일 때까지
        // 이어 붙이면 문자열 T가 만들어집니다.

        // 관찰 2 : Failure function의 정의에 따르면,
        // 1 ~ f[n]과, n - f[n] + 1 ~ n이 값이 같아집니다.
        // 이는 곧, 1 ~ (n - f[n])의 문자열이 반복되는 구조를 이룹니다.
        // f[n]은 이를 만족하는 최댓값이기도 하므로, 정답은 n - f[n]이 됩니다.
        System.out.print(n - f[n]);
    }
}
*/
