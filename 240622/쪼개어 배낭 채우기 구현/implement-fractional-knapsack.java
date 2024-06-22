import java.io.*;
import java.util.*;

class Jewel implements Comparable<Jewel> {
    int weight, cost;
    
    public Jewel(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public int compareTo(Jewel other) {
        double current = this.cost / this.weight;
        double cmp = other.cost / other.weight;
        
        if (current > cmp) {
            return 1;
        }
        else {
            return -1;
        }
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()); // 보석의 개수
        int m = Integer.parseInt(tokenizer.nextToken()); // 가방의 크기
        
        Jewel[] jewels = new Jewel[n]; // [i]: i번째 보석의 정보
        
        for (int idx = 0; idx < n; idx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            
            jewels[idx] = new Jewel(weight, cost);
        }
        
        Arrays.sort(jewels);
        
        int currentIdx = n - 1; // 현재 확인중인 보석의 인덱스
        double answer = 0; // 얻을 수 있는 최대 가치
        
        while (m > 0) {
            if (m >= jewels[currentIdx].weight) {
                answer += jewels[currentIdx].cost;
            }
            else {
                answer += (double) jewels[currentIdx].cost / jewels[currentIdx].weight * m;
            }
            
            m -= jewels[currentIdx].weight;
            currentIdx--;
        }
        System.out.printf("%.3f", answer);
    }
}