package lv1_붕대감기;

class Solution {
    static int t, x, a, hp, max, now;

    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        t = bandage[0];
        x = bandage[1];
        a = bandage[2];
        hp = health;
        max = health;
        now = 0;

        for (int[] attack : attacks) {
            int time = attack[0];
            int damage = attack[1];
            int term = (time - now) - 1;
            int cycle = term / t;
            hp += cycle * a;
            hp += term * x;
            if (hp > max) hp = max;
            hp -= damage;
            if (hp <= 0) {
                hp = -1;
                break;
            }
            now = time;
        }

        answer = hp;
        return answer;
    }
}
