package com.shenyue.IC50.lamda;

import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by shenyue3 on 2019/8/2.
 */
public class Practice1 {
    public static void main(String[] args){
        List<String> arrays = Arrays.asList("Java","JScala","C++","Haskell");
        Predicate<String> predicate = (n) -> n.startsWith("J");
        arrays.stream().filter(predicate).forEach(s -> System.out.println(s));

        List<String> arrays2 = Arrays.asList("aaa","bbb","ccc");
        List<String> arrays3 = Stream.of(arrays,arrays2).flatMap(Collection::stream).map(t -> t.toUpperCase()).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(arrays3));

        IntStream.range(0,arrays.size()).forEach(i -> System.out.println(i + ": " + arrays.get(i)));

        Map<String,String> arrayMap = IntStream.range(0,arrays2.size()).boxed().collect(Collectors.toMap(i -> arrays2.get(i), i -> arrays.get(i), (String oldValue, String newValue)->oldValue.concat(newValue)));
        System.out.println(JSONObject.toJSONString(arrayMap));

        arrayMap.entrySet().stream().forEach(k -> System.out.println("key: " + k.getKey() + "\tvalue:" + k.getValue()));

        List<Integer> integerList = Arrays.asList(1,2,3);
        System.out.println(integerList.stream().mapToInt(t -> t).sum());
    }
}
