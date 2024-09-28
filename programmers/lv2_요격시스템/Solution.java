package lv2_요격시스템;

import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        PriorityQueue<Bomb> pq = new PriorityQueue<Bomb>(new Comparator<Bomb>(){
            @Override
            public int compare(Bomb o1, Bomb o2) {
                return o1.e - o2.e;
            }
        });

        for(int[] target: targets) {
            pq.offer(new Bomb(target[0], target[1]));
        }

        double lastMissile = -1;

        while(!pq.isEmpty()) {
            Bomb bomb = pq.poll();
            if(lastMissile < 0) {
                answer++;
                lastMissile = bomb.e - 0.1;
            }

            if(lastMissile < bomb.e && lastMissile > bomb.s) continue;


            answer++;
            lastMissile = bomb.e - 0.1;
            System.out.println(lastMissile);
        }


        return answer;
    }
}

class Bomb{
    int s, e;
    public Bomb(int s, int e) {
        this.s = s;
        this.e = e;
    }
}
