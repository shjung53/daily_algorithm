package 강의실배정_11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int n, max;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        max = 0;

        PriorityQueue<Lecture> pq = new PriorityQueue<>(new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Lecture> pq2 = new PriorityQueue<>(new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                return o1.end - o2.end;
            }
        });
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Lecture(start, end));
        }

        while(!pq.isEmpty()) {
            if(pq2.isEmpty()) {
                Lecture lec = pq.poll();
                pq2.offer(lec);
                continue;
            }

            while(!pq.isEmpty() && !pq2.isEmpty() && pq.peek().start < pq2.peek().end) {
                pq2.offer(pq.poll());
                if(pq2.size() > max) max = pq2.size();
            }

            while(!pq.isEmpty() && !pq2.isEmpty() && pq.peek().start >= pq2.peek().end) {
                pq2.poll();
            }
        }

        System.out.println(max);
    }
}

class Lecture {
    int start, end;

    public Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
