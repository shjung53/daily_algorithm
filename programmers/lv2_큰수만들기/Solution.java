package lv2_큰수만들기;

import java.util.ArrayDeque;

class Solution {
    static int[] numbers;
    static int n, count;

    public String solution(String number, int k) {
        String answer = "";
        n = number.length();
        count = 0;
        numbers = new int[n];

        for(int i=0; i<n; i++) {
            numbers[i] = Character.getNumericValue(number.charAt(i));
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int i=0; i<n; i++) {
            int num = numbers[i];
            while(!deque.isEmpty() && num > deque.peekLast() && count < k) {
                deque.removeLast();
                count++;
            }
            if(n - i >= k - count) {
                deque.addLast(num);
            }else {
                count++;
            }

        }

        while(count < k) {
            deque.removeLast();
            count++;
        }

        while(!deque.isEmpty()) {
            answer += Integer.toString(deque.removeFirst());
        }

        return answer;
    }
}
