package lv2_혼자놀기의달인;

import java.util.*;

class Solution {
    static boolean[] visited;
    static int n, max, second;
    static int[] myCards;

    public int solution(int[] cards) {
        int answer = 0;
        max = 0;
        second = 0;
        n = cards.length;
        visited = new boolean[n];
        myCards = cards;

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                openBox(i, 0);
            }
        }

        answer = max * second;

        return answer;
    }

    static void openBox(int boxIndex, int count) {
        if(visited[boxIndex]) {
            if(count > max) {
                second = max;
                max = count;
                return;
            }
            if(count > second) {
                second = count;
                return;
            }
            return;
        }
        visited[boxIndex] = true;
        int next = myCards[boxIndex];
        openBox(next - 1, count + 1);
    }
}

// union and find

//import java.util.*;
//
//class Solution {
//    static int[] parent;
//    static int[] rank;
//    static int n, max, second;
//    public int solution(int[] cards) {
//        int answer = 0;
//        n = cards.length;
//        parent = new int[n + 1];
//        rank = new int[n + 1];
//
//        for(int i=1; i<=n; i++) {
//            parent[i] = i;
//            rank[i] = 1;
//        }
//
//        for(int i=0; i<n; i++) {
//            int now = i + 1;
//            int card = cards[i];
//            link(now, card);
//        }
//
//        max = 0;
//        second = 0;
//
//        System.out.println(Arrays.toString(rank));
//
//        for(int i=1; i<=n; i++) {
//            if(parent[i] == i) {
//                if(rank[i] > max) {
//                    max = rank[i];
//                    continue;
//                }
//                if(rank[i] > second) {
//                    second = rank[i];
//                    continue;
//                }
//            }
//        }
//
//        answer = max * second;
//
//        return answer;
//    }
//
//    private static void link(int a, int b) {
//        int parentA = findParent(a);
//        int parentB = findParent(b);
//
//        if(rank[parentA] < rank[parentB]) {
//            int temp = parentA;
//            parentA = parentB;
//            parentB = temp;
//        }
//
//        parent[parentB] = parentA;
//        rank[parentA]++;
//        rank[parentB] = 0;
//    }
//
//    private static int findParent(int number) {
//        if(parent[number] == number) return number;
//        return parent[number] = findParent(parent[number]);
//    }
//}
