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
			
			if (queue.size() > 1) {
				System.out.println("inf");
				return;
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

/*
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;


public class Main {
    public static final int MAX_ALPHA = 26;
    public static final int MAX_N = 100;
    
    // 변수 선언
    public static int n;
    
    public static String[] word = new String[MAX_N + 1];
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_ALPHA + 1];
    
    // 진입차수를 관리합니다.
    public static int[] indegree = new int[MAX_ALPHA + 1];
    
    // 각 정점을 방문했는지 판단합니다.
    public static boolean[] visited = new boolean[MAX_ALPHA + 1];
    
    // 위상정렬을 위한 큐를 관리합니다.
    public static Queue<Integer> q = new LinkedList<>();
    
    // 알파벳을 숫자로 관리합니다.
    public static HashMap<Character, Integer> alphaToNum = new HashMap<>();
    public static char[] numToAlpha = new char[MAX_ALPHA + 1];
    
    // 그래프에서 노드의 수를 관리합니다.
    public static int N;
    
    public static ArrayList<Integer> ans = new ArrayList<>();
    public static boolean isInf = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();

        // 각 단어를 입력받습니다.
        for(int i = 1; i <= n; i++)
            word[i] = sc.next();

        // 쓰인 알파벳의 개수를 확인합니다.
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < word[i].length(); j++) {
                char x = word[i].charAt(j);

                // x가 지금까지 나오지 않은 알파벳이면 추가합니다.
                if(!alphaToNum.containsKey(x)) {
                    alphaToNum.put(x, ++N);
                    numToAlpha[N] = x;
                }
            }
        }

        for(int i = 1; i <= N; i++)
            edges[i] = new ArrayList<>();

        // 각 단어를 순서대로 보며 알파벳이
        // 사전순으로 어떻게 돼야 하는지
        // 그래프로 저장합니다.
        // 그래프를 인접 리스트로 관리합니다.
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < Math.min(word[i].length(), word[i + 1].length()); j++) {
                char x = word[i].charAt(j);
                char y = word[i + 1].charAt(j);

                // 두 알파벳이 다르면
                // x가 y보다 사전순으로 앞섭니다.
                if(x != y) {
                    edges[alphaToNum.get(x)].add(alphaToNum.get(y));
                    indegree[alphaToNum.get(y)]++; // 진입차수를 갱신합니다.
                    break;
                }
            }
        }

        // 처음 indegree 값이 0인 곳이 루트가 됩니다.
        // 이 노드들을 queue에 넣어주고, 정답으로 미리 저장해 놓습니다.
        for(int i = 1; i <= N; i++)
            if(indegree[i] == 0) {
                q.add(i);
            }
        
        // 위상정렬을 진행합니다.
        // queue에 원소가 남아있다면 계속 진행합니다.
        while(!q.isEmpty()) {
            // queue에 여러 값이 들어갔을 경우
            // 가능한 답이 여러개입니다.
            if(q.size() > 1)
                isInf = true;
            
            // 가장 앞에 있는 원소를 뽑아줍니다.
            int x = q.poll();

            visited[x] = true;
            ans.add(x);

            // x에서 갈 수 있는 모든 곳을 탐색합니다.
            for(int i = 0; i < edges[x].size(); i++) {
                int y = edges[x].get(i);

                // 해당 노드의 indegree를 1만큼 감소시켜줍니다.
                indegree[y]--;

                // indegree가 0이 되었다면
                // queue에 새로 넣어줍니다.
                if(indegree[y] == 0) {
                    q.add(y);
                }
            }
        }

        // 모든 노드를 방문했다면 그래프 내에 사이클이 존재하지 않는다는 뜻입니다.
        boolean isCycle = false;
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) 
                isCycle = true;
        }

        if(isCycle) {
            // 사이클이 존재한다면
            // 알파벳 순서를 만들어내는 것이
            // 불가능한 상황입니다. -1을 출력합니다.
            System.out.print(-1);
        }
        else if(isInf) {
            // 답이 여러개 존재한다면 inf를 출력합니다.
            System.out.print("inf");
        }
        else {
            // 올바른 순서가 있을 경우 정답을 출력합니다.
            for(int i = 0; i < ans.size(); i++)
                System.out.print(numToAlpha[ans.get(i)]);
        }
    }
}
*/