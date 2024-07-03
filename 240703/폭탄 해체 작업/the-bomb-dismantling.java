import java.io.*;
import java.util.*;

class Bomb implements Comparable<Bomb> {
	int value, time;
	
	public Bomb(int value, int time) {
		this.value = value;
		this.time = time;
	}
	
	@Override
	public int compareTo(Bomb other) {
		if (this.time == other.time) return other.value - this.value;
		return this.time - other.time;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		
		int n = Integer.parseInt(reader.readLine()); // 폭탄의 개수
		Bomb[] bombs = new Bomb[n]; // [i]: i번째 폭탄의 정보
		
		for (int idx = 0; idx < n; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int value = Integer.parseInt(tokenizer.nextToken());
			int time = Integer.parseInt(tokenizer.nextToken());
			
			bombs[idx] = new Bomb(value, time);
		}
		
		Arrays.sort(bombs);
		
		int time = 0;
		int idx = 0;
		int sum = 0;
		while (idx < n) {
            // 폭탄이 터지기 전인지 확인
			if (bombs[idx].time > time) {
				sum += bombs[idx].value;
				time++;
				idx++;
			}
            // 터진 폭탄들 스킵하기
			else {
				while (bombs[idx].time <= time) {
					idx++;
				}
			}
		}
		
		System.out.println(sum);
	}
}