package xyz.chaobei.collection;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTest {


    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // ListIterator
        Iterator iterator = list.iterator();
        System.out.println(iterator.next());

    }

}
