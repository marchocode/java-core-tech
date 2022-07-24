package xyz.chaobei.collection;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {

    public static void main(String[] args) {

        LinkedHashMap<Key, Integer> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(new Key("alibaba"), 10);
        linkedHashMap.put(new Key("baidu"), 20);

    }


}
