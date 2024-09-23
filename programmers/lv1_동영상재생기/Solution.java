package lv1_동영상재생기;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        String[] lenArr = video_len.split(":");
        String[] posArr = pos.split(":");
        String[] opArr = op_start.split(":");
        String[] endArr = op_end.split(":");

        int max = Integer.parseInt(lenArr[0]) * 60 + Integer.parseInt(lenArr[1]);
        int now = Integer.parseInt(posArr[0]) * 60 + Integer.parseInt(posArr[1]);
        int op = Integer.parseInt(opArr[0]) * 60 + Integer.parseInt(opArr[1]);
        int end = Integer.parseInt(endArr[0]) * 60 + Integer.parseInt(endArr[1]);

        if (now >= op && now <= end) {
            now = end;
        }

        for (String command : commands) {
            if (command.equals("next")) {
                now += 10;
                if (now > max) now = max;
                if (now >= op && now <= end) {
                    now = end;
                }
                continue;
            }
            if (command.equals("prev")) {
                now -= 10;
                if (now < 0) now = 0;
                if (now >= op && now <= end) {
                    now = end;
                }
                continue;
            }
        }

        int answerM = now / 60;
        int answerS = now % 60;

        StringBuilder stb = new StringBuilder();
        if (answerM < 10) stb.append(0);
        stb.append(answerM).append(':');
        if (answerS < 10) stb.append(0);
        stb.append(answerS);
        answer = stb.toString();
        return answer;
    }
}
