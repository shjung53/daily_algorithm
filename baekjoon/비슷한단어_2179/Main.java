package 비슷한단어_2179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br;
    static int n, max, before;
    static Word answerS, answerT;
    static Word[] words;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        words = new Word[n];
        max = 0;
        answerS = new Word(99999, "");
        answerT = new Word(99999, "");
        PriorityQueue<Word> pq = new PriorityQueue<>(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.str.compareTo(o2.str);
            }
        });

        for (int i = 0; i < n; i++) {
            pq.offer(new Word(i, br.readLine().trim()));
        }

        int index = 0;

        while (!pq.isEmpty()) {
            words[index] = pq.poll();
            index++;
        }


        //            Word now = pq.poll();
//            if (before.num > n) {
//                before = now;
//                continue;
//            }
//
//            if(before.str.equals(now.str)) continue;
//
//            int prefixCount = countPrefix(before.str, now.str);

        System.out.println(answerS.str);
        System.out.println(answerT.str);

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

class Word {
    int num;
    String str;

    public Word(int num, String str) {
        this.num = num;
        this.str = str;
    }
}
