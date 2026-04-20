import java.util.Arrays;

public class MyVector<E> {
    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyVector() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(E element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elementData.length;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity) newCapacity = minCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
            System.out.println("\n[System] Буфер розширено: " + oldCapacity + " -> " + newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size));
    }
}