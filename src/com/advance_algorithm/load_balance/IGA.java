package com.advance_algorithm.load_balance;

import java.util.*;

public class IGA extends Environment{
    //种群规模
    int population_size = 200;
    //变异率
    double mut_rate = 0.1;
    //最大迭代代数
    int max_generation = 1000;

    //算法入口
    public int run(){
        List<Chrome> pop = initPop();
        for(int i=0;i<max_generation;++i){
            //epsilon==intensity 控制交叉强度
            double intensity = 0.5 + (2.0 - 15) / (2.0 * 15 * max_generation);
            List<Chrome> children = reproduce(pop, intensity);
            //合并父代与子代种群
            List<Chrome> all = new ArrayList<>();
            all.addAll(pop);
            all.addAll(children);
            all.sort(Comparator.comparingInt(o -> o.fitness));
            pop.clear();
            //二元锦标赛选择
            for (int j=0;j<population_size;++j){
                //抽取两个不同的个体
                int index = random.nextInt(all.size());
                Chrome chrome1 = all.get(index);
                all.remove(index);
                index = random.nextInt(all.size());
                Chrome chrome2 = all.get(index);
                if (chrome1.fitness < chrome2.fitness) pop.add(chrome1);
                else pop.add(chrome2);
            }
        }
        return pop.get(0).fitness;
//        System.out.println(Arrays.toString(pop.get(0).task2Ins));
    }

    //初始化种群操作
    public List<Chrome> initPop(){
        List<Chrome> pop = new ArrayList<>();
        for (int i=0;i<population_size;++i){
            int[] task2Ins = new int[15];
            //这里生成随机的task序列
            for(int j=0;j<15;++j){
                task2Ins[j] = random.nextInt(3);
            }
            Chrome chrome = new Chrome(task2Ins);
            pop.add(chrome);
        }
        return pop;
    }

    //产生子种群
    public List<Chrome> reproduce(List<Chrome> fa, double intensity){
        List<Chrome> children = new ArrayList<>();
        //子种群生成
        while (children.size()<population_size){
            Chrome c1 = null;
            Chrome c2 = null;
            //选取父个体
            while (c1==c2) {
                c1 = fa.get(random.nextInt(fa.size()));
                c2 = fa.get(random.nextInt(fa.size()));
            }
            //交叉
            List<Chrome> childPair = crossover(c1, c2, intensity);
            //变异
            if(random.nextDouble()<mut_rate){
                c1 = mutate(childPair.get(0));
                c2 = mutate(childPair.get(1));
            }
            children.add(c1);
            children.add(c2);
        }
        return children;
    }

    //交叉操作
    public List<Chrome> crossover(Chrome c1, Chrome c2, double intensity){
        int[] task2Ins1 = c1.task2Ins;
        int[] task2Ins2 = c2.task2Ins;
        int[] child1 = new int[15];
        int[] child2 = new int[15];
        for(int i=0;i<15;++i){
            if(random.nextDouble() < intensity){
                child1[i] = task2Ins2[i];
                child2[i] = task2Ins1[i];
            }else {
                child1[i] = task2Ins1[i];
                child2[i] = task2Ins2[i];
            }
        }
        List<Chrome> pair = new ArrayList<>();
        pair.add(new Chrome(child1));
        pair.add(new Chrome(child2));
        return pair;
    }

    public Chrome mutate(Chrome c){
        int[] child = c.task2Ins.clone();
        int i = random.nextInt(15);
        int j = random.nextInt(15);
//        while (i==j){
//            j = random.nextInt(15);
//        }
        if(random.nextDouble()<0.5) {
            int temp = child[i];
            child[i] = child[j];
            child[j] = temp;
        }else {
            int l = Math.min(i,j);
            int r = Math.max(i,j);
            while (l<r){
                int temp = child[l];
                child[l] = child[r];
                child[r] = temp;
                l++;
                r--;
            }
        }
        return new Chrome(child);
    }
}
