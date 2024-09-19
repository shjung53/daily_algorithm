package lv2_소수찾기;

import java.util.*;

class Solution {
    static char[] makingArr, charArr;
    static int n, answer;
    static boolean[] visited;
    static HashSet<Integer> set;
    public int solution(String numbers) {
        answer = 0;
        charArr = numbers.toCharArray();
        set = new HashSet<Integer>();

        for(int i=1; i <= charArr.length; i++) {
            n = i;
            visited = new boolean[charArr.length];
            makingArr = new char[n];
            dfs(0, 0);
        }

        return answer;
    }

    static void dfs(int index, int count) {
        if(count >= n) {
            String str = String.valueOf(makingArr);
            int number = Integer.parseInt(str);
            if(!set.contains(number)) {
                if(isPrime(number)) answer++;
                set.add(number);
            }
            return;
        }
        for(int i=0; i<charArr.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            makingArr[index] = charArr[i];
            dfs(index + 1, count + 1);
            visited[i] = false;
        }
    }

    static boolean isPrime(int number) {
        if(number <= 1) return false;
        for(int i=2; i*i <= number; i++) {
            if(number % i == 0) return false;
        }
        return true;
    }
}
