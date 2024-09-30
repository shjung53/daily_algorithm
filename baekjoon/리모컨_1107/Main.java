package 리모컨_1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, nowChannel, answer;
    static String goalChannel;

    static HashSet<Integer> brokenNumber;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        goalChannel = br.readLine().trim();
        n = Integer.parseInt(goalChannel);
        m = Integer.parseInt(br.readLine().trim());
        brokenNumber = new HashSet<>();
        if (m > 0) st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < m; i++) {
            brokenNumber.add(Integer.parseInt(st.nextToken()));
        }
        nowChannel = 100;
        answer = Integer.MAX_VALUE;

        int nearestNumber = getNearestNumber();

        answer = Math.min(answer, Math.abs(n - nowChannel));
        answer = Math.min(answer, Math.abs(n - nearestNumber) + goalChannel.length());

        System.out.println(answer);
    }

    private static int getNearestNumber() {
        int newChannel = 0;
        boolean fit = true;
        boolean bigger = false;
        boolean smaller = false;

        for (int i = 0; i < goalChannel.length(); i++) {
            int number = Character.getNumericValue(goalChannel.charAt(i));
            int biggerNumber = number;
            int smallerNumber = number;

            if (brokenNumber.contains(number)) {
                if (fit) {
                    while (brokenNumber.contains(biggerNumber)) {
                        biggerNumber++;
                        if (biggerNumber > 9) {
                            break;
                        }
                    }

                    while (brokenNumber.contains(smallerNumber)) {
                        smallerNumber--;
                        if (smallerNumber < 0) {
                            break;
                        }
                    }

                    if (biggerNumber > 9) {
                        smaller = true;
                    } else {
                        bigger = true;
                    }

                    if (bigger) {
                        number = biggerNumber;
                    } else {
                        number = smallerNumber;
                    }

                    fit = false;
                } else {
                    if (smaller) {
                        number = 9;
                    } else {
                        number = 0;
                    }

                    if (smaller) {
                        while (brokenNumber.contains(number)) {
                            number--;
                            if (biggerNumber < 0) {
                                break;
                            }
                        }
                    } else {
                        while (brokenNumber.contains(number)) {
                            number++;
                            if (biggerNumber > 9) {
                                break;
                            }
                        }
                    }
                }

            }

            newChannel = newChannel * 10 + number;
        }

        return newChannel;
    }
}
