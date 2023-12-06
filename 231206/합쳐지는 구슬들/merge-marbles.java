import java.util.*;

class Ball {
    int number, row, col, dir, weight;
    public Ball(int number, int row, int col, int dir, int weight) {
        this.number = number;
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.weight = weight;
    }
}

public class Main {
    static int N, M, T;
    static ArrayList<Ball> balls;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        T = sc.nextInt();
        balls = new ArrayList<>();

        int[] mapping = new int[128];
        mapping['L'] = 3;
        mapping['R'] = 2;
        mapping['U'] = 1;
        mapping['D'] = 0;
        for (int i = 0; i < M; i++) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            int dir = mapping[sc.next().charAt(0)];
            int weight = sc.nextInt();
            balls.add(new Ball(i, row, col, dir, weight));
        }

        while (T-- > 0) {
            simulate();
            for (Ball ball : balls) {
                System.out.printf("#%d : [%d,%d] D%d W%d\n", ball.number, ball.row, ball.col, ball.dir, ball.weight);
            }
            System.out.println();
        }

        System.out.print(balls.size() + " ");
        System.out.print(maxWeight(balls));
    }

    public static void simulate() {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int idx = 0; idx < balls.size(); idx++) {
            Ball ball = balls.get(idx);
            int nr = ball.row + dr[ball.dir];
            int nc = ball.col + dc[ball.dir];
            if (outOfRange(nr, nc)) {
                if (ball.dir < 2) {
                    balls.set(idx, new Ball(ball.number, ball.row, ball.col, 2 - ball.dir, ball.weight));
                } else {
                    balls.set(idx, new Ball(ball.number, ball.row, ball.col, 5 - ball.dir, ball.weight));
                }
            } else {
                balls.set(idx, new Ball(ball.number, nr, nc, ball.dir, ball.weight));
            }
        }

        ArrayList<Ball> temp = new ArrayList<>();
        boolean[] check = new boolean[balls.size()];
        for (int currentIdx = 0; currentIdx < balls.size(); currentIdx++) {
            if (check[currentIdx]) continue;
            check[currentIdx] = true;

            Ball ball = balls.get(currentIdx);
            int number = ball.number;
            int row = ball.row;
            int col = ball.col;
            int dir = ball.dir;
            int weight = ball.weight;

            for (int checkIdx = currentIdx + 1; checkIdx < balls.size(); checkIdx++) {
                if (check[checkIdx]) continue;

                Ball checkBall = balls.get(checkIdx);
                int checkRow = checkBall.row;
                int checkCol = checkBall.col;

                if (row == checkRow && col == checkCol) {
                    check[checkIdx] = true;
                    weight += checkBall.weight;
                    if (checkBall.number > number) {
                        number = checkBall.number;
                        dir = checkBall.dir;
                    }
                }
            }
            temp.add(new Ball(number, row, col, dir, weight));
        }
        balls = temp;
    }

    public static boolean outOfRange(int r, int c) {
        return (r < 1 || r > N || c < 1 || c > N);
    }

    public static int maxWeight(ArrayList<Ball> balls) {
        int max = 0;
        for (Ball ball : balls) {
            int ballWeight = ball.weight;
            if (max < ballWeight) {
                max = ballWeight;
            }
        }

        return max;
    }
}