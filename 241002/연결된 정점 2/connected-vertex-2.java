import java.io.*;
import java.util.*;

public class Main {
	static int N, roots[], counts[];
	static int MAX = 100_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		roots = new int[MAX+1];
		counts = new int[MAX+1];
		
		for (int i = 1; i <= MAX; i++) {
			roots[i] = i;
			counts[i] = 1;
		}
		
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
			answer.append(counts[find(a)]).append("\n");
		}
		System.out.println(answer);
	}
	
	public static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if (A == B) return;
		
		roots[B] = A;
		counts[A] += counts[B];
	}
	
	public static int find(int a) {
		if (roots[a] == a) return a;
		return roots[a] = find(roots[a]);
	}

}