package lv2_충돌위험찾기;

import java.util.*;

class Solution {
    static int[][] visited;
    static int[][] mRoutes;
    static int[][] mPoints;
    static int answer;
    static int n, m;

    public int solution(int[][] points, int[][] routes) {
        n = points.length;
        m = routes[0].length;
        answer = 0;
        mRoutes = routes;
        mPoints = points;
        bfs();

        return answer;
    }

    private void bfs() {
        Queue<Robot> queue = new ArrayDeque<>();
        for(int i=0; i < mRoutes.length; i++) {
            int startPoint = mRoutes[i][0] - 1;
            int startY = mPoints[startPoint][0];
            int startX = mPoints[startPoint][1];
            Robot robot = new Robot(i, startY, startX, 0);
            queue.offer(robot);
        }

        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            visited = new int[101][101];
            for(int q=0; q < queueSize; q++) {
                Robot robot = queue.poll();
                visited[robot.y][robot.x]++;
                if(visited[robot.y][robot.x] == 2) answer++;
                int nextGoal = mRoutes[robot.number][robot.goalIndex] - 1;
                int goalIndex = robot.goalIndex;
                if(robot.y == mPoints[nextGoal][0] && robot.x == mPoints[nextGoal][1]) goalIndex++;
                if(goalIndex >= m) continue;

                int[] nextPosition = getNextPosition(robot.number, robot.y, robot.x, goalIndex);
                int nextY = nextPosition[0];
                int nextX = nextPosition[1];
                queue.offer(new Robot(robot.number, nextY, nextX, goalIndex));
            }
        }
    }

    private int[] getNextPosition(int robotNumber, int y, int x, int goalIndex) {
        int pointIndex = mRoutes[robotNumber][goalIndex] - 1;
        int[] goalPoint = mPoints[pointIndex];
        int goalY = goalPoint[0];
        int goalX = goalPoint[1];
        int[] nextPos = new int[2];
        nextPos[0] = y;
        nextPos[1] = x;
        if(y != goalY) {
            if(y > goalY) {
                nextPos[0] = y - 1;
            } else {
                nextPos[0] = y + 1;
            }
            return nextPos;
        }

        if(x != goalX) {
            if(x > goalX) {
                nextPos[1] = x - 1;
            } else {
                nextPos[1] = x + 1;
            }
            return nextPos;
        }

        return nextPos;
    }
}

class Robot{
    int number, y, x, goalIndex;
    public Robot(int number, int y, int x, int goalIndex) {
        this.number = number;
        this.y = y;
        this.x = x;
        this.goalIndex = goalIndex;
    }
}
