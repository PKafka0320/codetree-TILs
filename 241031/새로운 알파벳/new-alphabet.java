import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		boolean[] isUsed = new boolean[26];
		int[] lastWord = new int[100];
		List<Integer>[] edges = new ArrayList[26];
		int[] indegree = new int[26];
		
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < lastWord.length; i++) {
			lastWord[i] = -1;
		}
		
		for (int i = 0; i < N; i++) {
			String string = br.readLine();
			char[] word = string.toCharArray();
			
			for (int idx = 0; idx < word.length; idx++) {
				int wordNum = cti(word[idx]);
				isUsed[wordNum] = true;
				
				if (lastWord[idx] == -1) {
					lastWord[idx] = wordNum;
				} else if (lastWord[idx] != wordNum) {
					for (int j = idx+1; j < lastWord.length; j++) {
						lastWord[j] = -1;
					}
					
					edges[lastWord[idx]].add(wordNum);
					lastWord[idx] = wordNum;
					indegree[wordNum]++;
				}
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 0; i < 26; i++) {
			if (isUsed[i] && indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		if (queue.size() > 1) {
			System.out.println("inf");
			return;
		}
		
		int totalWordCount = 0;
		for (int i = 0; i < 26; i++) {
			if (isUsed[i]) {
				totalWordCount++;
			}
		}
		
		int count = 0;
		String answer = "";
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			answer += itc(cur);
			
			for (int next : edges[cur]) {
				if (--indegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		System.out.println(count == totalWordCount ? answer : -1);
	}
	
	public static int cti(char ch) {
		return ch - 'a';
	}
	
	public static char itc(int i) {
		return (char) (i + 'a');
	}
}