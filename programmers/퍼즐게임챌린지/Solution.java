package 퍼즐게임챌린지;

class Solution {
    static int[] mDiffs, mTimes;
    static int n;
    static long mLimit;
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        mLimit = limit;
        n = diffs.length;
        mDiffs = diffs;
        mTimes = times;
        int left = 1;
        int right = 100000;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(checkLimit(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        answer = left;
        return answer;
    }

    private static boolean checkLimit(int level) {
        int preTime = 0;
        long time = 0;
        for(int i=0; i < n; i++) {
            int nowDiff = mDiffs[i];
            int nowTime = mTimes[i];
            if(level >= nowDiff) {
                time += nowTime;
                preTime = nowTime;
                continue;
            }

            time += (preTime + nowTime) * (nowDiff - level);
            time += nowTime;
            preTime = nowTime;
        }

        if(time > mLimit) return false;
        return true;
    }
}
