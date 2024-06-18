import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 랜선 길이가 2^31 - 1보다 작거나 같은 자연수였다. 범위 잘 확인하고 타입 사용할 것
public class 랜선자르기_1654 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;
    static long[] lines;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        lines = new long[k];
        for (int i = 0; i < k; i++) {
            lines[i] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(lines);
        long left = 1;
        long right = lines[lines.length - 1];

        /*
        mid로 만들 수 있으면 최대의 길이를 얻기 위해 left를 mid + 1, 만들지 못하면 right를 mid - 1 해준다.
        만들 수 있는 최댓값은 left의 -1값을 가지게 되는 right -> 만들 수 있으면 left + 1이고 만들지 못하면 right - 1이기 때문에
        */
        while (left <= right) {
            long mid = (left + right) / 2;
            if (cutLines(mid) >= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }

    // 정해진 길이로 잘라서 몇개가 만들어지는 지 확인
    private static int cutLines(long length) {
        int count = 0;
        for (long line : lines) {
            count += line / length;
        }
        return count;
    }
}
