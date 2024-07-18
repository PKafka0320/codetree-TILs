import java.io.*;
import java.util.*;

// 그래프 데이터 클래스
// 데이터: 도착 지점, 버스번호, 비용
class Path {
	int destination, busId;
	long fare;
	
	public Path(int destination, int busId, long fare) {
		this.destination = destination;
		this.busId = busId;
		this.fare = fare;
	}
	
	@Override
	public String toString() {
		return destination + ":" + busId;
	}
}

// 최소 비용으로 이동할 수 있는 다음 위치
// 데이터: 다음 위치, 버스번호, 다음 위치로 이동할 때의 비용
class Route implements Comparable<Route> {
	int destination, busId, time;
	long fare;
	
	public Route(int destination, int busId, long fare, int time) {
		this.destination = destination;
		this.busId = busId;
		this.fare = fare;
		this.time = time;
	}
	
	@Override
	public int compareTo(Route o) {
		if (this.fare > o.fare) return 1;
		else if (this.fare == o.fare) return 0;
		else return -1;
	}
	
	@Override
	public String toString() {
		return destination + ":" + busId + "(" + fare + ")";
	}
}

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 출발 지점, 도착 지점, 버스의 수 입력
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int startStation = Integer.parseInt(st.nextToken()) - 1;
    	int endStation = Integer.parseInt(st.nextToken()) - 1;
    	int totalBusCount = Integer.parseInt(st.nextToken());
    	
    	// 인접리스트 그래프 생성 및 데이터 입력
    	int maxStationIdx = 0;
    	int[] fares = new int[totalBusCount];
    	int[][] routes = new int[totalBusCount][];
    	for (int busId = 0; busId < totalBusCount; busId++) {
    		st = new StringTokenizer(br.readLine());
    		fares[busId] = Integer.parseInt(st.nextToken());
    		int routeCount = Integer.parseInt(st.nextToken());
    		
    		routes[busId] = new int[routeCount];
    		st = new StringTokenizer(br.readLine());
    		for (int count = 0; count < routeCount; count++) {
    			routes[busId][count] = Integer.parseInt(st.nextToken()) - 1;
    			maxStationIdx = Math.max(maxStationIdx, routes[busId][count]);
    		}
    	}
    	
		ArrayList<Path>[] graph = new ArrayList[maxStationIdx + 1];
    	for (int idx = 0; idx <= maxStationIdx; idx++) {
    		graph[idx] = new ArrayList<>();
    	}
    	
    	for (int busId = 0; busId < totalBusCount; busId++) {
    		int recentStation = routes[busId][0];
    		for (int station = 1; station < routes[busId].length; station++) {
    			int nextStation = routes[busId][station];
    			graph[nextStation].add(new Path(recentStation, busId, fares[busId]));
    			recentStation = nextStation;
    		}
    	}

    	// 도착 지점에서 각 지점으로 갈 수 있는 최소 비용 배열 생성 및 초기화
    	long[] minFares = new long[maxStationIdx + 1];
    	Arrays.fill(minFares, -1);
    	minFares[endStation] = 0;
    	
    	// 도착 지점에서 각 지점으로 갈 수 있는 최단 비용에 따른 시간 배열 생성 및 초기화
    	int[] times = new int[maxStationIdx + 1];
    	Arrays.fill(times, -1);
    	times[endStation] = 0;
    	
    	// 현재 타고 있는 버스와 비교하면서 다음 위치로의 최소 비용 및 버스 번호 갱신
    	PriorityQueue<Route> pq = new PriorityQueue<>();
    	pq.add(new Route(endStation, -1, 0, 0));
    	
    	while (!pq.isEmpty()) {
    		Route currentRoute = pq.poll();
    		int currentStation = currentRoute.destination;
    		int currentBusId = currentRoute.busId;
    		long currentFare = currentRoute.fare;
    		int currentTime = currentRoute.time;
    		
    		if (currentFare != minFares[currentStation]) continue;
    		
    		for (Path path : graph[currentStation]) {
    			int nextStation = path.destination;
    			int nextBusId = path.busId;
    			long nextFare = path.fare;
    			
    			if (minFares[nextStation] == -1) {
    				if (currentBusId == nextBusId) {
    					minFares[nextStation] = currentFare;
    				} else {
    					minFares[nextStation] = currentFare + nextFare;
    				}
					times[nextStation] = currentTime + 1;
    				pq.add(new Route(nextStation, nextBusId, minFares[nextStation], currentTime + 1));
    			} else if (minFares[nextStation] > minFares[currentStation]) {
    				if (currentBusId == nextBusId && minFares[nextStation] > minFares[currentStation]) {
    					minFares[nextStation] = currentFare;
    				} else if (currentBusId != nextBusId && minFares[nextStation] > minFares[currentStation] + nextFare) {
    					minFares[nextStation] = currentFare + nextFare;
    				}
					times[nextStation] = currentTime + 1;
    				pq.add(new Route(nextStation, nextBusId, minFares[nextStation], currentTime + 1));
    			}
    		}
    	}
    	
    	System.out.println(minFares[startStation] + " " + times[startStation]);
    }
}