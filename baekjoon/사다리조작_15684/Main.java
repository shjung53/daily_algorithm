package 사다리조작_15684;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, h, answer;
    static int[][] lines;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        answer = 4;

        lines = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            lines[col][row] = 1;
            lines[col][row + 1] = 2;
        }

        for (int goal = 0; goal < 4; goal++) {
            selectLine(1, 0, goal);
            if (answer != 4) break;
        }

        if (answer == 4) answer = -1;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void selectLine(int y, int count, int goal) {
        if (count == goal) {
            boolean allPass = true;
            for (int i = 1; i <= n; i++) {
                if (!checkLadder(i)) allPass = false;
            }
            if (allPass) {
                answer = goal;
            }
            return;
        }

        for (int i = y; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (lines[i][j] == 0 && lines[i][j + 1] == 0) {
                    lines[i][j] = 1;
                    lines[i][j + 1] = 2;
                    selectLine(i, count + 1, goal);
                    lines[i][j] = 0;
                    lines[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean checkLadder(int number) {
        int now = number;
        for (int i = 1; i <= h; i++) {
            if (lines[i][now] == 1) {
                now++;
                continue;
            }
            if (lines[i][now] == 2) now--;
        }
        if (number == now) return true;
        return false;
    }
}
