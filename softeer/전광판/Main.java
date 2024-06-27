package 전광판;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder stb;
    static int t, answer;
    static String a, b;
    static boolean[][] light;

    static boolean[] none = {false, false, false, false, false, false, false};
    static boolean[] zero = {true, true, true, false, true, true, true};
    static boolean[] one = {false, false, true, false, false, true, false};
    static boolean[] two = {true, false, true, true, true, false, true};
    static boolean[] three = {true, false, true, true, false, true, true};
    static boolean[] four = {false, true, true, true, false, true, false};
    static boolean[] five = {true, true, false, true, false, true, true};
    static boolean[] six = {true, true, false, true, true, true, true};
    static boolean[] seven = {true, true, true, false, false, true, false};
    static boolean[] eight = {true, true, true, true, true, true, true};
    static boolean[] nine = {true, true, true, true, false, true, true};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().trim());
        stb = new StringBuilder();

        for (int test = 1; test <= t; test++) {
            answer = 0;
            st = new StringTokenizer(br.readLine().trim());
            a = st.nextToken();
            b = st.nextToken();
            answer = countSwitchClick(numberToLightGroup(a), numberToLightGroup(b));
            stb.append(answer).append('\n');
        }

        System.out.println(stb);
    }

    static int countSwitchClick(boolean[][] lightA, boolean[][] lightB) {
        int count = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                if (lightA[i][j] != lightB[i][j]) count++;
            }
        }
        return count;
    }

    // 전광판 그룹
    static boolean[][] numberToLightGroup(String number) {
        boolean[][] lightGroup = new boolean[5][7];

        // 5자리로 만들기, n은 none으로 치환
        while (number.length() < 5) {
            number = "n" + number;
        }

        // 한 자리씩 전광판 arr로 만들기
        for (int i = 0; i < 5; i++) {
            if (Character.isDigit(number.charAt(i))) {
                lightGroup[i] = numberToLight(Character.getNumericValue(number.charAt(i)));
            } else {
                lightGroup[i] = none;
            }
        }

        return lightGroup;
    }

    static boolean[] numberToLight(int number) {
        switch (number) {
            case 0:
                return zero;
            case 1:
                return one;
            case 2:
                return two;
            case 3:
                return three;
            case 4:
                return four;
            case 5:
                return five;
            case 6:
                return six;
            case 7:
                return seven;
            case 8:
                return eight;
            case 9:
                return nine;
        }
        return zero;
    }
}
