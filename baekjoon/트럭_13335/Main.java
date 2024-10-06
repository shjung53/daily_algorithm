package 트럭_13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, w, l, answer, onBridge;
    static int[] truckTime;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        truckTime = new int[n];
        answer = 0;
        onBridge = 0;
        st = new StringTokenizer(br.readLine());

        ArrayDeque<Truck> bridge = new ArrayDeque<>();
        ArrayDeque<Truck> trucks = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            trucks.addLast(new Truck(i, Integer.parseInt(st.nextToken())));
        }

        while (!trucks.isEmpty()) {
            if (trucks.peekFirst().weight + onBridge <= l) {
                int weight = trucks.peekFirst().weight;
                bridge.addLast(trucks.removeFirst());
                int first = bridge.peekFirst().num;
                int last = bridge.peekLast().num;
                for (int i = first; i <= last; i++) {
                    truckTime[i]++;
                }
                onBridge += weight;
                if (truckTime[first] >= w + 1) {
                    Truck out = bridge.removeFirst();
                    onBridge -= out.weight;
                }
                answer++;
            } else {
                int first = bridge.peekFirst().num;
                int left = w - truckTime[first];
                int last = bridge.peekLast().num;
                for (int i = first; i <= last; i++) {
                    truckTime[i]+= left;
                }
                Truck out = bridge.removeFirst();
                onBridge -= out.weight;
                answer += left;
            }
        }

        if(!bridge.isEmpty()) {
            int last = bridge.peekLast().num;
            int left = w + 1 - truckTime[last];
            answer += left;
        }

        System.out.println(answer);
    }
}

class Truck {
    int num, weight;

    public Truck(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}
