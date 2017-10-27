package lesson3;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class BinaryTreeStudentTest {
    BinaryTree<Integer> binaryTree = new BinaryTree<>();
    List<Integer> list = Arrays.asList(7,4,2,6,5,1,8,5,2,7,10,18,72);

    @Before
    public void filling() {
        //Collections.shuffle(list);
        binaryTree.addAll(list);
    }

    @Test()
    public void testIterator() {
        Iterator<Integer> iterator = binaryTree.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
            System.out.println("------------------------");
    }
}
