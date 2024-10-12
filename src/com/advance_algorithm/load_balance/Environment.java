package com.advance_algorithm.load_balance;

import java.util.Random;

public class Environment {
    int[][] time = new int[3][15];
    Random random = new Random();
    public Environment() {
        // init time array
        for (int i = 0; i < 15; i++) {
            time[0][i] = i + 1;
            time[1][i] = 2 * (i + 1);
            if(i<10) time[2][i] = 3 * (i + 1);
            else time[2][i] = 15;
        }
    }
}
