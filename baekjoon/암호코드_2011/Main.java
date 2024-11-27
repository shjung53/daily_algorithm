package 암호코드_2011;

import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine().trim();
        int length = str.length();
        int before = 0;
        int[] dp = new int[length + 1];
        dp[0] = 1;

        for (int i = 1; i <= length; i++) {
            int number = Character.getNumericValue(str.charAt(i - 1));

            if (before == 1 && number > 0) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
                before = number;
                continue;
            }

            if (before == 2 && number >= 1 && number <= 6) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
                before = number;
                continue;
            }

            if (number == 0) {
                if (i - 2 < 0) break;
                if (before == 0) break;
                if (before > 2) break;
                dp[i] = dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }

            before = number;
        }

        bw.write(String.valueOf(dp[length]));
        bw.flush();
        bw.close();
        br.close();
    }
}
