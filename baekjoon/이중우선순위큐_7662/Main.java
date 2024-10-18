package 이중우선순위큐_7662;

import java.io.*;
import java.nio.Buffer;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int t, k;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= t; tc++) {
            k = Integer.parseInt(br.readLine().trim());
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine().trim());
                String cal = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if ("I".equals(cal)) {
                    treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
                } else {
                    if (n > 0) {
                        if (treeMap.isEmpty()) continue;
                        int last = treeMap.lastKey();
                        int count = treeMap.getOrDefault(last, 0) - 1;
                        if (count <= 0) {
                            treeMap.remove(last);
                        } else {
                            treeMap.put(last, count);
                        }
                    } else {
                        if (treeMap.isEmpty()) continue;
                        int first = treeMap.firstKey();
                        int count = treeMap.getOrDefault(first, 0) - 1;
                        if (count <= 0) {
                            treeMap.remove(first);
                        } else {
                            treeMap.put(first, count);
                        }
                    }
                }
            }


            if (treeMap.isEmpty()) {
                bw.write("EMPTY\n");
            } else {
                bw.write(treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
