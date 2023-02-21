package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

class Node{
    private int data;
    public Node next;
    public Node(){}
    public Node(int data){
        this.data = data;
        this.next = null;
    }
    public Node(int data, Node next){
        this.data = data;
        this.next = next;
    }
    public int getData() {
        return data;
    }
}
public class SinglyLinkedList implements List {
    private int size;
    private Node head;
    private Node tail;



    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        if(head == null){
            return true;
        }
        return  true;
    }
    
    @Override
    public boolean contains(int num) {
        Node n = head;
        while (!(n == null)){
            if(num == n.getData()){
                return true;
            }else{
                n = n.next;
            }
        }
        return false;
    }
    
    @Override
    public int get(int index) throws  CustomIndexOutOfBoundsException{
        if (index>=size || index<0){
            throw new CustomIndexOutOfBoundsException();
        }
        Node n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.getData();
    }
    
    public int getFirst() throws CustomNoSuchElementException{
        if (size==0){
            throw new CustomNoSuchElementException();
        }
        return head.getData();
    }
    
    @Override
    public void add(int num) {
        Node newN = new Node(num);
        Node n = head;
        if(n == null){
            head = newN;
            size+=1;
            return;
        }
        while (n.next != null){
            n = n.next;
        }
        n.next = newN;
        size +=1;
    }
    
    @Override
    public void add(int index, int num) throws CustomIndexOutOfBoundsException{
        if(index > size || index <0){
            throw new CustomIndexOutOfBoundsException();
        }
        if(head == null){
            Node n = new Node(num);
            head = n;
            return;
        }
        Node pre = head;
        for (int i = 0; i < index-1; i++) {
            pre = pre.next;
        }
        Node n = new Node(num, pre.next);
        pre.next = n;
        size +=1;

    }
    
    @Override
    public int remove(int index) throws CustomIndexOutOfBoundsException{
        if(index >= size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }
        int data;

        if(index ==0){
            data = head.getData();
            head = null;
            return data;
        }

        Node pre = head;
        for (int i = 0; i < index-1; i++) {
            pre = pre.next;
        }
        Node target = pre.next;
        data = target.getData();
        pre.next = target.next;
        return data;
    }

    public int removeFirst() throws CustomNoSuchElementException{
        if (size==0 ){
            throw new CustomNoSuchElementException();
        }

        int data = head.getData();
        if(head.next == null){
            head = null;
            return data;
        }
        head = head.next;
        return data;
    }
    
    @Override
    public void clear() {
        head = null;
        size=0;
    }
}

