package 외판원순회_2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;

public class Main {
    static int n, maxVisited;
    static int[][] costMap;
    static int[][] dp;
    static BufferedReader br;
    static StringTokenizer st;
    static int answer;
    static ArrayList<String> visitedArr;

    /* 비트마스킹을 사용한다. 01000인 경우 2번째 도시면 방문한 것 2차원 배열이 너무 커지지 않도록 2진수로 관리할 것
    dp의 경우 [index][visited] 구조를 가진다.
    index는 현재 도시의 위치, visited는 방문한 도시 목록 그리고 값은 남은 동선의 최소거리
     */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        costMap = new int[n][n];
        answer = Integer.MAX_VALUE;
        visitedArr = new ArrayList<>();
        StringBuilder stb = new StringBuilder();

        for(int i=0; i<n; i++) {
            stb.append('0');
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                costMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxVisited = (int) Math.pow(2, n) - 1; // 2^n - 1을 하면 n이 3일 경우 7이되어 000부터 111까지 표현할 수 있다
        dp = new int[n][maxVisited + 1];

        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = '1';
        }

        for (int goal = 0; goal <= n; goal++) {
            combination(0, 0, goal, arr);
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = findDP(i, stb.toString(), i);
        }

        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, dp[i][0]);
        }

        System.out.println(answer);

    }

    static void combination(int index, int count, int goal, char[] arr) {
        if (count == goal) {
            visitedArr.add(String.valueOf(arr));
            return;
        }
        if (index >= n) return;
        combination(index + 1, count, goal, arr);
        arr[index] = '0';
        combination(index + 1, count + 1, goal, arr);
        arr[index] = '1';
    }

    static int findDP(int i, String visited, int start) {
        char[] visitedCharArr = visited.toCharArray();
        int min = Integer.MAX_VALUE;
        int visitedNumber = Integer.parseInt(visited, 2);
        if (visitedNumber == maxVisited) return dp[i][visitedNumber] = 0;
        if (dp[i][visitedNumber] > 0 && visited.charAt(start) != '0') return dp[i][visitedNumber];

        for (int j = 0; j < n; j++) {
            if (i == j) continue;
            if (visitedCharArr[j] == '1') continue;
            if (costMap[i][j] == 0) continue;
            if (countZero(visited) > 1) {
                if (j == start) continue;
            }
            visitedCharArr[j] = '1'; // 아직 안 가본 도시의 경우 갔다 생각하기
            min = Math.min(min, findDP(j, String.valueOf(visitedCharArr), start) + costMap[i][j]); // 갔다고 생각한 후의 dp 값에 가는 경로 값 더해서 최소 구하기
            visitedCharArr[j] = '0';
        }

        return dp[i][visitedNumber] = min;
    }

    static int countZero(String visited) {
        int count = 0;
        for (char c : visited.toCharArray()) {
            if (c == '0') count++;
        }

        return count;
    }
}
