package 옥상정원꾸미기_6198;

import java.io.*;
import java.util.ArrayDeque;
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
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        long answer = 0;
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine().trim());
            if (stack.isEmpty()) {
                stack.push(height);
                continue;
            }

            while (!stack.isEmpty() && stack.peekFirst() <= height) {
                stack.pop();
            }

            answer += stack.size();
            stack.push(height);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
