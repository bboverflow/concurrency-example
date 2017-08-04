package cn.trusteye.concurrency.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) throws IOException {
//        createStreamFromCollection().forEach(System.out::println);
//        createStreamFromValue().forEach(System.out::println);
//        createStreamFromArray().forEach(System.out::println);
//        createStreamFromFile().forEach(System.out::println);
        createStreamFromFun().forEach(System.out::println);
    }
    //由集合创建流
    private static Stream<String> createStreamFromCollection(){
        List<String> list = Arrays.asList("hello", "alex", "wangwenjun", "world", "stream");
        return list.stream();
    }

    //由值创建流
    private static Stream<String> createStreamFromValue(){
        Stream<String> stream = Stream.of("java8", "action", "lambda", "value");
        return stream;
    }

    //由数组创建流
    private static Stream<Integer> createStreamFromArray(){
        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        return Arrays.stream(numbers);
    }

    //文件生成流
    private static Stream<String> createStreamFromFile() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("D:\\IDEA workspace\\concurrency-example\\concurrency-java8\\src\\main\\java\\cn\\trusteye\\concurrency\\lambda\\CreateStream.java"));
        return lines;
    }
    //由函数生成流
/*
    private static Stream<Integer> createStreamFromFun(){
        //迭代
        return Stream.iterate(2,n->n*2).limit(10);
    }
*/
    private static Stream<Double> createStreamFromFun(){
        //生成
        return Stream.generate(Math::random).limit(10);
    }

}
