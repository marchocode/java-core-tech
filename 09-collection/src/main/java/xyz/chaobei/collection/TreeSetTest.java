package xyz.chaobei.collection;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {


    public static void main(String[] args) {

        TreeSet<String> treeSet = new TreeSet<>();

        // add some elements
        treeSet.add("a");
        treeSet.add("d");
        treeSet.add("c");
        treeSet.add("1");
        treeSet.add("b");
        treeSet.add("2");

        Iterator<String> iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            // output 1,2,a,b,c,d
            System.out.println(iterator.next());
        }


    }
}
