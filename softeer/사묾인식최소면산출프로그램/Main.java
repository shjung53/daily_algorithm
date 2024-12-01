package 사묾인식최소면산출프로그램;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, k, answer;
    static ArrayList<Point>[] points;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        points = new ArrayList[k + 1];
        answer = Integer.MAX_VALUE;

        for (int i = 1; i <= k; i++) {
            points[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            points[color].add(new Point(x, y));
        }

        dfs(1, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int colorIndex, int maxX, int maxY, int minX, int minY) {
        if (colorIndex > k) {
            int size = Math.abs((maxX - minX) * (maxY - minY));
            if (size < answer) answer = size;
            return;
        }
        for (Point point : points[colorIndex]) {
            int newMaxX = maxX;
            int newMaxY = maxY;
            int newMinX = minX;
            int newMinY = minY;

            if (point.x > newMaxX) newMaxX = point.x;
            if (point.y > newMaxY) newMaxY = point.y;
            if (point.x < newMinX) newMinX = point.x;
            if (point.y < newMinY) newMinY = point.y;

            int size = Math.abs((newMaxX - newMinX) * (newMaxY - newMinY));
            if (size < answer) dfs(colorIndex + 1, newMaxX, newMaxY, newMinX, newMinY);
        }
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
