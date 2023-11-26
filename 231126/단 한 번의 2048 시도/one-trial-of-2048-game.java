import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 4;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        char dir = sc.next().charAt(0);
        if (dir == 'R') {
            for (int line = 0; line < n; line++) {
                int curr = n - 1;
                for (int next = curr - 1; next >= 0; next--) {
                    if (grid[line][next] == 0) continue;
                    if (grid[line][curr] == 0) {
                        grid[line][curr] = grid[line][next];
                        grid[line][next] = 0;
                    } else if (grid[line][curr] == grid[line][next]) {
                        grid[line][curr] *= 2;
                        grid[line][next] = 0;
                        curr--;
                    } else {
                        if (Math.abs(curr - next) > 1) {
                            next++;
                        }
                        curr--;
                    }
                }
            }
        } else if (dir == 'L') {
            for (int line = 0; line < n; line++) {
                int curr = 0;
                for (int next = curr + 1; next < n; next++) {
                    if (grid[line][next] == 0) continue;
                    if (grid[line][curr] == 0) {
                        grid[line][curr] = grid[line][next];
                        grid[line][next] = 0;
                    } else if (grid[line][curr] == grid[line][next]) {
                        grid[line][curr] *= 2;
                        grid[line][next] = 0;
                        curr++;
                    } else {
                        if (Math.abs(curr - next) > 1) {
                            next--;
                        }
                        curr++;
                    }
                }
            }
        } else if (dir == 'U') {
            for (int line = 0; line < n; line++) {
                int curr = 0;
                for (int next = curr + 1; next < n; next++) {
                    if (grid[next][line] == 0) continue;
                    if (grid[curr][line] == 0) {
                        grid[curr][line] = grid[next][line];
                        grid[next][line] = 0;
                    } else if (grid[curr][line] == grid[next][line]) {
                        grid[curr][line] *= 2;
                        grid[next][line] = 0;
                        curr++;
                    } else {
                        if (Math.abs(curr - next) > 1) {
                            next--;
                        }
                        curr++;
                    }
                }
            }
        } else if (dir == 'D') {
            for (int line = 0; line < n; line++) {
                int curr = n - 1;
                for (int next = curr - 1; next >= 0; next--) {
                    if (grid[next][line] == 0) continue;
                    if (grid[curr][line] == 0) {
                        grid[curr][line] = grid[next][line];
                        grid[next][line] = 0;
                    } else if (grid[curr][line] == grid[next][line]) {
                        grid[curr][line] *= 2;
                        grid[next][line] = 0;
                        curr--;
                    } else {
                        if (Math.abs(curr - next) > 1) {
                            next++;
                        }
                        curr--;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}