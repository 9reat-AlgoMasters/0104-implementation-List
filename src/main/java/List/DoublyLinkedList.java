package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

public class DoublyLinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
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
        Node t = head;
        while (t != null) {
            if (t.val == num) {
                return true;
            }
            t = t.next;
        }

        return false;
    }
    public Node searchIndexNode(int index){
        Node t = head;

        for (int i = 0; i < index; i++) {
            t = t.next;
        }

        return t;
    }
    @Override
    public int get(int index) {
        if(index <0 || index > size-1) {
            throw new CustomIndexOutOfBoundsException();
        }
        Node node = searchIndexNode(index);
        return node.val;
    }

    public int getFirst() {
        if (head == null) {
            throw new CustomNoSuchElementException();
        }
        return head.val;
    }

    public int getLast() {
        if (tail == null) {
            throw new CustomNoSuchElementException();
        }
        return tail.val;
    }

    @Override
    public void add(int num) {
        Node node = new Node(num);
        if (size == 0) {
            addFirst(num);
            return;
        }

        if (size != 0) {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(int index, int num) {
        if(index <0 || index > size-1) {
            throw new CustomIndexOutOfBoundsException();
        }
        Node temp = searchIndexNode(index-1);
        Node newNode = new Node(num);

        if (size == 0){
            addFirst(num);
            return;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            // 넣을 노드의 next를 설정
            newNode.next = temp.next;
            // 넣을 노드의 위치다음의 node의 prev 설정
            temp.next.prev = newNode;
            // 넣을 node 이전node의 next를 넣을 node로 설정
            temp.next = newNode;
            // 넣을 node의 prev를 이전node로 설정
            newNode.prev = temp;
        }

        size++;
    }

    public void addFirst(int num) {
        Node node = new Node(num);
        node.next = head;
//        head.prev = node;
        head = node;

        if (head.next == null) {
            tail = head;
        }
        size++;
    }

    @Override
    public int remove(int index) {
        if(index <0 || index > size-1) {
            throw new CustomIndexOutOfBoundsException();
        }
        if(index == 0) {
            return removeFirst();
        }
        Node node = searchIndexNode(index-1);
        Node node2 = node.next;
        Node node3 = node.next.next;

        int v = node2.val;
        if (node2 == tail) {
            // removeLast();
            return removeLast();
        } else {
            node3.prev = node;
            node.next = node3;
            size--;
        }
        return v;
    }

    public int removeFirst() {
        if (head == null) {
            throw new CustomNoSuchElementException();
        }

        int t = head.val;
        Node node = head.next;
        head.val = 0;

        // head를 전부 null로
        head.prev = null;
        head.next = null;

        //head위치 변경
        head = node;
        head.prev = null;
        size--;

        return t;
    }

    public int removeLast() {
        if(tail == null) {
            throw new CustomNoSuchElementException();
        }

        int t = tail.val;
        Node node = tail.prev;
        tail.val = 0;
        tail.prev = null;
        tail.next = null;
        tail = node;
        tail.next = null;
        size--;

        return t;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        head = tail;
    }

    static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
}
