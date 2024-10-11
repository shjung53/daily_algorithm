package 맥주마시며걸어가기_9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int t, n, startY, startX, endY, endX;
    static String answer;
    static Pos[] conveniences;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().trim());
        StringBuilder stb = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());
            startY = Integer.parseInt(st.nextToken());
            startX = Integer.parseInt(st.nextToken());
            answer = "";
            conveniences = new Pos[n];
            visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                conveniences[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine().trim());
            endY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());

            findRoute(startY, startX, 1000);

            if (answer.isEmpty()) answer = "sad";
            stb.append(answer).append('\n');
        }

        System.out.println(stb);
    }

    private static void findRoute(int y, int x, int beer) {
        if (!answer.isEmpty()) return;

        if (Math.abs(y - endY) + Math.abs(x - endX) <= beer) {
            answer = "happy";
            return;
        }

        for (int i = 0; i < n; i++) {
            Pos convenience = conveniences[i];
            if (visited[i]) continue;
            if (Math.abs(y - convenience.y) + Math.abs(x - convenience.x) > beer) continue;
            visited[i] = true;
            findRoute(convenience.y, convenience.x, 1000);
        }
    }
}

class Pos {
    int y, x;

    public Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
