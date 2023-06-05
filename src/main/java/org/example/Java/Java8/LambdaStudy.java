package org.example.Java.Java8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author liushixing
 * @date 2023/4/26 10:28
 */
public class LambdaStudy {

    interface Add{
        int add(int a,int b);
    }

    private String test(int a,int b,Add add){
        return String.valueOf(add.add(a,b));
    }

    public static void main(String[] args) {

        LambdaStudy lambdaStudy = new LambdaStudy();
        Add add = (a,b) -> {
            return a + b;
        };
        System.out.println("1 + 2 = " + lambdaStudy.test(1,2,add));

        System.out.println("===========================================");

        /**
         * map() 方法：将流的元素映射为另一种类型
         * 打成流式对象 => 元素操作（映射、过滤、） => 收集为指定数据结构
         */
        List<Integer> numbers = Lists.newArrayList(1,2,3,4,5);
        List<Integer> squares = numbers.stream().map(x -> x * x).collect(Collectors.toList());

        /**
         *
         * filter() 方法：从流中过滤出符合条件的元素
         */
        List<String> names = Lists.newArrayList("Java","Javascript","C++");
        List<String> filter = names.stream().filter(y -> y.startsWith("Java")).collect(Collectors.toList());

        /**
         * reduce() 方法：对流进行规约操作并返回其值
         */
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
        int sum = list.stream().reduce(0,(a,b) -> (a + b));
        System.out.println(sum);
    }

}
