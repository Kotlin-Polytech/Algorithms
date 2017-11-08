package lesson3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class BinaryTreeStudentTest {
    private BinaryTree<Integer> binaryTree = new BinaryTree<>();
    private List<Integer> list = Arrays.asList(7, 4, 2, 6, 5, 1, 8, 10, 18, 72);
    private List<Integer> testList = new ArrayList<>();

    @Before
    public void preparation() {
//        Collections.shuffle(list);
        binaryTree.addAll(list);
        Collections.sort(list);
    }

    @Test
    public void testIterator() {
        binaryTree.iterator().forEachRemaining(testList::add);
        Collections.sort(testList);
        assertArrayEquals(list.toArray(), testList.toArray());
    }
}
