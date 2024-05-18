import java.io.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

class Tuple implements Comparable<Tuple> {
    int position, value, index, level;
    
    public Tuple(int position, int value, int index, int level) {
        this.position = position;
        this.value = value;
        this.index = index;
        this.level = level;
    }
    
    @Override
    public int compareTo(Tuple t) {
        return position - t.position;
    }
}

class Pair implements Comparable<Pair> {
    int level, index;
    
    public Pair(int level, int index) {
        this.level = level;
        this.index = index;
    }
    
    @Override
    public int compareTo(Pair p) {
        return level - p.level;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine()); // 선분의 개수
        TreeSet<Tuple> infos = new TreeSet<>(); // 선분의 시작과 끝 지점 정보
        TreeSet<Pair> lines = new TreeSet<>(); // 현재 위치에 있는 선분 정보
        HashSet<Integer> bottomLines = new HashSet<>(); // 보이는 선분
        
        for (int idx = 1; idx <= n; idx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int level = Integer.parseInt(tokenizer.nextToken());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            
            infos.add(new Tuple(start, 1, idx, level));
            infos.add(new Tuple(end, -1, idx, level));
        }
        
        for (Tuple tuple : infos) {
            if (tuple.value == 1) {
                lines.add(new Pair(tuple.level, tuple.index));
            }
            else if (tuple.value == -1) {
                lines.remove(new Pair(tuple.level, tuple.index));
            }
            
            if (lines.isEmpty()) continue;
            bottomLines.add(lines.first().index);
        }
        
        System.out.println(bottomLines.size());
    }
}