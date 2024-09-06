package 폭발문자열_9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static String str;
    static String explosive;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().trim();
        explosive = br.readLine().trim();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        int lastIndex = explosive.length() - 1;

        for (char c : str.toCharArray()) {
            stack.addLast(c);
            int index = lastIndex;
            if (c == explosive.charAt(index)) {
                ArrayDeque<Character> temp = new ArrayDeque<>();
                while (!stack.isEmpty() && stack.peekLast() == explosive.charAt(index)) {
                    temp.addLast(stack.removeLast());
                    index--;
                    if (index < 0) break;
                }

                if (index >= 0) {
                    while (!temp.isEmpty()) {
                        stack.addLast(temp.removeLast());
                    }
                }
                continue;
            }
        }

        StringBuilder stb = new StringBuilder();

        while (!stack.isEmpty()) {
            stb.append(stack.removeFirst());
        }

        String answer = "";

        answer = stb.toString();

        if (answer.isEmpty()) answer = "FRULA";

        System.out.println(answer);

    }
}
