package 비슷한단어_2179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static int n, max;
    static String s, t;

    static HashSet<String> set;
    static ArrayList<String> words;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        set = new HashSet<>();
        words = new ArrayList<>();
        s = "";
        t = "";
        max = -1;

        for (int i = 0; i < n; i++) {
            String word = br.readLine().trim();
            if (!set.contains(word)) words.add(word);
        }

        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String word1 = words.get(i);
                String word2 = words.get(j);
                int length = Math.min(word1.length(), word2.length());
                int prefixCount = 0;
                for (int l = 0; l < length; l++) {
                    if (word1.charAt(l) == word2.charAt(l)) {
                        prefixCount++;
                    } else {
                        break;
                    }
                }
                if (prefixCount > max) {
                    max = prefixCount;
                    s = word1;
                    t = word2;
                }
            }
        }

        System.out.println(s);
        System.out.println(t);
    }

    private static int countPrefix(String w1, String w2) {
        int index = 0;
        int count = 0;

        while (index < w1.length() && index < w2.length()) {
            char c1 = w1.charAt(index);
            char c2 = w2.charAt(index);

            if (c1 == c2) count++;
            index++;
        }

        return count;
    }
}