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
    public void testByMikhailIgorevich() {
        List<Integer> list = Arrays.asList(26, 41, 31, 29, 28, 86, 87, 37, 97, 70, 97, 6, 57, 98, 35, 95, 43, 70, 48, 62);
        binaryTree.addAll(list);
        Collections.sort(list);
        binaryTree.remove(31);
        Iterator<Integer> iterator = binaryTree.iterator();
        assertTrue(binaryTree.checkInvariant());
        assertFalse(binaryTree.contains(31));
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Test
    public void removeInRandomTreeByIterator() {
        for (int i = 0; i < 1000; i++) {
            assertTrue(forRemoveInRandomTreeByIterator());
        }
    }

    private boolean forRemoveInRandomTreeByIterator() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        while (binaryTree.size() != 10) {
            int next = random.nextInt(1000);
            if (binaryTree.add(next)) list.add(next);
        }

        Iterator<Integer> iterator = binaryTree.iterator();
        int randomIndex = random.nextInt(10);
        try {
            while (iterator.hasNext()) {
                int it = iterator.next();
                if (it == list.get(randomIndex)) iterator.remove();
            }
        } catch (NullPointerException e) {
            System.out.println(list);
            System.out.println("Пытались удалить: " + list.get(randomIndex));
            e.printStackTrace();
            return false;
        }
        assertFalse(binaryTree.contains(list.get(randomIndex)));
        assertTrue(binaryTree.checkInvariant());
        assertTrue(binaryTree.size() == 9);
        return true;
    }

    @Test
    public void removeInRandomTree() {
        for (int i = 0; i < 1000; i++) {
            assertTrue(forRemoveInRandomTree());
        }
    }

    private boolean forRemoveInRandomTree() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        //Заполняем дерево 50 случайными числами от 0 до 1000
        while (binaryTree.size() != 10) {
            int next = random.nextInt(1000);
            if (binaryTree.add(next)) list.add(next);
        }
        //Удаляем из дерева случайное число
        int removeIt = list.get(random.nextInt(10));
        assertTrue(binaryTree.remove(removeIt));
        Iterator<Integer> iterator = binaryTree.iterator();
        int x = -1;
        try {
            while (iterator.hasNext()) {
                x = iterator.next();
            }
        } catch (Exception e) {
            System.out.println(list);
            System.out.println("Последний элемент до ошибки: " + x);
            System.out.println("Пытались удалить: " + removeIt);
            e.printStackTrace();
            return false;
        }
        assertTrue(binaryTree.checkInvariant());
        return true;
    }
}
