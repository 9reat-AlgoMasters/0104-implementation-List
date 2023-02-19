package List;

import java.util.Arrays;

public class ArrayList implements List {
    private static final int INIT_CAPACITY = 5;
    static int[] arr;
    static int size;

    public ArrayList() {
        this.arr = new int[INIT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean contains(int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }

        }
        return false;
    }
    
    @Override
    public int get(int index) {
        return arr[index];
    }
    
    @Override
    public void add(int num) {
        // 배열이 사이즈를 넘은 경우
        if (size == arr.length) {
            resize();
        }
        size++;
        // 맨 뒤에 넣어줌
        arr[size-1] = num;
    }
    
    @Override
    public void add(int index, int num) {
        // 배열이 사이즈를 넘은 경우
        if (size == arr.length) {
            resize();
        }


        for (int i = size; i > index; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = num;
        size++;
    }
    
    @Override
    public int remove(int index) {
        int t = arr[index];
        for (int i = index; i < size-1; i++) {
            arr[index] = arr[index + 1];
        }
        size--;
        return t;
    }
    
    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < size; i++) {
            arr[i] = 0;
        }
        resize();
    }

    public void resize() {
        arr = Arrays.copyOf(arr,size*2);
//        size *= 2;
        if (size == 0) {
            arr = new int[INIT_CAPACITY];
        }
    }
}
