package lesson3;

@SuppressWarnings("WeakerAccess")
public interface RBNode<T> {

    T getValue();
    default boolean isRed() {
        return true;
    }

    RBNode<T> getLeft();
    RBNode<T> getRight();

    // Optional
    void setLeft(RBNode<T> left);
    void setRight(RBNode<T> right);
    void setColor(boolean isRed);
}
