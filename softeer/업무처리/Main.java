package 업무처리;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int h, k, r, workerCount;
    static int[][] works;
    static int[][] nextWorks;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        workerCount = (int) Math.pow(2.0, h);
        works = new int[workerCount][k];

        for (int i = 0; i < workerCount; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < k; j++) {
                works[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < h; i++) {
            int length = works.length / 2;
            int size = works[0].length * 2;
            nextWorks = new int[length][size];
            for (int j = 0; j < length; j++) {
                nextWorks[j] = mergeWork(j, i, size);
            }
            works = nextWorks;
        }

        int possibleDay = Math.min((r - h), works[0].length);

        int sum = 0;
        for (int i = 0; i < possibleDay; i++) {
            sum += works[0][i];
        }

        System.out.println(sum);
    }

    private static int[] mergeWork(int startIndex, int level, int size) {
        int[] totalWork = new int[size];

        int[] leftJob = works[startIndex];
        int[] rightJob = works[startIndex + 1];

        for (int i = 0; i < size; i++) {
            if (level % 2 == 0) {
                if (i % 2 == 0) {
                    totalWork[i] = rightJob[i / 2];
                } else {
                    totalWork[i] = leftJob[i / 2];
                }
            } else {
                if (i % 2 == 0) {
                    totalWork[i] = leftJob[i / 2];
                } else {
                    totalWork[i] = rightJob[i / 2];
                }
            }
        }

        return totalWork;
    }
}
