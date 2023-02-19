package List;

import exceptions.CustomIndexOutOfBoundsException;

public class ArrayList implements List {
    private int[] array;
    private static final int INIT_CAPACITY = 5;
    private int capacity;
    private int size;

    public ArrayList() {
        array =  new int[INIT_CAPACITY];
        capacity = INIT_CAPACITY;
    }

    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    @Override
    public boolean contains(int num) {
        for (int i = 0; i < this.size; i++) {
            if(array[i] == num){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int get(int index) throws CustomIndexOutOfBoundsException {
        if(index >= size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }
        return array[index];
    }
    
    @Override
    public void add(int num) {
        if(size == capacity){
            int[] temp = new int[size];
            System.arraycopy(array, 0, temp, 0, size);
            capacity *= 2;
            array = new int[capacity];
            System.arraycopy(temp, 0, array, 0, size);
        }
        array[size++] = num;
    }
    
    @Override
    public void add(int index, int num) {
        if(index > size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }
        if(size == capacity){
            int[] temp = new int[size];
            System.arraycopy(array, 0, temp, 0, size);
            capacity *= 2;
            array = new int[capacity];
            System.arraycopy(temp, 0, array, 0, size);
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = num;
        size++;
    }
    
    @Override
    public int remove(int index) throws CustomIndexOutOfBoundsException{
        if(index >= size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }
        int value = array[index];
        for (int i = index; i < size-1; i++) {
            array[i] = array[i+1];
        }
        size--;
        return value;
    }
    
    @Override
    public void clear() {
        size = 0;
        array = new int[capacity];
    }
}
