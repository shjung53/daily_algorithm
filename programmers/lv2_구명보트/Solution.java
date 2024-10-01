package lv2_구명보트;

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 1;
        int onBoat = 0;
        int onBoatCount = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int weight: people) {
            pq.offer(weight);
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        while(!pq.isEmpty()) {
            deque.addLast(pq.poll());
        }

        while(!deque.isEmpty()) {

            if(onBoatCount > 1) {
                onBoat = 0;
                onBoatCount = 0;
                answer++;
                continue;
            }

            if(deque.peekLast() + onBoat <= limit) {
                onBoat += deque.pollLast();
                onBoatCount++;
                continue;
            }

            if(deque.peekFirst() + onBoat <= limit) {
                onBoat += deque.pollFirst();
                onBoatCount++;
                continue;
            }

            onBoat = 0;
            onBoatCount = 0;
            answer++;
        }

        return answer;
    }
}
