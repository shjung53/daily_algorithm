package 흙길보수하기_1911;

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, l, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        answer = 0;

        PriorityQueue<Pool> pq = new PriorityQueue<>(new Comparator<Pool>() {
            @Override
            public int compare(Pool o1, Pool o2) {
                return o1.start - o2.start;
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) - 1;
            pq.offer(new Pool(start, end));
        }

        while (!pq.isEmpty()) {
            Pool now = pq.poll();
            int nowS = now.start;
            int nowE = now.end;
            int width = nowE - nowS + 1;
            answer += width / l;

            int cover = nowE;

            if (width % l > 0) {
                answer++;
                cover += l - (width % l);
            }

            while (!pq.isEmpty() && pq.peek().start <= cover) {
                Pool next = pq.poll();
                int nextS = next.start;
                int nextE = next.end;
                if (nextE <= cover) continue;
                if (nextS > cover) {
                    pq.offer(next);
                    continue;
                }
                nextS = cover + 1;
                pq.offer(new Pool(nextS, nextE));
            }
        }

        System.out.println(answer);
    }
}

class Pool {
    int start, end;

    public Pool(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
