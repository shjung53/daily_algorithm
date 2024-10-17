package 카드정렬하기_1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br;
    static int n;
    static long answer;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        answer = 0;

        PriorityQueue<Integer> cardsDeck = new PriorityQueue<>();

        for(int i=0; i<n; i++) {
            cardsDeck.offer(Integer.parseInt(br.readLine().trim()));
        }

        while(!cardsDeck.isEmpty()) {
            if(cardsDeck.size() == 1) break;
            int cards1 = cardsDeck.poll();
            int cards2 = cardsDeck.poll();
            answer += cards1 + cards2;
            cardsDeck.offer(cards1 + cards2);
        }

        System.out.println(answer);
    }
}
