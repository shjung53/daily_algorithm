package 단어맞추기_9081;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int t;
    static String noun;
    static ArrayList<Integer> ascii;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().trim());
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            noun = br.readLine();
            ascii = new ArrayList<>();

            for (int j = 0; j < noun.length(); j++) {
                ascii.add(Character.codePointAt(noun, j));
            }

            stb.append(getNextString()).append('\n');

        }
        System.out.println(stb);
    }

    static String getNextString() {
        int lastIndex = noun.length() - 1;
        int latter = -1;
        int minIndex = -1;
        int breakPoint = -1;
        int target = -1;

        for (int i = lastIndex; i >= 0; i--) {
            if (ascii.get(i) < latter) {
                breakPoint = i;
                target = ascii.get(i);
                break;
            }
            latter = ascii.get(i);
        }

        if (breakPoint >= 0) {
            for (int i = breakPoint; i <= lastIndex; i++) {
                if (ascii.get(i) <= target) continue;
                if (minIndex < 0) {
                    minIndex = i;
                    continue;
                }
                if (ascii.get(i) < ascii.get(minIndex)) minIndex = i;
            }
            ascii.set(breakPoint, ascii.get(minIndex));
            ascii.set(minIndex, target);
            Collections.sort(ascii.subList(breakPoint + 1, ascii.size()));
        }

        StringBuilder str = new StringBuilder();

        for (int c : ascii) {
            str.append(Character.toChars(c));
        }

        return str.toString();
    }
}
