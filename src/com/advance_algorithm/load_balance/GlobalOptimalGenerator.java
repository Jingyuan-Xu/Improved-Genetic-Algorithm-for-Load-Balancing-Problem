package com.advance_algorithm.load_balance;

import java.util.*;

public class GlobalOptimalGenerator extends Environment{
    static int min = Integer.MAX_VALUE;
    static List<Integer> best = null;
    public static void main(String[] args) {
//        Stack<Pair> stack = new Stack<>();
//        Pair start = new Pair(-1, -1);
//        stack.push(start);
//        List<Integer> types = new ArrayList<>();
//        int min = Integer.MAX_VALUE;
//        List<Integer> best = null;
//        boolean
//        while (!stack.isEmpty()){
//            Pair p = stack.pop();
//            if(p.x!=-1) types.add(p.y);
//            if(p.x==14) {
//                int fitness = evaluate(types);
//                if (fitness < min){
//                    min = fitness;
//                    best = types;
//                    types = new ArrayList<>(types);
//                }
//            }else {
//                stack.push(new Pair(p.x+1,0));
//                stack.push(new Pair(p.x+1,1));
//                stack.push(new Pair(p.x+1,2));
//            }
//        }
        search(-1, new ArrayList<>());
        System.out.println(min);
        System.out.println(Arrays.toString(best.toArray()));

    }

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
