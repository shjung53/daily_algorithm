package 스택수열_1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static BufferedReader br;
    static int n;
    static int[] order;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        n = Integer.parseInt(br.readLine().trim());
        order = new int[n];
        StringBuilder stb = new StringBuilder();
        int index = 0;
        int number = 1;

        for(int i=0; i<n; i++) {
            order[i] = Integer.parseInt(br.readLine().trim());
        }

        while(index < n && number <= n + 1) {
            if(stack.isEmpty()) {
                stack.addLast(number);
                stb.append('+').append('\n');
                number++;
                continue;
            }

            if(order[index] == stack.peekLast()) {
                stack.removeLast();
                stb.append('-').append('\n');
                index++;
                continue;
            }

            stack.addLast(number);
            stb.append('+').append('\n');
            number++;
        }

        if(!stack.isEmpty()) {
            stb.setLength(0);
            stb.append("NO");
        }

        System.out.println(stb);
    }
}
