package com.advance_algorithm.load_balance;

//一个染色体，或者称为种群中的一个个体
public class Chrome extends Environment {
    public final int[] task2Ins;
    public int fitness = 0;
    public int time1 = 0;
    public int time2 = 0;
    public int time3 = 0;
    public Chrome(int[] task2Ins) {
        this.task2Ins = task2Ins;
        fitnessCalculate();
    }

    //私有方法，所有生成的新个体必须被深拷贝，不能基于已有个体修改，所以该方法只在构造染色体时使用。
    private void fitnessCalculate(){
        int time1 = 0;
        int time2 = 0;
        int time3 = 0;
        for (int i = 0; i < 15; i++) {
            int type = task2Ins[i];
            if(type == 0) time1+=time[0][i];
            if(type == 1) time2+=time[1][i];
            if(type == 2) time3+=time[2][i];
        }
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        fitness = Math.max(time1, time2);
        fitness = Math.max(fitness, time3);
    }
}
