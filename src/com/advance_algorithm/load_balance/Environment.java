package com.advance_algorithm.load_balance;

import java.util.Random;

//环境中包含全局随机数对象，以及任务执行时间信息。
public class Environment {
    int[][] time = new int[3][15];
    public Random random = new Random();
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
