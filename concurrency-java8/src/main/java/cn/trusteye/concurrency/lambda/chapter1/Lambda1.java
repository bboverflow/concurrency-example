package cn.trusteye.concurrency.lambda.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rayman on 2017/7/31.
 */
public class Lambda1 {
    public static void main(String[] args) {
/*
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names,(a,b)->a.compareTo(b));
        System.out.println(names);
*/
        Runnable r1 = () -> System.out.println("Hello world");
        r1.run();
        /**
         *
         */
    }
}
