import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = sc.nextInt();
        int[][] area = new int[N][N];
        int max_height = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                area[i][j] = sc.nextInt();
                max_height = max(max_height, area[i][j]);
            }
        }

        // output
        int answer = 0;

        // 물의 높이 max부터 0이상인 높이까지 안전 구역 체크
        for(int height = max_height; height >= 0; height--) {
            int[][] water = new int[N][N];
            int[][] visited = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (area[i][j] <= height) {
                        water[i][j] = 1;
                    }
                }
            }

            int temp = 0;
            for (int i = 0; i < N; i++) {
                Queue<Pair> q = new LinkedList<>();
                for (int j = 0; j < N; j++) {
                    if(water[i][j] == 0 && visited[i][j] == 0) {
                        bfs(water, N, q, i, j, visited);
                        temp++;
                    }
                }
            }
            answer = max(answer, temp);
        }

        // 아무 지역도 물이 잠기지 않을 때
        if(answer == 0) answer = 1;

        System.out.println(answer);
    }

    public static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void bfs(int[][] w, int n, Queue<Pair> q, int x, int y, int[][] visited) {
        visited[x][y]=1;
        q.add(new Pair(x, y));

        while(!q.isEmpty()) {
            Pair p = q.poll();

            int[] dx = {-1,0,1,0};
            int[] dy = {0,1,0,-1};

            for(int i=0;i<4;i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(w[nx][ny] == 0 && visited[nx][ny] == 0) {
                    q.add(new Pair(nx, ny));
                    visited[nx][ny]=1;
                }
            }
        }
    }

}