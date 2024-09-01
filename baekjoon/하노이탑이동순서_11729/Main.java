package 하노이탑이동순서_11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder stb;

    static int n;
    static int answerCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        stb = new StringBuilder();
        answerCount = 0;
        n = Integer.parseInt(br.readLine().trim());
        movePlate(1, 3, 2, n);
        System.out.println(answerCount);
        System.out.println(stb);
    }

    private static void movePlate(int start, int goal, int temp, int count) {
        if(count == 1) {
            stb.append(start).append(' ').append(goal).append('\n');
            answerCount++;
            return;
        };

        movePlate(start, temp, goal, count - 1);

        stb.append(start).append(' ').append(goal).append('\n');
        answerCount++;

        movePlate(temp, goal, start, count - 1);
    }
}
