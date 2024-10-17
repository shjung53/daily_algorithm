package 탑_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        answer = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        ArrayDeque<Tower> deque = new ArrayDeque<>();

        OUT:
        for (int i = 0; i < n; i++) {
            Tower tower = new Tower(i + 1, Integer.parseInt(st.nextToken()));
            while (!deque.isEmpty()) {
                if (deque.peekFirst().height > tower.height) {
                    answer[i] = deque.peekFirst().num;
                    deque.push(tower);
                    continue OUT;
                }
                deque.pop();
            }
            answer[i] = 0;
            deque.push(tower);
        }

        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stb.append(answer[i]).append(' ');
        }
        br.close();
        System.out.println(stb);
    }
}

class Tower {
    int num, height;

    public Tower(int num, int height) {
        this.num = num;
        this.height = height;
    }
}
