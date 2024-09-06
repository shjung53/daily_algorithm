package 택배마스터광우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, k, answer;
    static int[] rail, newRail;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        rail = new int[n];
        newRail = new int[n];
        visited = new boolean[n];
        answer = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            rail[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int index) {
        if (index >= n) {
            int weight = getWeight();
            if (weight < answer) answer = weight;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            newRail[index] = rail[i];
            dfs(index + 1);
            visited[i] = false;
        }
    }

    static int getWeight() {
        int basket = 0;
        int basketCount = 0;
        int weight = 0;
        int railIndex = 0;

        while (basketCount < k) {
            if (newRail[railIndex] + basket > m) {
                weight += basket;
                basket = 0;
                basketCount++;
                continue;
            }

            basket += newRail[railIndex];
            railIndex = (railIndex + 1) % n;
        }

        return weight;
    }
}
