package org.acme.functional;

import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    interface MyNum {
        double getValue();
    }

    final static List<Integer> prices = Arrays.asList(10, 30, 17, 20, 18, 45, 12);

    static double oldDiscount(int countPrice) {
        double totaldicount = 0.0;
        for (int price : prices) {
            if (price > countPrice) {
                totaldicount += price * 0.9;
            }
        }
        return totaldicount;
    }


    public static double funcDis(int dis){
        return prices.stream().filter(p->p>dis).mapToDouble(p->p*0.9).sum();
    }

    public static void main(String[] args) {
        int dis = 20;
        double oldD = oldDiscount(dis);
        System.out.println(oldD);
       double adds = prices.stream().filter(p -> p > dis).mapToDouble(p -> p * 0.9).sum();

        System.out.println(adds);

        System.out.println(funcDis(dis));


//        List<String> cities = Arrays.asList("seoul", "dajeon", "busan", "incheon", "kimhae", "chunhu");


//        System.out.println("Here is ~ " + cities.contains("dajeon"));
//
//        Random randon = new Random();
//        MyNum myNum;
//        myNum = () -> 12 + randon.nextDouble(3222);
//        double a = randon.nextDouble(131);
//        System.out.println(myNum.getValue());
//        System.out.println(myNum.getValue());
//
    }
}





