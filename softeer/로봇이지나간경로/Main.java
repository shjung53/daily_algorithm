package 로봇이지나간경로;

import java.io.*;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int h, w, startD, answerD, answerLength;
    static boolean[][][] visited;
    static boolean[][] example;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static ArrayDeque<Character> command;
    static ArrayList<Character> answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        example = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < w; j++) {
                if (line.charAt(j) == '#') example[i][j] = true;
            }
        }

        command = new ArrayDeque<>();
        visited = new boolean[h][w][4];
        answerLength = Integer.MAX_VALUE;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!example[i][j]) continue;
                for (int d = 0; d < 4; d++) {
                    visited[i][j][d] = true;
                    startD = d;
                    dfs(i, j, d);
                    visited[i][j][d] = false;
                }
            }
        }

        bw.write(answerD);
    }

    private static void dfs(int y, int x, int direction) {
        int[] aResult = commandA(y, x, direction);
        if (aResult != null) {
            command.offer('A');
            dfs(aResult[0], aResult[1], aResult[2]);
            undoA(aResult[0], aResult[1], aResult[2]);
            command.pop();
        } else {
            if (checkMap()) {
                if (command.size() < answerLength) {
                    answerLength = command.size();
                    answer = new ArrayList<>(command);
                    answerD = startD;
                }
            }
        }

        int lResult = commandL(y, x, direction);
        if (lResult > 0) {
            command.offer('L');
            dfs(y, x, lResult);
            undoL(y, x, lResult);
            command.pop();
        }

        int rResult = commandR(y, x, direction);
        if (rResult > 0) {
            command.offer('R');
            dfs(y, x, rResult);
            undoR(y, x, rResult);
            command.pop();
        }
    }

    private static boolean checkMap() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (example[i][j]) {
                    boolean visit = false;
                    for (int d = 0; d < 4; d++) {
                        if (visited[i][j][d]) visit = true;
                    }
                    if (!visit) return false;
                }
            }
        }
        return true;
    }

    private static int[] commandA(int y, int x, int direction) {
        int oneStepY = y + dy[direction];
        int oneStepX = x + dx[direction];
        int twoStepY = oneStepY + dy[direction];
        int twoStepX = oneStepX + dx[direction];

        if (oneStepY < 0 || oneStepX < 0 || oneStepY >= h || oneStepX >= w) return null;
        if (!example[oneStepY][oneStepX]) return null;
        for (int d = 0; d < 4; d++) {
            if (visited[oneStepY][oneStepX][d]) return null;
        }

        if (twoStepY < 0 || twoStepX < 0 || twoStepY >= h || twoStepX >= w) return null;
        if (!example[twoStepY][twoStepX]) return null;
        for (int d = 0; d < 4; d++) {
            if (visited[twoStepY][twoStepX][d]) return null;
        }

        visited[oneStepY][oneStepX][direction] = true;
        visited[twoStepY][twoStepX][direction] = true;
        int[] next = new int[3];
        next[0] = twoStepY;
        next[1] = twoStepX;
        next[2] = direction;
        return next;
    }

    private static int[] undoA(int y, int x, int direction) {
        int backDirection = (direction + 2) % 4;
        int oneStepY = y + dy[backDirection];
        int oneStepX = x + dx[backDirection];
        int twoStepY = oneStepY + dy[backDirection];
        int twoStepX = oneStepX + dx[backDirection];

        if (oneStepY < 0 || oneStepX < 0 || oneStepY >= h || oneStepX >= w) return null;
        if (twoStepY < 0 || twoStepX < 0 || twoStepY >= h || twoStepX >= w) return null;
        visited[y][x][direction] = false;
        visited[oneStepY][oneStepX][direction] = false;

        int[] original = new int[3];
        original[0] = twoStepY;
        original[1] = twoStepX;
        original[2] = direction;
        return original;
    }

    private static int commandL(int y, int x, int direction) {
        int leftDirection = (direction + 3) % 4;
        if (visited[y][x][leftDirection]) return -1;
        visited[y][x][leftDirection] = true;
        return leftDirection;
    }

    private static int undoL(int y, int x, int direction) {
        int originalDirection = (direction + 1) % 4;
        visited[y][x][direction] = false;
        return originalDirection;
    }

    private static int commandR(int y, int x, int direction) {
        int rightDirection = (direction + 1) % 4;
        if (visited[y][x][rightDirection]) return -1;
        visited[y][x][rightDirection] = true;
        return rightDirection;
    }

    private static int undoR(int y, int x, int direction) {
        int originalDirection = (direction + 3) % 4;
        visited[y][x][direction] = false;
        return originalDirection;
    }
}
