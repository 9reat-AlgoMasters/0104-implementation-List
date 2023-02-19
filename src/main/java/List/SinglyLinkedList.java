package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

public class SinglyLinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
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

        while(t != null) {
            if (t.val == num) {
                return true;
            }
            t = t.next;
        }

        return false;
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

    public void addFirstPosition(int num) {
        Node node = new Node(num);
        node.next = head;
        head = node;

        if (head.next == null) {
            tail = head;
        }
    }

    @Override
    public void add(int num) {
        Node node = new Node(num);

        if (size == 0) {
            addFirstPosition(num);
        }

        if(size != 0) {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public Node searchIndexNode(int index){
        Node t = head;

        for (int i = 0; i < index; i++) {
            t = t.next;
        }
        return t;
    }

    @Override
    public void add(int index, int num) {
        if(index <0 || index > size-1) {
            throw new CustomIndexOutOfBoundsException();
        }
        Node temp = searchIndexNode(index-1);
        Node newNode = new Node(num);

        if (size == 0) {
            addFirstPosition(num);
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        } else {
            newNode.next = temp.next;
            temp.next = newNode;
        }

        size++;
    }

    @Override
    public int remove(int index) {
        if(index <0 || index > size-1) {
            throw new CustomIndexOutOfBoundsException();
        }
        Node node = searchIndexNode(index-1);
        Node node2 = searchIndexNode(index);
        int v = node2.val;
        node.next = node2.next;
        size--;
        return v;
    }

    public int removeFirst() {
        if (head == null) {
            throw new CustomNoSuchElementException();
        }
        int t = head.val;
        Node node = head.next;
        head.val = 0;
        head.next = null;
        head = node;
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
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }
}