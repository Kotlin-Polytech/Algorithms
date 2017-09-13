package lesson3;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @Test
    public void add1() {
        BinaryTree<Integer> tree = new BinaryTree<>(Integer::compareTo);
        testAdd(tree);
    }

    @Test
    public void add2() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        testAdd(tree);
    }

    private void testAdd(BinaryTree<Integer> tree) {
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(10);
        assertEquals(3, tree.size());
        assertTrue(tree.contains(5));
        tree.add(3);
        tree.add(1);
        tree.add(3);
        tree.add(4);
        assertEquals(6, tree.size());
        assertFalse(tree.contains(8));
        tree.add(8);
        tree.add(15);
        tree.add(15);
        tree.add(20);
        assertEquals(9, tree.size());
        assertTrue(tree.contains(8));
        assertTrue(tree.checkInvariant());
    }

    @Test
    public void testIterator() {
        for (int i = 0; i < 10; ++i) {
            Set<Integer> expected = new TreeSet<>();
            Set<Integer> actual = new BinaryTree<>();
            for (int j = 0; j < 100_000; ++j) {
                int elem = RANDOM.nextInt(100_000);
                expected.add(elem);
                actual.add(elem);
            }
            Iterator<Integer> actualIterator = actual.iterator();
            Iterator<Integer> expectedIterator = expected.iterator();
            while (expectedIterator.hasNext()) {
                assertEquals(expectedIterator.next(), actualIterator.next());
            }
        }
    }

}