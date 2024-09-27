package 순서대로방문하기;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, answer;
    static int[][] map;
    static Position[] route;
    static boolean[][] visited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        route = new Position[m];
        visited = new boolean[n][n];
        answer = 0;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            route[i] = new Position(y, x);
        }

        visited[route[0].y][route[0].x] = true;

        dfs(route[0].y, route[0].x, 1);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int nextIndex) {
        if(y == route[m - 1].y && x == route[m - 1].x) {
            boolean success = true;
            for(Position pos: route) {
                if(!visited[pos.y][pos.x]) {success = false; break;}
            }
            if(success) {
                answer++;
            }
            return;
        }
        for(int d = 0; d<4; d++) {
            int newY = y + dy[d];
            int newX = x + dx[d];
            int newIndex = nextIndex;

            if(newY < 0 || newY >= n || newX < 0 || newX >= n) continue;
            if(visited[newY][newX]) continue;
            if(map[newY][newX] == 1) continue;
            if(newY == route[nextIndex].y && newX == route[nextIndex].x) {
                if(!checkRoute(newY, newX, nextIndex)) continue;
                newIndex++;
            }
            visited[newY][newX] = true;
            dfs(newY, newX, newIndex);
            visited[newY][newX] = false;
        }
    }

    private static boolean checkRoute(int y, int x, int nextIndex) {
        for(int i=0; i<nextIndex; i++) {
            Position trace = route[i];
            if(!visited[trace.y][trace.x]) return false;
        }
        return true;
    }
}

class Position{
    int y, x;
    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
