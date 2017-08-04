package cn.trusteye.concurrency.java8.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {
/*
    //办法1 笨办法
    public static List<Apple> findGreenApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findRedApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("red".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }
*/

/*
    //办法2 函数价参数
    static List<Apple> findApple(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }
*/


    @FunctionalInterface
    //办法3 策略模式
    interface AppleFilter {
        Boolean filter(Apple apple);
    }

    class GreenFilter implements AppleFilter {
        @Override
        public Boolean filter(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }


    static class GreenWeight150Filter implements AppleFilter {
        @Override
        public Boolean filter(Apple apple) {
            return ("green".equals(apple.getColor()) && apple.getWeight() > 150);
        }
    }

    static List<Apple> findApple(List<Apple> apples,AppleFilter appleFilter){
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 100), new Apple("red", 100), new Apple("green", 170));
/*
        //办法1
        List<Apple> greenApples = findGreenApple(apples);
        System.out.println(greenApples);
        List<Apple> redApples = findRedApple(apples);
        System.out.println(redApples);
*/
/*
        //办法2
        List<Apple> greenApples = findApple(apples, "green");
        System.out.println(greenApples);
*/

/*
        //办法3
        List<Apple> complexApples = filterApple(apples, new GreenWeight150Filter());
        System.out.println(complexApples);
*/

/*
        //办法4 匿名内部类
        List<Apple> filterApple = findApple(apples, new AppleFilter() {
            @Override
            public Boolean filter(Apple apple) {
                return apple.getColor().equals("red");
            }
        });
        System.out.println(filterApple);
*/

        //办法5 lambda表达式
        List<Apple> filterApple = findApple(apples, (Apple apple) -> {
            return apple.getColor().equals("green");
        });
        System.out.println(filterApple);
    }
}



