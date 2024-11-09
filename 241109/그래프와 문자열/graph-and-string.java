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
//		System.out.println(patternHash);

		dfs(1, 0, 0);
	}

	private static void dfs(int node, int idx, long hash) {
//		System.out.printf("node %d: %d -> ", node, hash);
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
//		System.out.println(hash);
		
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