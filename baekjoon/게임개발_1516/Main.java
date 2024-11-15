package 게임개발_1516;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static Building[] buildings;
    static int[] times;
    static int[] totalTimes;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        buildings = new Building[n + 1];
        times = new int[n + 1];
        totalTimes = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            buildings[i] = new Building();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;
            int preceding = Integer.parseInt(st.nextToken());
            while (preceding > 0) {
                buildings[i].precedings.add(preceding);
                preceding = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            bw.write(getTime(i) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getTime(int num) {
        if (totalTimes[num] > 0) return totalTimes[num];
        if (buildings[num].precedings.size() == 0) return totalTimes[num] = times[num];
        int precedingTime = 0;
        for (int preceding : buildings[num].precedings) {
            precedingTime = Math.max(precedingTime, getTime(preceding));
        }
        return totalTimes[num] = precedingTime + times[num];
    }
}

class Building {
    HashSet<Integer> precedings;

    public Building() {
        this.precedings = new HashSet<>();
    }
}
