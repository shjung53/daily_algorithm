package z_1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, r, c, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken()) + 1;
        c = Integer.parseInt(st.nextToken()) + 1;

        answer = 0;

        int size = (int) Math.pow(2.0, n);

        find(r, c, size);

        System.out.println(answer);

    }

    private static void find(int y, int x, int size) {
        int nextSize = size / 2;
        int block = nextSize * nextSize;
        if (nextSize < 1) return;
        if (y <= nextSize && x <= nextSize) {
            find(y, x, nextSize);
        }
        if (y <= nextSize && x > nextSize) {
            answer += block;
            find(y, x - nextSize, nextSize);
        }
        if (y > nextSize && x <= nextSize) {
            answer += block * 2;
            find(y - nextSize, x, nextSize);
        }
        if (y > nextSize && x > nextSize) {
            answer += block * 3;
            find(y - nextSize, x - nextSize, nextSize);
        }
    }
}
