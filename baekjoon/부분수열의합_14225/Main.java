package 부분수열의합_14225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] list;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        list = new int[n];
        check = new boolean[2000001];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        addAndNext(0, 0);
        int answer = 0;
        for(int i=1; i<2000001; i++) {
            if(!check[i]) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    static void addAndNext(int index, int value) {
        if(index >= n) return;
        int now = list[index];
        check[value + now] = true;
        addAndNext(index + 1, value + now);
        check[value] = true;
        addAndNext(index + 1, value);
    }
}
