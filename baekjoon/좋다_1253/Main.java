package 좋다_1253;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, answer;

    static int[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        arr = new int[n];
        answer = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            int number = arr[i];

            int left = 0;
            int right = n - 1;

            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }

                if (right == i) {
                    right--;
                    continue;
                }

                if (arr[left] + arr[right] == number) {
                    answer++;
                    break;
                }
                if (arr[left] + arr[right] < number) left++;
                else right--;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
