package List;

import exceptions.CustomIndexOutOfBoundsException;

import java.util.Arrays;

public class ArrayList implements List {
    private static final int INIT_CAPACITY = 5;
    
    private int[] arr;
    private int size;
    
    public ArrayList() {
        arr = new int[INIT_CAPACITY];
        size = 0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean contains(int num) {
        for (int i=0; i<size; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int get(int index) {
        if (isInRange(index)) {
            return arr[index];
        }
        throw new CustomIndexOutOfBoundsException("올바르지 않은 인덱스입니다.");
    }
    
    @Override
    public void add(int num) {
        checkArraySize();
        arr[size++] = num;
    }
    
    @Override
    public void add(int index, int num) {
        if (index < 0 || index > size) {
            throw new CustomIndexOutOfBoundsException("올바르지 않은 인덱스입니다.");
        }
        checkArraySize();
        System.arraycopy(arr, index, arr, index+1, size - index);
        arr[index] = num;
        size++;
    }
    
    @Override
    public int remove(int index) {
        if (isInRange(index)) {
            int removedValue = arr[index];
            System.arraycopy(arr, index+1, arr, index, size - index-1);
            size--;
            return removedValue;
        }
        throw new CustomIndexOutOfBoundsException("올바르지 않은 인덱스입니다.");
    }
    
    @Override
    public void clear() {
        arr = new int[INIT_CAPACITY];
        size = 0;
    }
    
    private void checkArraySize() {
        if (size == arr.length) {
            arr = increaseCapacity();
        }
    }
    
    private int[] increaseCapacity() {
        int curCapacity = arr.length;
        int newCapacity = curCapacity + (curCapacity >> 1);
        return Arrays.copyOf(arr, newCapacity);
    }
    
    private boolean isInRange(int index) {
        return index >= 0 && index < size;
    }
}
