package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

public class SinglyLinkedList implements List {
    static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
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
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }
    
    public int getFirst() throws CustomNoSuchElementException {
        if(size == 0){
            throw new CustomNoSuchElementException();
        }
        return head.data;
    }
    
    @Override
    public void add(int num) {
        Node node = new Node(num, null);
        if(size == 0){
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
            //맨 앞에 오는 경우는 기존 head를 next로 가지는 새 노드 생성, head로
            head = new Node(num, head);
            size++;
            return;
        }

        //index-1번 노드 = 새로 만들어질 노드의 앞 노드
        Node prevNode = head;
        for (int i = 0; i < index-1; i++) {
            prevNode = prevNode.next;
        }

        //기존 index번 노드
        Node nextNode = prevNode.next;

        // 기존 index번 노드를 next로 가지는 새 노드 생성
        // index-1번 노드의 next로 설정
        prevNode.next = new Node(num, nextNode);
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

        Node prevNode = head;
        for (int i = 0; i < index-1; i++) {
            prevNode = prevNode.next;
        }

        Node removingNode = prevNode.next;
        int value = removingNode.data;

        if(index == size-1){
            //삭제할 노드가 맨끝 노드라면 tail 재설정
            prevNode.next = null;
            tail = prevNode;
        }else {
            // 맨끝 아니면 삭제할 노드의 다음 노드를 이전 노드의 next로 연결
            prevNode.next = removingNode.next;
        }
        size--;
        return value;
    }
    
    public int removeFirst() throws CustomNoSuchElementException {
        if(size == 0){
            throw new CustomNoSuchElementException();
        }
        int value = head.data;
        head = head.next;
        size--;
        if(size == 0){
            //삭제 후 리스트가 텅 비게 되면 tail 정리 필요
            tail = null;
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
