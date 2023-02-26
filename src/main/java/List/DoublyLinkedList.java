package List;

import exceptions.CustomIndexOutOfBoundsException;

class DNode{
    private int data;
    public DNode lNode;
    public DNode rNode;
    public DNode(){}
    public DNode(int data){
        this.data = data;
        this.lNode = null;
        this.rNode = null;
    }
    public DNode(DNode lNode, int data, DNode rNode){
        this.lNode = lNode;
        this.data = data;
        this.rNode = rNode;
    }
    public int getData() {
        return data;
    }
}
public class DoublyLinkedList implements List {
    private int size;
    private DNode head;
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        if(size ==0){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean contains(int num) {
        DNode n = head;
        while (!(n == null)){
            if(num == n.getData()){
                return true;
            }else{
                n = n.rNode;
            }
        }
        return false;
    }
    
    @Override
    public int get(int index) {
        if (index>=size || index<0){
            throw new CustomIndexOutOfBoundsException();
        }
        DNode n = head;
        for (int i = 0; i < index; i++) {
            n = n.rNode;
        }
        return n.getData();
    }
    
    public int getFirst() {
        return 0;
    }
    
    public int getLast() {
        return 0;
    }
    
    @Override
    public void add(int num) {
    
    }
    
    @Override
    public void add(int index, int num) {
    
    }
    
    public void addFirst(int num) {
    
    }
    
    @Override
    public int remove(int index) {
        return 0;
    }
    
    public int removeFirst() {
        return 0;
    }
    
    public int removeLast() {
        return 0;
    }
    
    @Override
    public void clear() {
    
    }
}
