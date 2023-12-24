import java.util.*;
import java.io.*;

class Value {
    int min, max, sub;

    public Value(int min, int max) {
        this.min = min;
        this.max = max;
        this.sub = max - min;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] grid;
    static Value[][] values;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        values = new Value[n][n];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        values[0][0] = new Value(grid[0][0], grid[0][0]);
        for (int idx = 1; idx < n; idx++) {
            Value exVal = values[0][idx - 1];
            int current = grid[0][idx];
            if (current > exVal.max) {
                values[0][idx] = new Value(exVal.min, current);
            }
            else if (current < exVal.min) {
                values[0][idx] = new Value(current, exVal.max);
            }
            else {
                values[0][idx] = new Value(exVal.min, exVal.max);
            }
        }
        for (int idx = 1; idx < n; idx++) {
            Value exVal = values[idx - 1][0];
            int current = grid[idx][0];
            if (current > exVal.max) {
                values[idx][0] = new Value(exVal.min, current);
            }
            else if (current < exVal.min) {
                values[idx][0] = new Value(current, exVal.max);
            }
            else {
                values[idx][0] = new Value(exVal.min, exVal.max);
            }
        }

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < n; col++) {
                int current = grid[row][col];

                Value exValUp = values[row - 1][col];
                Value newValUp;
                if (current > exValUp.max) {
                    newValUp = new Value(exValUp.min, current);
                }
                else if (current < exValUp.min) {
                    newValUp = new Value(current, exValUp.max);
                }
                else {
                    newValUp = new Value(exValUp.min, exValUp.max);
                }

                Value exValLeft = values[row][col - 1];
                Value newValLeft;
                if (current > exValLeft.max) {
                    newValLeft = new Value(exValLeft.min, current);
                }
                else if (current < exValLeft.min) {
                    newValLeft = new Value(current, exValLeft.max);
                }
                else {
                    newValLeft = new Value(exValLeft.min, exValLeft.max);
                }

                if (newValLeft.sub < newValUp.sub) {
                    values[row][col] = newValLeft;
                }
                else {
                    values[row][col] = newValUp;
                }
            }
        }

        System.out.println(values[n - 1][n - 1].sub);
    }
}