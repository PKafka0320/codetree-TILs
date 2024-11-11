import java.io.*;
import java.util.*;

public class Main {
	static String text;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		text = br.readLine();

		int maxLen = 0;
		int left = 1, right = text.length();
		while (left <= right) {
			int mid = (left + right) / 2;

			if (patternExist(mid)) {
				left = mid + 1;
				maxLen = mid;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(maxLen);
	}

	public static boolean patternExist(int patternLength) {
		int textLen = text.length();
		Set<Long> hashes = new HashSet<>();

		int[] POWER = {31, 37}, MOD = {(int) 1e9 + 7, (int) 1e9 + 9};
		long[][] patternPower = new long[2][patternLength + 1];
		patternPower[0][0] = patternPower[1][0] = 1;
		for (int k = 0; k < 2; k++) {
			for (int i = 1; i <= patternLength; i++) {
				patternPower[k][i] = (patternPower[k][i - 1] * POWER[k]) % MOD[k];
			}
		}

		long[] textHash = {0, 0};
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < patternLength; i++) {
				long hash = toInt(text.charAt(i)) * patternPower[k][patternLength - 1 - i];
				textHash[k] = (textHash[k] + hash) % MOD[k];
			}
		}

		hashes.add(genUniqueKey(textHash[0], textHash[1], MOD));

		for (int i = 1; i <= textLen - patternLength; i++) {
			for (int k = 0; k < 2; k++) {
				textHash[k] = ((textHash[k] * POWER[k]) - (toInt(text.charAt(i - 1)) * patternPower[k][patternLength])
						+ toInt(text.charAt(i + patternLength - 1))) % MOD[k];
				if (textHash[k] < 0) {
					textHash[k] += MOD[k];
				}
			}
			
			if (hashes.contains(genUniqueKey(textHash[0], textHash[1], MOD))) {
				return true;
			}
			hashes.add(genUniqueKey(textHash[0], textHash[1], MOD));
		}

		return false;
	}
	
    public static long genUniqueKey(long h1, long h2, int[] MOD) {
        return h1 * Math.max(MOD[0], MOD[1]) + h2;
    }

	public static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}

/*
import java.util.Scanner;
import java.util.HashSet;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static String s;
    
    public static int n;
    
    // 2개의 polynomial rolling 해싱을 위한 p, m 값을 정의합니다.
    public static int[] p = new int[]{31, 37};
    public static int[] m = new int[]{(int)1e9 + 7, (int)1e9 + 9};
    
    // p^i, 값을 m으로 나눈 나머지를 관리합니다.
    public static long[][] pPow = new long[2][MAX_N + 1];
    
    // 소문자 알파벳을 수로 변경합니다.
    public static int toInt(char c) {
        return c - 'a' + 1;
    }

    // (h1, h2) 값으로 unique한 값을 만들어줍니다.
    public static long genUniqueKey(long h1, long h2) {
        return h1 * Math.max(m[0], m[1]) + h2;
    }
    
    // 길이가 l인 부분 문자열 중 2번 이상 등장하는 경우가 있는지 확인합니다.
    public static boolean exists(int l) {
        // pPow 값을 계산합니다.
        for(int k = 0; k < 2; k++) {
            // pPow[i] = p^i % m
            pPow[k][0] = 1;
            for(int i = 1; i <= n; i++)
                pPow[k][i] = (pPow[k][i - 1] * p[k]) % m[k];
        }
    
        HashSet<Long> hs = new HashSet<>();
    
        // s에서 구간 [0, l - 1]에 해당하는 해싱값을 계산합니다.
        long[] h = new long[2];
        for(int k = 0; k < 2; k++) {
            for(int i = 0; i < l; i++)
                h[k] = (h[k] + toInt(s.charAt(i)) * pPow[k][l - 1 - i]) % m[k];
        }
    
        // set에 넣어줍니다.
        hs.add(genUniqueKey(h[0], h[1]));
    
        // [i, i + l - 1] 구간을 부분문자열로 하는 경우를 전부 탐색합니다.
        for(int i = 1; i <= n - l; i++) {
            for(int k = 0; k < 2; k++) {
                // hash 값을 갱신해줍니다.
                h[k] = (h[k] * p[k] - toInt(s.charAt(i - 1)) * pPow[k][l] + toInt(s.charAt(i + l - 1))) % m[k];
                // 값을 양수로 변환해줍니다.
                if(h[k] < 0)
                    h[k] += m[k];
            }
    
            // 이미 동일한 해싱값이 존재한다면
            // 2번 이상 등장하는 것입니다.
            if(hs.contains(genUniqueKey(h[0], h[1])))
                return true;
            
            // set에 넣어줍니다.
            hs.add(genUniqueKey(h[0], h[1]));
        }
    
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        s = sc.next();
        n = s.length();

        // Parametric Search를 진행합니다.
        int l = 1, r = n, ans = 0;
        while(l <= r) {
            int mid = (l + r) / 2;
            // 길이가 mid인 부분 문자열 중 2번 이상 등장하는 경우가 있다면
            // 답을 갱신해주고
            // 문자열의 길이를 더 늘려봐도 됩니다.
            if(exists(mid)) {
                ans = mid;
                l = mid + 1;
            }
            else 
                r = mid - 1;
        }

        System.out.print(ans);
    }
}
*/