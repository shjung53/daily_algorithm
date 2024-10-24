package 숨바꼭질2_12851;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int[] visited;

    static int n, k, answer, answerSecond;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = 0;
        answerSecond = -1;

        visited = new int[100001];

        for (int i = 0; i < 100001; i++) {
            visited[i] = Integer.MAX_VALUE;
        }

        findRoute();

        bw.write(answerSecond + "\n" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findRoute() {
        Queue<Pos> queue = new ArrayDeque<>();
        visited[n] = 0;
        queue.offer(new Pos(n, 0));
        int second = 0;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                Pos pos = queue.poll();

                if (visited[pos.num] < pos.second) continue;

                if (pos.num == k) {
                    if (answerSecond < 0) answerSecond = second;
                    if (answerSecond >= 0) answer++;
                    continue;
                }

                int nextPos = pos.num - 1;

                if (nextPos >= 0 && nextPos <= 100000) {
                    if (visited[nextPos] >= second) {
                        if (visited[nextPos] > second) visited[nextPos] = second;
                        queue.offer(new Pos(nextPos, second));
                    }
                }

                if (pos.num <= k) {
                    nextPos = pos.num + 1;

                    if (nextPos >= 0 && nextPos <= 100000) {
                        if (visited[nextPos] >= second) {
                            if (visited[nextPos] > second) visited[nextPos] = second;
                            queue.offer(new Pos(nextPos, second));
                        }
                    }

                    nextPos = pos.num * 2;

                    if (nextPos >= 0 && nextPos <= 100000) {
                        if (visited[nextPos] >= second) {
                            if (visited[nextPos] > second) visited[nextPos] = second;
                            queue.offer(new Pos(nextPos, second));
                        }
                    }
                }
            }

            second++;
        }
    }
}

class Pos {
    int num, second;

    public Pos(int num, int second) {
        this.num = num;
        this.second = second;
    }
}
