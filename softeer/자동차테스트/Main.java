package 자동차테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, q;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            pq.offer(number);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int index = 1;

        while (!pq.isEmpty()) {
            map.put(pq.poll(), index);
            index++;
        }

        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int number = Integer.parseInt(br.readLine().trim());
            int numberIndex = map.getOrDefault(number, 1);
            stb.append((n - numberIndex) * (numberIndex - 1)).append('\n');
        }

        System.out.println(stb);
    }
}
