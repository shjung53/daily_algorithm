package lv2_아날로그시계;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        int startSecond = h1 * 3600 + m1 * 60 + s1;
        double hDegree = ((startSecond * 0.1) / 12) % 360;
        double mDegree = (startSecond * 0.1) % 360;
        double sDegree = (startSecond * 6) % 360;
        int endSecond = h2 * 3600 + m2 * 60 + s2;

        if(hDegree == 0 && mDegree == 0 && sDegree == 0) answer++;

        for(double i=startSecond; i<endSecond; i++) {
            double nowHDegree = (i / 120) % 360;
            double nowMDegree = (i / 10) % 360;
            double nowSDegree = (i * 6) % 360;

            double nextHDegree = ((i + 1) / 120) % 360;
            double nextMDegree = ((i + 1)/ 10) % 360;
            double nextSDegree = ((i + 1) * 6) % 360;

            if(nextHDegree == 0) nextHDegree = 360;
            if(nextMDegree == 0) nextMDegree = 360;
            if(nextSDegree == 0) nextSDegree = 360;

            if(nowHDegree > nowSDegree && nextSDegree >= nextHDegree) answer++;
            if(nowMDegree > nowSDegree && nextSDegree >= nextMDegree) answer++;
            if(nextHDegree == nextMDegree) answer--;
        }


        return answer;
    }
}
