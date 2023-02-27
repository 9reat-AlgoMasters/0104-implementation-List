package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

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
    private DNode tail;
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
//        DNode n;
//        if(index < size-1/2){
//            n = head;
//            for (int i = 0; i < index; i++) {
//                n = n.rNode;
//            }
//        }else {
//            n = tail;
//            for (int i = 0; i < size- index-1; i++) {
//                n = n.lNode;
//            }
//        }
//        return n.getData();
        if(head == null){

        }
        DNode n = head;
        for (int i = 0; i < index; i++) {
            n = n.rNode;
        }
        return n.getData();
    }
    
    public int getFirst() throws CustomNoSuchElementException {
        if (size==0){
            throw new CustomNoSuchElementException();
        }
        return head.getData();
    }
    
    public int getLast() throws CustomNoSuchElementException{
        if (size==0){
            throw new CustomNoSuchElementException();
        }
        return tail.getData();
    }
    
    @Override
    public void add(int num) {
        DNode newN = new DNode(num);
        if(size ==0){
            head = newN;
            tail = newN;
            size+=1;
            return;
        }
        tail.rNode = newN;
        newN.lNode = tail;
        tail = newN;
        size+=1;
    }
    
    @Override
    public void add(int index, int num) {
        if(index > size || index < 0){
            throw  new CustomIndexOutOfBoundsException();
        }
        if(head == null){
            DNode n = new DNode(num);
            head = n;
            return;
        }
        if(index == 0){
            DNode n = new DNode(null,num,head);
            head.lNode = n;
            head =n;
            return;
        }
        DNode pre = head;
        for (int i = 0; i < index-1; i++) {
            pre = pre.rNode;
        }
        DNode n = new DNode(pre, num, pre.rNode);
        pre.rNode = n;
        pre.rNode.lNode = n;
        size +=1;

    
    }
    
    public void addFirst(int num)  {
        DNode newN = new DNode(null, num, head);

        if(size !=0){
            head.lNode = newN;
            head = newN;
            size+=1;
        }else{
            head = newN;
            tail = newN;
            size +=1;
        }
    
    }
    
    @Override
    public int remove(int index) throws CustomIndexOutOfBoundsException{
        if(index >= size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }
        int data ;

        if(index == 0){
            data = head.getData();
            head = null;
            tail = null;
            size -= 1;
            return data;
        }
        DNode pre = head;
        for (int i = 0; i < index-1; i++) {
            pre = pre.rNode;
        }
        DNode target = pre.rNode;
        data = target.getData();
        pre.rNode = target.rNode;
        size-=1;
        return data;
    }
    
    public int removeFirst() throws CustomNoSuchElementException{
        if (size==0 ){
            throw new CustomNoSuchElementException();
        }

        int data = head.getData();
        if(head.rNode == null){
            head = null;
            size-=1;
            return data;
        }
        head = head.rNode;
        size-=1;
        return data;
    }
    
    public int removeLast() throws CustomNoSuchElementException{
        if (size==0 ){
            throw new CustomNoSuchElementException();
        }
        int data = tail.getData();
        if(tail.lNode == null){
            tail = null;
            size-=1;
            return data;
        }
        tail = tail.lNode;
        size-=1;
        return data;
    }
    
    @Override
    public void clear() {
        head = null;
        tail = null;
        size =0;
    
    }
}
