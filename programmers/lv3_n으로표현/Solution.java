package lv3_n으로표현;

import java.util.*;

class Solution {
    static HashSet<Integer>[] sets;
    static int n;

    public int solution(int N, int number) {
        int answer = -1;
        n = N;
        sets = new HashSet[9];
        for (int i = 1; i < 9; i++) {
            sets[i] = new HashSet<>();
        }
        sets[1].add(N);

        for (int i = 2; i < 9; i++) {
            for (int j = 1; j <= i / 2; j++) {
                cal(i, j);
            }

            int num = 0;
            for (int k = 0; k < i; k++) {
                num = num * 10 + n;
            }
            sets[i].add(num);
        }


        for (int i = 1; i < 9; i++) {
            if (sets[i].contains(number)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    private static void cal(int index, int number) {
        for (int a : sets[number]) {
            for (int b : sets[index - number]) {
                sets[index].add(a + b);
                sets[index].add(a - b);
                sets[index].add(b - a);
                sets[index].add(a * b);
                if (b != 0) sets[index].add(a / b);
                if (a != 0) sets[index].add(b / a);
            }
        }
    }
}