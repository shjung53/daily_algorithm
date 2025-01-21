package gpt식숫자비교;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return compareString(o1, o2);
            }
        });

        for (int i = 0; i < n; i++) {
            pq.offer(br.readLine().trim());
        }

        while(!pq.isEmpty()) {
            bw.write(pq.poll());
            bw.write('\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int compareString(String o1, String o2) {
        int o1Dot = o1.indexOf('.');
        int o2Dot = o2.indexOf('.');
        int o1Int = -1;
        int o2Int = -1;
        if (o1Dot >= 0) o1Int = Integer.parseInt(o1.substring(0, o1Dot));
        if (o2Dot >= 0) o2Int = Integer.parseInt(o2.substring(0, o2Dot));

        if(o1Int == -1) o1Int = Integer.parseInt(o1);
        if(o2Int == -1) o2Int = Integer.parseInt(o2);

        if (o1Int > o2Int) return 1;
        if (o1Int < o2Int) return -1;

        int o1Real = -1;
        int o2Real = -1;
        if (o1Dot >= 0) o1Real = Integer.parseInt(o1.substring(o1Dot + 1));
        if (o2Dot >= 0) o2Real = Integer.parseInt(o2.substring(o2Dot + 1));

        if (o1Real > o2Real) return 1;
        return -1;
    }
}
