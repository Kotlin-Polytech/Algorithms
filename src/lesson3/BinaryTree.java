package lesson3;

import org.jetbrains.annotations.NotNull;

import java.util.*;

@SuppressWarnings("WeakerAccess")
public class BinaryTree<T> extends AbstractSet<T> {

    private final Comparator<T> comparator;
    private Node<T> root = null;
    private int size = 0;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinaryTree() {
        this(null);
    }

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : compare(t, closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
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
        if (left != null && (compare(left.value, node.value) >= 0 || !checkInvariant(left))) return false;

        Node<T> right = node.right;
        return right == null || compare(right.value, node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && compare(t, closest.value) == 0;
    }

    private int compare(T o1, T o2) {
        @SuppressWarnings("unchecked")
        Comparable<T> co1 = (Comparable<T>) o1;
        return comparator == null ? co1.compareTo(o2) : comparator.compare(o1, o2);
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = compare(value, start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
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

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;
        private Deque<Node<T>> stack = new ArrayDeque<>();

        private BinaryTreeIterator() {
            Node<T> node = root;
            fillTheStack(node);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        private Node<T> findNext() {
            Node<T> node = current == null ? null : current.right;
            fillTheStack(node);
            try {
                return stack.pop();
            } catch (NoSuchElementException e) {
                return null;
            }
        }

        private void fillTheStack(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

    }

}
