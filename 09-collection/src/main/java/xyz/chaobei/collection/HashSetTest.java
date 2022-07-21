package xyz.chaobei.collection;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {


    public static void main(String[] args) {

        HashSet<String> hashSet = new HashSet<>();

        hashSet.add("a");
        hashSet.add("a");
        // same element is avoided

        hashSet.add("1");
        hashSet.add("c");
        hashSet.add("2");

        Iterator<String> stringIterator = hashSet.iterator();

        while (stringIterator.hasNext()) {
            // output a,1,2,c
            System.out.println(stringIterator.next());
        }


    }

}
