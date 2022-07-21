package xyz.chaobei.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {


    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // ListIterator
        Iterator iterator = list.iterator();
        ListIterator<String> listIterator = list.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        list.get(0);

    }

}
