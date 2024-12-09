package pccp_운영체제;

import java.util.*;

class Solution {
    public long[] solution(int[][] program) {
        long[] answer = new long[11];
        int time = 0;

        PriorityQueue<Program> leftProgramQueue = new PriorityQueue<Program>(new Comparator<Program>(){
            @Override
            public int compare(Program o1, Program o2) {
                if(o1.callTime == o2.callTime) return o1.priority - o2.priority;
                return o1.callTime - o2.callTime;
            }
        });

        PriorityQueue<Program> waitingQueue = new PriorityQueue<Program>(new Comparator<Program>(){
            @Override
            public int compare(Program o1, Program o2) {
                if(o1.priority == o2.priority) return o1.callTime - o2.callTime;
                return o1.priority - o2.priority;
            }
        });

        for(int[] programInfo: program) {
            leftProgramQueue.offer(new Program(programInfo[0],programInfo[1],programInfo[2]));
        }

        while(!leftProgramQueue.isEmpty() || !waitingQueue.isEmpty()) {
            while(!waitingQueue.isEmpty()) {
                Program nowProgram = waitingQueue.poll();
                answer[nowProgram.priority] += time - nowProgram.callTime;
                time += nowProgram.operationTime;

                while(!leftProgramQueue.isEmpty() && time >= leftProgramQueue.peek().callTime) {
                    waitingQueue.offer(leftProgramQueue.poll());
                }
            }

            if(leftProgramQueue.isEmpty()) break;

            Program nowProgram = leftProgramQueue.poll();
            if(time < nowProgram.callTime) time = nowProgram.callTime;
            answer[nowProgram.priority] += time - nowProgram.callTime;
            time += nowProgram.operationTime;

            while(!leftProgramQueue.isEmpty() && time >= leftProgramQueue.peek().callTime) {
                waitingQueue.offer(leftProgramQueue.poll());
            }
        }

        answer[0] = time;

        return answer;
    }
}

class Program{
    int priority, callTime, operationTime;
    public Program(int priority, int callTime, int operationTime) {
        this.priority = priority;
        this.callTime = callTime;
        this.operationTime = operationTime;
    }
}
