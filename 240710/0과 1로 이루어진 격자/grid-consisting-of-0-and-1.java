import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] grid;
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(reader.readLine()); // 격자의 크기
		grid = new int[n][n]; // [i][j]: i행 j열의 숫자
		int count = 0; // 반전 횟수
		
		for (int row = 0; row < n; row++) {
			char[] line = reader.readLine().toCharArray();
			for (int col = 0; col < n; col++) {
				grid[row][col] = line[col] - '0';  
			}
		}
		
		// 격자의 오른쪽 아래부터 왼쪽 위 순서로 확인
		// 1일 경우에만 반전
		for (int row = n - 1; row >=0; row--) {
			for (int col = n - 1; col >= 0; col--) {
				if (grid[row][col] == 0) continue;
				change(row, col);
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	public static void change(int startRow, int startCol) {
		for (int row = startRow; row >= 0; row--) {
			for (int col = startCol; col >= 0; col--) {
				grid[row][col] = 1 - grid[row][col];
			}
		}
	}
}