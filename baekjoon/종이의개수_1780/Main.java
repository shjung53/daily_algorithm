package 종이의개수_1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, minusOne, zero, one;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        map = new int[n][n];
        minusOne = 0;
        zero = 0;
        one = 0;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        countPaper(0, 0, n);

        System.out.println(minusOne);
        System.out.println(zero);
        System.out.println(one);
    }

    private static void countPaper(int y, int x, int length) {
        int number = map[y][x];
        boolean same = true;

        Loop:
        for (int i = y; i < y + length; i++) {
            for (int j = x; j < x + length; j++) {
                if (map[i][j] != number) {
                    same = false;
                    break Loop;
                }
            }
        }

        int nextLength = length / 3;

        if (!same) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    countPaper(y + nextLength * i, x + nextLength * j, nextLength);
                }
            }
        } else {
            if(number == -1) minusOne++;
            if(number == 0) zero++;
            if(number == 1) one++;
        }
    }
}
