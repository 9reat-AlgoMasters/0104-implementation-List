package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

public class SinglyLinkedList implements List {
    private static final String INDEX_ERROR = "올바르지 않은 인덱스입니다.";
    private static final String EMPTY_ERROR = "리스트가 비어있습니다.";
    
    private SingleNode head;
    private int size;
    
    public SinglyLinkedList() {
        head = null;
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
        SingleNode curNode = head;
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
        
        SingleNode curNode = head;
        int idx = 0;
        while (idx != index) {
            curNode = curNode.next;
            idx++;
        }
        return curNode.value;
    }
    
    public int getFirst() {
        if (isEmpty()) {
            throw new CustomNoSuchElementException(EMPTY_ERROR);
        }
        return head.value;
    }
    
    @Override
    public void add(int num) {
        if (isEmpty()) {
            head = new SingleNode(num);
        } else {
            SingleNode lastNode = findByIndex(size-1);
            lastNode.next = new SingleNode(num);
        }
        size++;
    }
    
    @Override
    public void add(int index, int num) {
        if (index < 0 || index > size) {
            throw new CustomIndexOutOfBoundsException(INDEX_ERROR);
        }
        if (index == 0) {
            addFirst(num);
        } else {
            SingleNode node = findByIndex(index-1);
            SingleNode newNode = new SingleNode(num);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
    }
    
    private void addFirst(int num) {
        SingleNode newFirstNode = new SingleNode(num);
        newFirstNode.next = head;
        head = newFirstNode;
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
        } else {
            SingleNode node = findByIndex(index-1);
            removedValue = node.next.value;
            node.next = node.next.next;
        }
        size--;
        return removedValue;
    }
    
    public int removeFirst() {
        if (isEmpty()) {
            throw new CustomNoSuchElementException(EMPTY_ERROR);
        }
        
        int removedValue = head.value;
        if (size == 1) {
            head = null;
        } else {
            head = head.next;
        }
        size--;
        return removedValue;
    }
    
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    private SingleNode findByIndex(int index) {
        SingleNode curNode = head;
        int idx = 0;
        while (idx != index) {
            curNode = curNode.next;
            idx++;
        }
        return curNode;
    }
    
    private boolean isNotInRange(int index) {
        return index < 0 || index >= size;
    }
}
