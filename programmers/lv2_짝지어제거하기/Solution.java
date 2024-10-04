package lv2_짝지어제거하기;

import java.util.*;

class Solution {
    public int solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int answer = 0;
        for(char c: s.toCharArray()) {
            if(stack.isEmpty()) {
                stack.addLast(c);
                continue;
            }

            if(c == stack.peekLast()) {
                stack.removeLast();
                continue;
            }

            stack.addLast(c);
        }

        if(stack.isEmpty()) answer = 1;

        return answer;
    }
}