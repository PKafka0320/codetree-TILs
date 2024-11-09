import java.io.*;
import java.util.*;

public class Main {
	static class Path {
		int node, num;

		public Path(int node, int num) {
			super();
			this.node = node;
			this.num = num;
		}
	}
	static int N, answer, patternLen, MOD, POWER, memo[];
	static long patternHash, pPow[];
	static String pattern;
	static List<Path> edges[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		pattern = st.nextToken();
		edges = new ArrayList[N + 1];
		memo = new int[N + 1];
		answer = 0;

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);

			edges[from].add(new Path(to, toInt(ch)));
		}

		manacher();

		System.out.println(answer);
	}

	private static void manacher() {
		patternLen = pattern.length();
		POWER = 31;
		MOD = (int) 1e9 + 7;
		pPow = new long[patternLen + 1];
		patternHash = 0;

		pPow[0] = 1;
		for (int i = 1; i <= patternLen; i++) {
			pPow[i] = (pPow[i - 1] * POWER) % MOD;
		}

		for (int i = 0; i < patternLen; i++) {
			patternHash = (patternHash + toInt(pattern.charAt(i)) * pPow[patternLen - 1 - i]) % MOD;
		}

		dfs(1, 0, 0);
	}

	private static void dfs(int node, int idx, long hash) {
		if (idx == patternLen) {
			for (int i = 0; i < patternLen; i++) {
				hash = (hash + memo[i] * pPow[patternLen - 1 - i]) % MOD;
			}
		} else if (idx > patternLen) {
			hash = ((hash * POWER) - (memo[idx - patternLen - 1] * pPow[patternLen]) + memo[idx - 1]) % MOD;
			if (hash < 0) {
				hash += MOD;
			}
		}
		
		if (hash == patternHash) {
			answer++;
		}

		for (Path path : edges[node]) {
			memo[idx] = path.num;
			dfs(path.node, idx+1, hash);
		}
	}

	private static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}

/*
import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    int y; char c;
    
    public Pair(int y, char c) {
        this.y = y;
        this.c = c;
    }
}

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static String pattern;
    
    public static ArrayList<Pair>[] edges = new ArrayList[MAX_N + 1];
    
    public static char[] path = new char[MAX_N + 1];
    
    public static int n, l;
    
    // 2개의 polynomial rolling 해싱을 위한 p, m 값을 정의합니다.
    public static int[] p = new int[]{31, 37};
    public static int[] m = new int[]{(int)1e9 + 7, (int)1e9 + 9};
    
    // p^i, 값을 m으로 나눈 나머지를 관리합니다.
    public static long[][] pPow = new long[2][MAX_N + 1];
    
    // 처음 주어진 패턴에 대한 해싱값을 관리합니다.
    public static long[] pH = new long[2];
    
    public static int ans;
    
    // 소문자 알파벳을 수로 변경합니다.
    public static int toInt(char c) {
        return c - 'a' + 1;
    }
    
    public static void dfs(int x, int cnt, long tH1, long tH2) {
        long[] tH = new long[]{tH1, tH2};
    
        // 해싱값을 계산해줍니다.
        if(cnt == l) {
            // text에서 구간 [0, l - 1]에 해당하는 해싱값을 계산합니다.
            for(int k = 0; k < 2; k++) {
                for(int i = 0; i < l; i++)
                    tH[k] = (tH[k] + toInt(path[i]) * pPow[k][l - 1 - i]) % m[k];
            }
        }
        else if(cnt > l) {
            for(int k = 0; k < 2; k++) {
                // 이전 [cnt - l - 1, cnt - 2]에 해당하는 해싱값은 tH에 있습니다.
                // 이전 값(tH)은 (T[cnt - l - 1] * p^(l - 1) + T[cnt - l] * p^(l - 2) + ... + T[cnt - 2] * 1) % m입니다.
                // 이제 tH * p - T[cnt - l - 1] * p^l + T[cnt - 1]를 계산하면
                // 새로 계산을 원하는 해싱값인 (T[cnt - l] * p^(l - 1) + T[cnt - l + 1] * p^(l - 2) + ... + T[cnt - 1] * 1) % m이 됩니다.
                tH[k] = (tH[k] * p[k] - toInt(path[cnt - l - 1]) * pPow[k][l] + toInt(path[cnt - 1])) % m[k];
                // tH값을 양수로 변환해줍니다.
                if(tH[k] < 0)
                    tH[k] += m[k];
            }
        }
    
        // 일치하면 답을 갱신해줍니다.
        if(tH[0] == pH[0] && tH[1] == pH[1])
            ans++;
    
        for(int i = 0; i < edges[x].size(); i++) {
            int y = edges[x].get(i).y;
            char c = edges[x].get(i).c;
            path[cnt] = c;
            dfs(y, cnt + 1, tH[0], tH[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        pattern = sc.next();
        l = pattern.length();

        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        for(int i = 0; i < n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            char c = sc.next().charAt(0);
            edges[x].add(new Pair(y, c));
        }

        // pPow 값을 계산합니다.
        for(int k = 0; k < 2; k++) {
            // pPow[i] = p^i % m
            pPow[k][0] = 1;
            for(int i = 1; i <= n; i++)
                pPow[k][i] = (pPow[k][i - 1] * p[k]) % m[k];
        }

        // pattern에 대한 해싱값인 pH값을 계산합니다.
        // pH = (P[0] * p^(l - 1) + P[1] * p^(l - 2) + ... + P[l - 1] * 1) % m
        // 소문자 알파벳은 a부터 z까지 순서대로 1부터 26까지의 수와 대응됩니다.
        for(int k = 0; k < 2; k++) {
            for(int i = 0; i < l; i++)
                pH[k] = (pH[k] + toInt(pattern.charAt(i)) * pPow[k][l - 1 - i]) % m[k];
        }

        dfs(1, 0, 0, 0);

        System.out.print(ans);
    }
}
*/