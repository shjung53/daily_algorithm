package 선분위의_11663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder stb;
    static int n, m;
    static int[] points;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        stb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        points = new int[n];
        st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순으로 정렬
        Arrays.sort(points);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int min = getMin(start);
            int max = getMax(end);

            // 어떠한 점도 포함하지 않는 경우
            if (min >= n || max <= 0) {
                stb.append(0).append('\n');
            } else {
                stb.append(max - min + 1).append('\n');
            }
        }
        System.out.println(stb);
    }

    //  선분을 포함하는 최소의 index
    private static int getMin(int start) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = points[mid];
            if (midValue >= start) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 선분을 포함하는 최대의 index
    private static int getMax(int end) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = points[mid];
            if (midValue <= end) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
