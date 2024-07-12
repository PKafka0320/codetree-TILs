import java.io.*;
import java.util.*;

class Place implements Comparable<Place> {
	int index, time;
	
	public Place(int index, int time) {
		this.index = index;
		this.time = time;
	}
	
	@Override
	public int compareTo(Place other) {
		return this.time - other.time;
	}
}

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer tokenizer;
    	
    	tokenizer = new StringTokenizer(reader.readLine());
    	int n = Integer.parseInt(tokenizer.nextToken()); // 장소의 개수
    	int m = Integer.parseInt(tokenizer.nextToken()); // 간선의 개수
    	ArrayList<Place>[] graph = new ArrayList[n + 1]; // [i]: i번 장소와 연결된 장소들
    	PriorityQueue<Place> pQueue = new PriorityQueue<>(); // 이동할 수 있는 장소 목록
    	int[] minTime = new int[n + 1]; // [i]: i번 장소로 이동할 수 있는 최단시간
    	int maxTime = 0; // 최장시간
    	
    	// 그래프, 최단시간 배열 초기화
    	for(int idx = 1; idx <= n; idx++) {
    		graph[idx] = new ArrayList<>();
    		minTime[idx] = -1;
    	}
    	
    	// 그래프 생성
    	for(int idx = 0; idx < m; idx++) {
    		tokenizer = new StringTokenizer(reader.readLine());
    		int fromIdx = Integer.parseInt(tokenizer.nextToken());
    		int toIdx = Integer.parseInt(tokenizer.nextToken());
    		int time = Integer.parseInt(tokenizer.nextToken());
    		
    		graph[toIdx].add(new Place(fromIdx, time));
    	}
    	
    	// 초기값 설정
    	minTime[n] = 0;
    	pQueue.add(new Place(n, 0));
    	
    	// 다익스트라
    	while(!pQueue.isEmpty()) {
    		Place current = pQueue.poll();
    		int currentIndex = current.index;
    		int currentTime = current.time;
    		
    		// 최신 데이터가 아닐 경우 무시
    		if (minTime[currentIndex] != currentTime) continue;

    		for (Place nextPlace : graph[currentIndex]) {
    			int nextIndex = nextPlace.index;
    			int nextTime = nextPlace.time;
    			
    			// 최단시간 갱신
    			int newTime = minTime[currentIndex] + nextTime;
    			if (minTime[nextIndex] == -1 || minTime[nextIndex] > newTime) {
    				minTime[nextIndex] = newTime;
    				pQueue.add(new Place(nextIndex, newTime));
    			}
    		}
    	}
    	
    	for (int idx = 1; idx <= n; idx++) {
    		maxTime = Math.max(maxTime, minTime[idx]);
    	}
    	System.out.println(maxTime);
    }
}