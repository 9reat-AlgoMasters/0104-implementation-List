package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

import java.util.regex.Pattern;

public class DoublyLinkedList implements List {
    private static final String INDEX_ERROR = "올바르지 않은 인덱스입니다.";
    private static final String EMPTY_ERROR = "리스트가 비어있습니다.";
    
    DoubleNode head;
    DoubleNode tail;
    int size;
    
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean contains(int num) {
        DoubleNode curNode = head;
        while (curNode != null) {
            if (curNode.value == num) {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }
    
    @Override
    public int get(int index) {
        if (isNotInRange(index)) {
            throw new CustomIndexOutOfBoundsException(INDEX_ERROR);
        }
        return findByIndex(index).value;
    }
    
    public int getFirst() {
        if (isEmpty()) {
            throw new CustomNoSuchElementException(EMPTY_ERROR);
        }
        return head.value;
    }
    
    public int getLast() {
        if (isEmpty()) {
            throw new CustomNoSuchElementException(EMPTY_ERROR);
        }
        return tail.value;
    }
    
    @Override
    public void add(int num) {
        if (isEmpty()) {
            addFirst(num);
        } else {
            DoubleNode newNode = new DoubleNode(num);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
    }
    
    @Override
    public void add(int index, int num) {
        if (index < 0 || index > size) {
            throw new CustomIndexOutOfBoundsException(INDEX_ERROR);
        }
        
        if (index == 0) {
            addFirst(num);
        } else if (index == size) {
            add(num);
        } else {
            DoubleNode newNode = new DoubleNode(num);
            DoubleNode node = findByIndex(index-1);
            // 새로 만들어진 노드와 기존에 index 자리에 있던 노드를 서로 연결
            newNode.next = node.next;
            node.next.prev = newNode;
            
            // 새로 만들어진 노드와 기존에 index-1 자리에 있던 노드를 서로 연결
            node.next = newNode;
            newNode.prev = node;
            size++;
        }
    }
    
    public void addFirst(int num) {
        DoubleNode newNode = new DoubleNode(num);
        if (size > 1) {
            head.prev = newNode;
            newNode.next = head;
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }
    
    @Override
    public int remove(int index) {
        if (isNotInRange(index)) {
            throw new CustomIndexOutOfBoundsException(INDEX_ERROR);
        }
        
        int removedValue;
        if (index == 0) {
            removedValue = removeFirst();
        } else if (index == size-1) {
            removedValue = removeLast();
        } else {
            DoubleNode node = findByIndex(index);
            removedValue = node.value;
            node.next.prev = node.prev;
            node.prev.next = node.next;
            size--;
        }
        return removedValue;
    }
    
    public int removeFirst() {
        if (isEmpty()) {
            throw new CustomNoSuchElementException(EMPTY_ERROR);
        }
        
        int removedValue = head.value;
        if (size == 1) {
            clear();
        } else {
            head = head.next;
            head.prev = null;
            size--;
        }
        return removedValue;
    }
    
    public int removeLast() {
        if (isEmpty()) {
            throw new CustomNoSuchElementException(EMPTY_ERROR);
        }
        
        int removedValue = tail.value;
        if (size == 1) {
            clear();
        } else {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
        return removedValue;
    }
    
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    private DoubleNode findByIndex(int index) {
        if (index < size/2) {
            return findByIndexFromHead(index);
        } else {
            return findByIndexFromTail(index);
        }
    }
    
    private DoubleNode findByIndexFromHead(int index) {
        DoubleNode curNode = head;
        int idx = 0;
        while (idx != index) {
            curNode = curNode.next;
            idx++;
        }
        return curNode;
    }
    
    private DoubleNode findByIndexFromTail(int index) {
        DoubleNode curNode = tail;
        int idx = size-1;
        while (idx != index) {
            curNode = curNode.prev;
            idx--;
        }
        return curNode;
    }
    
    private boolean isNotInRange(int index) {
        return index < 0 || index >= size;
    }
}
