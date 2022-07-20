package xyz.chaobei.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        // add some element
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // create a new iterator
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);

            // test delete some elements;
            if ("c".equals(next)) {
                iterator.remove();
            }
        }

        System.out.println("2----------");

        for (String str : list) {
            System.out.println(str);
        }


    }

}
