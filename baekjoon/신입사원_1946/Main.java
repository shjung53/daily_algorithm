package 신입사원_1946;

import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int t, n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            PriorityQueue<Student> pq = new PriorityQueue<>(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.aRank - o2.aRank;
                }
            });

            TreeSet<Integer> treeSet = new TreeSet<>();

            int count = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int aRank = Integer.parseInt(st.nextToken());
                int bRank = Integer.parseInt(st.nextToken());
                pq.offer(new Student(aRank, bRank));
            }

            while (!pq.isEmpty()) {
                Student student = pq.poll();
                if (treeSet.isEmpty()) {
                    treeSet.add(student.bRank);
                    count++;
                    continue;
                }

                if (treeSet.lower(student.bRank) == null) {
                    treeSet.add(student.bRank);
                    count++;
                }
            }

            bw.write(String.valueOf(count));
            if (tc < t) bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class Student {
    int aRank, bRank;

    public Student(int aRank, int bScore) {
        this.aRank = aRank;
        this.bRank = bScore;
    }
}
