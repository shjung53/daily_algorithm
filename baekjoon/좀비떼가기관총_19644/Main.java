package 좀비떼가기관총_19644;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int l, range, m, c;
    static long[] map;
    static long[] damage;
    static int[] recover;
    static String answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        l = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        range = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(br.readLine().trim());
        answer = "YES";

        map = new long[l + 1];
        damage = new long[l + 1];
        recover = new int[l + 1];

        for (int i = 1; i <= l; i++) {
            int zombie = Integer.parseInt(br.readLine());
            map[i] = zombie;
        }

        long accumulate = m;
        for (int i = 1; i <= l; i++) {
            damage[i] = accumulate;
            if (accumulate < (long) m * range) accumulate += m;
        }

        int recoverCount = 0;
        int totalCount = 0;

        for (int i = 1; i <= l; i++) {
            if(recover[i] < 0) recoverCount--;
            long recovery = (long) recoverCount * m;
            if(map[i] > damage[i] - recovery) {
                recover[i] = 1;
                recoverCount++;
                totalCount++;
                int end = i + range;
                if (end > l) continue;
                recover[end] = -1;
            }
        }

        if(totalCount > c) answer = "NO";

        bw.write(answer);
        bw.flush();
        bw.close();
        br.close();
    }
}
