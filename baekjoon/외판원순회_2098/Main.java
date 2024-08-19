package 외판원순회_2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, maxVisited, INF;
    static int[][] costMap;
    static int[][] dp;
    static BufferedReader br;
    static StringTokenizer st;

    /* 비트마스킹을 사용한다. 01000인 경우 2번째 도시면 방문한 것 2차원 배열이 너무 커지지 않도록 2진수로 관리할 것
    dp의 경우 [index][visited] 구조를 가진다.
    index는 현재 도시의 위치, visited는 방문한 도시 목록 그리고 값은 남은 동선의 최소거리
     */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        costMap = new int[n][n];
        INF = 99999999;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                costMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxVisited = (int) Math.pow(2, n) - 1; // 2^n - 1을 하면 n이 3일 경우 7이되어 000부터 111까지 표현할 수 있다
        dp = new int[n][maxVisited + 1];
        System.out.println(findDP(0, 1)); // 순환 루트기 때문에 한 곳의 정점에서 구할 수 있다. 시작 정점 방문을 1로하고 시작
    }

    static int findDP(int now, int visited) {
        // 도시를 다 방문했으면 시작 도시로 돌아오기
        if(visited == maxVisited) {
            // 돌아가는 길이 없으면 패스
            if(costMap[now][0] == 0) return INF;
            return costMap[now][0];
        }

        // 이미 계산이 되었으면 반환
        if (dp[now][visited] != 0) return dp[now][visited];

        dp[now][visited] = INF;

        for (int i = 0; i < n; i++) {
            // 이동할 도시 확인하기
            int visitCheck = 1 << i;

            visitCheck = visitCheck & visited;

            // 간적이 있는 도시 or 경로가 없는 경우는 패스
            if (visitCheck != 0 || costMap[now][i] == 0) continue;

            int nextVisited = visited | (1 << i);

            dp[now][visited] = Math.min(dp[now][visited], findDP(i, nextVisited) + costMap[now][i]); // 다음 도시로 이동한 상태로 가장 짧은 남은거리를 받기
        }

        return dp[now][visited];
    }
}
