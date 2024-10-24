package 슈퍼컴퓨터클러스터;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static long b;
    static int[] cpu;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        cpu = new int[n];

        for (int i = 0; i < n; i++) {
            cpu[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = Integer.MAX_VALUE - 1;
        long answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (!costPossible(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        answer = right;

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean costPossible(int min) {
        long sum = 0;
        for (int power : cpu) {
            if (power < min) {
                int upgrade = min - power;
                sum += (long) upgrade * upgrade;
            }
            if (sum > b) return false;
        }

        return true;
    }
}
