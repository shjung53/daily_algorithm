package CPTI;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, answer;
    static HashMap<Integer, Integer> cptiMap;
    static char[] chars;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;

        cptiMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String cpti = br.readLine().trim();
            chars = cpti.toCharArray();
            dfs(0, 0);
            int cptiNumber = Integer.parseInt(cpti, 2);
            cptiMap.put(cptiNumber, cptiMap.getOrDefault(cptiNumber, 0) + 1);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int count) {
        if (count > 2) return;

        if (index >= m) {
            String cpti = String.valueOf(chars);
            answer += cptiMap.getOrDefault(Integer.parseInt(cpti, 2), 0);
            return;
        }

        if (chars[index] == '1') {
            chars[index] = '0';
        } else {
            chars[index] = '1';
        }
        dfs(index + 1, count + 1);

        if (chars[index] == '1') {
            chars[index] = '0';
        } else {
            chars[index] = '1';
        }
        dfs(index + 1, count);
    }

    private static boolean feelFamiliar(int number) {
        int differentCount = 0;
        for (char c : String.valueOf(number).toCharArray()) {
            if (c == '1') differentCount++;
            if (differentCount > 2) return false;
        }
        return true;
    }
}
