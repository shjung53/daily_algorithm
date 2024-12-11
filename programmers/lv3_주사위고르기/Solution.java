package lv3_주사위고르기;

import java.util.*;

class Solution {
    static boolean[] selected;
    static int n, winCount, maxWinCount;
    static int[] answer;
    static int[][] dices;
    static TreeMap<Integer, Integer> selectedGroup, unSelectedGroup;

    public int[] solution(int[][] dice) {
        n = dice.length;
        selected = new boolean[n];
        dices = dice;
        maxWinCount = 0;

        selectDice(0, 0);

        return answer;
    }

    private static void selectDice(int index, int count) {
        if(count >= n / 2) {
            winCount = 0;
            selectedGroup = new TreeMap<Integer, Integer>();
            unSelectedGroup = new TreeMap<Integer, Integer>();
            findEverySum(true, 0, 0, 0);
            findEverySum(false, 0, 0, 0);
            countResult();
            if(winCount > maxWinCount) {
                maxWinCount = winCount;
                int[] result = new int[n / 2];
                int r = 0;
                for(int i=0; i<n; i++){
                    if(selected[i]) {
                        result[r] = i + 1;
                        r++;
                    }
                }
                answer = result;
            }
            return;
        }

        if(index >= n) return;

        selected[index] = true;
        selectDice(index + 1, count + 1);
        selected[index] = false;
        selectDice(index + 1, count);
    }

    private static void countResult() {
        while(!selectedGroup.isEmpty()) {
            int sum = selectedGroup.lastKey();

            SortedMap<Integer, Integer> lower = unSelectedGroup.headMap(sum);
            int selectCount = selectedGroup.get(sum);
            int unSelectCount = lower.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            winCount += selectCount * unSelectCount;
            selectedGroup.pollLastEntry();
            if(unSelectCount <= 0) break;
        }
    }

    private static void findEverySum(boolean isSelectedGroup, int index, int sum, int count) {
        if(count >= n / 2) {
            if(isSelectedGroup) {
                selectedGroup.put(sum, selectedGroup.getOrDefault(sum, 0) + 1);
            }else {
                unSelectedGroup.put(sum, unSelectedGroup.getOrDefault(sum, 0) + 1);
            }
            return;
        }

        if(isSelectedGroup) {
            if(selected[index]) {
                for(int value: dices[index]) {
                    findEverySum(isSelectedGroup, index + 1, sum + value, count + 1);
                }
            }else {
                findEverySum(isSelectedGroup, index + 1, sum, count);
            }
        }else {
            if(!selected[index]) {
                for(int value: dices[index]) {
                    findEverySum(isSelectedGroup, index + 1, sum + value, count + 1);
                }
            }else {
                findEverySum(isSelectedGroup, index + 1, sum, count);
            }
        }
    }


}
