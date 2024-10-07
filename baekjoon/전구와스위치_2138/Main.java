package 전구와스위치_2138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static int n, answer, index, count;
    static boolean[] lights, lights1;
    static boolean[] goal;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        answer = Integer.MAX_VALUE;
        lights = new boolean[n];
        goal = new boolean[n];
        index = 1;
        count = 0;
        String line = br.readLine().trim();
        for (int i = 0; i < n; i++) {
            int onOff = Character.getNumericValue(line.charAt(i));
            if (onOff == 1) lights[i] = true;
        }
        line = br.readLine().trim();
        for (int i = 0; i < n; i++) {
            int onOff = Character.getNumericValue(line.charAt(i));
            if (onOff == 1) goal[i] = true;
        }
        lights1 = lights.clone();

        while (index < n) {
            if (index == n - 1) {
                if (lights[index - 1] != goal[index - 1]) {
                    lights[index - 1] = !lights[index - 1];
                    lights[index] = !lights[index];
                    count++;
                }
                index++;
                continue;
            }

            if (lights[index - 1] != goal[index - 1]) {
                lights[index] = !lights[index];
                lights[index - 1] = !lights[index - 1];
                lights[index + 1] = !lights[index + 1];
                count++;
            }
            index++;
        }
        if (checkGoal(lights)) {
            if (answer > count) answer = count;
        }

        lights1[0] = !lights1[0];
        lights1[1] = !lights1[1];
        count = 1;
        index = 1;

        while (index < n) {
            if (index == n - 1) {
                if (lights1[index - 1] != goal[index - 1]) {
                    lights1[index - 1] = !lights1[index - 1];
                    lights1[index] = !lights1[index];
                    count++;
                }
                index++;
                continue;
            }

            if (lights1[index - 1] != goal[index - 1]) {
                lights1[index] = !lights1[index];
                lights1[index - 1] = !lights1[index - 1];
                lights1[index + 1] = !lights1[index + 1];
                count++;
            }
            index++;
        }

        if (checkGoal(lights1)) {
            if (answer > count) answer = count;
        }

        if (answer == Integer.MAX_VALUE) answer = -1;

        System.out.println(answer);
        br.close();
    }

    private static boolean checkGoal(boolean[] light) {
        for (int i = 0; i < n; i++) {
            if (goal[i] != light[i]) return false;
        }
        return true;
    }
}
