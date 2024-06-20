package NQueen_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static int n, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        map = new int[n][n];
        answer = 0;
        dfs(0);
        System.out.println(answer);
    }

    /* 그냥 0으로 지워 버리면 이전에 두었던 퀸의 공격 범위도 지우게 된다. 겹치는 부분을 알 수 있도록 더하기 연산으로 공격 길을 표시하고
    마이너스 연산으로 공격 길을 지워준다.
    * */
    private static void dfs(int row) {
        if (row == n) {
            answer++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (map[i][row] == 0) {
                checkMap(i, row, 0);
                checkMap(i, row, 1);
                checkMap(i, row, -1);
                dfs(row + 1);
                eraseCheck(i, row, 0);
                eraseCheck(i, row, 1);
                eraseCheck(i, row, -1);
            }
        }
    }

    private static void checkMap(int col, int row, int inc) {
        int y = col;
        int x = row;
        while (y >= 0 && y < n && x < n) {
            map[y][x]++;
            y += inc;
            x++;
        }
    }

    private static void eraseCheck(int col, int row, int inc) {
        int y = col;
        int x = row;
        while (y >= 0 && y < n && x < n) {
            map[y][x]--;
            y += inc;
            x++;
        }
    }
}
