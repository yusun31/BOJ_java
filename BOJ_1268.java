import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int student_no = sc.nextInt();
        int[][] group = new int[student_no][5];
        for(int i = 0; i < student_no; i++) {
            for(int j = 0; j < 5; j++) {
                group[i][j] = sc.nextInt();
            }
        }

        int[][] class_no = new int[student_no][student_no];
        for(int k = 0; k < student_no; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < student_no; j++) {
                    if (group[j][i] == group[k][i] && j != k) {
                        class_no[k][j] = 1;
                    }
                }
            }
        }

        int answer = 0;
        int max = 0;

        for(int i = 0; i < student_no; i++) {
            int count = 0;
            for (int j = 0; j < student_no; j++) {
                if(class_no[i][j] == 1) {
                    count++;
                }
            }
            if(count > max) {
                max = count;
                answer = i + 1;
            }
        }

        if(answer == 0) {
            answer = 1;
        }

        System.out.println(answer);
    }
}