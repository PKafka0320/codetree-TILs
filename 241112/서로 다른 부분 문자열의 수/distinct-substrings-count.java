import java.io.*;
import java.util.*;

public class Main {
	static final int POWER[] = { 31, 37 }, MOD[] = { (int) 1e9 + 7, (int) 1e9 + 9 };
	static String text;
	static int textLen;
	static long powerValue[][], hash[];
	static long count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		text = br.readLine();
		textLen = text.length();
		powerValue = new long[2][textLen + 1];
		count = 0;

		for (int k = 0; k < 2; k++) {
			powerValue[k][0] = 1;
			for (int i = 1; i <= textLen; i++) {
				powerValue[k][i] = (powerValue[k][i - 1] * POWER[k]) % MOD[k];
			}
		}

		for (int patternLen = 1; patternLen <= textLen; patternLen++) {
			count += check(patternLen);
		}

		System.out.println(count);
	}

	public static int check(int patternLen) {
		Set<Long> hashSet = new HashSet<>();
		hash = new long[2];

		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < patternLen; i++) {
				hash[k] = (hash[k] + toInt(text.charAt(i)) * powerValue[k][patternLen - 1 - i]) % MOD[k];
			}

			if (hash[k] < 0) {
				hash[k] += MOD[k];
			}
		}

		hashSet.add(getHash(hash[0], hash[1]));

		for (int i = patternLen; i < textLen; i++) {
			for (int k = 0; k < 2; k++) {
				hash[k] = (hash[k] * POWER[k]) - (toInt(text.charAt(i - patternLen)) * powerValue[k][patternLen])
						+ toInt(text.charAt(i));

				if (hash[k] < 0) {
					hash[k] += MOD[k];
				}
			}

			hashSet.add(getHash(hash[0], hash[1]));
		}

		return hashSet.size();
	}

	public static long getHash(long hash1, long hash2) {
		return hash1 * Math.max(MOD[0], MOD[1]) + hash2;
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        s = sc.next();
        n = s.length();

        // pPow 값을 계산합니다.
        for(int k = 0; k < 2; k++) {
            // pPow[i] = p^i % m
            pPow[k][0] = 1;
            for(int i = 1; i <= n; i++)
                pPow[k][i] = (pPow[k][i - 1] * p[k]) % m[k];
        }

        // 동일한 길이 l에 대해
        // 문자열의 hashing값을 set으로 관리하여
        // 서로 다른 문자열의 수를 누적해줍니다.
        int ans = 0;
        for(int l = 1; l <= n; l++) {
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
                
                // set에 넣어줍니다.
                hs.add(genUniqueKey(h[0], h[1]));
            }

            // 길이가 l인 문자열 중 서로 다른 문자열의 수를 더해줍니다.
            ans += hs.size();
        }

        System.out.print(ans);
    }
}
*/
