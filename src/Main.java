import com.advance_algorithm.load_balance.IGA;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> result = new HashMap<>();
        for(int i=0;i<100;++i){
            IGA iga = new IGA();
            iga.random = new Random(i);
            int num = iga.run();
            if (result.containsKey(num)) result.put(num, result.get(num)+1);
            else result.put(num,1);
        }
        for(Map.Entry<Integer,Integer> entry:result.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}