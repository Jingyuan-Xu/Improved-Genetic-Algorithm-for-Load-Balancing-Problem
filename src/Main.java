import com.advance_algorithm.load_balance.IGA;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
//程序入口
public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> result = new HashMap<>();
        //100次独立实验
        for(int i=0;i<100;++i){
            IGA iga = new IGA();
            //确定随机数种子，方便复现实验结果
            iga.random = new Random(i*100);
            int num = iga.run();
            if (result.containsKey(num)) result.put(num, result.get(num)+1);
            else result.put(num,1);
        }
        for(Map.Entry<Integer,Integer> entry:result.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}