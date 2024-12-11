package 리모컨_1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, goalNumber, answer;
    static String goalChannel;

    static HashSet<Integer> brokenNumber;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        goalChannel = br.readLine().trim();
        goalNumber = Integer.parseInt(goalChannel);
        n = Integer.parseInt(goalChannel);
        m = Integer.parseInt(br.readLine().trim());
        brokenNumber = new HashSet<>();
        if (m > 0) st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < m; i++) {
            brokenNumber.add(Integer.parseInt(st.nextToken()));
        }
        answer = Integer.MAX_VALUE;

        int ceilingNumber = getCeilingNumber();
        int floorNumber = getFloorNumber();

        if(ceilingNumber >= 0) {
            answer = Math.min(Math.abs(100 - goalNumber), Math.abs(ceilingNumber - goalNumber) + goalChannel.length());
        }

        if(floorNumber >= 0) {
            answer = Math.min(Math.abs(100 - goalNumber), Math.abs(floorNumber - goalNumber) + goalChannel.length());
        }

        System.out.println(answer);
    }

    private static int getCeilingNumber() {
        int ceilingChannel = 0;
        boolean fit = true;

        for (int i = 0; i < goalChannel.length(); i++) {
            int number = Character.getNumericValue(goalChannel.charAt(i));

            if (fit) {
                if (!brokenNumber.contains(number)) {
                    ceilingChannel = ceilingChannel * 10 + number;
                    continue;
                }

                fit = false;
                while (brokenNumber.contains(number)) {
                    number++;
                    if (number > 9) return -1;
                }

                ceilingChannel = ceilingChannel * 10 + number;
                continue;
            }

            number = 0;

            while (brokenNumber.contains(number)) {
                number++;
                if (number > 9) return -1;
            }
            ceilingChannel = ceilingChannel * 10 + number;
        }
        return ceilingChannel;
    }

    private static int getFloorNumber() {
        int floorChannel = 0;
        boolean fit = true;

        for (int i = 0; i < goalChannel.length(); i++) {
            int number = Character.getNumericValue(goalChannel.charAt(i));

            if (fit) {
                if (!brokenNumber.contains(number)) {
                    floorChannel = floorChannel * 10 + number;
                    continue;
                }

                fit = false;
                while (brokenNumber.contains(number)) {
                    number--;
                    if (number < 0) return -1;
                }

                floorChannel = floorChannel * 10 + number;
                continue;
            }

            number = 9;

            while (brokenNumber.contains(number)) {
                number--;
                if (number < 0) return -1;
            }
            floorChannel = floorChannel * 10 + number;
        }
        return floorChannel;
    }
}
