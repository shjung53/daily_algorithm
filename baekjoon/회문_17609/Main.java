package 회문_17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int t, chance;

    static char[] charArr;
    static StringBuilder stb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().trim());
        stb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String str = br.readLine().trim();
            charArr = str.toCharArray();
            int right = charArr.length - 1;
            chance = 1;
            int type = 0;
            if (checkSide(0, right)) {
                if (chance < 1) type = 1;
            } else {
                type = 2;
            }
            stb.append(type).append('\n');
        }

        System.out.println(stb.toString());
    }

    private static boolean checkSide(int left, int right) {
        if(left >= right) return true;
        if (charArr[left] == charArr[right]) return checkSide(left + 1, right - 1);
        chance--;
        if (chance < 0) return false;
        return checkSide(left + 1, right) || checkSide(left, right - 1);
    }
}
