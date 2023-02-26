package List;

import exceptions.CustomIndexOutOfBoundsException;

import java.util.Arrays;

public class ArrayList implements List {
    private int[] arr;
    private int size;
    public ArrayList() {
        this.arr = new int[0];
        this.size = 0;
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
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int get(int index) {

        if (index >= this.size || this.size == 0 || index < 0) {
            throw new CustomIndexOutOfBoundsException();
        } else {
            return arr[index];
        }
    }

    @Override
    public void add(int num) {
        // 새로운 배열을 만들고 사이즈를 1 더해준 다음, 추가 하기 전의 배열을 복사하고, num을 맨 뒤에 넣고 깊은 배열로 복사하여 멤버 변수 변경
        int[] tmp = new int[this.size+1];
        if (this.size >= 0) System.arraycopy(this.arr, 0, tmp, 0, this.size);
        tmp[this.size] = num;
        this.arr = Arrays.copyOf(tmp, this.size + 1);
        this.size++;

    }

    @Override
    public void add(int index, int num) {
        if (index >= this.size || this.size == 0 || index < 0) {
            throw new CustomIndexOutOfBoundsException();
        }else {
            int[] tmp = new int[this.size + 1];

            System.arraycopy(this.arr, 0, tmp, 0, this.size);
            // 기존 배열의 크기의 1만큼 더 큰 배열을 만들고, 맨 뒤 요소부터 접근하여 땡겨줌. 만약 추가하고자 하는 index라면 그 곳에 num을 넣고 return.
            for (int i = this.size; i > 0; i--) {
                if (i == index) {
                    tmp[i] = num;
                    this.arr = Arrays.copyOf(tmp, this.size + 1);
                    this.size++;
                    return;
                }
                tmp[i] = tmp[i - 1];
            }
        }

    }

    @Override
    public int remove(int index) {
        // 기존 배열의 크기의 -1만큼의 배열을 생성하고, 순차탐색하며 index를 찾기 전까진 그대로 복사, 찾고 나서는 한 칸 뒤를 복사함으로써 배열의 크기를 -1만큼 줄임.

        if (index >= this.size || this.size == 0 || index < 0) {
            throw new CustomIndexOutOfBoundsException();
        }
        else {
            int num = this.arr[index];

            boolean check = false;
            int[] tmp = new int[this.size - 1];
            for (int i = 0; i < this.size - 1; i++) {
                if (i == index) {
                    num = this.arr[i];
                    check = true;
                }
                if (!check) {
                    tmp[i] = this.arr[i];
                } else {
                    tmp[i] = this.arr[i + 1];
                }
            }
            this.arr = Arrays.copyOf(tmp, this.size - 1);
            this.size--;
            return num;
        }
    }

    @Override
    public void clear() {
        this.arr = new int[0];
        this.size = 0;
    }
}
