package com.advance_algorithm.load_balance;

import java.util.*;

/**
 * 该类用于生成最优解，最优解用于测试算法性能
 */
public class GlobalOptimalGenerator extends Environment{
    static int min = Integer.MAX_VALUE;
    static List<Integer> best = null;
    public static void main(String[] args) {
        search(-1, new ArrayList<>());
        System.out.println(min);
        System.out.println(Arrays.toString(best.toArray()));
    }

    //回溯法最优解生成器
    public static void search(int i, List<Integer> type){
        if(i==14){
            int fitness = evaluate(type);
            if (fitness < min){
                min = fitness;
                best = new ArrayList<>(type);
            }
            return;
        }
        type.add(0);
        search(i+1,type);
        type.remove(type.size()-1);
        type.add(1);
        search(i+1,type);
        type.remove(type.size()-1);
        type.add(2);
        search(i+1,type);
        type.remove(type.size()-1);
    }

    public static int evaluate(List<Integer> list){
        Environment environment = new Environment();
        int time1 = 0;
        int time2 = 0;
        int time3 = 0;
        int fitness;
        for (int i = 0; i < 15; i++) {
            int type = list.get(i);
            if(type == 0) time1+=environment.time[0][i];
            if(type == 1) time2+=environment.time[1][i];
            if(type == 2) time3+=environment.time[2][i];
        }
        fitness = Math.max(time1, time2);
        fitness = Math.max(fitness, time3);
        return fitness;
    }
}
