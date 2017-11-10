package lesson3;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BinaryTreeStudentTest {
    private BinaryTree<Integer> binaryTree = new BinaryTree<>();
    private List<Integer> list = Arrays.asList(7, 4, 2, 6, 5, 1, 8, 10, 18, 72);
    private List<Integer> testList = new ArrayList<>();

    @Before
    public void preparation() {
        testList.clear();
    }

    @Test
    public void testIterator() {
        Collections.shuffle(list);
        binaryTree.addAll(list);
        binaryTree.iterator().forEachRemaining(testList::add);
        Collections.sort(list);
        assertArrayEquals(list.toArray(), testList.toArray());
    }

    @Test
    public void removeLeaf() {
        binaryTree.addAll(list);
        Iterator<Integer> iterator = binaryTree.iterator();
        while (iterator.hasNext()) {
            int it = iterator.next();
            if (it == 1) iterator.remove();
            testList.add(it);
        }
        Collections.sort(list);
        assertArrayEquals(testList.toArray(), list.toArray());
        assertFalse(binaryTree.contains(1));
        assertTrue(binaryTree.checkInvariant());
    }

    @Test
    public void removeNodeWithOneChild() {
        binaryTree.addAll(list);
        Iterator<Integer> iterator = binaryTree.iterator();
        while (iterator.hasNext()) {
            int it = iterator.next();
            if (it == 8) iterator.remove();
            testList.add(it);
        }
        Collections.sort(list);
        assertArrayEquals(testList.toArray(), list.toArray());
        assertFalse(binaryTree.contains(8));
        assertTrue(binaryTree.checkInvariant());
    }

    @Test
    public void removeNodeWithTwoChildren() {
        binaryTree.addAll(list);
        int sizeBeforeRemoving = binaryTree.size();
        Iterator<Integer> iterator = binaryTree.iterator();
        while (iterator.hasNext()) {
            int it = iterator.next();
            if (it == 7) iterator.remove();
        }
        assertFalse(binaryTree.contains(7));
        assertTrue(binaryTree.checkInvariant());
        assertTrue(binaryTree.size() == sizeBeforeRemoving - 1);
    }

    @Test
    public void removeInRandomTree() {
        Collections.shuffle(list);
        binaryTree.addAll(list);
        int sizeBeforeRemoving = binaryTree.size();
        Iterator<Integer> iterator = binaryTree.iterator();
        int randomIndex = new Random().nextInt(list.size() - 1);
        while (iterator.hasNext()) {
            int it = iterator.next();
            if (it == list.get(randomIndex)) iterator.remove();
        }
        assertFalse(binaryTree.contains(list.get(randomIndex)));
        assertTrue(binaryTree.checkInvariant());
        assertTrue(binaryTree.size() == sizeBeforeRemoving - 1);
    }
}
