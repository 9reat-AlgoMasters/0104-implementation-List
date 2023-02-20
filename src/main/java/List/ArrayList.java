package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

public class ArrayList implements List {
    private static final  int INIT_CAPACITY = 5;
    private int size=0;
    private int[] elements = new int[INIT_CAPACITY];
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(int num) {
        for (int i = 0; i < size; i++) {
            if(elements[i] == num){
                return  true;
            }
        }
        return false;
    }

    @Override
    public int get(int index) throws CustomIndexOutOfBoundsException{
        if(index>=size || index<0){
            throw new CustomIndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public void add(int num) {
        int [] temp = new int[size+1];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        temp[size] = num;
        elements = temp;
        size +=1;
    }

    @Override
    public void add(int index, int num) throws CustomIndexOutOfBoundsException{
        if(index>size || index<0){
            throw new CustomIndexOutOfBoundsException();
        }

        int [] temp = new int[size+1];

        for (int i = 0; i < index; i++) {
            temp[i] = elements[i];
        }
        temp[index] = num;
        for (int i = index; i < elements.length; i++) {
            temp[i+1] = elements[i];
        }
        elements = temp;
        size +=1;
    }

    @Override
    public int remove(int index) throws CustomIndexOutOfBoundsException{
        if(index>=size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }

        int [] temp = new int[size-1];
        int e;
        for (int i = 0; i < index; i++) {
            temp[i] = elements[i];
        }
        e = elements[index];
        for (int i = index+1; i < size; i++) {
            temp[i-1] = elements[i];
        }
        elements = temp;
        size -=1;
        return e;
    }

    @Override
    public void clear() {
        elements = new int[INIT_CAPACITY];
        size = 0;
    }
}
