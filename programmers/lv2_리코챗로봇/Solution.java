package lv2_리코챗로봇;

import java.util.*;

class Solution {
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] map, visited;
    static int n, m, startY, startX;
    static int answer;

    public int solution(String[] board) {
        answer = Integer.MAX_VALUE;
        n = board.length;
        m = board[0].length();
        map = new int[n][m];
        visited = new int[n][m];

        for(int i=0; i<n; i++) {
            String line = board[i];
            for(int j=0; j<m; j++) {
                visited[i][j] = Integer.MAX_VALUE;
                char c = line.charAt(j);
                if(c == '.') {
                    map[i][j] = 0;
                    continue;
                }

                if(c == 'R') {
                    startY = i;
                    startX = j;
                    map[i][j] = 0;
                    continue;
                }

                if(c == 'D') {
                    map[i][j] = 1;
                    continue;
                }

                if(c == 'G') {
                    map[i][j] = 3;
                    continue;
                }
            }
        }

        findRoute();

        if(answer == Integer.MAX_VALUE) answer = -1;

        return answer;
    }

    private static void findRoute() {
        PriorityQueue<Robot> pq = new PriorityQueue<>(new Comparator<Robot>(){
            @Override
            public int compare(Robot o1, Robot o2) {
                return o1.count - o2.count;
            }
        });

        visited[startY][startX] = 0;
        pq.offer(new Robot(startY, startX, 0));

        while(!pq.isEmpty()) {
            Robot robot = pq.poll();
            int nowY = robot.y;
            int nowX = robot.x;

            if(map[nowY][nowX] == 3) {
                if(answer > robot.count) answer = robot.count;
                continue;
            }

            for(int d=0; d<4; d++) {
                int[] nextPos = getNextPos(nowY, nowX, d);
                int nextY = nextPos[0];
                int nextX = nextPos[1];
                if(nowY == nextY && nowX == nextX) continue;
                if(visited[nextY][nextX] < robot.count + 1) {
                    continue;
                }
                visited[nextY][nextX] = robot.count + 1;
                pq.offer(new Robot(nextY, nextX, robot.count + 1));
            }
        }
    }
    private static int[] getNextPos(int y, int x, int d) {
        int nextY = y;
        int nextX = x;
        int[] nextPos = new int[2];

        while(nextY >= 0 && nextX >= 0 && nextY < n && nextX < m && map[nextY][nextX] != 1) {
            nextY += dy[d];
            nextX += dx[d];
        }

        nextY -= dy[d];
        nextX -= dx[d];

        nextPos[0] = nextY;
        nextPos[1] = nextX;

        return nextPos;
    }
}

class Robot{
    int y, x, count;
    public Robot(int y, int x, int count) {
        this.y = y;
        this.x = x;
        this.count = count;
    }
}
