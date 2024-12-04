package 플레이페어암호;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static String message, key;
    static char[][] map;
    static Queue<String> pairs;
    static StringBuilder stb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        message = br.readLine().trim();
        key = br.readLine().trim();

        boolean[] visited = new boolean[26];
        visited[9] = true; //j는 i랑 같은 취급
        map = new char[5][5];
        stb = new StringBuilder();

        int index = 0;

        OUT:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (index >= key.length()) break OUT;
                char alphabet = key.charAt(index);

                while (visited[alphabet - 'A']) {
                    index++;
                    if (index >= key.length()) break OUT;
                    alphabet = key.charAt(index);
                }

                visited[alphabet - 'A'] = true;
                map[i][j] = alphabet;
                index++;
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!Character.isAlphabetic(map[i][j])) {
                    for (int k = 0; k < 26; k++) {
                        if (!visited[k]) {
                            map[i][j] = Character.toChars('A' + k)[0];
                            visited[k] = true;
                            break;
                        }
                    }
                }
            }
        }

        makePairs();
        checkPairs();
        bw.write(stb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void checkPairs() {
        while (!pairs.isEmpty()) {
            String pair = pairs.poll();
            if (checkRow(pair)) continue;
            if (checkColumn(pair)) continue;
            checkAll(pair);
        }
    }

    private static boolean checkRow(String pair) {
        char first = pair.charAt(0);
        char second = pair.charAt(1);
        char transFirst = ' ';
        char transSecond = ' ';
        for (int i = 0; i < 5; i++) {
            boolean firstCheck = false;
            boolean secondCheck = false;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == first) {
                    firstCheck = true;
                    transFirst = map[i][(j + 1) % 5];
                }
                if (map[i][j] == second) {
                    secondCheck = true;
                    transSecond = map[i][(j + 1) % 5];
                }
            }
            if (firstCheck && secondCheck) {
                stb.append(transFirst);
                stb.append(transSecond);
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumn(String pair) {
        char first = pair.charAt(0);
        char second = pair.charAt(1);
        char transFirst = ' ';
        char transSecond = ' ';
        for (int i = 0; i < 5; i++) {
            boolean firstCheck = false;
            boolean secondCheck = false;
            for (int j = 0; j < 5; j++) {
                if (map[j][i] == first) {
                    firstCheck = true;
                    transFirst = map[(j + 1) % 5][i];
                }
                if (map[j][i] == second) {
                    secondCheck = true;
                    transSecond = map[(j + 1) % 5][i];
                }
            }
            if (firstCheck && secondCheck) {
                stb.append(transFirst);
                stb.append(transSecond);
                return true;
            }
        }
        return false;
    }

    private static void checkAll(String pair) {
        char first = pair.charAt(0);
        char second = pair.charAt(1);
        int firstCol = 0;
        int firstRow = 0;
        int secondCol = 0;
        int secondRow = 0;
        char transFirst = ' ';
        char transSecond = ' ';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == first) {
                    firstCol = i;
                    firstRow = j;
                }
                if (map[i][j] == second) {
                    secondCol = i;
                    secondRow = j;
                }
            }
        }
        transFirst = map[firstCol][secondRow];
        transSecond = map[secondCol][firstRow];
        stb.append(transFirst);
        stb.append(transSecond);
    }

    private static void makePairs() {
        pairs = new ArrayDeque<>();
        ArrayDeque<Character> queue = new ArrayDeque<>();
        for (char c : message.toCharArray()) queue.offer(c);

        char[] chars = new char[2];

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (!Character.isAlphabetic(chars[0])) {
                chars[0] = c;
                continue;
            }

            if (c == chars[0]) {
                if (chars[0] == 'X') {
                    chars[1] = 'Q';
                } else {
                    chars[1] = 'X';
                }
                queue.push(c);
                pairs.offer(String.valueOf(chars));
                chars = new char[2];
                continue;
            }

            chars[1] = c;
            pairs.offer(String.valueOf(chars));
            chars = new char[2];
        }

        if (Character.isAlphabetic(chars[0])) {
            chars[1] = 'X';
            pairs.offer(String.valueOf(chars));
        }
    }
}
