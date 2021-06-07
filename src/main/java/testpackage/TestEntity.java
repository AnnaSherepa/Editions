package testpackage;


import java.util.HashSet;
import java.util.Set;

public class TestEntity {
    public static void main(String[] args) {
        Set<Integer> uniqueValue = new HashSet<>();
        uniqueValue.add(1);
        if(uniqueValue.contains(1)){
            System.out.println("contains");
        }
        uniqueValue.add(1);
        uniqueValue.add(2);
        uniqueValue.add(5);

        for (Integer integer:
             uniqueValue) {
            System.out.println(integer);
        }
    }

}
