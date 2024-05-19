import java.io.*;
import java.util.*;

class Reserve implements Comparable<Reserve> {
    int day, value, index;
    
    public Reserve(int day, int value, int index) {
        this.day = day;
        this.value = value;
        this.index = index;
    }
    
    @Override
    public int compareTo(Reserve r) {
        if (this.day == r.day) return r.value - this.value; // 들어오는 날이 우선이기 때문에 내림차순으로 정렬
        return this.day - r.day; // 날짜 순으로 정렬
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 사람의 수
        
        ArrayList<Reserve> reserves = new ArrayList<>(); // 예약
        TreeSet<Integer> rooms = new TreeSet<>(); // 예약에 필요한 방 번호
        HashMap<Integer, Integer> assigns = new HashMap<>(); // <K, V>: K번 사람에게 할당된 방 번호
        int count = 0; // 최대 방의 개수
        
        for (int idx = 0 ; idx < n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            
            reserves.add(new Reserve(start, 1, idx));
            reserves.add(new Reserve(end, -1, idx));
        }
        
        Collections.sort(reserves);
        
        // 예약의 시작과 끝 지점에 따라 방 번호를 할당
        for (Reserve reserve : reserves) {
            // 들어오는 날인 경우 방을 할당
            // 할당 가능한 방이 없다면 추가
            if (reserve.value == 1) {
                if (rooms.isEmpty()) {
                    count++;
                    rooms.add(count);
                }
                
                assigns.put(reserve.index, rooms.pollFirst());
            }
            
            // 나가는 날인 경우 방을 반환
            else {
                rooms.add(assigns.get(reserve.index));
                assigns.remove(reserve.index);
            }
        }
        
        System.out.println(count);
    }
}