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

	static class Hash {
		long value;
		int len;

		public Hash(long value, int len) {
			super();
			this.value = value;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Hash [value=" + value + ", len=" + len + "]";
		}
	}

	static int N, answer, patternLen, MOD, POWER, indegree[];
	static long patternHash, pPow[];
	static String pattern;
	static List<Hash> hashes[];
	static List<Path> edges[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		indegree = new int[N + 1];
		pattern = st.nextToken();
		edges = new ArrayList[N + 1];
		hashes = new ArrayList[N + 1];
		answer = 0;

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			hashes[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);

			edges[from].add(new Path(to, toInt(ch)));
			indegree[to]++;
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

		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			preHashCheck(cur);

			for (Path path : edges[cur]) {
				hashes[path.node].add(new Hash(path.num, 1));

				for (Hash hash : hashes[cur]) {
					long nextHash = (hash.value + path.num) % MOD;
					if (nextHash < 0) {
						nextHash += MOD;
					}

					hashes[path.node].add(new Hash(nextHash, hash.len + 1));
				}

				if (--indegree[path.node] == 0) {
					queue.add(path.node);
				}
			}
		}
	}

	private static void preHashCheck(int cur) {
//		System.out.println(cur + " : " + hashes[cur]);
		for (int i = hashes[cur].size() - 1; i >= 0; i--) {
			Hash hash = hashes[cur].get(i);
			if (hash.len == patternLen) {
				if (hash.value == patternHash) {
					answer++;
				}
				hashes[cur].remove(i);
				continue;
			}

			long hashValue = hash.value;
			hashValue = (hashValue * POWER) % MOD;
			hash.value = hashValue;
		}
	}

	private static int toInt(char ch) {
		return ch - 'a' + 1;
	}
}