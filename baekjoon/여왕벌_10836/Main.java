package 여왕벌_10836;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static int[] growth;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][m];
        growth = new int[2 * m - 1];

        for (int i = 0; i < m; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int d = 0; d < n; d++) {
            st = new StringTokenizer(br.readLine().trim());
            int zeroCount = Integer.parseInt(st.nextToken());
            int oneCount = Integer.parseInt(st.nextToken());
            int twoCount = Integer.parseInt(st.nextToken());

            int index = 0;

            for (int i = 0; i < zeroCount; i++) {
                growth[index] += 0;
                index++;
            }

            for (int i = 0; i < oneCount; i++) {
                growth[index] += 1;
                index++;
            }

            for (int i = 0; i < twoCount; i++) {
                growth[index] += 2;
                index++;
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            map[i][0] += growth[(m - 1) - i];
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    bw.write(map[i][j] + "");
                } else {
                    bw.write(map[i][j] + growth[m - 1 + j] + "");
                }

                if (j < m - 1) bw.write(" ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
