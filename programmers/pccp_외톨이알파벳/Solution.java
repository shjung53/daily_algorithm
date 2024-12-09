package pccp_외톨이알파벳;

import java.util.*;

class Solution {
    public String solution(String input_string) {
        String answer = "";
        boolean[] isShownBefore = new boolean[26];
        boolean[] alone = new boolean[26];

        StringBuilder stb = new StringBuilder();

        for(int i=0; i<input_string.length(); i++) {
            if(isShownBefore[input_string.charAt(i) - 'a']) {
                if(input_string.charAt(i) != input_string.charAt(i - 1)) alone[input_string.charAt(i) - 'a'] = true;
            }else {
                isShownBefore[input_string.charAt(i) - 'a'] = true;
            }
        }

        for(int i=0; i<26; i++) {
            if(alone[i]) stb.append(Character.toChars(i + 'a'));
        }

        answer = stb.toString();

        if(answer.isEmpty()) answer = "N";

        return answer;
    }
}
