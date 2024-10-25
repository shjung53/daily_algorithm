package 입국심사_3079;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, max;
    static int[] audit;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        audit = new int[n];
        max = 0;

        for (int i = 0; i < n; i++) {
            audit[i] = Integer.parseInt(br.readLine().trim());
            if (audit[i] > max) max = audit[i];
        }

        long left = 1;
        long right = (long) max * ((m / n) + 1);

        while (left <= right) {
            long mid = (left + right) / 2;
            if (checkTime(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(left+ "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkTime(long maxTime) {
        long humanCount = 0;
        for (int auditTime : audit) {
            long count = maxTime / auditTime;
            humanCount += count;
        }
        return humanCount >= m;
    }
}
