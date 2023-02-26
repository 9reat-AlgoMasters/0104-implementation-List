package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

public class DoublyLinkedList implements List {
    static class Node{
        private int data;
        private Node next;
        private Node prev;

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;
    private int size;
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
        for(Node node = head; node != null; node = node.next){
            if(node.data == num){
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
        Node node;
        if(index < size / 2) {
            //절반 나눠서 더 빠른 쪽으로 탐색
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }else{
            node = tail;
            for (int i = size-1; i > index; i--) {
                node = node.prev;
            }
        }
        return node.data;
    }
    
    public int getFirst() throws CustomNoSuchElementException {
        if(size == 0){
            throw new CustomNoSuchElementException();
        }
        return head.data;
    }
    
    public int getLast() throws CustomNoSuchElementException{
        if(size == 0){
            throw new CustomNoSuchElementException();
        }
        return tail.data;
    }
    
    @Override
    public void add(int num) {
        Node node = new Node(num, null, tail);
        if(size == 0){
            //list에 처음 들어가는 원소면 head = tail = node
            head = node;
        }else {
            tail.next = node;
        }
        tail = node;
        size++;
    }
    
    @Override
    public void add(int index, int num) throws CustomIndexOutOfBoundsException{
        if(index > size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }
        if(index == size){
            //맨 뒤에 붙이는 경우는 add(int num) 메소드와 동일
            add(num);
            return;
        }
        if(index == 0){
            addFirst(num);
            return;
        }

        //기존 index번 노드
        Node nextNode;
        if(index < size / 2) {
            nextNode = head;
            for (int i = 0; i < index; i++) {
                nextNode = nextNode.next;
            }
        }else{
            nextNode = tail;
            for (int i = size-1; i > index; i--) {
                nextNode = nextNode.prev;
            }
        }

        //index-1번 노드
        Node prevNode = nextNode.prev;

        // 기존 index번 노드를 next, index-1번을 prev로 가지는 새 노드 생성
        // 기존 노드 연결 재설정
        Node node = new Node(num, nextNode, prevNode);
        prevNode.next = node;
        nextNode.prev = node;
        size++;
    }
    
    public void addFirst(int num) {
        Node node = new Node(num, head, null);
        if(size == 0){
            tail = node;
        }else{
            head.prev = node;
        }
        head = node;
        size++;
    }
    
    @Override
    public int remove(int index) throws CustomIndexOutOfBoundsException{
        if(index >= size || index < 0){
            throw new CustomIndexOutOfBoundsException();
        }
        if(index == 0){
            return removeFirst();
        }
        if(index == size-1){
            return removeLast();
        }

        Node removingNode;
        if(index < size / 2) {
            removingNode = head;
            for (int i = 0; i < index; i++) {
                removingNode = removingNode.next;
            }
        }else {
            removingNode = tail;
            for (int i = size - 1; i > index; i--) {
                removingNode = removingNode.prev;
            }
        }

        int value = removingNode.data;

        Node prevNode = removingNode.prev;
        Node nextNode = removingNode.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        size--;
        return value;
    }
    
    public int removeFirst() throws CustomNoSuchElementException{
        if(size == 0){
            throw new CustomNoSuchElementException();
        }
        int value = head.data;
        head = head.next;
        if(head != null) {
            head.prev = null;
        }
        size--;
        if(size == 0){
            tail = null;
        }
        return value;
    }

    public int removeLast() throws CustomNoSuchElementException{
        if(size == 0){
            throw new CustomNoSuchElementException();
        }
        int value = tail.data;
        tail = tail.prev;
        if(tail != null) {
            tail.next = null;
        }
        size--;
        if(size == 0){
            head = null;
        }
        return value;
    }
    
    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }
}
