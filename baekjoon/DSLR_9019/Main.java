package DSLR_9019;

import java.io.*;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int t, a, b;
    static long answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            answer = 0;

            findAnswer();

            StringBuilder stb = new StringBuilder();

            for (char c : Long.toString(answer).toCharArray()) {
                switch (c) {
                    case '1':
                        stb.append('D');
                        break;
                    case '2':
                        stb.append('S');
                        break;
                    case '3':
                        stb.append('L');
                        break;
                    case '4':
                        stb.append('R');
                        break;
                    default:
                }
            }
            bw.write(stb + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void findAnswer() {
        Queue<Cal> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[10000];

        queue.offer(new Cal(a, 0));
        visited[a] = true;

        while (!queue.isEmpty()) {
            Cal now = queue.poll();

            if (now.num == b) {
                answer = now.history;
                return;
            }

            int resultD = executeD(now.num);
            int resultS = executeS(now.num);
            int resultL = executeL(now.num);
            int resultR = executeR(now.num);

            if (!visited[resultD]) {
                visited[resultD] = true;
                queue.offer(new Cal(resultD, now.history * 10 + 1));
            }
            if (!visited[resultS]) {
                visited[resultS] = true;
                queue.offer(new Cal(resultS, now.history * 10 + 2));
            }
            if (!visited[resultL]) {
                visited[resultL] = true;
                queue.offer(new Cal(resultL, now.history * 10 + 3));
            }
            if (!visited[resultR]) {
                visited[resultR] = true;
                queue.offer(new Cal(resultR, now.history * 10 + 4));
            }
        }
    }

    private static int executeD(int num) {
        int result = num * 2;
        result %= 10000;
        return result;
    }

    private static int executeS(int num) {
        int result = num - 1;
        if (result < 0) result = 9999;
        return result;
    }

    private static int executeL(int num) {
        int highest = num / 1000;
        int result = num % 1000;
        result = result * 10 + highest;
        return result;
    }

    private static int executeR(int num) {
        int lowest = num % 10;
        int result = num / 10;
        result += lowest * 1000;
        return result;
    }
}

class Cal {
    //    1은 D, 2는 S, 3은 L, 4는 R
    int num;
    long history;

    public Cal(int num, long history) {
        this.num = num;
        this.history = history;
    }
}
