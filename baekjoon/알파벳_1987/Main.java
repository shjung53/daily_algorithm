package 알파벳_1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static char[][] map;

    static boolean[] visited;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int r, c, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[26];
        answer = 0;

        for (int i = 0; i < r; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(answer);

    }

    static void dfs(int y, int x, int count) {
        if (count > answer) answer = count;

        for (int d = 0; d < 4; d++) {
            int newY = y + dy[d];
            int newX = x + dx[d];
            if (newY < 0 || newX < 0 || newY >= r || newX >= c) continue;
            char nextStepChar = map[newY][newX];
            if (visited[nextStepChar - 'A']) continue;
            visited[nextStepChar - 'A'] = true;
            dfs(newY, newX, count + 1);
            visited[nextStepChar - 'A'] = false;
        }
    }
}
