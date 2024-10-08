package 염기서열커버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, answer;

    static ArrayList<String> mixture;
    static ArrayList<String> temp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;
        mixture = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            mixture.add(line);
        }

        int compoundCount;

        do {
            compoundCount = 0;
            visited = new boolean[mixture.size()];
            temp = new ArrayList<>();
            for (int i = 0; i < mixture.size(); i++) {
                if (visited[i]) continue;
                for (int j = i + 1; j < mixture.size(); j++) {
                    if (visited[j]) continue;
                    if (compound(i, j)) {
                        compoundCount++;
                    }
                }
                if (!visited[i]) answer++;
            }
            mixture = temp;
        } while (compoundCount > 0);

        System.out.println(answer);
    }

    private static boolean compound(int n1Index, int n2Index) {
        char[] n1 = mixture.get(n1Index).toCharArray();
        char[] n2 = mixture.get(n2Index).toCharArray();
        char[] mix = new char[m];
        int index = 0;

        while (index < m) {
            if (n1[index] == n2[index]) {
                mix[index] = n1[index];
                index++;
                continue;
            }
            if (n1[index] == '.') {
                mix[index] = n2[index];
                index++;
                continue;
            }

            if (n2[index] == '.') {
                mix[index] = n1[index];
                index++;
                continue;
            }

            if (n1[index] != '.' && n2[index] != '.' && n1[index] != n2[index]) return false;
            index++;
        }

        visited[n1Index] = true;
        visited[n2Index] = true;

        temp.add(String.valueOf(mix));
        return true;
    }
}
