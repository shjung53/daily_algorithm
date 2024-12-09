package pccp_체육대회;

import java.util.*;

class Solution {
    static int[][] abilityMap;
    static boolean[] visited;
    static int n, m; // 학생수, 어빌수
    static int answer;
    public int solution(int[][] ability) {
        answer = 0;
        abilityMap = ability;
        n = ability.length;
        m = ability[0].length;

        visited = new boolean[n];

        dfs(0, 0);
        return answer;
    }

    private static void dfs(int index, int sum){
        if(index >= m) {
            if(sum > answer) answer = sum;
            return;
        }
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(index + 1, sum + abilityMap[i][index]);
            visited[i] = false;
        }
    }
}
