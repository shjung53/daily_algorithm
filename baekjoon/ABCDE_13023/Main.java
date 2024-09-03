package ABCDE_13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static Person[] people;
    static int n, m, friendCount, answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        people = new Person[n];
        friendCount = 0;
        answer = 0;
        visited = new boolean[n];

        for(int i=0; i<n; i++) {
            people[i] = new Person();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            people[a].friends.add(b);
            people[b].friends.add(a);
        }

        for (int i = 0; i < n; i++) {
            if (answer > 0) break;
            visited[i] = true;
            friendCount++;
            dfs(i);
            visited[i] = false;
            friendCount--;
        }

        System.out.println(answer);

    }

    static void dfs(int number) {
        if (friendCount >= 5) {
            answer = 1;
            return;
        }
        for (int friend: people[number].friends) {
            if (visited[friend]) continue;
            visited[friend] = true;
            friendCount++;
            dfs(friend);
            visited[friend] = false;
            friendCount--;
        }
    }
}

class Person{
    ArrayList<Integer> friends = new ArrayList<>();
}
