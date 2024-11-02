package 숨바꼭질4_13913;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static boolean[] visited;
    static int n, k, answer;
    static int[] before;
    static int[] answerArr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = 0;

        visited = new boolean[100001];
        before = new int[100001];

        Queue<Integer> queue = new ArrayDeque<>();
        visited[n] = true;
        queue.offer(n);
        int time = 0;

        OUT:
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int pos = queue.poll();

                if (pos == k) {
                    answer = time;
                    ArrayDeque<Integer> stack = new ArrayDeque<>();
                    int nowPos = pos;
                    while (nowPos != n) {
                        stack.push(nowPos);
                        nowPos = before[nowPos];
                    }
                    stack.push(nowPos);
                    answerArr = new int[stack.size()];
                    int index = 0;
                    while (!stack.isEmpty()) {
                        answerArr[index] = stack.pop();
                        index++;
                    }
                    break OUT;
                }

                if (pos < k) {
                    if (!visited[pos + 1]) {
                        visited[pos + 1] = true;
                        before[pos + 1] = pos;
                        queue.offer(pos + 1);
                    }

                    if (pos * 2 <= 100000 && !visited[pos * 2]) {
                        visited[pos * 2] = true;
                        before[pos * 2] = pos;
                        queue.offer(pos * 2);
                    }
                }

                if (pos > 0 && !visited[pos - 1]) {
                    visited[pos - 1] = true;
                    before[pos - 1] = pos;
                    queue.offer(pos - 1);
                }
            }
            time++;
        }

        bw.write(answer + "\n");
        for (int i = 0; i < answerArr.length; i++) {
            bw.write(String.valueOf(answerArr[i]));
            if (i != answerArr.length - 1) bw.write(" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}


