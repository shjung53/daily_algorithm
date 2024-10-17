package 오큰수_17298;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] arr;
    static int[] answer;
    static int n, index;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];
        answer = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        index = n - 1;

        while (index >= 0) {
            if (deque.isEmpty()) {
                answer[index] = -1;
                deque.push(arr[index]);
                index--;
                continue;
            }

            if(deque.peekFirst() <= arr[index]) {
                deque.pop();
                continue;
            }

            answer[index] = deque.peekFirst();
            deque.push(arr[index]);
            index--;
        }

        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            stb.append(answer[i]).append(' ');
        }

        br.close();
        System.out.println(stb.toString().trim());
    }
}
