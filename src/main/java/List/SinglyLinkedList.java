package List;

import com.sun.org.apache.xpath.internal.objects.XNull;
import exceptions.CustomIndexOutOfBoundsException;

import java.util.NoSuchElementException;

public class SinglyLinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    public class Node {
         int data;
         Node next;

        Node(int n) {
            this.data = n;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean contains(int num) {
        int index = 0;
        for (Node x = head; x != null; x = x.next) {
            if (x.data == num) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int get(int index) {

        return search(index).data;
    }
    
    public int getFirst() {
        Node headNode = head;
        if (headNode == null) {
            throw new CustomIndexOutOfBoundsException();
        } else {
            return headNode.data;
        }
    }
    
    @Override
    public void add(int num) {

        Node node = new Node(num);
        if (size == 0) {
            Node first_node = new Node(num);
            first_node.next = head;
            head = first_node;
            size++;

            if (head.next == null) {
                tail = head;

            }
            return;
        }
        tail.next = node;
        tail = node;
        size++;

    }
    
    @Override
    public void add(int index, int num) {
        if (index >= size || index < 0) {
            throw new CustomIndexOutOfBoundsException();
        } else{
            if (index == 0) {
                Node first_node = new Node(num);
                first_node.next = head;
                head = first_node;
                size++;

                if (head.next == null) {
                    tail = head;

                }
                return;
            }

            Node left_node = search(index - 1);
            Node right_node = left_node.next;
            Node new_node = new Node(num);

            left_node.next = new_node;
            new_node.next = right_node;
            size++;

        }
    }
    
    @Override
    public int remove(int index) {
        if (index >= size || index < 0) {
            throw new CustomIndexOutOfBoundsException();
        }
        else {

            if (index == 0) {
                Node headNode = head;
                if (headNode == null) {
                    throw new CustomIndexOutOfBoundsException();
                }
                int tmp = headNode.data;
                Node right_node = head.next;
                head.data = -1;
                head.next = null;
                head = right_node;
                size--;
                if (size == 0) {
                    tail = null;
                }
                return tmp;
            }

            Node left_node = search(index - 1);
            Node removedNode = left_node.next;
            Node right_node = removedNode.next;

            int tmp = removedNode.data;

            left_node.next = right_node;

            if (left_node.next == null) {
                tail = left_node;
            }
            removedNode.next = null;
            removedNode.data = -1;
            size--;

            return tmp;
        }
    }
    
    public int removeFirst() {

        Node headNode = head;

        if (headNode == null) {
            throw new CustomIndexOutOfBoundsException();
        }

        else {
            int tmp = headNode.data;
            Node rightNode = head.next;
            head.next = null;
            head.data = -1;

            head = rightNode;
            size--;

            if (size == 0) {
                tail = null;
            }

            return tmp;
        }

    }
    
    @Override
    public void clear() {
        for (Node x = head; x != null; ) {
            Node right_node = x.next;
            x.data = -1;
            x.next = null;
            x = right_node;
        }
        head = tail = null;
        size = 0;
    }

    public Node search(int index) {
        if (index < 0 || index >= size) {
            throw new CustomIndexOutOfBoundsException();
        }

        Node x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }

        return x;
    }
}
