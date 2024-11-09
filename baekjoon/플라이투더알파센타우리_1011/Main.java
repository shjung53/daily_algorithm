package 플라이투더알파센타우리_1011;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int t, x, y;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int count = 0;
            long maxN = getMaxN(y - x);
            long left = (y - x) - (maxN * maxN);

            count += maxN * 2 - 1;

            if (left > 0) {
                count += left / maxN;
                if (left % maxN > 0) count += 1;
            }

            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static long getMaxN(int gap) {
        for (long i = 0; i < Integer.MAX_VALUE - 1; i++) {
            if (i * i > gap) return i - 1;
        }
        return 0;
    }
}
