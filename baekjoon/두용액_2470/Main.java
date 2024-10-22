package 두용액_2470;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, min, answer1, answer2;
    static int[] liquid;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        liquid = new int[n];
        min = Integer.MAX_VALUE;
        answer1 = 0;
        answer2 = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        int index = 0;

        while (!pq.isEmpty()) {
            liquid[index] = pq.poll();
            index++;
        }

        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (liquid[left] >= 0) {
                right = left + 1;
            }

            if (liquid[right] < 0) {
                left = right - 1;
            }

            if (Math.abs(liquid[left] + liquid[right]) < min) {
                min = Math.abs(liquid[left] + liquid[right]);
                answer1 = liquid[left];
                answer2 = liquid[right];
            }

            if (liquid[left] + liquid[right] > 0) {
                right--;
                continue;
            }

            if (liquid[left] + liquid[right] < 0) {
                left++;
                continue;
            }

            if (liquid[left] + liquid[right] == 0) {
                break;
            }
        }

        bw.write(answer1 + " " + answer2);
        bw.flush();
        bw.close();
        br.close();

    }
}
