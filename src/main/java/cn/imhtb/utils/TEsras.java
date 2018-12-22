package cn.imhtb.utils;

import java.util.Scanner;

public class TEsras {
    public static void main(String[] args) {
        int n;
        Integer[] x1,y1,x2,y2,x3,y3;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        x1 = new Integer[n];
        y1 = new Integer[n];
        for (int i = 0; i < n; i++) {
            x1[i] = sc.nextInt();
            y1[i] = sc.nextInt();
        }
        System.out.println();
    }
}
