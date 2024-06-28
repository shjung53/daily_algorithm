package lv2_땅따먹기;

class Solution {
    static int[][] dp;
    static int depth;
    static int length;
    static int[][] sLand;
    int solution(int[][] land) {
        int answer = 0;
        depth = land.length;
        length = land[0].length;
        dp = new int[depth][length];
        sLand = land;

        for(int i=0; i < depth; i++) {
            for(int j=0; j < length; j++){
                dp[i][j] = findDP(i, j);
            }
        }

        for(int i=0; i<length; i++) {
            answer = Math.max(answer, dp[depth - 1][i]);
        }

        return answer;
    }

    static int findDP(int i, int j) {
        if(i == 0) return sLand[i][j];
        if(dp[i][j] != 0) return dp[i][j];
        int max = Integer.MIN_VALUE;
        for(int k = 0; k < length; k++) {
            if(k == j) continue;
            max = Math.max(max, findDP(i - 1, k));
        }
        return max + sLand[i][j];
    }
}
