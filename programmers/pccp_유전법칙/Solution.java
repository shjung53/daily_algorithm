package pccp_유전법칙;

import java.util.*;

class Solution {
    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];

        for(int i=0; i<queries.length; i++) {
            int[] query = queries[i];
            String dna = getDNA(query[0] - 1, query[1] - 1);
            answer[i] = dna;
        }

        return answer;
    }

    private static String getDNA(int gen, int index) {
        if(gen == 0) return "Rr";
        String parent = getDNA(gen - 1, index / 4);

        if(parent.equals("RR")){
            return "RR";
        }
        if(parent.equals("Rr")){
            if(index % 4 == 0) return "RR";
            if(index % 4 == 3) return "rr";
            return "Rr";
        }
        if(parent.equals("rr")){
            return "rr";
        }

        return "";
    }
}