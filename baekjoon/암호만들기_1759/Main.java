package 암호만들기_1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int l, c;
    static BufferedReader br;
    static StringTokenizer st;
    static char[] secretChars;
    static StringBuilder stb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        stb = new StringBuilder();
        secretChars = new char[c];

        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < c; i++) {
            secretChars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(secretChars);
        combination(0, "");
        System.out.println(stb);
    }

    private static void combination(int index, String secret) {
        if (secret.length() >= l) {
            // 암호 완성 확인
            if(checkCondition(secret)) stb.append(secret).append('\n');
            return;
        }
        if(index >= c) return; // 알파벳 인덱스 넘어가면 끝
        combination(index + 1, secret + secretChars[index]);
        combination(index + 1, secret);
    }

    private static boolean checkCondition(String secret) {
        int consonant = 0;
        int vowel = 0;

        for (char c : secret.toCharArray()) {
            if (c == 'i' || c == 'e' || c == 'o' || c == 'u' || c == 'a') {
                consonant++;
            } else {
                vowel++;
            }
        }

        if(consonant < 1) return false;
        if(vowel < 2) return false;
        return true;
    }
}
