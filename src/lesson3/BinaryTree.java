package lesson3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node<T> parent = null;

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
            newNode.parent = closest;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
            newNode.parent = closest;
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
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        Node<T> node = find((T) o);
        iterator.current = node;
        if (node == null || node.value != o) return false;
        try {
            Node<T> parent = node.parent;
            if (node.left == null && node.right == null) {  //элемент-лист
                if (parent.left == node) {
                    parent.left = null;
                }
                if (parent.right == node) {
                    parent.right = null;
                }
            } else if ((node.left == null || node.right == null)) {
                if (node.left == null) {
                    if (node != root) {
                        if (parent.left == node) {
                            parent.left = node.right;
                        } else {
                            parent.right = node.right;
                            node.right.parent = parent;
                        }
                    } else {
                        root = node.right;
                        node.right = null;
                    }
                } else {
                    if (node != root) {
                        if (parent.left == node)
                            parent.left = node.left;
                        else {
                            parent.right = node.left;
                            node.left.parent = parent;
                        }
                    } else {
                        root = node.left;
                        node.left = null;
                    }
                }
            } else {
                Node<T> successor = iterator.findNext();
                if (node != root) {
                    node.value = successor.value;

                    if (successor.parent.left == successor) {
                        successor.parent.left = successor.right;
                        if (successor.right != null)
                            successor.right.parent = successor.parent;
                    } else {
                        successor.parent.right = successor.right;
                        if (successor.right != null)
                            successor.right.parent = successor.parent;
                    }
                } else {
                    root.value = successor.value;
                    root.left = node.left;
                    root.right = node.right;

                    if (successor.parent.left == successor) {
                        successor.parent.left = successor.right;
                        if (successor.right != null)
                            successor.right.parent = successor.parent;
                    } else {
                        successor.parent.right = successor.right;
                        if (successor.right != null)
                            successor.right.parent = successor.parent;
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
        size--;
        return true;
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

        private BinaryTreeIterator() {}

        private Node<T> findNext() {
            Node<T> cur = root;
            if (current != null)
                while (cur != null) {
                    if (cur.value.compareTo(current.value) > 0) {
                        next = cur;
                        cur = cur.left;
                    } else
                        cur = cur.right;
                }
            else return null;
            return next;
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
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
