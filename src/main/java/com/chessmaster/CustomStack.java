package main.java.com.chessmaster;

public class CustomStack<T> {

    private T[] array;
    private int lastIndex;

    public CustomStack() {
        this.lastIndex = -1;
        this.array = (T[]) new Object[100];
    }

    public T pop() {
        T obj = null;
        if (lastIndex >= 0) {
            obj = array[lastIndex];
            lastIndex--;
        }

        return obj;
    }

    public void push(T objectToPush) {
        lastIndex++;
        array[lastIndex] = objectToPush;
    }

    public T peek() {
        T obj = null;
        if (lastIndex >= 0) {
            obj = array[lastIndex];
        }

        return obj;
    }

    public int size() {
        return lastIndex + 1;
    }

    public boolean isEmpty() {
        return lastIndex == -1;
    }


}
