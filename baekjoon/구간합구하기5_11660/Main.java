package 구간합구하기5_11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder stb;
    static int n, m, startX, startY, endX, endY, sum;
    static int[][] map;
    static int[][] sumMap;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1]; // 1,1부터 n,n까지
        sumMap = new int[n + 1][n + 1];
        stb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            sumMap[i][n] = getSum(i, n);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            sum = 0;
            // 나의 편의를 위해 y와 x를 바꿈
            startY = Integer.parseInt(st.nextToken());
            startX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());

            for (int j = startY; j <= endY; j++) {
                sum += sumMap[j][endX] - sumMap[j][startX - 1]; // 행 기준으로 누적합
            }
            stb.append(sum).append('\n');
        }

        System.out.println(stb);
    }

    private static int getSum(int y, int x) {
        if (x == 1) return sumMap[y][x] = map[y][x];
        return sumMap[y][x] = getSum(y, x - 1) + map[y][x];
    }
}
