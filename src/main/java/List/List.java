package List;

public interface List {
    int size();
    boolean isEmpty();
    boolean contains(int num);
    int get(int index);
    void add(int num);
    void add(int index, int num);
    int remove(int index);
    void clear();
}