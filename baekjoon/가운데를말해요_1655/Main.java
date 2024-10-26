package 가운데를말해요_1655;

import java.io.*;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());

        PriorityQueue<Integer> leftHalf = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> rightHalf = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine().trim());
            if (leftHalf.size() <= rightHalf.size()) {
                if (!rightHalf.isEmpty() && number > rightHalf.peek()) {
                    leftHalf.add(rightHalf.poll());
                    rightHalf.add(number);
                } else {
                    leftHalf.add(number);
                }
            } else {
                if (!leftHalf.isEmpty() && number < leftHalf.peek()) {
                    rightHalf.add(leftHalf.poll());
                    leftHalf.add(number);
                } else {
                    rightHalf.add(number);
                }
            }
            bw.write(leftHalf.peek() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
