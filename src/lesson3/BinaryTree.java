package lesson3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || root == null) return false;
        @SuppressWarnings("unchecked")
        Node<T> it = find((T) o);
        Node<T> parent = findParent(it);
        //Если удаляем лист
        if (it.left == it.right) {
            if (parent != null) {
                if (parent.left == it) parent.left = null;
                else parent.right = null;
            } else root = null;
        }
        //Если удаляем узел с одним дочерним элементом
        else if (it.left == null || it.right == null) {
            if (parent != null) {
                if (it.left != null) {
                    if (parent.left == it) parent.left = it.left;
                    else parent.right = it.left;
                } else {
                    if (parent.left == it) parent.left = it.right;
                    else parent.right = it.right;
                }
            } else {
                if (it.left != null) root = it.left;
                else root = it.right;
            }
        }
        //Если удаляем узел с двумя дочерними элементами
        else {
            Pair<Node<T>, Node<T>> min = findMin(it.right, parent);
            if (parent != null) {
                if (parent.left == it) {
                    parent.left = min.getKey();
                } else parent.right = min.getKey();
            } else root = min.getKey();

            min.getKey().left = it.left;
        }
        size--;
        return true;
    }

    private Pair<Node<T>, Node<T>> findMin(Node<T> node, Node<T> parent) {
        while (node.left != null) {
            parent = node;
            node = node.left;
        }
        return new Pair<>(node, parent);
    }

    private Node<T> findParent(Node<T> node) {
        if (node == null) throw new IllegalArgumentException();
        if (node == root) return null;
        Node<T> parent = root;
        while (parent.left != node && parent.right != node) {
            int compression = node.value.compareTo(parent.value);
            if (compression < 0) parent = parent.left;
            else parent = parent.right;
        }
        return parent;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {
        private Node<T> current;
        private Node<T> next;
        private int counter;
        private boolean back;
        private Deque<Node<T>> innerRoots;

        private BinaryTreeIterator() {
            innerRoots = new ArrayDeque<>();
            next = root;
            counter = size;
        }

        private Node<T> findNext() {
            current = next;
            if (!back && next.left != null) {
                innerRoots.add(next);
                next = next.left;
                return findNext();
            } else if (next.right != null) {
                next = next.right;
                back = false;
            } else {
                next = innerRoots.pollLast();
                back = true;
            }
            counter--;
            return current;
        }

        @Override
        public boolean hasNext() {
            return counter != 0;
        }

        @Override
        public T next() {
            Node<T> it = findNext();
            if (it == null) throw new NoSuchElementException();
            return it.value;
        }

        @Override
        public void remove() {
            BinaryTree.this.remove(current.value);
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
