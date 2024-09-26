package lv2_전력망둘로나누기;

import java.util.*;

class Solution {
    static boolean[][] link;
    static boolean[] visited;
    static int myN;
    static int answer;

    public int solution(int n, int[][] wires) {
        answer = Integer.MAX_VALUE;
        myN = n;
        link = new boolean[n][n];

        for (int[] wire : wires) {
            int e1 = wire[0] - 1;
            int e2 = wire[1] - 1;
            link[e1][e2] = true;
            link[e2][e1] = true;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (link[i][j]) {
                    link[i][j] = false;
                    link[j][i] = false;
                    count();
                    link[i][j] = true;
                    link[j][i] = true;
                }
            }
        }

        return answer;
    }

    private static void count() {
        visited = new boolean[myN];
        int countA = -1;
        int countB = -1;
        for (int i = 0; i < myN; i++) {
            if (!visited[i]) {
                if (countA < 0) {
                    countA = bfs(i);
                    continue;
                }
                if (countB < 0) countB = bfs(i);
            }
        }
        int gap = Math.abs(countA - countB);
        if (answer > gap) answer = gap;
    }

    private static int bfs(int number) {
        int count = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        visited[number] = true;
        count++;
        queue.offer(number);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i < myN; i++) {
                if (link[now][i] && !visited[i]) {
                    visited[i] = true;
                    count++;
                    queue.offer(i);
                }
            }
        }

        return count;
    }
}
