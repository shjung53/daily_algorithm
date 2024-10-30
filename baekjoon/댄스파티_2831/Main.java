package 댄스파티_2831;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    static StringTokenizer st;
    static int n, answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine().trim());

        TreeMap<Human, Integer> male1 = new TreeMap<>(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.height - o2.height;
            }
        });

        TreeMap<Human, Integer> male2 = new TreeMap<>(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.height - o2.height;
            }
        });

        TreeMap<Human, Integer> female1 = new TreeMap<>(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.height - o2.height;
            }
        });

        TreeMap<Human, Integer> female2 = new TreeMap<>(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.height - o2.height;
            }
        });

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            int want = 1;
            if (height < 0) {
                want *= -1;
                height *= -1;
            }
            Human man = new Human(height, want);
            if(want > 0) {
                male1.put(man, male1.getOrDefault(man, 0) + 1);
            }else {
                male2.put(man, male2.getOrDefault(man, 0) + 1);
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            int want = 1;
            if (height < 0) {
                want *= -1;
                height *= -1;
            }
            Human woman = new Human(height, want);
            if(want > 0) {
                female1.put(woman, female1.getOrDefault(woman, 0) + 1);
            }else {
                female2.put(woman, female2.getOrDefault(woman, 0) + 1);
            }
        }

        while (!male1.isEmpty()) {
            Human man = male1.firstKey();
            male1.put(man, male1.get(man) - 1);
            if(male1.get(man) == 0) male1.remove(man);

            Human woman = female2.higherKey(man);
            if (woman == null) continue;
            answer++;
            female2.put(woman, female2.get(woman) - 1);
            if (female2.get(woman) == 0) female2.remove(woman);
        }

        while (!male2.isEmpty()) {
            Human man = male2.firstKey();
            male2.put(man, male2.get(man) - 1);
            if(male2.get(man) == 0) male2.remove(man);

            Human woman = female1.lowerKey(man);
            if (woman == null) continue;
            answer++;
            female1.put(woman, female1.get(woman) - 1);
            if (female1.get(woman) == 0) female1.remove(woman);
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Human {
    int height, want;

    public Human(int height, int want) {
        this.height = height;
        this.want = want;
    }
}
